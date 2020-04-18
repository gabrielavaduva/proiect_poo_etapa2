package main;

import players.*;
import abilities.Abilities;
import abilities.AbilityVisitor;
import land.Cell;
import land.Land;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private Main() {
        // just to trick checkstyle
    }
    public static void main(String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        int n, m;
        n = gameInput.getnDimension();
        m = gameInput.getmDimension();
        Land map = Land.getInstance();
        map.setDimensions(n, m);

        PlayerFactory playerFactory = PlayerFactory.getInstance();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = gameInput.getLandOrder().get(i).charAt(j);
                if (c == 'L') {
                    map.getCells().get(i).add(new Cell(i, j, "LAND"));
                } else if (c == 'V') {
                    map.getCells().get(i).add(new Cell(i, j, "VOLCANIC"));
                } else if (c == 'D') {
                    map.getCells().get(i).add(new Cell(i, j, "DESERT"));
                } else {
                    map.getCells().get(i).add(new Cell(i, j, "WOODS"));
                }
            }
        }

        int noPlayers = gameInput.getNoPlayers();
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < noPlayers; i++) {
            String s = gameInput.getPlayersOrder().get(i);
            int l = gameInput.getxPositions().get(i);
            int c = gameInput.getyPositions().get(i);
            String land = map.getCells().get(l).get(l).getTypeOfLand();
            players.add(playerFactory.createPlayer(s, i, l, c, land));
        }

        int rounds = gameInput.getRounds();
        AbilityVisitor a = new Abilities();
        List<String> moves = gameInput.getMovesOrder();
        for (int i = 0; i < rounds; i++) {
            for (int k = 0; k < noPlayers; k++) {
                players.get(k).takeOvertimeDamage();
                players.get(k).verifyIfStillAlive();
            }

            for (int j = 0; j < noPlayers; j++) {
                char move = moves.get(i).charAt(j);
                if (!players.get(j).isDead()) {
                    players.get(j).move(move, map);
                    map.putPlayer(players.get(j));
                }
            }

            for (int it = 0; it < noPlayers; it++) {
                int x = players.get(it).getxPos();
                int y = players.get(it).getyPos();
                if (map.getCells().get(x).get(y).isHavingABattle()) {
                    int id1 = map.getCells().get(x).get(y).getIdPlayer1();
                    int id2 = map.getCells().get(x).get(y).getIdPlayer2();

                    players.get(id1).fight(a, players.get(id2));
                    players.get(id2).verifyIfStillAlive();

                    players.get(id2).fight(a, players.get(id1));
                    players.get(id1).verifyIfStillAlive();

                    int winner = -1;
                    int loser = -1;
                    boolean somebodyDied = false;
                    if (players.get(id1).isDead()) {
                        winner = id2;
                        loser = id1;
                        somebodyDied = true;
                    } else if (players.get(id2).isDead()) {
                        winner = id1;
                        loser = id2;
                        somebodyDied = true;
                    }

                    players.get(loser).notifyObserver(players.get(winner));

                    if (players.get(id1).isDead() && players.get(id2).isDead()) {
                        players.get(id1).calculateExperience(players.get(id2));
                        players.get(id2).calculateExperience(players.get(id1));
                    } else if (somebodyDied) {
                        players.get(winner).calculateExperience(players.get(loser));
                    }
                    map.getCells().get(x).get(y).clear();
                }

            }
            map.clear();
        }

        gameInputLoader.print(players);
    }
}
