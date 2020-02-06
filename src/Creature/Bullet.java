package Creature;

import Game.Game;

public class Bullet extends Game {
    private int x;
    private int y;
    private int bulletTurn;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBulletTurn() {
        return bulletTurn;
    }

    public void setBulletTurn(int bulletTurn) {
        this.bulletTurn = bulletTurn;
    }

    public Bullet(int x, int y, int bulletTurn) {
        this.x = x;
        this.y = y;
        this.bulletTurn = bulletTurn;
    }

}
