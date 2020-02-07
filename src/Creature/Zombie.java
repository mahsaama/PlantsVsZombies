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
    private int guardLife;
    private boolean guard;
    private boolean car;
    private int price;
    private int stoppedTurns = 0;
    private int DuckOrLadder = 0;

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void guardLifeDec(int amount) {
        if (guardLife - amount > 0) {
            guardLife -= amount;
        } else {
            guard = false;
        }
    }

    public void setStoppedTurns(int stoppedTurns) {
        this.stoppedTurns += stoppedTurns;
    }

    public void setHat(boolean hat) {
        this.hat = hat;
    }

    public void setDuckOrLadder(int situation) {
        this.DuckOrLadder = situation;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    public int getDuckOrLadder() {
        return DuckOrLadder;
    }

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

    public void setLife(int life) {
        this.life = life;
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

    public int getGuardLife() {
        return guardLife;
    }

    public boolean isGuard() {
        return guard;
    }

    public boolean isCar() {
        return car;
    }

    public ArrayList<String> attack(PlayGround playGround) {
        ArrayList<String> nameOfPlant = new ArrayList<>();
        Plant plant = playGround.getCells()[x][y].getPlantContent().get(playGround.getCells()[x][y].getPlantContent().size() - 1);
        if (name.equals("Giga-gargantuar")) {
            nameOfPlant.add(plant.name);
            playGround.getCells()[x][y].getPlantContent().remove(playGround.getCells()[x][y].getPlantContent().size() - 1);
            return nameOfPlant;
        } else if (name.equals("PogoZombie")) {
            y--;
            nameOfPlant.add("not");
            return nameOfPlant;
        }
        if (car) {
            for (int i = 18; i <= 0; i--) {
                playGround.getCells()[x][i].getPlantContent().remove(playGround.getCells()[x][i].getPlantContent().size() - 1);
                nameOfPlant.add(plant.name);
            }
            if (name.equals("Zomboni")) {
                name = "Zombie";
                y = 18;
                life = 2;
                speed = 2;
                currentSpeed = speed;
                lifeDec = 1;
                car = false;
                price = 60;
                return nameOfPlant;
            }
            return nameOfPlant;
        }
        if (name.equals("BungeeZombie")) {
            
        }
        plant.setLife();
        if (plant.getLife() == 0) {
            nameOfPlant.add(plant.name);
            playGround.getCells()[x][y].getPlantContent().remove(playGround.getCells()[x][y].getPlantContent().size() - 1);
            return nameOfPlant;
        }
        nameOfPlant.add("not");
        return nameOfPlant;
    }
}
