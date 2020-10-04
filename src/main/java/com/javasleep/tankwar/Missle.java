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
        return direction.getImage("missile");
    }

    void move(){

        switch (direction){
            case UP: y-=15;break;

            case LEFT_UP: x-=15;y-=15;break;
            case RIGHT_UP: x+=15;y-=15;break;
            case LEFT_DOWN: x-=15;y+=15;break;
            case RIGHT_DOWN: x+=15;y+=15;break;

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
