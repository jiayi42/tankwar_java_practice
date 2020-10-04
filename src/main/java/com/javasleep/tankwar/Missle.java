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
        x+= direction.xFactor*15;
        y+= direction.yFactor*15;
    }

    void draw(Graphics g) {
        move();
        if(x<0|| x>800|| y<0|| y>600){
            return;
        }
        g.drawImage(getImage(),x,y,null);
    }
}
