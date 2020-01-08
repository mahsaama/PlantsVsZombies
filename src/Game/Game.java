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
        } else if (typeOfGame.compareToIgnoreCase ("zombie") == 0) {
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
        int[] startTurn = new int[3];
        int[] finishTurn = new int[3];
        for (int i = 0; i < numberOfWaves; i++) {
            while (checkWinnerForSingleWave (playGround)) {
                String order = scanner.nextLine ( );
                if (turn >= 3 && startTurn[0] == 0) {
                    startTurn[0] = turn;
                    if (currentPlayer.equals (secondPlayer)) {
                        startWave ( );
                    }
                }
                if (!checkWinnerForSingleWave (playGround)) {
                    finishTurn[i] = turn;
                    continue;
                }
                if (i != 0 && turn - finishTurn[i - 1] >= 7 && startTurn[i] == 0) {
                    startTurn[i] = turn;
                    startWave ( );
                }
                if (!checkWinnerForRail (playGround)) {///this method was deleted...........................................//TODO
                    winner = firstPlayer;
                    currentPlayer.setScore (currentPlayer.getNumberOfKilledZombies ( ));
                    winner.setCoins (winner.getCoins ( ) + 10 * winner.getNumberOfKilledZombies ( ));
                    winner.setNumberOfZombiesKilledOverAll (winner.getNumberOfZombiesKilledOverAll ( ) + winner.getNumberOfKilledZombies ( ));
                    System.out.println ("game finished");
                    Menu.mainMenu ( );
                    return;
                }
                if (order.compareToIgnoreCase ("show hand") == 0) {
                    for (Plant plant : currentPlayer.getPlantHand ( )) {
                        System.out.println ("Name: " + plant.getName ( ) + "needed suns: " + plant.getNumberOfSuns ( )
                                + "rest: " + plant.getRest ( ));
                    }
                } else if (order.compareToIgnoreCase ("select") == 0) {
                    String name = scanner.nextLine ( );
                    if (Menu.checkPlant (name)) {
                        Plant plant = Menu.getPlantByName (name);
                        plant.setStartedTurn (turn);
                        if (plant.getPlantType ( ).compareToIgnoreCase ("land") == 0) {
                            if (currentPlayer.getNumberOfSuns ( ) >= plant.getNumberOfSuns ( )) {
                                if (firstPlayer.getNumberOfSuns () >= plant.getNumberOfSuns ( ) && turn - plant.getStartedTurn ( ) >= plant.getRest ( )) {
                                    currentPlayer.setSelectedPlant (plant);
                                } else {
                                    System.out.println ("you don't have enough energy");
                                }
                            } else {
                                System.out.println ("you don't have enough suns");
                            }
                        }

                    } else {
                        System.out.println ("plant doesn't exist");
                    }
                } else if (order.compareToIgnoreCase ("plant") == 0) {
                    int y = scanner.nextInt ();
                    int x = scanner.nextInt ();
                    if(gameKind.compareToIgnoreCase ("land") == 0){
                        if(currentPlayer.getSelectedPlant ().getPlantType ().compareToIgnoreCase ("water") == 0){
                            System.out.println ("you can't use this plant" );
                        }
                        else{
                            currentPlayer.getSelectedPlant ( ).setY (y);
                            currentPlayer.getSelectedPlant ( ).setX (x);
                            currentPlayer.setSelectedPlant (null);
                        }
                    }
                    else{
                        if(currentPlayer.getSelectedPlant ().getPlantType ().compareToIgnoreCase ("water") == 0){
                            if(playGround.getSingleCell (x,y).getCellKind () == CellKind.WATER){
                                currentPlayer.getSelectedPlant ( ).setY (y);
                                currentPlayer.getSelectedPlant ( ).setX (x);
                                currentPlayer.setSelectedPlant (null);
                            }
                            else
                                System.out.println ("you can't use this plant" );
                        }
                    }

                } else if (order.compareToIgnoreCase ("remove") == 0) {
                    int a, b;
                    a = scanner.nextInt ( );
                    b = scanner.nextInt ( );
                    playGround.getCells ( )[b][a].getPlantContent ( ).clear ( );
                } else if (order.compareToIgnoreCase ("end turn") == 0) {
                    turn++;
                    SecureRandom secureRandom = new SecureRandom ( );
                    int j = secureRandom.nextInt (2);
                    if (j == 0) {
                        currentPlayer.setNumberOfSuns (currentPlayer.getNumberOfSuns ( ) + getRandomNumberOfSuns ( ));
                    }
                    if (currentPlayer.equals (firstPlayer)) {
                        currentPlayer = secondPlayer;
                    } else
                        currentPlayer = firstPlayer;
                   attackCheck ();
                } else if (order.compareToIgnoreCase ("show lawn") == 0) {
                    showLawn (playGround);
                } else
                    System.out.println ("invalid command");
            }
        }

    }

    private void zombieGame() {
        String order = scanner.nextLine ( );
        int coin = 50;
        int numLadder = 3;
        int numDuck = 3;
        while (true) {
            if (checkWinnerForZombie (playGround) == true) {
                System.out.println ("game finished");
                Menu.mainMenu ( );
                break;
            } else if (order.compareToIgnoreCase ("show hand") == 0) {
                for (Zombie zombie : currentPlayer.getZombieHand ( )) {
                    System.out.println ("Zombie name = " + zombie.getName ( ) + " life = " + zombie.getLife ( ));
                }
            } else if (order.compareToIgnoreCase ("show lanes") == 0) {
                currentPlayer.getZombieHand ( ).sort (Comparator.comparing (Zombie::getY));
                for (int i = 0; i < 6; i++) {
                    for (Zombie zombie : currentPlayer.getZombieHand ( )) {
                        if (zombie.getX ( ) == i) {
                            System.out.println (i + 1);
                            break;
                        }
                    }
                }
                for (int i = 0; i < 6; i++) {
                    for (Zombie zombie : currentPlayer.getZombieHand ( )) {
                        if (zombie.getX ( ) == i) {
                            System.out.println (zombie.getName ( ) + " ");
                        }
                    }
                }
            } else if (order.compareToIgnoreCase ("put") == 0) {
                String name = scanner.nextLine ( );
                int num = scanner.nextInt ( );
                Zombie zombie = Menu.getZombieByName (name);
                boolean isThere2Zombies = false;
                int numOfZombieInRow = 0;
                if (coin >= zombie.getLife ( ) * 10) {
                    for (int i = 0; i < 19; i++) {
                        if (numOfZombieInRow == 2) {
                            isThere2Zombies = true;
                            zombie = null;
                            break;
                        }
                        if (playGround.getCells ( )[num][i].getZombieContent ( ) != null) {
                            numOfZombieInRow++;
                        }
                    }
                    if (isThere2Zombies != true) {
                        zombie.setX (num);
                        coin -= zombie.getLife ( ) * 10;
                    }
                }
            } else if (order.compareToIgnoreCase ("start") == 0) {
                SecureRandom rand = new SecureRandom ( );
                for (int i = 0; i < 7; i++) {
                    int randInt1 = rand.nextInt (Shop.getPlantList ( ).size ( ));
                    Plant newRandPlant = Shop.makeNewPlantByName (Shop.getPlantList ( ).get (randInt1).getName ( ));
                    while (true) {
                        int randInt2 = rand.nextInt (6);
                        int randInt3 = rand.nextInt (rand.nextInt (3));
                        if (playGround.getCells ( )[randInt2][randInt3].getPlantContent ( ) != null) {
                            newRandPlant.setX (randInt2);
                            newRandPlant.setY (randInt3);
                            break;
                        }

                    }
                }
                while (!checkWinnerForZombie (playGround)) {
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 19; j++) {
                            for (Plant plant : playGround.getCells ( )[i][j].getPlantContent ( )) {
                                for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( ))
                                    plant.attack (playGround, true);
                                plant.attack (playGround, false);
                            }
                            if (playGround.getCells ( )[i][j].getZombieContent ( ) != null &&
                                    playGround.getCells ( )[i][j].getPlantContent ( ) != null) {
                                for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( )) {
                                    zombie.attack (playGround);
                                }
                            }
                        }
                    }
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 19; j++) {
                            for (Zombie zombie : playGround.getCells ( )[i][j].getZombieContent ( )) {
                                boolean wasPlantInTheWay = false;
                                for (int k = 0; k < zombie.getSpeed ( ); k++) {
                                    if (j - k >= 0) {
                                        if (playGround.getCells ( )[i][j - k].getPlantContent ( ) != null) {
                                            zombie.setY (j - k);
                                            wasPlantInTheWay = true;
                                            break;
                                        }
                                    }
                                }
                                if (wasPlantInTheWay == false) {
                                    if (j - zombie.getSpeed ( ) <= 0) {
                                        zombie.setY (0);
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (order.compareToIgnoreCase ("End turn") == 0) {
                turn++;
                //choosing zombie having ladder or duck
                System.out.println ("if you want a zombie to have a duck or ladder enter 1");
                int choice = scanner.nextInt ( );
                if (choice == 1) {
                    System.out.println ("choose a zombie");
                    Zombie chosenZombie = null; // it should be chosen in the game
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
        }
    }

    private void railGame() {
        ArrayList<Plant> cards = new ArrayList<> ( );
        int plantNextTurn = 0;
        int zombieNextTurn = 0;
        while (true) {
            String order = scanner.nextLine ( );
            if (order.compareToIgnoreCase ("list") == 0) {
                for (Plant plant : currentPlayer.getPlantHand ( )) {
                    System.out.println (plant.getName ( ));
                }
            } else if (order.compareToIgnoreCase ("select") == 0) {
                int n = scanner.nextInt ( );
                if (turn == 0) {
                    randomCard (cards);
                    int randomNum = (int) Math.random ( );
                    randomNum = randomNum % 3 + 2;
                    plantNextTurn = turn + randomNum;
                } else if (turn == plantNextTurn) {
                    randomCard (cards);
                }
                currentPlayer.setSelectedPlant (cards.get (n));
                currentPlayer.getPlantHand ( ).remove (cards.get (n));
            } else if (order.compareToIgnoreCase ("record") == 0) {
                System.out.println ("number of killed zombies:" + currentPlayer.getNumberOfKilledZombies ( ));
            } else if (order.compareToIgnoreCase ("plant") == 0) {
                currentPlayer.getSelectedPlant ( ).setY (scanner.nextInt ( ));
                currentPlayer.getSelectedPlant ( ).setX (scanner.nextInt ( ));
            } else if (order.compareToIgnoreCase ("remove") == 0) {
                int a, b;
                a = scanner.nextInt ( );
                b = scanner.nextInt ( );
                playGround.getCells ( )[b][a].getPlantContent ( ).clear ( );
            } else if (order.compareToIgnoreCase ("end turn") == 0) {
                turn++;
                attackCheck ( );
                if(zombieMove(playGround)){
                    currentPlayer.setScore (currentPlayer.getNumberOfKilledZombies ( ));
                    System.out.println ("game finished");
                    Menu.mainMenu ( );
                    break;
                }
                if (turn == 1) {
                    randomZombieSet(playGround);
                    int randomNum = (int) Math.random ( );
                    randomNum = randomNum % 3 + 3;
                    zombieNextTurn = turn + randomNum;
                } else if (turn == zombieNextTurn) {
                    randomZombieSet (playGround);
                }
            } else if (order.compareToIgnoreCase ("show lawn") == 0) {
                showLawn (playGround);
            }
        }
    }

    private void attackCheck() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells ( )[i][j].getZombieContent() != null) {
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
                    Plant plant = Menu.getPlantByName (name);
                    plant.setStartedTurn (turn);
                    if (firstPlayer.getNumberOfSuns ()>= plant.getNumberOfSuns ( ) && turn - plant.getStartedTurn ( ) >= plant.getRest ( )) {
                        currentPlayer.setSelectedPlant (Menu.getPlantByName (name));
                    }
                } else if (order.compareToIgnoreCase ("plant") == 0) {
                    currentPlayer.getSelectedPlant ( ).setY (scanner.nextInt ( ));
                    currentPlayer.getSelectedPlant ( ).setX (scanner.nextInt ( ));
                } else if (order.compareToIgnoreCase ("remove") == 0) {
                    int a, b;
                    a = scanner.nextInt ( );
                    b = scanner.nextInt ( );
                    playGround.getCells ( )[b][a].getPlantContent ( ).clear ( );
                } else if (order.compareToIgnoreCase ("ready") == 0) {

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
                } else if (order.compareToIgnoreCase ("start") == 0) {

                } else if (order.compareToIgnoreCase (("end turn")) == 0) {
                    turn++;
                    firstPlayer.setNumberOfSuns (firstPlayer.getNumberOfSuns () + 1);
                    if (checkWinnerForSingleWave (playGround)){
                        break;
                    }
                } else if (order.compareToIgnoreCase ("put") == 0) {
                    String name = scanner.nextLine ( );
                    int num = scanner.nextInt ( );
                    int rowNum = scanner.nextInt ( );
                    secondPlayer.setInsideGameCoins (50);
                    Zombie zombie = Menu.getZombieByName (name);
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
        if (isFinished == 1){
            Menu.mainMenu();
        }
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


    public boolean checkWinnerForZombie(PlayGround playGround) {

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells ( )[i][j].getZombieContent ( ) != null) {
                    for (int k = 0; k < 6; k++) {
                        for (int l = 0; l < 19; l++) {
                            if (playGround.getCells ( )[k][l].getPlantContent ( ) != null)
                                return false;
                        }
                    }
                    //it means there's no plants left
                    return true;
                }

            }

        //it means there's no zombie left
        return true;
    }

    public void randomCard(ArrayList<Plant> cards) {
        String[] plantLibrary = {"Cabbage-pult", "Cactus", "Cattail", "CherryBomb", "Explode-o-nut", "GatlingPea", "Jalapeno", "Kernel-pult", "LilyPad", "Magnet-Shroom", "Melon-pult", "PeaShooter", "PotatoMine", "Repeater", "Scaredy-shroom", "SnowPea", "SplitPea", "Tall-nut", "TangleKelp", "Threepeater", "Wall-nut", "WinterMelon"};
        if (cards.size ( ) < 11) {
            int randomNum = (int) Math.random ( ) % 22;
            currentPlayer.getPlantHand ( ).add (Menu.getPlantByName (plantLibrary[randomNum]));
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
        String[] zombieLibrary = {"BalloonZombie", "Zombie", "FootballZombie", "BucketheadZombie", "ConeheadZombie", "Zomboni", "CatapultZombie", "BungeeZombie", "BalloonZombie", "NewspaperZombie", "TargetZombie", "ScreenDoorZombie", "Giga-gargantuar", "PogoZombie"};
        int randomNum = (int) Math.random ( ) % 13;
        int randomX = (int) Math.random ( ) % 6;
        Zombie zombie = Menu.getZombieByName (zombieLibrary[randomNum]);
        zombie.setX (randomX);
        zombie.setY (18);
        currentPlayer.getZombieHand ( ).add (zombie);
    }

    public void randomZombieSetForDay(PlayGround playGround) {
        SecureRandom secureRandom = new SecureRandom ( );
        String[] zombieLibrary = {"BalloonZombie", "Zombie", "FootballZombie", "BucketheadZombie", "ConeheadZombie", "Zomboni", "CatapultZombie", "BungeeZombie", "BalloonZombie", "NewspaperZombie", "TargetZombie", "ScreenDoorZombie", "Giga-gargantuar", "PogoZombie"};
        int numberOfZombies = secureRandom.nextInt (7) + 4;
        for (int i = 0; i < numberOfZombies; i++) {
            int zombieNum = secureRandom.nextInt ( );
            int randomX = secureRandom.nextInt ( );
            Zombie zombie = Shop.makeNewZombieByName (zombieLibrary[zombieNum]);
            zombie.setX (randomX);
            zombie.setY (18);
            currentPlayer.getZombieHand ( ).add (zombie);
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
                for (int j = 0; i < 19; j++) {
                    playGround.getSingleCell (i, j).setCellKind (CellKind.LAND);
                }

            }
        } else
            for (int i = 2; i < 4; i++) {
                for (int j = 0; i < 19; j++) {
                    playGround.getSingleCell (i, j).setCellKind (CellKind.WATER);
                }

            }

    }
    private boolean zombieMove(PlayGround playGround){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 19; j++) {
                if (playGround.getCells()[i][j].getZombieContent() != null) {
                    for (Zombie zombie : playGround.getCells()[i][j].getZombieContent()) {
                        for (int k=0;k<zombie.getSpeed();k++){
                            if (playGround.getCells()[i][j].getPlantContent().size() != 0){
                                zombie.attack(playGround);
                            }
                            if (zombie != null){
                                zombie.setY(zombie.getY() - 1);
                            }
                            if (zombie.getY() < 0){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
