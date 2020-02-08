package Creature;

import Map.PlayGround;

public class Plant{
    String name;
    String bulletType;
    private int plantTurn;
    private static int x;
    private static int y;
    private int id;
    private int life;
    private int coolDown;
    private int numberOfSuns;
    private int hurtOfBullet;
    private int hurtToZombie;
    private int zombieStopTurns;
    private int offTurns;
    private int minDistanceToShoot;
    private int maxDistanceToShoot;
    private int effectOnZombieSpeed;
    private int turnOfFront;
    private int shootNumOfFront;
    private int sunProduct;
    private int price;
    private String plantType;
    private int numberOfUsesInGame = 0;
    private Zombie zombie;

    public int getPrice() {
        return price;
    }

    public void setPlantTurn(int plantTurn) {
        this.plantTurn = plantTurn;
    }

    public int getPlantTurn() {
        return plantTurn;
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

    public void zombieLifeDec() {
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

    public void setLife() {
        this.life --;
    }

    public int getCoolDown() {
        return coolDown;
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

    public int getNumberOfUsesInGame() {
        return numberOfUsesInGame;
    }

    public void setNumberOfUsesInGame(int numberOfUsesInGame) {
        this.numberOfUsesInGame = numberOfUsesInGame;
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

    public int getTurnOfFront() {
        return turnOfFront;
    }

    public int getShootNumOfFront() {
        return shootNumOfFront;
    }

    public int getSunProduct() {
        return sunProduct;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public Plant(int plantTurn) {
        this.plantTurn = plantTurn;
    }

    public void attack(PlayGround playGround, int turn) {
        zombie = findZombie(playGround, x);
        if (offTurns == 0) {
            if (bulletType != null) {
                if ((turn-plantTurn) % turnOfFront == 0) {
                    int distance = zombie.getY() - y;
                    if (name.equals("Threepeater")) {
                        if (x > 0) {
                            shoot(playGround, x - 1);
                        }
                        if (x < 6) {
                            shoot(playGround, x + 1);
                        }
                    }
                    if (distance >= minDistanceToShoot && distance <= maxDistanceToShoot) {
                            if (zombie == null) {
                                return;
                            }
                            if (hurtToZombie != 0 && zombie.getX() == x && zombie.getY() == y) {
                                if (!zombie.getName().equals("FootballZombie")) {
                                    if (!zombie.isGuard()){
                                        zombie.setLife(zombie.getLife() - hurtToZombie);
                                        if (zombie.getLife() == 0){
                                            playGround.getCells()[zombie.getX()][zombie.getY()].getZombieContent().remove(zombie);
                                        }
                                    }else{
                                        zombie.guardLifeDec(hurtToZombie);
                                    }
                                }
                            }
                            if (zombieStopTurns != 0) {
                                zombie.setStoppedTurns(zombieStopTurns);
                            }
                            for (int i = 0; i < shootNumOfFront; i++) {
                                shoot(playGround, x);
                            }
                    }
                }
            }else {
                if (hurtToZombie != 0 && zombie.getX() == x && zombie.getY() == y) {
                    if (zombie == null) {
                        return;
                    }
                    zombie.setLife(zombie.getLife() - hurtToZombie);
                    if (zombie.getLife() == 0){
                        playGround.getCells()[zombie.getX()][zombie.getY()].getZombieContent().remove(zombie);
                    }
                }
                if (name.equals("TangleKelp") || name.equals("PotatoMine")) {
                    if (zombie == null) {
                        return;
                    }
                    playGround.getCells()[zombie.getX()][zombie.getY()].getZombieContent().remove(zombie);
                    //TODO
                    //remove this plant
                } else if (name.equals("Jalapeno")) {
                            for (int i = 0; i < 16; i++) {
                                playGround.getCells()[x][i].getZombieContent().clear();
                            }
                } else if (name.equals("CherryBomb")) {
                    if (playGround.getCells()[x][y].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x][y].getZombieContent()) {
                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                        }
                    }
                    if (x > 1 && playGround.getCells()[x - 1][y].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x - 1][y].getZombieContent()) {
                            playGround.getCells()[item.getX() - 1][item.getY()].getZombieContent().remove(item);
                        }
                    }
                    if (y > 1 && playGround.getCells()[x][y - 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x][y - 1].getZombieContent()) {
                            playGround.getCells()[item.getX()][item.getY() - 1].getZombieContent().remove(item);
                        }
                    }
                    if (x < 6 && playGround.getCells()[x + 1][y].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x + 1][y].getZombieContent()) {
                            playGround.getCells()[item.getX() + 1][item.getY()].getZombieContent().remove(item);
                        }
                    }
                    if (y < 19 && playGround.getCells()[x][y + 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x][y + 1].getZombieContent()) {
                            playGround.getCells()[item.getX()][item.getY() + 1].getZombieContent().remove(item);
                        }
                    }
                    if (y < 19 && x < 6 && playGround.getCells()[x + 1][y + 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x + 1][y + 1].getZombieContent()) {
                            playGround.getCells()[item.getX() + 1][item.getY() + 1].getZombieContent().remove(item);
                        }
                    }
                    if (x > 0 && y > 0 && playGround.getCells()[x - 1][y - 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x - 1][y - 1].getZombieContent()) {
                            playGround.getCells()[item.getX() - 1][item.getY() - 1].getZombieContent().remove(item);
                        }
                    }
                    if (x < 9 && y > 0 && playGround.getCells()[x + 1][y - 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x + 1][y - 1].getZombieContent()) {
                            playGround.getCells()[item.getX() + 1][item.getY() - 1].getZombieContent().remove(item);
                        }
                    }
                    if (x > 0 && y < 19 && playGround.getCells()[x - 1][y + 1].getZombieContent() != null) {
                        for (Zombie item : playGround.getCells()[x - 1][y + 1].getZombieContent()) {
                            playGround.getCells()[item.getX() - 1][item.getY() + 1].getZombieContent().remove(item);
                        }
                    }
                } else if (name.equals("Magnet-shroom")) {
                    if (playGround.getCells()[x][y].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x][y].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (x > 1 && playGround.getCells()[x - 1][y].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x - 1][y].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (y > 1 && playGround.getCells()[x][y - 1].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x][y - 1].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (x < 6 && playGround.getCells()[x + 1][y].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x + 1][y].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (y < 19 && playGround.getCells()[x][y + 1].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x][y + 1].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (y < 19 && x < 6 && playGround.getCells()[x + 1][y + 1].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x + 1][y + 1].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (x > 0 && y > 0 && playGround.getCells()[x - 1][y - 1].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x - 1][y - 1].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (x < 9 && y > 0 && playGround.getCells()[x + 1][y - 1].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x + 1][y - 1].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    if (x > 0 && y < 19 && playGround.getCells()[x - 1][y + 1].getZombieContent().size() != 0) {
                        for (Zombie item : playGround.getCells()[x - 1][y + 1].getZombieContent())
                            if (magnetZombies(item)) {
                                item.setGuard(false);
                                item.setHat(false);
                                if (item.getName().equals("BucketheadZombie")) {
                                    if (item.getLife() > 1) {
                                        item.setLife(item.getLife() - 1);
                                        if (item.getLife() == 0){
                                            playGround.getCells()[item.getX()][item.getY()].getZombieContent().remove(item);
                                        }
                                    }
                                }
                            }
                    }
                    offTurns = 3;
                }
            }
        } else {
            offTurns--;
        }
    }
    public static boolean magnetZombies(Zombie zombie){
        if (zombie.getName().equals("BucketheadZombie") || zombie.getName().equals("NewspaperZombie")
                || zombie.getName().equals("TargetZombie") || zombie.getName().equals("ScreenDoorZombie") ||
                zombie.getName().equals("DolphinRiderZombie")) {
            return true;
        }
        return false;
    }

    public static Zombie findZombie(PlayGround playGround, int row) {
        for (int i = y; i < 19; i++) {
            for (Zombie item : playGround.getCells()[row][i].getZombieContent()) {
                if (item != null) {
                    return item;
                }
            }
        }
        return null;
    }

    public void shoot(PlayGround playGround,int row){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Zombie zombie;
                Bullet bullet = new Bullet(row,y,plantTurn);
                boolean speedEffect = true;
                while (bullet.getTurn() == bullet.getBulletTurn()){
                    zombie = findZombie(playGround, row);
                    if (zombie != null){
                        if (zombie.getY() <= bullet.getY()){
                            if (!zombie.isGuard()){
                                if (!(zombie.getName().equals("BalloonZombie") && bulletType.equals("pea"))){
                                    zombie.setLife(zombie.getLife() - hurtOfBullet);
                                    if (zombie.getLife() == 0){
                                        playGround.getCells()[zombie.getX()][zombie.getY()].getZombieContent().remove(zombie);
                                    }
                                }
                            }else{
                                zombie.guardLifeDec(hurtOfBullet);
                            }
                            if (effectOnZombieSpeed != 0) {
                                if (speedEffect) {
                                    if (zombie.getSpeed() == zombie.getCurrentSpeed()) {
                                        zombie.setCurrentSpeed(zombie.getCurrentSpeed() / effectOnZombieSpeed);
                                    } else if (zombie.getSpeed() / effectOnZombieSpeed < zombie.getCurrentSpeed()) {
                                        zombie.setCurrentSpeed(zombie.getSpeed() / effectOnZombieSpeed);
                                    }
                                    speedEffect = false;
                                }
                            }
                            break;
                        }else{
                            bullet.setY(bullet.getY()+3);
                            bullet.setBulletTurn(bullet.getBulletTurn() +1);
                            if (bullet.getY() >18){
                                bullet = null;
                                if (!zombie.isGuard()){
                                    if (!(zombie.getName().equals("BalloonZombie") && bulletType.equals("pea"))){
                                        zombie.setLife(zombie.getLife() - hurtOfBullet);
                                        if (zombie.getLife() == 0){
                                            playGround.getCells()[zombie.getX()][zombie.getY()].getZombieContent().remove(zombie);
                                        }
                                    }
                                }else{
                                    zombie.guardLifeDec(hurtOfBullet);
                                }
                                if (effectOnZombieSpeed != 0) {
                                    if (speedEffect) {
                                        if (zombie.getSpeed() == zombie.getCurrentSpeed()) {
                                            zombie.setCurrentSpeed(zombie.getCurrentSpeed() / effectOnZombieSpeed);
                                        } else if (zombie.getSpeed() / effectOnZombieSpeed < zombie.getCurrentSpeed()) {
                                            zombie.setCurrentSpeed(zombie.getSpeed() / effectOnZombieSpeed);
                                        }
                                        speedEffect = false;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
