package Creature;

import java.util.ArrayList;

public class Zombie {
    private String name;
    private int id;
    private int life;
    private int speed;
    private boolean hat;
    private int lifeDec;
    private int remainTurns;
    private int guardLife;
    private boolean guard;
    private boolean car;
    private int price;
    private int stoppedTurns =0;

    public void setStoppedTurns(int stoppedTurns) {
        this.stoppedTurns += stoppedTurns;
    }

    public void setHat(boolean hat) {
        this.hat = hat;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
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

    public void setLife(int amount,int shootnum) {
        this.life -= amount  * shootnum;//check
    }

    public void setSpeed(int amount) {
        this.speed /= amount;
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

    public void attack(Plant plant){

        if (plant.getName().equals("CherryBomb") || plant.getName().equals("Jalapeno")
        || name == "PogoZombie"){
            return;
        }
        if (plant.getLife() > 0){
            plant.setLife();
            if (plant.getLife() == 0){
                plant = null;
                //TODO
            }
        }
        if (car){

        }
    }

}
