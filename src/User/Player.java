package User;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class Player extends User {
    private int insideGameCoins = 0;
    private int numberOfWavesWon = 0 ;
    private String typeOfPlayer ;
    private int numberOfKilledZombies;
    private int numberOfKilledPlants;
    private Zombie selectedZombie;
    private Plant selectedPlant;
    private int score;


    public Player(String username , String password,String typeOfPlayer){
        super(username,password);
        this.typeOfPlayer = typeOfPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfWavesWon() {
        return numberOfWavesWon;
    }

    public void setNumberOfWavesWon(int numberOfWavesWon) {
        this.numberOfWavesWon = numberOfWavesWon;
    }

    public String getTypeOfPlayer() {
        return typeOfPlayer;
    }

    public void setTypeOfPlayer(String typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
    }


    public int getNumberOfKilledZombies() {
        return numberOfKilledZombies;
    }

    public void setNumberOfKilledZombies(int numberOfKilledZombies) {
        this.numberOfKilledZombies = numberOfKilledZombies;
    }

    public int getNumberOfKilledPlants() {
        return numberOfKilledPlants;
    }

    public void setNumberOfKilledPlants(int numberOfKilledPlants) {
        this.numberOfKilledPlants = numberOfKilledPlants;
    }

    public int getInsideGameCoins() {
        return insideGameCoins;
    }

    public void setInsideGameCoins(int insideGameCoins) {
        this.insideGameCoins = insideGameCoins;
    }

    public Zombie getSelectedZombie() {
        return selectedZombie;
    }

    public void setSelectedZombie(Zombie selectedZombie) {
        this.selectedZombie = selectedZombie;
    }

    public Plant getSelectedPlant() {
        return selectedPlant;
    }

    public void setSelectedPlant(Plant selectedPlant) {
        this.selectedPlant = selectedPlant;
    }
}
