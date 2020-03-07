package players;

public class PlayerFactory {
    private static PlayerFactory instance;

    private PlayerFactory() {}

    /**
     * Singleton, because we need only one Factory
     * @return
     */
    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    public Player createPlayer(String playerName, int id, int l, int c, String land) {
        switch (playerName) {
            case "W":
                return new Wizard(id, l, c, land);
            case "R":
                return new Rogue(id, l, c, land);
            case "P":
                return new Pyromancer(id, l, c, land);
            default:
                return new Knight(id, l, c, land);
        }
    }
}
