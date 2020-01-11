package main;

import players.Player;
import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class GameInputLoader {
    private final String inputPath;
    private final String outputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public GameInput load() {
        List<String> lands = new ArrayList<>();
        List<String> playersOrder = new ArrayList<>();
        List<String> movesOrder = new ArrayList<>();
        List<Integer> xPositions = new ArrayList<>();
        List<Integer> yPositions = new ArrayList<>();
        int rounds = 0;
        int mDimension = 0;
        int nDimension = 0;
        int noPlayers = 0;

        try {
            FileSystem fs = new FileSystem(inputPath, outputPath);

            nDimension = fs.nextInt();
            mDimension = fs.nextInt();

            for (int i = 0; i < nDimension; i++) {
                lands.add(fs.nextWord());
            }

            noPlayers = fs.nextInt();
            for (int i = 0; i < noPlayers; i++) {
                playersOrder.add(fs.nextWord());
                xPositions.add(fs.nextInt());
                yPositions.add(fs.nextInt());
            }

            rounds = fs.nextInt();
            for (int i = 0; i < rounds; i++) {
                movesOrder.add(fs.nextWord());
            }
            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GameInput(playersOrder, lands, movesOrder, xPositions,
                yPositions, rounds, nDimension, mDimension, noPlayers);
    }

    public void print(final List<Player> players) {
        try {
            FileSystem fs = new FileSystem(inputPath, outputPath);
            for (Player p: players) {
                fs.writeWord(p.getName());
                fs.writeWord(" ");
                if (p.isDead()) {
                    fs.writeWord("dead");
                } else {
                    fs.writeInt(p.getLevel());
                    fs.writeWord(" ");
                    fs.writeInt(p.getXp());
                    fs.writeWord(" ");
                    fs.writeInt(p.getHp());
                    fs.writeWord(" ");
                    fs.writeInt(p.getxPos());
                    fs.writeWord(" ");
                    fs.writeInt(p.getyPos());
                }
                fs.writeNewLine();
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
