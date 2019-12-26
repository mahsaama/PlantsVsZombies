package Creature;


import java.util.ArrayList;

public class Plant {
    String name;
    String bulletType;
    private int id;
    private int life;
    private int rest;
    private int numberOfSuns;
    private int hurtOfBullet;
    private int turn;
    private int shootNum;
    private int hurtToZombie;
    private int zombieStopTurns;
    private int offTurns;
    private int minDistanceToShoot;
    private int effectOnZombieSpeed;
    private int turnsUnderEffect;
    private int turnOfFront;
    private int shootNumOfFront;
    private int turnOfBack;
    private int shootNumOfBack;
    private int sunProduct;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLife() {
        this.life--;
    }

    public String getName() {
        return name;
    }

    public String getBulletType() {
        return bulletType;
    }

    public int getId() {
        return id;
    }

    public int getLife() {
        return life;
    }

    public int getRest() {
        return rest;
    }

    public int getNumberOfSuns() {
        return numberOfSuns;
    }

    public int getHurtOfBullet() {
        return hurtOfBullet;
    }

    public int getTurn() {
        return turn;
    }

    public int getShootNum() {
        return shootNum;
    }

    public int getHurtToZombie() {
        return hurtToZombie;
    }

    public int getZombieStopTurns() {
        return zombieStopTurns;
    }

    public int getOffTurns() {
        return offTurns;
    }

    public int getMinDistanceToShoot() {
        return minDistanceToShoot;
    }

    public int getEffectOnZombieSpeed() {
        return effectOnZombieSpeed;
    }

    public int getTurnsUnderEffect() {
        return turnsUnderEffect;
    }

    public int getTurnOfFront() {
        return turnOfFront;
    }

    public int getShootNumOfFront() {
        return shootNumOfFront;
    }

    public int getTurnOfBack() {
        return turnOfBack;
    }

    public int getShootNumOfBack() {
        return shootNumOfBack;
    }

    public int getSunProduct() {
        return sunProduct;
    }



    public void attack(Zombie zombie,boolean sameCell){
        if (sameCell){
            if(zombie.getName().equals("FootballZombie") && name == "Cactus"){
                if(turn % 2 == 0){
                    zombie.setLife();
                    if (zombie.getLife() == 0){
                        zombie = null;
                        //TODO
                    }
                }
            }
        }
    }
    public void checkPlantsInRow(ArrayList<Plant> plants){

    }
    public void checkZombiesInRow(ArrayList<Zombie> zombies){

    }
}
