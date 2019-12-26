package User;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class User {
    private String username ;
    private String password ;
    private int coins = 0;
    private int numberOfZombiesKilledOverAll = 0;
    private Collection collection = new Collection ();
    private ArrayList<Plant> plantHand = new ArrayList<> ();
    private ArrayList<Zombie> zombieHand = new ArrayList<> ();

    public User(String username , String password){
        this.password = password;
        this.username = username;
    }

    public ArrayList<Plant> getPlantHand() {
        return plantHand;
    }

    public ArrayList<Zombie> getZombieHand() {
        return zombieHand;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberOfZombiesKilled() {
        return numberOfZombiesKilledOverAll;
    }

    public void setNumberOfZombiesKilled(int numberOfZombiesKilled) {
        this.numberOfZombiesKilledOverAll = numberOfZombiesKilled;
    }
    public int getCoins(){ return coins;}
    public void setCoins(int amount){this.coins += amount;}

    public boolean checkHandPlant(String name){
        for(Plant plant : plantHand){
            if(plant.getName ().compareTo (name) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean checkHandZombie(String name){
        for(Zombie zombie : zombieHand){
            if(zombie.getName ().compareTo (name) == 0){
                return true;
            }
        }
        return false;
    }
}
