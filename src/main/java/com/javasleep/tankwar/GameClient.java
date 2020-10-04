package com.javasleep.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class GameClient extends JComponent {

    private static final GameClient INSTANCE = new GameClient();

    public static GameClient getInstance(){
        return INSTANCE;
    }

    private Tank playerTank;

    private List<Tank> enemyTanks;

    private List<Wall> walls;

    private List<Missle> missles;

    private List<Explosion> explosions;

    synchronized void add(Missle missle){
        missles.add(missle);
    }

    void addExplosion(Explosion explosion){
        explosions.add(explosion);
    }


    void removeMissle(Missle missle){
        missles.remove(missle);
    }

    Tank getPlayerTank() {
        return playerTank;
    }

    List<Missle> getMissles() {
        return missles;
    }

    List<Wall> getWalls() {
        return walls;
    }

    List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public GameClient(){
        this.playerTank= new Tank(400,100,Direction.DOWN);

        this.missles =new ArrayList<Missle>();
        this.explosions= new ArrayList<Explosion>();
        this.walls = Arrays.asList(
            new Wall(200,140,true,15),
            new Wall(200,540,true,15),
            new Wall(100,80,false,15),
            new Wall(700,80,false,15)
        );


        this.initiEnemyTanks();
        this.setPreferredSize(new Dimension(800,600));
    }

    private void initiEnemyTanks() {
        this.enemyTanks =new ArrayList<Tank>(12);
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                this.enemyTanks.add(new Tank(200+j*80,400+40*i,true, Direction.UP));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,600);
        playerTank.draw(g);
        enemyTanks.removeIf(t->!t.isLive());
        if(enemyTanks.isEmpty()){
            this.initiEnemyTanks();
        }
        for (Tank tank: enemyTanks){
            tank.draw(g);
        }
        for (Wall wall:walls){
            wall.draw(g);
        }
        missles.removeIf(m->!m.isLive());
        for (Missle missle: missles){
            missle.draw(g);
        }

        explosions.removeIf(m->!m.isLive());
        for (Explosion explosion: explosions){
            explosion.draw(g);
        }
    }

    public static void main(String[] args){
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        JFrame frame = new JFrame();
        frame.setTitle("明穎哥哥回來開戰車了");
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        final GameClient client = GameClient.getInstance();
        frame.add(client);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                client.playerTank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                client.playerTank.keyReleased(e);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true){
            client.repaint();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }


    }



}
