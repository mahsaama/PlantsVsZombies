package Map;


public class PlayGround {
    Cell[][] cells = new Cell[6][19];
    boolean[] mowers = new boolean[6];

    public PlayGround() {
        for(int i = 0; i < 6; i++){
            mowers[i] = true;
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
