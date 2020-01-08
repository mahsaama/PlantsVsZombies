package Creature;

import Map.PlayGround;

import java.util.ArrayList;

public class Plant {
    String name;
    String bulletType;
    private int plantTurn;
    private int x;
    private int y;
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
    private int startedTurn;
    private String plantType;

    public int getPrice() {
        return price;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public String getPlantType() {
        return plantType;
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

    public void setStartedTurn(int startedTurn) {
        this.startedTurn = startedTurn;
    }

    public int getStartedTurn() {
        return startedTurn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void attack(PlayGround playGround, boolean sameCell) {
        Zombie zombie;
        zombie = findZombie(playGround, x);

        if (zombie == null) {
            return;
        }
        if (sameCell) {
            if (zombie.getName().equals("FootballZombie") && name == "Cactus") {
                if (plantTurn % 2 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }
                }
            } else if (name.equals("TangleKelp")) {
                zombie = null;
                return;
            } else if (name.equals("PotatoMine")) {
                if (offTurns == 1) {
                    offTurns--;
                    return;
                } else {
                    zombie = null;
                }
            }
        } else {
            if (name.equals("Peashooter")) {
                if (plantTurn % 2 == 0) {
                    zombie.setLife(hurtOfBullet, 1);//life should be set when the pea arrives to zombie
                }
            } else if (name.equals("SnowPea")) {
                if (plantTurn % 3 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    zombie.setCurrentSpeed(effectOnZombieSpeed);

                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }
                }
            } else if (name.equals("Cabbage-pult")) {
                if (plantTurn % 2 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }

                }
            } else if (name.equals("Repeater")) {
                if (plantTurn % 3 == 0) {
                    zombie.setLife(hurtOfBullet, 2);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }

                }
            } else if (name.equals("Threepeater")) {
                if (plantTurn % 4 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }

                    if (x > 0) {
                        Zombie zombie1;
                        zombie1 = findZombie(playGround, x - 1);
                        if (zombie1 != null) {
                            zombie1.setLife(hurtOfBullet, 1);
                            if (zombie1.getLife() == 0) {
                                zombie1 = null;
                                //TODO
                            }
                        }
                    }
                    if (x < 6) {
                        Zombie zombie2;
                        zombie2 = findZombie(playGround, x + 1);
                        if (zombie2 != null) {

                            zombie2.setLife(hurtOfBullet, 1);
                            if (zombie2.getLife() == 0) {
                                zombie2 = null;
                                //TODO
                            }
                        }
                    }
                }
            } else if (name.equals("Cactus")) {
                if (plantTurn % 2 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }
                }
            } else if (name.equals("GatlingPea")) {
                if (plantTurn % 5 == 0) {
                    zombie.setLife(hurtOfBullet, 4);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }
                }
            } else if (name.equals("Scaredy-shroom")) {
                if (plantTurn % 2 == 0) {
                    int distance;

                    for (int i = y; i < 19; i++) {
                        for (Zombie item : playGround.getCells()[x][i].getZombieContent()) {
                            if (item != null) {
                                distance = i - y;
                                zombie = item;
                                if (distance > minDistanceToShoot) {
                                    zombie.setLife(hurtOfBullet, 1);
                                    if (zombie.getLife() == 0) {
                                        zombie = null;
                                        //TODO
                                    }
                                }
                                return;
                            }
                        }
                    }
                }
            } else if (name.equals("Kernel-pult")) {
                if (plantTurn % 4 == 0) {
                    zombie.setLife(hurtOfBullet, 4);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }
                    zombie.setStoppedTurns(zombieStopTurns);
                }
            } else if (name.equals("SplitPea")) {
                zombie.setLife(hurtOfBullet, 1);
                if (zombie.getLife() == 0) {
                    zombie = null;
                    //TODO
                }
                Zombie zombie1 = null;
                for (int i = y - 1; i >= 0; i--) {
                    for (Zombie item : playGround.getCells()[x][i].getZombieContent()) {
                        if (item != null) {
                            zombie1 = item;
                            break;
                        }
                    }
                    if (zombie1 != null)
                        break;
                }
                zombie1.setLife(hurtOfBullet, 2);
                if (zombie.getLife() == 0) {
                    zombie = null;
                    //TODO
                }
            } else if (name.equals("Melon-pult")) {
                if (plantTurn % 4 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }

                }
            } else if (name.equals("WinterMelon")) {
                if (plantTurn % 4 == 0) {
                    zombie.setLife(hurtOfBullet, 1);
                    zombie.setCurrentSpeed(effectOnZombieSpeed);
                    if (zombie.getLife() == 0) {
                        zombie = null;
                        //TODO
                    }

                }
            } else if (name.equals("Cattail")) {
                zombie = nearestZombie(playGround);
                zombie = null;
            } else if (name.equals("CherryBomb")) {
                if (playGround.getCells()[x][y].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x][y].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (x > 1 && playGround.getCells()[x - 1][y].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x - 1][y].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (y > 1 && playGround.getCells()[x][y - 1].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x][y - 1].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (x < 6 && playGround.getCells()[x + 1][y].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x + 1][y].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (y < 19 && playGround.getCells()[x][y + 1].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x][y + 1].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (y < 19 && x < 6 && playGround.getCells()[x + 1][y + 1].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x + 1][y + 1].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (x > 0 && y > 0 && playGround.getCells()[x - 1][y - 1].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x - 1][y - 1].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (x < 9 && y > 0 && playGround.getCells()[x + 1][y - 1].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x + 1][y - 1].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
                if (x > 0 && y < 19 && playGround.getCells()[x - 1][y + 1].getZombieContent() != null) {
                    for (Zombie item : playGround.getCells()[x - 1][y + 1].getZombieContent())
                        item = null; // kill the zombie in graphic
                }
            } else if (name.equals("Magnet-shroom")) {
                if (plantTurn % 3 == 0) {
                    if (playGround.getCells()[x][y].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x][y].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }
                    }
                    if (x > 1 && playGround.getCells()[x - 1][y].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x - 1][y].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }

                    }
                    if (y > 1 && playGround.getCells()[x][y - 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x][y - 1].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }

                    }
                    if (x < 6 && playGround.getCells()[x + 1][y].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x + 1][y].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }

                    }
                    if (y < 19 && playGround.getCells()[x][y + 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x][y + 1].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }

                    }
                    if (y < 19 && x < 6 && playGround.getCells()[x + 1][y + 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x + 1][y + 1].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }

                    }
                    if (x > 0 && y > 0 && playGround.getCells()[x - 1][y - 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x - 1][y - 1].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }

                    }
                    if (x < 9 && y > 0 && playGround.getCells()[x + 1][y - 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x + 1][y - 1].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }
                    }
                    if (x > 0 && y < 19 && playGround.getCells()[x - 1][y + 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x - 1][y + 1].getZombieContent())
                            if (item.getName().equals("BucketheadZombie") || item.getName().equals("NewspaperZombie")
                                    || item.getName().equals("TargetZombie") || item.getName().equals("ScreenDoorZombie") ||
                                    item.getName().equals("DolphinRiderZombie")) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(1, 0);
                                    }
                                }
                            }
                    }
                }

            } else if (name.equals("Jalapeno")) {
                for (int i = 0; i < 16; i++) {
                    if (playGround.getCells()[x][i].getZombieContent() != null) {
                        playGround.getCells()[x][i].getZombieContent().clear();
                    }
                }
            }
        }
    }

    public Zombie findZombie(PlayGround playGround, int row) {
        for (int i = y; i < 19; i++) {
            for (Zombie item : playGround.getCells()[row][i].getZombieContent()) {
                if (item != null) {
                    return item;
                }
            }
        }
        return null;
    }

    public Zombie nearestZombie(PlayGround playGround) {
        Zombie zombie = null;
        int Distance = 50690;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells()[i][j].getZombieContent() != null) {
                    int d = (int) (Math.pow(Math.abs(i - x), 2) + Math.pow(Math.abs(j - y), 2));
                    if (d < Distance) {
                        Distance = d;
                        zombie = playGround.getCells()[i][j].getZombieContent().get(0);
                    }
                }
            }
        }
        return zombie;
    }

    public void checkPlantsInRow(ArrayList<Plant> plants) {

    }

    public void checkZombiesInRow(ArrayList<Zombie> zombies) {

    }
}
