package User;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class Player {
    private String username;
    private ArrayList<Plant> plantHand = new ArrayList<> ();
    private ArrayList<Zombie> zombieHand = new ArrayList<> ();
    private int insideGameCoins = 0;


    public Player(User user){
        this.username = user.getUsername ();
        this.setPlantHand (user.getPlantHand ());
        this.setZombieHand (user.getZombieHand ());
    }

    public ArrayList<Plant> getPlantHand() {
        return plantHand;
    }

    public ArrayList<Zombie> getZombieHand() {
        return zombieHand;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlantHand(ArrayList<Plant> plantHand) {
        this.plantHand = plantHand;
    }

    public void setZombieHand(ArrayList<Zombie> zombieHand) {
        this.zombieHand = zombieHand;
    }
}
