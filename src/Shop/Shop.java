package Shop;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class Shop {
    ArrayList<Zombie> ZombieList = new ArrayList<>();
    ArrayList<Plant> PlantList = new ArrayList<>();


    public ArrayList<Zombie> getZombieList() {
        return ZombieList;
    }
    public void setZombieList(ArrayList<Zombie> zombies){}

    public ArrayList<Plant> getPlantList() {
        return PlantList;
    }
    public void setPlantList(ArrayList<Plant> plants){}
}
