package Game;

import Creature.Plant;
import Creature.Zombie;
import User.Player;
import java.util.Scanner;

public class Game {
    private String typeOfGame;
    private Player firstPlayer; //always playing plants
    private Player secondPlayer; //always playing zombies
    private int numberOfWaves = 0;
    private Player winner;
    private Scanner scanner = new Scanner (System.in);
    private Player currentPlayer = firstPlayer;//TODO
    private int turn = 0;

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


    public void setGameEnvironment(){
        if(typeOfGame.compareTo ("PvP") == 0){
            String order = scanner.nextLine ();
            while(true){
                if(checkWinnerForPvP ()){
                    System.out.println ("game finished" );
//                Main.mainMenu();

                    break;
                }
                if(order.compareToIgnoreCase ("show hand") == 0){
                    if(currentPlayer.getTypeOfPlayer ().compareToIgnoreCase ("plant") == 0){
                        for(Plant plant : currentPlayer.getPlantHand ()){
                            System.out.println ("selected plant:"+ plant.getName () + " numberOfsuns:" +
                                    plant.getNumberOfSuns () + " timeToRest: " + plant.getRest () );
                        }
                    }
                    else{
                       for(Zombie zombie : currentPlayer.getZombieHand ()){
                           System.out.println ("selected zombie:"+zombie.getName () + "life:" + zombie.getLife () );
                       }
                    }
                }
                else if(order.compareToIgnoreCase ("select") == 0){
                    String name = scanner.nextLine ();
                    if(currentPlayer.getTypeOfPlayer ().compareToIgnoreCase ("plant") == 0){
//                        Plant plant = getPlantByName(name);
//                        if(currentPlayer.)
//                        currentPlayer.setSelectedPlant (getPlantByName(name));

                    }
                }


            }


        }
    }

    public boolean checkWinnerForPvP(){
        if(firstPlayer.getNumberOfWavesWon () >= numberOfWaves / 2){
            winner = firstPlayer;
            winner.setCoins (winner.getCoins () + 10 * winner.getNumberOfKilledZombies ());
            winner.setNumberOfZombiesKilledOverAll (winner.getNumberOfZombiesKilledOverAll () + winner.getNumberOfKilledZombies ());
            return true;
        }
        else if(secondPlayer.getNumberOfWavesWon () >= numberOfWaves / 2){
            winner = secondPlayer;
            winner.setCoins (winner.getCoins () + 10 * winner.getNumberOfKilledPlants ());
            winner.setNumberOfPlantsKilledOverAll (winner.getNumberOfPlantsKilledOverAll () + winner.getNumberOfKilledPlants ());
            return true;
        }
        return false;
    }

    public boolean checkWinnerForSingleWave(){
        winner.setInsideGameCoins (winner.getInsideGameCoins () + 200);
        winner.setNumberOfWavesWon (winner.getNumberOfWavesWon () + 1);
        return false;
        //TODO
    }
}
