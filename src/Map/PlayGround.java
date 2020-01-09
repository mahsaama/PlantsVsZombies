package Map;


public class PlayGround {
    Cell[][] cells = new Cell[6][19];
    boolean[] mowers = new boolean[6];

    public PlayGround() {
        for(int i = 0; i < 6; i++){
            mowers[i] = true;
        }
        for(int i = 0 ; i < 6; i++)
            for(int j = 0 ; j < 19; j++)
                cells[i][j] = new Cell();
    }


    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void setSingleCell(int i , int j){
        cells[i][j] = new Cell ();
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getSingleCell(int i , int j){
        return cells[i][j];
    }
}
