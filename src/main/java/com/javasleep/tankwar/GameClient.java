package com.javasleep.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GameClient extends JComponent {

    public GameClient(){
        this.setPreferredSize(new Dimension(800,600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(new ImageIcon("assets/images/tankD.gif").getImage(),400,100,null);
    }

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setTitle("明穎哥哥回來開戰車了");
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        GameClient client = new GameClient();
        frame.add(client);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



}
