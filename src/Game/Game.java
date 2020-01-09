package Game;

import Creature.Plant;
import Creature.Zombie;
import Map.Cell;
import Map.CellKind;
import Map.PlayGround;
import Menu.Menu;
import Shop.Shop;
import User.Player;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Game {
    private String typeOfGame;
    private Player firstPlayer; //always playing plants
    private Player secondPlayer; //always playing zombies
    private int numberOfWaves = 0;
    private Player winner;
    private Scanner scanner = new Scanner (System.in);
    private Player currentPlayer;
    private int turn = 0;
    PlayGround playGround = new PlayGround ( );

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
        currentPlayer = firstPlayer;
        if (typeOfGame.compareTo ("PvP") == 0) {
            PvPGame ( );
        } else if (typeOfGame.compareTo ("Rail") == 0) {
            railGame ( );
        } else if (typeOfGame.compareToIgnoreCase ("Zombie") == 0) {
            zombieGame ( );
        } else if (typeOfGame.compareToIgnoreCase ("Day") == 0) {
            dayOrWaterGame ("Day");
        } else if (typeOfGame.compareToIgnoreCase ("Water") == 0) {
            dayOrWaterGame ("Water");
        }
    }

    private void dayOrWaterGame(String gameKind) {
        if (gameKind.compareToIgnoreCase ("Day") == 0) {
            setPlayGround ("land");
        } else
            setPlayGround ("water");
        firstPlayer.setNumberOfSuns (2);
        numberOfWaves = 3;
        int waveNum = 0;
        int[] startTurn = new int[3];
        int[] finishTurn = new int[3];
        startTurn[0] = 3;
        for (int i = 0; i < numberOfWaves; i++) {
            while (true) {
                String order = scanner.nextLine();
                if (order.compareToIgnoreCase("show hand") == 0) {
                    for (Plant plant : currentPlayer.getPlantHand()) {
                        System.out.println("Name: " + plant.getName() + " numberOfSuns: " + plant.getNumberOfSuns()
                                + " timeToRest: " + plant.getRest());
                    }
                } else if (order.compareToIgnoreCase("select") == 0) {
                    String name = scanner.nextLine();
                    selectPlant(name);
                } else if (order.compareToIgnoreCase("plant") == 0) {
                    int y = scanner.nextInt();
                    int x = scanner.nextInt();
                    scanner.nextLine();
                    plantPlant(gameKind, y, x);

                } else if (order.compareToIgnoreCase("remove") == 0) {
                    int a, b;
                    a = scanner.nextInt();
                    b = scanner.nextInt();
                    scanner.nextLine();
                    removePlant(a, b);
                } else if (order.compareToIgnoreCase("end turn") == 0) {
                    turn++;
                    if(turn % 2 == 1){
                        System.out.println("zombie's turn");
                    }
                    else
                        System.out.println("your turn");
                    SecureRandom secureRandom = new SecureRandom();
                    int j = secureRandom.nextInt(2);
                    if (j == 0) {
                        firstPlayer.setNumberOfSuns(firstPlayer.getNumberOfSuns() + getRandomNumberOfSuns());
                    }
                    if (checkWinnerForSingleWave(playGround)) {
                        System.out.println("you won the wave");
                        finishTurn[waveNum] = turn;
                        if (waveNum == 3) {
                            winner = firstPlayer;
                            firstPlayer.setScore(firstPlayer.getNumberOfKilledZombies());
                            winner.setCoins(winner.getCoins() + 10 * winner.getNumberOfKilledZombies());
                            winner.setNumberOfZombiesKilledOverAll(winner.getNumberOfZombiesKilledOverAll() + winner.getNumberOfKilledZombies());
                            System.out.println("game finished");
                            Menu.mainMenu();
                            break;
                        }
                        break;
                    }
                    else if(!checkWinnerForSingleWave(playGround)){
                        System.out.println("you lost the game");
                        Menu.mainMenu();
                        break;
                    }
                    if (turn == startTurn[waveNum]) {
                        System.out.println("wave began");
                        int l = waveNum;
                        startTurn[l] = turn;
                        waveNum++;
                        startTurn[waveNum] = finishTurn[l];
                        startWave();
                    }
                } else if (order.compareToIgnoreCase("show lawn") == 0) {
                    showLawn(playGround);
                } else
                    System.out.println("invalid command");
            }
        }

    }

    private void plantPlant(String gameKind, int y, int x) {
        if (gameKind.compareToIgnoreCase ("land") == 0 && currentPlayer.getSelectedPlant ( ).getPlantType ( ).compareToIgnoreCase ("water") == 0) {
            System.out.println ("you can't use this plant");
        } else if (gameKind.compareToIgnoreCase ("water") == 0 && currentPlayer.getSelectedPlant ( ).getPlantType ( ).compareToIgnoreCase ("land") == 0)
            System.out.println ("you can't use this plant");
        else {
            currentPlayer.getSelectedPlant ( ).setY (y);
            currentPlayer.getSelectedPlant ( ).setX (x);
            playGround.getCells ( )[x][y].getPlantContent ( ).add (currentPlayer.getSelectedPlant ( ));
            System.out.println ("planted successfully" );
            currentPlayer.setSelectedPlant (null);
        }
    }

    private void selectPlant(String name) {
        if (checkPlantInHand (name)) {
            Plant plant = Shop.makeNewPlantByName (name);
            if (plant.getPlantType ( ).compareToIgnoreCase ("land") == 0) {
                if (firstPlayer.getNumberOfSuns ( ) >= plant.getNumberOfSuns ( ) && getPlantFromHand (name).getNumberOfUsesInGame ( ) == 0) {
                    currentPlayer.setSelectedPlant (plant);
                    currentPlayer.setNumberOfSuns (currentPlayer.getNumberOfSuns ( ) - plant.getNumberOfSuns ( ));
                    getPlantFromHand (name).setNumberOfUsesInGame (1);
                    getPlantFromHand (name).setStartedTurn (turn);
                    System.out.println ("you selected this plant");
                } else if (firstPlayer.getNumberOfSuns ( ) >= plant.getNumberOfSuns ( ) && turn - getPlantFromHand (name).getStartedTurn ( ) >= plant.getRest ( ) && getPlantFromHand (name).getNumberOfUsesInGame ( ) != 0) {
                    currentPlayer.setSelectedPlant (plant);
                    currentPlayer.setNumberOfSuns (currentPlayer.getNumberOfSuns ( ) - plant.getNumberOfSuns ( ));
                    getPlantFromHand (name).setStartedTurn (turn);
                    System.out.println ("you selected this plant");
                } else
                    System.out.println ("you can't use this plant now.");
            } else
                System.out.println ("you can't use this plant");

        } else {
            System.out.println ("plant doesn't exist");
        }
    }

    private void zombieGame() {
        int coin = 50;
        int numLadder = 3;
        int numDuck = 3;
        currentPlayer = secondPlayer;
        while (true) {
            String order = scanner.nextLine ( );
            if (order.compareToIgnoreCase ("show hand") == 0) {
                for (Zombie zombie : currentPlayer.getZombieHand ( )) {
                    System.out.println ("Zombie name = " + zombie.getName ( ) + " life = " + zombie.getLife ( ));
                }
            } else if (order.compareToIgnoreCase ("show lanes") == 0) {
                for (int i = 0; i < 6; i++) {
                    if(playGround.getCells()[i][18].getZombieContent().size() != 0){
                        System.out.println(i + 1);
                        for(Zombie zom: playGround.getCells()[i][18].getZombieContent()){
                            System.out.print(zom.getName() + " ,");
                        }
                        System.out.println();
                    }
                }

            } else if (order.compareToIgnoreCase ("put") == 0) {
                System.out.println("inter zombie name");
                String name = scanner.nextLine ( );
                System.out.println("inter number of zombies:" );
                int num = scanner.nextInt();
                System.out.println("inter row num:");
                int row = scanner.nextInt();
                System.out.println("ok");
                Zombie zombie = Shop.makeNewZombieByName (name);
                boolean isThere2Zombies = false;
                int numOfZombieInRow = 0;
                if (coin >= zombie.getLife ( ) * 10) {
                    for (int i = 0; i < 19; i++) {
                        /*if (numOfZombieInRow == 2) {
                            isThere2Zombies = true;
                            zombie = null;
                            break;
                        }*/
                        if (playGround.getCells ( )[row][i].getZombieContent ( ) != null) {
                            numOfZombieInRow++;
                        }
                    }
                    if (!isThere2Zombies) {
                        for(int k = 0; k < num; k++){
                            zombie.setX (row);
                            playGround.getCells()[row][18].setZombieContent(zombie);
                            System.out.println("here");
                        }
                        coin -= zombie.getLife ( ) * 10;
                    }
                    System.out.println("Zombie put successfully");
                }else
                    System.out.println("not enough money!");
            } else if (order.compareToIgnoreCase ("start") == 0) {
                SecureRandom rand = new SecureRandom ( );
                for (int i = 0; i < 7; i++) {
                    int randInt1 = rand.nextInt (Shop.getPlantList ( ).size ( ));
                    Plant newRandPlant = Shop.makeNewPlantByName (Shop.getPlantList ( ).get (randInt1).getName ( ));
                    while (true) {
                        int randInt2 = rand.nextInt (6);
                        int randInt3 = rand.nextInt (3);
                        if (playGround.getCells ( )[randInt2][randInt3].getPlantContent ( ) == null) {
                            newRandPlant.setX (randInt2);
                            newRandPlant.setY (randInt3);
                            playGround.getCells()[randInt2][randInt3].setPlantContent(newRandPlant);
                            break;
                        }

                    }
                }
                while (checkWinnerForZombie (playGround, coin) == -1) {
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 19; j++) {
                            for (Plant plant : playGround.getCells ( )[i][j].getPlantContent ( )) {
                                for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( ))
                                    plant.attack (playGround, true);
                                plant.attack (playGround, false);
                            }
                            ArrayList<String> killedPlantsInTheTurn = new ArrayList<> ( );
                            for (int m = 0; m < 6; m++) {
                                for (int l = 0; l < 19; l++) {
                                    if (playGround.getCells ( )[m][l].getZombieContent ( ) != null) {
                                        for (Zombie zombie : playGround.getCells ( )[m][l].getZombieContent ( )) {
                                            for (int k = 0; k < zombie.getSpeed ( ); k++) {
                                                if (playGround.getCells ( )[m][l].getPlantContent ( ).size ( ) != 0) {
                                                    String name = zombie.attack (playGround);
                                                    if (!name.equals ("not")) {
                                                        killedPlantsInTheTurn.add (name);
                                                    }
                                                }
                                                if (zombie != null && (zombie.getY ( ) - 1) > 0) {
                                                    zombie.setY (zombie.getY ( ) - 1);
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                            for (int m = 0; m < killedPlantsInTheTurn.size ( ); m++) {
                                Plant plant = Shop.makeNewPlantByName (killedPlantsInTheTurn.get (m));
                                coin += plant.getLife ( ) * 10;
                            }
                            killedPlantsInTheTurn.clear ( );


                        }
                    }

                }

            } else if (order.compareToIgnoreCase ("End turn") == 0) {
                turn++;
                System.out.println(" turn is " + turn);
                //choosing zombie having ladder or duck
                System.out.println ("if you want a zombie to have a duck or ladder enter 1");
                int choice = scanner.nextInt ( );
                if (choice == 1) {
                    int x = 0 , y = 0, tired = 0;
                    while(true){
                        System.out.println ("choose a zombie");

                        x = scanner.nextInt();
                        y = scanner.nextInt();
                        if(playGround.getCells()[x][y].getZombieContent() == null){
                            System.out.println("NO ZOMBIE FOUND HERE");

                            System.out.println("tired of Choosing? if yes, enter 1, else enter 0");
                            tired = scanner.nextInt();
                            if(tired == 1)
                                break;
                        } else {
                            break;
                        }

                    }
                    if(tired == 1){
                        break;
                    }
                    Zombie  chosenZombie = playGround.getCells()[x][y].getZombieContent().get(0);
                    int choice2;
                    System.out.println ("if you want ladder enter 1, if you want duck enter 2");
                    choice2 = scanner.nextInt ( );
                    if (choice2 == 1)
                        chosenZombie.setDuckOrLadder (choice2);
                    else
                        chosenZombie.setDuckOrLadder (choice2);

                }

            } else if (order.compareToIgnoreCase ("show lawn") == 0) {
                showLawn (playGround);
            }

            if (checkWinnerForZombie (playGround, coin) == 1) {
                System.out.println ("game finished");
                Menu.mainMenu ( );
                break;
            }
        }
    }

    private void railGame() {
        SecureRandom rand = new SecureRandom ( );
        ArrayList<Plant> cards = new ArrayList<> ( );
        int plantNextTurn = 0;
        int zombieNextTurn = 0;
        while (true) {
            String order = scanner.nextLine ( );
            if (turn == plantNextTurn) {
                randomCard (cards);
                int randomNum = rand.nextInt (3) + 2;
                plantNextTurn = turn + randomNum;
            }
            if (order.compareToIgnoreCase ("list") == 0) {
                for (Plant plant : currentPlayer.getPlantHand ( )) {
                    System.out.println (plant.getName ( ));
                }
            } else if (order.compareToIgnoreCase ("select") == 0) {
                int n = scanner.nextInt ( );
                if (n > currentPlayer.getPlantHand ( ).size ( )) {
                    System.out.println ("Out of bound!");
                    continue;
                }
                currentPlayer.setSelectedPlant (cards.get (n - 1));
                currentPlayer.getPlantHand ( ).remove (cards.get (n - 1));
            } else if (order.compareToIgnoreCase ("record") == 0) {
                System.out.println ("number of killed zombies:" + currentPlayer.getNumberOfKilledZombies ( ));
            } else if (order.compareToIgnoreCase ("plant") == 0) {
                int y = scanner.nextInt ( );
                int x = scanner.nextInt ( );
                scanner.nextLine ( );
                currentPlayer.getSelectedPlant ( ).setY (y);
                currentPlayer.getSelectedPlant ( ).setX (x);
                playGround.getCells ( )[x][y].setPlantContent (currentPlayer.getSelectedPlant ( ));
                currentPlayer.setSelectedPlant (null);
                System.out.println ("Plant successfully planted!");
            } else if (order.compareToIgnoreCase ("remove") == 0) {
                int a, b;
                a = scanner.nextInt ( );
                b = scanner.nextInt ( );
                playGround.getCells ( )[b][a].clearPlantContent ( );
                System.out.println ("Plant removed successfully!");
            } else if (order.compareToIgnoreCase ("end turn") == 0) {
                if (turn == zombieNextTurn) {
                    randomZombieSet (playGround);
                    int randomNum = rand.nextInt (3) + 3;
                    zombieNextTurn = turn + randomNum;
                }
                turn++;
                attackCheck ( );
                if (zombieMove (playGround)) {
                    currentPlayer.setScore (currentPlayer.getNumberOfKilledZombies ( ));
                    System.out.println ("game finished");
                    Menu.mainMenu ( );
                    break;
                }
                System.out.println ("Turn ended. New turn Started!");
            } else if (order.compareToIgnoreCase ("show lawn") == 0) {
                showLawn (playGround);
            }
        }
    }

    private void attackCheck() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells ( )[i][j].getZombieContent ( ) != null) {
                    for (int k = 0; k <= j; k++) {
                        for (Plant plant : playGround.getCells ( )[i][k].getPlantContent ( )) {
                            if (j == k) {
                                plant.attack (playGround, true);
                            } else {
                                plant.attack (playGround, false);
                            }
                        }
                    }
                }
            }
        }
    }

    private void PvPGame() {
        int isFinished = 0;
        for (int i = 0; i < numberOfWaves; i++) {
            while (true) {
                String order = scanner.nextLine ( );
                if (order.compareToIgnoreCase ("show hand") == 0) {
                    if (currentPlayer.getTypeOfPlayer ( ).compareToIgnoreCase ("plant") == 0) {
                        for (Plant plant : currentPlayer.getPlantHand ( )) {
                            System.out.println ("selected plant:" + plant.getName ( ) + " numberOfSuns:" +
                                    plant.getNumberOfSuns ( ) + " timeToRest: " + plant.getRest ( ));
                        }
                    } else {
                        for (Zombie zombie : currentPlayer.getZombieHand ( )) {
                            System.out.println ("selected zombie:" + zombie.getName ( ) + "life:" + zombie.getLife ( ));
                        }
                    }
                } else if (order.compareToIgnoreCase ("select") == 0) {
                    String name = scanner.nextLine ( );
                    selectPlant (name);
                } else if (order.compareToIgnoreCase ("plant") == 0) {
                    int y = scanner.nextInt ( );
                    int x = scanner.nextInt ( );
                    plantPlant ("PvP", y, x);
                } else if (order.compareToIgnoreCase ("remove") == 0) {
                    int a, b;
                    a = scanner.nextInt ( );
                    b = scanner.nextInt ( );
                    scanner.nextLine ();
                    removePlant (a, b);
                } else if (order.compareToIgnoreCase ("ready") == 0) {
                    attackZombie(secondPlayer);
                    System.out.println("zombie mode began");
                    turn++;
                    firstPlayer.setNumberOfSuns (firstPlayer.getNumberOfSuns ( ) + 1);
                    if (checkWinnerForSingleWave (playGround)) {
                        break;
                    }

                } else if (order.compareToIgnoreCase ("show lawn") == 0) {
                    showLawn (playGround);
                } else if (order.compareToIgnoreCase ("show lanes") == 0) {
                    secondPlayer.getZombieHand ( ).sort (Comparator.comparing (Zombie::getY));
                    for (int j = 0; j < 6; j++) {
                        for (Zombie zombie : secondPlayer.getZombieHand ( )) {
                            if (zombie.getX ( ) == j) {
                                System.out.println ((j + 1) + " " + zombie.getName ( ));
                                break;
                            }
                        }
                    }
                } else if (order.compareToIgnoreCase ("start") == 0) {}
                else if(order.compareToIgnoreCase("end turn") == 0){
                    turn++;
                    System.out.println("your turn");
                }
                else if (order.compareToIgnoreCase ("put") == 0) {
                    String name = scanner.nextLine ( );
                    int num = scanner.nextInt ( );
                    int rowNum = scanner.nextInt ( );
                    secondPlayer.setInsideGameCoins (50);
                    Zombie zombie = Shop.makeNewZombieByName (name);
                    int numOfZombieInRow = playGround.getCells ( )[rowNum][18].getZombieContent ( ).size ( );
                    //check number of zombies in last cell of that row to be no zombie there
                    if (secondPlayer.getInsideGameCoins ( ) >= zombie.getPrice ( ) * num) {
                        if (numOfZombieInRow == 0) {
                            for (int k = 0; k < num; k++) {
                                playGround.getCells ( )[rowNum][18].getZombieContent ( ).add (zombie);
                                secondPlayer.setInsideGameCoins (secondPlayer.getInsideGameCoins ( ) - zombie.getPrice ( ));
                            }
                        }
                    }
                }
            }
            if (checkWinnerForPvP ( )) {
                System.out.println ("game finished");
                isFinished = 1;
                break;
            }
        }
        if (isFinished == 1) {
            Menu.mainMenu ( );
        }
    }

    private void removePlant(int a, int b) {
        System.out.println ("removed successfully");
        playGround.getCells ( )[b][a].clearPlantContent ( );
    }

    public boolean checkWinnerForPvP() {
        if (firstPlayer.getNumberOfWavesWon ( ) >= numberOfWaves / 2) {
            winner = firstPlayer;
            winner.setCoins (winner.getCoins ( ) + 10 * winner.getNumberOfKilledZombies ( ));
            winner.setNumberOfZombiesKilledOverAll (winner.getNumberOfZombiesKilledOverAll ( ) + winner.getNumberOfKilledZombies ( ));
            return true;
        } else if (secondPlayer.getNumberOfWavesWon ( ) >= numberOfWaves / 2) {
            winner = secondPlayer;
            winner.setCoins (winner.getCoins ( ) + 10 * winner.getNumberOfKilledPlants ( ));
            winner.setNumberOfPlantsKilledOverAll (winner.getNumberOfPlantsKilledOverAll ( ) + winner.getNumberOfKilledPlants ( ));
            return true;
        }
        return false;
    }

    public boolean checkWinnerForSingleWave(PlayGround playGround) {
        int num = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                num += playGround.getCells ( )[i][j].getZombieContent ( ).size ( );
            }
            if (num == 0) {
                return false;
            }
        }
        for (int i = 0; i < 6; i++) {
            if (playGround.getCells ( )[i][0].getZombieContent ( ).size ( ) > 0 && playGround.getCells ( )[i][0].getPlantContent ( ).size ( ) == 0) {
                winner.setInsideGameCoins (winner.getInsideGameCoins ( ) + 200);
                winner.setNumberOfWavesWon (winner.getNumberOfWavesWon ( ) + 1);
                return false;
            }
        }
        return true;
    }


    public int checkWinnerForZombie(PlayGround playGround, int coin) {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells ( )[i][j].getZombieContent ( ) != null) {
                    for (int k = 0; k < 6; k++) {
                        for (int l = 0; l < 19; l++) {
                            if (playGround.getCells ( )[k][l].getPlantContent ( ) != null)
                                return -1;
                        }
                    }

                    //it means there's no plants left
                    System.out.println("Zombies won!");
                    return 1;
                }
            }

        //it means there's no zombie left
        ArrayList<Zombie> zombies =  Shop.getZombieList();
        zombies.sort(Comparator.comparing(Zombie :: getLife));
        if(coin < (zombies.get(0).getLife() * 10) ){
            System.out.println("Zombies lost");
            return 1;
        }
        //now we should buy new zombies maybe use put item??
        System.out.println("Buy new Zombies to fight, use put command!");
        return 0;

    }

    public void randomCard(ArrayList<Plant> cards) {
        SecureRandom rand = new SecureRandom ( );
        String[] plantLibrary = {"Cabbage-pult", "Cactus", "Cattail", "CherryBomb", "Explode-o-nut", "GatlingPea", "Jalapeno", "Kernel-pult", "LilyPad", "Magnet-Shroom", "Melon-pult", "PeaShooter", "PotatoMine", "Repeater", "Scaredy-shroom", "SnowPea", "SplitPea", "Tall-nut", "TangleKelp", "Threepeater", "Wall-nut", "WinterMelon"};
        if (cards.size ( ) < 11) {
            int randomNum = rand.nextInt (22);
            currentPlayer.getPlantHand ( ).add (Shop.makeNewPlantByName (plantLibrary[randomNum]));
            cards.add (Shop.makeNewPlantByName (plantLibrary[randomNum]));
        }
    }

    public void showLawn(PlayGround playGround) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                for (Plant plant : playGround.getCells ( )[i][j].getPlantContent ( )) {
                    System.out.println ("name:" + plant.getName ( ) + " x: " + plant.getX ( ) + " y: " + plant.getY ( ) +
                            " remained life: " + plant.getLife ( ));
                }
                for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( )) {
                    System.out.println ("name:" + zombie.getName ( ) + " x: " + zombie.getX ( ) + " y: " + zombie.getY ( ) +
                            " remained life: " + zombie.getLife ( ));
                }
            }
        }
    }

    public void randomZombieSet(PlayGround playGround) {
        SecureRandom rand = new SecureRandom ( );
        String[] zombieLibrary = {"BalloonZombie", "Zombie", "FootballZombie", "BucketheadZombie", "ConeheadZombie", "Zomboni", "CatapultZombie", "BungeeZombie", "BalloonZombie", "NewspaperZombie", "TargetZombie", "ScreenDoorZombie", "Giga-gargantuar", "PogoZombie"};
        int randomNum = rand.nextInt (13);
        int randomX = rand.nextInt (6);
        Zombie zombie = Shop.makeNewZombieByName (zombieLibrary[randomNum]);
        zombie.setX (randomX);
        zombie.setY (18);
        currentPlayer.getZombieHand ( ).add (zombie);
        playGround.getCells ( )[randomX][18].setZombieContent (zombie);
    }

    public void randomZombieSetForDay(PlayGround playGround) {
        SecureRandom secureRandom = new SecureRandom ( );
        int numberOfZombies = secureRandom.nextInt (7) + 4;
        for (int i = 0; i < numberOfZombies; i++) {
            int zombieNum = secureRandom.nextInt (Shop.getZombieList ().size () );
            int randomX = secureRandom.nextInt (6);
            Zombie zombie = Shop.makeNewZombieByName (Shop.getZombieList ().get (zombieNum).getName ());
            zombie.setX (randomX);
            zombie.setY (18);
            playGround.getCells ()[randomX][18].getZombieContent ().add (zombie);
            attackCheck ();
        }
    }

    private int getRandomNumberOfSuns() {
        SecureRandom secureRandom = new SecureRandom ( );
        return secureRandom.nextInt (4) + 2;
    }

    private void startWave() {
        randomZombieSetForDay (playGround);
    }

    private void setPlayGround(String type) {
        if (type.compareToIgnoreCase ("land") == 0) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 19; j++) {
                    playGround.setSingleCell (i, j);
                    playGround.getSingleCell (i, j).setCellKind (CellKind.LAND);
                }

            }
        } else
            for (int i = 2; i < 4; i++) {
                for (int j = 0; j < 19; j++) {
                    playGround.setSingleCell (i, j);
                    playGround.getSingleCell (i, j).setCellKind (CellKind.WATER);
                }

            }

    }

    private boolean zombieMove(PlayGround playGround) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells ( )[i][j].getZombieContent ( ) != null) {
                    for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( )) {
                        for (int k = 0; k < zombie.getSpeed ( ); k++) {
                            if (playGround.getCells ( )[i][j].getPlantContent ( ).size ( ) != 0) {
                                zombie.attack (playGround);
                            }
                            if (zombie != null) {
                                zombie.setY (zombie.getY ( ) - 1);
                            }
                            if (zombie.getY ( ) < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkPlantInHand(String name){
        for(Plant plant : firstPlayer.getPlantHand ()){
            if(plant.getName ().compareToIgnoreCase (name) == 0){
                return true;
            }
        }
        return false;
    }

    private Plant getPlantFromHand(String name){
        for(Plant plant : firstPlayer.getPlantHand ()){
            if(plant.getName ().compareToIgnoreCase (name) == 0){
                return plant;
            }
        }
        return null;
    }
    private void attackZombie(Player player){
        while (checkWinnerForZombie (playGround, player.getInsideGameCoins()) == -1) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 19; j++) {
                    for (Plant plant : playGround.getCells ( )[i][j].getPlantContent ( )) {
                        for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( ))
                            plant.attack (playGround, true);
                        plant.attack (playGround, false);
                    }
                    ArrayList<String> killedPlantsInTheTurn = new ArrayList<> ( );
                    for (int m = 0; m < 6; m++) {
                        for (int l = 0; l < 19; l++) {
                            if (playGround.getCells ( )[m][l].getZombieContent ( ) != null) {
                                for (Zombie zombie : playGround.getCells ( )[m][l].getZombieContent ( )) {
                                    for (int k = 0; k < zombie.getSpeed ( ); k++) {
                                        if (playGround.getCells ( )[m][l].getPlantContent ( ).size ( ) != 0) {
                                            String name = zombie.attack (playGround);
                                            if (!name.equals ("not")) {
                                                killedPlantsInTheTurn.add (name);
                                            }
                                        }
                                        if (zombie != null && (zombie.getY ( ) - 1) > 0) {
                                            zombie.setY (zombie.getY ( ) - 1);
                                        }

                                    }
                                }
                            }
                        }
                    }
                    for (int m = 0; m < killedPlantsInTheTurn.size ( ); m++) {
                        Plant plant = Shop.makeNewPlantByName (killedPlantsInTheTurn.get (m));
                        player.setInsideGameCoins(player.getInsideGameCoins() + plant.getLife ( ) * 10);
                    }
                    killedPlantsInTheTurn.clear ( );


                }
            }

        }
    }
}
