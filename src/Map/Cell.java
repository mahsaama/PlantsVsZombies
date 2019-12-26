package Map;

import Creature.Plant;
import Creature.Zombie;

import java.util.ArrayList;

public class Cell {
    ArrayList<Zombie> zombieContent = new ArrayList<>();
    ArrayList<Plant> plantContent = new ArrayList<>();
    CellKind cellKind;

    public ArrayList<Zombie> getZombieContent() {
        return zombieContent;
    }
}
