package land;

import players.Player;

import java.util.ArrayList;
import java.util.List;

import static common.Constants.NO_PLAYER;

public class Land {
    private int dimensionM, dimensionN;
    private List<List<Cell>> cells = new ArrayList<>();
    private static Land instance;

    private Land(){ }

    /**
     * getter for instance
     */

    public static Land getInstance(){
        if (instance == null) {
            instance = new Land();
        }
        return instance;
    }

    public void setDimensions(final int dimensionN, final int dimensionM) {
        this.dimensionM = dimensionM;
        this.dimensionN = dimensionN;
        for (int i = 0; i < dimensionN; i++) {
            cells.add(new ArrayList<>());
        }
    }


    /**
     * Puts players on the map.
     */
    public void putPlayer(final Player p) {
        int x = p.getxPos();
        int y = p.getyPos();
        if (cells.get(x).get(y).getIdPlayer1() == NO_PLAYER) {
            cells.get(x).get(y).setIdPlayer1(p.getId());
        } else {
            cells.get(x).get(y).setIdPlayer2(p.getId());
            cells.get(x).get(y).setHavingABattle(true);
        }
    }

    /**
     * Clears the map, when called after every round.
     */
    public void clear() {
        for (int i = 0; i < this.dimensionN; i++) {
            for (int j = 0; j < this.dimensionM; j++) {
                cells.get(i).get(j).setIdPlayer1(NO_PLAYER);
                cells.get(i).get(j).setIdPlayer2(NO_PLAYER);
            }
        }
    }

    /**
     * Getter for cells.
     */
    public List<List<Cell>> getCells() {
        return cells;
    }
}
