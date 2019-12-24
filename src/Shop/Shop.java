package Shop;

import Creature.Plant;
import Creature.Zombie;
import Game.Player;

import java.util.ArrayList;

public class Shop {
    ArrayList<Zombie> ZombieList = new ArrayList<>();
    ArrayList<Plant> PlantList = new ArrayList<>();
    public void showShop(){

    }
    public void showCollection(Player player){

    }
    public void buy(String name, Player player){

    }
    public void money(Player player){}

    public ArrayList<Zombie> getZombieList() {
        return ZombieList;
    }
    public void setZombieList(ArrayList<Zombie> zombies){}

    public ArrayList<Plant> getPlantList() {
        return PlantList;
    }
    public void setPlantList(ArrayList<Plant> plants){}
}
