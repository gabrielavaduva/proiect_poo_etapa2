package main;

import java.util.List;

public final class GameInput {
    private final List<String> playersOrder;
    private final List<String> landOrder;
    private final List<String> movesOrder;
    private final List<Integer> xPositions;
    private final List<Integer> yPositions;
    private int rounds;
    private int mDimension;
    private int nDimension;
    private int noPlayers;


    public GameInput(final List<String> playersOrder, final List<String> landOrder,
                     final List<String> movesOrder, final List<Integer> xPositions,
                     final List<Integer> yPositions, final int rounds, final int nDimension,
                     final int mDimension, final int noPlayers) {
        this.playersOrder = playersOrder;
        this.movesOrder = movesOrder;
        this.landOrder = landOrder;
        this.rounds = rounds;
        this.nDimension = nDimension;
        this.mDimension = mDimension;
        this.noPlayers = noPlayers;
        this.xPositions = xPositions;
        this.yPositions = yPositions;
    }

    List<String> getPlayersOrder() {
        return playersOrder;
    }

    List<String> getLandOrder() {
        return landOrder;
    }

    List<String> getMovesOrder() {
        return movesOrder;
    }

    int getRounds() {
        return rounds;
    }

    int getmDimension() {
        return mDimension;
    }

    int getnDimension() {
        return nDimension;
    }

    int getNoPlayers() {
        return noPlayers;
    }

    List<Integer> getxPositions() {
        return xPositions;
    }

    List<Integer> getyPositions() {
        return yPositions;
    }

    public boolean isValidInput() {
        boolean membersInstantiated = playersOrder != null
                && movesOrder != null && landOrder != null;
        boolean membersNotEmpty = movesOrder.size() > 0
                && playersOrder.size() > 0 && landOrder.size() > 0 && rounds > 0;

        return membersInstantiated && membersNotEmpty;
    }
}
