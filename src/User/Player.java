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
    private int numberOfSuns = 0;


    public Player(User user){
        super(user.getUsername (),user.getPassword ());
        this.setUsername (user.getUsername ());
        this.setPassword (user.getPassword ());
        this.setCoins (user.getCoins ());
        this.setNumberOfPlantsKilledOverAll (user.getNumberOfPlantsKilledOverAll ());
        this.setNumberOfZombiesKilledOverAll (user.getNumberOfZombiesKilledOverAll ());
        this.setCollection (user.getCollection ());
        this.setPlantHand (user.getPlantHand ());
        this.setZombieHand (user.getZombieHand ());
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

    public int getNumberOfSuns() {
        return numberOfSuns;
    }

    public void setNumberOfSuns(int numberOfSuns) {
        this.numberOfSuns = numberOfSuns;
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
        return this.selectedPlant;
    }

    public void setSelectedPlant(Plant selectedPlant) {
        this.selectedPlant = selectedPlant;
    }
}
