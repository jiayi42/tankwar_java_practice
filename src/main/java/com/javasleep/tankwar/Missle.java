package com.javasleep.tankwar;

import java.awt.*;

class Missle {
    private int x;

    private int y;

    private final boolean enemy;

    private final Direction direction;

    Missle(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    Image getImage(){
        switch (direction){
            case UP:
                return Tools.getImage( "missleU.gif");
            case UPLEFT:
                return Tools.getImage( "missileLU.gif");
            case UPRIGHT:
                return Tools.getImage( "missileRU.gif");
            case DOWNLEFT:
                return Tools.getImage( "missileLD.gif");
            case DOWNRIGHT:
                return Tools.getImage( "missileRD.gif");
            case DOWN:
                return Tools.getImage( "missileD.gif");
            case LEFT:
                return Tools.getImage( "missileL.gif");
            case RIGHT:
                return Tools.getImage( "missileR.gif");
        }
        return null;
    }
    void move(){

        switch (direction){
            case UP: y-=15;break;

            case UPLEFT: x-=15;y-=15;break;
            case UPRIGHT: x+=15;y-=15;break;
            case DOWNLEFT: x-=15;y+=15;break;
            case DOWNRIGHT: x+=15;y+=15;break;

            case DOWN: y+=15;break;
            case LEFT: x-=15;break;
            case RIGHT: x+=15;break;
        }
    }
    void draw(Graphics g) {
        move();
        if(x<0|| x>800|| y<0|| y>600){
            return;
        }
        g.drawImage(getImage(),x,y,null);
    }
}
