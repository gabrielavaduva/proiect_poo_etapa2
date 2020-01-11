package land;

import common.Constants;

public final class Cell {
    private int x;
    private int y;
    private String typeOfLand;
    private int idPlayer1;
    private int idPlayer2;
    private boolean havingABattle;

    public Cell(final int x, final int y, final String typeOfLand) {
        this.x = x;
        this.y = y;
        this.idPlayer1 = Constants.NO_PLAYER;
        this.idPlayer2 = Constants.NO_PLAYER;
        this.typeOfLand = typeOfLand;
        this.havingABattle = false;
    }

    public void clear() {
        this.setIdPlayer1(Constants.NO_PLAYER);
        this.setIdPlayer2(Constants.NO_PLAYER);
        this.havingABattle = false;
    }

    public boolean isHavingABattle() {
        return havingABattle;
    }

    void setHavingABattle(final boolean havingABattle) {
        this.havingABattle = havingABattle;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public String getTypeOfLand() {
        return typeOfLand;
    }

    public void setTypeOfLand(final String typeOfLand) {
        this.typeOfLand = typeOfLand;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    void setIdPlayer1(final int idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    void setIdPlayer2(final int idPLayer2) {
        this.idPlayer2 = idPLayer2;
    }
}
