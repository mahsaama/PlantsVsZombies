package User;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class Player {
    private String username;
    private ArrayList<Plant> plantHand = new ArrayList<> ();
    private ArrayList<Zombie> zombieHand = new ArrayList<> ();
    private int insideGameCoins = 0;

    public ArrayList<Plant> getPlantHand() {
        return plantHand;
    }

    public ArrayList<Zombie> getZombieHand() {
        return zombieHand;
    }
}
