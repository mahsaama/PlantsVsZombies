package Game;

import Creature.Plant;
import Creature.Zombie;
import Map.Cell;
import Map.PlayGround;
import User.Player;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String typeOfGame;
    private Player firstPlayer; //always playing plants
    private Player secondPlayer; //always playing zombies
    private int numberOfWaves = 0;
    private Player winner;
    private Scanner scanner = new Scanner(System.in);
    private Player currentPlayer = firstPlayer;//TODO
    private int turn = 0;
    private int numberOfSuns = 0;
    PlayGround playGround = new PlayGround();

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setTypeOfGame(String typeOfGame) {
        this.typeOfGame = typeOfGame;
    }

    public void setNumberOfWaves(int numberOfWaves) {
        this.numberOfWaves = numberOfWaves;
    }


    public void setGameEnvironment() {
        if (typeOfGame.compareTo("PvP") == 0) {
            String order = scanner.nextLine();
            while (true) {
                if (checkWinnerForPvP()) {
                    System.out.println("game finished");
                    Main.mainmenu();
                    break;
                }
                if (order.compareToIgnoreCase("show hand") == 0) {
                    if (currentPlayer.getTypeOfPlayer().compareToIgnoreCase("plant") == 0) {
                        for (Plant plant : currentPlayer.getPlantHand()) {
                            System.out.println("selected plant:" + plant.getName() + " numberOfsuns:" +
                                    plant.getNumberOfSuns() + " timeToRest: " + plant.getRest());
                        }
                    } else {
                        for (Zombie zombie : currentPlayer.getZombieHand()) {
                            System.out.println("selected zombie:" + zombie.getName() + "life:" + zombie.getLife());
                        }
                    }
                } else if (order.compareToIgnoreCase("select") == 0) {
                    String name = scanner.nextLine();
                    Plant plant = getPlantByName(name);
                    plant.setStartedTurn(turn);
                    if (numberOfSuns >= plant.getNumberOfSuns() && turn - plant.getStartedTurn() >= plant.getRest()) {
                        currentPlayer.setSelectedPlant(getPlantByName(name));
                    }
                } else if (order.compareToIgnoreCase("plant") == 0) {
                    currentPlayer.getSelectedPlant().setY(scanner.nextInt());
                    currentPlayer.getSelectedPlant().setX(scanner.nextInt());
                } else if (order.compareToIgnoreCase("remove") == 0) {
                    int a, b;
                    a = scanner.nextInt();
                    b = scanner.nextInt();
                    playGround.getCells()[b][a].getPlantContent().clear();
                } else if (order.compareToIgnoreCase("ready") == 0) {
                    turn++;
                    //attack
                } else if (order.compareToIgnoreCase("show lawn") == 0) {
                    showLawn(playGround);
                } else if (order.compareToIgnoreCase("show lanes") == 0) {

                } else if (order.compareToIgnoreCase("start") == 0) {

                } else if (order.compareToIgnoreCase("put") == 0) {
                    String name = scanner.nextLine();
                    int zombieNumber = scanner.nextInt();

                }
            }
        } else if (typeOfGame.compareTo("Rail") == 0) {
            String order = scanner.nextLine();
            ArrayList<Plant> cards = new ArrayList<>();
            int plantNextTurn = 0;
            int zombieNextTurn = 0;
            while (true) {
                if (checkWinnerForRail(playGround)) {
                    currentPlayer.setScore(currentPlayer.getNumberOfKilledZombies());
                    System.out.println("game finished");
                    Main.mainMenu();
                    break;
                } else if (order.compareToIgnoreCase("list") == 0) {
                    for (Plant plant : currentPlayer.getPlantHand()) {
                        System.out.println(plant.getName());
                    }
                } else if (order.compareToIgnoreCase("select") == 0) {
                    int n = scanner.nextInt();
                    if (turn == 0) {
                        randomCard(cards);
                        int randomNum = (int) Math.random();
                        randomNum = randomNum % 3 + 2;
                        plantNextTurn = turn + randomNum;
                    } else if (turn == plantNextTurn) {
                        randomCard(cards);
                    }
                    currentPlayer.setSelectedPlant(cards.get(n));
                    currentPlayer.getPlantHand().remove(cards.get(n));
                } else if (order.compareToIgnoreCase("record") == 0) {
                    System.out.println("number of killed zombies:" + currentPlayer.getNumberOfKilledZombies());
                } else if (order.compareToIgnoreCase("plant") == 0) {
                    currentPlayer.getSelectedPlant().setY(scanner.nextInt());
                    currentPlayer.getSelectedPlant().setX(scanner.nextInt());
                } else if (order.compareToIgnoreCase("remove") == 0) {
                    int a, b;
                    a = scanner.nextInt();
                    b = scanner.nextInt();
                    playGround.getCells()[b][a].getPlantContent().clear();
                } else if (order.compareToIgnoreCase("end turn") == 0) {
                    turn++;
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 19; j++) {
                            if (playGround.getCells()[i][j].getZombieContent() != null) {
                                for (int k = 0; k <= j; k++) {
                                    for (Plant plant : playGround.getCells()[i][k].getPlantContent()) {
                                        if (j == k) {
                                            plant.attack(playGround, true);
                                        } else {
                                            plant.attack(playGround, false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //plants turn finished & zombies turn started
                    if (turn == 1) {
                        randomZombieSet(playGround);
                        int randomNum = (int) Math.random();
                        randomNum = randomNum % 3 + 3;
                        zombieNextTurn = turn + randomNum;
                    } else if (turn == zombieNextTurn) {
                        randomZombieSet(playGround);
                    }
                    turn++;
                } else if (order.compareToIgnoreCase("show lawn") == 0) {
                    showLawn(playGround);
                }

            }
        }
    }

    public boolean checkWinnerForPvP() {
        if (firstPlayer.getNumberOfWavesWon() >= numberOfWaves / 2) {
            winner = firstPlayer;
            winner.setCoins(winner.getCoins() + 10 * winner.getNumberOfKilledZombies());
            winner.setNumberOfZombiesKilledOverAll(winner.getNumberOfZombiesKilledOverAll() + winner.getNumberOfKilledZombies());
            return true;
        } else if (secondPlayer.getNumberOfWavesWon() >= numberOfWaves / 2) {
            winner = secondPlayer;
            winner.setCoins(winner.getCoins() + 10 * winner.getNumberOfKilledPlants());
            winner.setNumberOfPlantsKilledOverAll(winner.getNumberOfPlantsKilledOverAll() + winner.getNumberOfKilledPlants());
            return true;
        }
        return false;
    }

    public boolean checkWinnerForSingleWave() {
        winner.setInsideGameCoins(winner.getInsideGameCoins() + 200);
        winner.setNumberOfWavesWon(winner.getNumberOfWavesWon() + 1);
        return false;
        //TODO
    }

    public boolean checkWinnerForRail(PlayGround playGround) {
        for (int i = 0; i < 6; i++) {
            if (playGround.getCells()[i][0].getPlantContent() == null && playGround.getCells()[i][0].getZombieContent() != null) {
                return true;
            }
        }
        return false;
    }

    public void randomCard(ArrayList<Plant> cards) {
        String[] plantLibrary = {"Cabbage-pult", "Cactus", "Cattail", "CherryBomb", "Explode-o-nut", "GatlingPea", "Jalapeno", "Kernel-pult", "LilyPad", "Magnet-Shroom", "Melon-pult", "PeaShooter", "PotatoMine", "Repeater", "Scaredy-shroom", "SnowPea", "SplitPea", "Tall-nut", "TangleKelp", "Threepeater", "Wall-nut", "WinterMelon"};
        if (cards.size() < 11) {
            int randomNum = (int) Math.random() % 22;
            currentPlayer.getPlantHand().add(Main.getPlantByName(plantLibrary[randomNum]));
        }
    }

    public void showLawn(PlayGround playGround) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                for (Plant plant : playGround.getCells()[i][j].getPlantContent()) {
                    System.out.println("name:" + plant.getName() + " x: " + plant.getX() + " y: " + plant.getY() +
                            " remained life: " + plant.getLife());
                }
                for (Zombie zombie : playGround.getCells()[i][j].getZombieContent()) {
                    System.out.println("name:" + zombie.getName() + " x: " + zombie.getX() + " y: " + zombie.getY() +
                            " remained life: " + zombie.getLife());
                }
            }
        }
    }

    public void randomZombieSet(PlayGround playGround) {
        String[] zombieLibrary = {"BalloonZombie", "Zombie", "FootballZombie", "BucketheadZombie", "ConeheadZombie", "Zomboni", "CatapultZombie", "BungeeZombie", "BalloonZombie", "NewspaperZombie", "TargetZombie", "ScreenDoorZombie", "Giga-gargantuar", "PogoZombie"};
        int randomNum = (int) Math.random() % 13;
        int randomX = (int) Math.random() % 6;
        Zombie zombie = Main.getZombieByName(zombieLibrary[randomNum];
        zombie.setX(randomX);
        zombie.setY(18);
        currentPlayer.getZombieHand().add(zombie);
    }
}
