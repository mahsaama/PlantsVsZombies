package Creature;

import Map.PlayGround;

import java.util.ArrayList;

public class Zombie {
    private String name;
    private int x;
    private int y;
    private int id;
    private int life;
    private int speed;
    private int currentSpeed = speed;
    private boolean hat;
    private int lifeDec;
    private int remainTurns;
    private int guardLife;
    private boolean guard;
    private boolean car;
    private int price;
    private int stoppedTurns = 0;
    private int DuckOrLadder = 0;

    public void setCurrentSpeed(int amount) {
        if (speed == currentSpeed){
            this.currentSpeed /= amount;
        }else if(speed / amount < currentSpeed){
            this.currentSpeed = speed / amount;
        }

    }

    public void setStoppedTurns(int stoppedTurns) {
        this.stoppedTurns += stoppedTurns;
    }

    public void setHat(boolean hat) {
        this.hat = hat;
    }

    public void setDuckOrLadder(int situation){ this.DuckOrLadder = situation; }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    public int getDuckOrLadder(){ return DuckOrLadder; }

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

    public int getStoppedTurns() {
        return stoppedTurns;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLife(int amount, int shootnum) {
        this.life -= amount * shootnum;//check & zamboni check kon
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isHat() {
        return hat;
    }

    public int getLifeDec() {
        return lifeDec;
    }

    public int getRemainTurns() {
        return remainTurns;
    }

    public int getGuardLife() {
        return guardLife;
    }

    public boolean isGuard() {
        return guard;
    }

    public boolean isCar() {
        return car;
    }


    public String attack(PlayGround playGround) {
        Plant plant = playGround.getCells()[x][y].getPlantContent().get(playGround.getCells()[x][y].getPlantContent().size() - 1);
        if (plant.getName().equals("CherryBomb") || plant.getName().equals("Jalapeno")
                || name == "PogoZombie") {
            return "not";
        } else if (name.equals("Zomboni") || name.equals("CatapultZombie")) {
            if (car) {
                String nameOfPlant = plant.name;
                plant = null;
                return nameOfPlant;
            }
        } else if (name.equals("Giga-gargantuar")) {
            String nameOfPlant = plant.name;
            plant = null;
            return nameOfPlant;
        } else if (name.equals("PogoZombie")) {
            y--;

            return "not";
        } else if (name.equals("BungeeZombie")) {
            return "not";
        }
        if (plant.getLife() > 0) {
            plant.setLife();
            if (plant.getLife() == 0) {
                String nameOfPlant = plant.name;
                plant = null;
                return nameOfPlant;
            }
        }
        return "not";
    }


}
