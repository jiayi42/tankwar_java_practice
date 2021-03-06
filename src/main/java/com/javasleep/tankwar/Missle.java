package com.javasleep.tankwar;

import java.awt.*;

class Missle {
    private int x;

    private int y;

    private final boolean enemy;

    private final Direction direction;

    private boolean live=true;

    boolean isLive() {
        return live;
    }

    void setLive(boolean live) {
        this.live = live;
    }

    Missle(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    Image getImage(){
        return direction.getImage("missile");
    }

    void move(){
        x+= direction.xFactor*15;
        y+= direction.yFactor*15;
    }

    void draw(Graphics g) {
        move();
        if(x<0|| x>800|| y<0|| y>600){
            this.live=false;
            return;
        }

        Rectangle rectangle =this.getRectangle();
        for (Wall wall: GameClient.getInstance().getWalls()){
            if(rectangle.intersects(wall.getRectangle())){
                this.setLive(false);
                return;
            }
        }
        if(enemy){
            Tank playerTank= GameClient.getInstance().getPlayerTank();
            if(rectangle.intersects(playerTank.getRectangle())){
                addExplosiion();
                playerTank.setHp(playerTank.getHp()-10);
                if(playerTank.getHp()<=0){
                    playerTank.setLive(false);
                }
                this.setLive(false);

            }
        }else{
            for(Tank tank:GameClient.getInstance().getEnemyTanks()){
                if(rectangle.intersects(tank.getRectangle())){
                    addExplosiion();
                    tank.setLive(false);
                    this.setLive(false);
                    break;
                }
            }
        }


        g.drawImage(getImage(),x,y,null);
    }

    private void addExplosiion(){
        GameClient.getInstance().addExplosion(new Explosion(x,y));
        Tools.playAudio("explode.wav");
    }

    Rectangle getRectangle(){
        return new Rectangle(x,y,getImage().getWidth(null),
                getImage().getHeight(null));

    }



}
