package com.javasleep.tankwar;


import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {

    @Test
    void getImage() {


        for( Direction direction: Direction.values()){
            Tank tank = new Tank(0,0,false,direction);

            assertTrue(tank.getImage().getWidth(null)>0,direction+" cannot get valid image" );


            Tank enemyTank = new Tank(0, 0, false, direction);
            Image image =enemyTank.getImage();
            assertTrue(enemyTank.getImage().getWidth(null)>0);
        }

    }
}