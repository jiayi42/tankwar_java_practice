package com.javasleep.tankwar;

//import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.KeyEvent;
        import java.util.Random;

class Tank {

    private int x;

    private int y;

    private boolean enemy;

    private boolean live=true;

    private int hp=100;

    int getHp() {
        return hp;
    }

    void setHp(int hp) {
        this.hp = hp;
    }

    boolean isLive(){
        return live;
    }

    void setLive(boolean live) {
        this.live = live;
    }

    private Direction direction;

    boolean isEnemy(){
        return enemy;
    }

    Tank(int x, int y, Direction direction){
        this(x,y,false, direction);
    }


    Tank(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    private void move(){
        if(this.stopped) return;
        x+= direction.xFactor*5;
        y+= direction.yFactor*5;
    }

    Image getImage(){
        String prefix = enemy ? "e" : "";
        return direction.getImage(prefix+"tank");
    }


    void draw(Graphics g){

        int oldX=x, oldY=y;
        this.determineDirection();
        this.move();

        if(x<0) x=0;
        else if (x>800- getImage().getWidth(null)) x=800- getImage().getWidth(null);
        if(y<0) y=0;
        else if (y>600 - getImage().getHeight(null)) y=600- getImage().getHeight(null);

        Rectangle rec = this.getRectangle();
        for (Wall wall : GameClient.getInstance().getWalls()){
            if (rec.intersects(wall.getRectangle())){
                x=oldX;
                y=oldY;
                break;
            }
        }

        for (Tank tank: GameClient.getInstance().getEnemyTanks()){
            if (rec.intersects(tank.getRectangle())){
                x=oldX;
                y=oldY;
                break;
            }
        }

        g.drawImage(this.getImage(), this.x, this.y, null);
    }

    Rectangle getRectangle(){
        return new Rectangle(x,y, getImage().getWidth(null),getImage().getHeight(null));
    }


    private boolean up,down,left,right;

    void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: up=true;break;
            case KeyEvent.VK_DOWN: down=true;break;
            case KeyEvent.VK_LEFT: left=true;break;
            case KeyEvent.VK_RIGHT: right=true;break;
            case KeyEvent.VK_CONTROL: fire(); break;
            case KeyEvent.VK_A: superfire(); break;
        }
        this.determineDirection();
    }

    private void fire(){
        Missle missle =new Missle(x + getImage().getHeight(null)/2-4,
                y + getImage().getHeight(null)/2-4, enemy, direction);
        GameClient.getInstance().getMissles().add(missle);

        Tools.playAudio("shoot.wav");
    }

    private void superfire(){
        for (Direction direction:Direction.values()){
            Missle missle =new Missle(x + getImage().getHeight(null)/2-4,
                    y + getImage().getHeight(null)/2-4, enemy, direction);
            GameClient.getInstance().getMissles().add(missle);
        }

        String audioFile = new Random().nextBoolean() ? "supershoot.aiff" : "supershoot.wav";
        Tools.playAudio(audioFile);
    }

    private boolean stopped;

    private void determineDirection(){
        if (!up && !left && !down && !right){
            this.stopped=true;
        }
        else{
            if (up && left && !down && !right) this.direction = Direction.LEFT_UP;
            else if (up && !left && !down && right) this.direction = Direction.RIGHT_UP;
            else if (up && !left && !down && !right) this.direction = Direction.UP;
            else if (!up && !left && down && !right) this.direction = Direction.DOWN;
            else if (!up && left && down && !right) this.direction = Direction.LEFT_DOWN;
            else if (!up && !left && down && right) this.direction = Direction.RIGHT_DOWN;
            else if (!up && left && !down && !right) this.direction = Direction.LEFT;
            else if (!up && !left && !down && right) this.direction = Direction.RIGHT;

            this.stopped= false;
        }

    }

    void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: up=false;break;
            case KeyEvent.VK_DOWN: down=false;break;
            case KeyEvent.VK_LEFT: left=false;break;
            case KeyEvent.VK_RIGHT: right=false;break;
        }
        this.determineDirection();
    }
}
