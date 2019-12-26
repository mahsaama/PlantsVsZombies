package User;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class Collection {
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Plant> plants = new ArrayList<>();


    public ArrayList<Zombie> getZombies(){ return zombies;}
    public void setZombies(ArrayList<Zombie> zombies){

    }
    public ArrayList<Plant> getPlants(){ return plants;}
    public void setPlants(ArrayList<Plant> plants){

    }
}
