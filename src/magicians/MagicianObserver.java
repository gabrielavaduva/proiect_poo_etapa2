package magicians;

import players.Player;

public class MagicianObserver implements Observer{
//    public Player player;
    private int id1;
    private int id2;
    public void update(Player player1, Player player2){
//        this.player = player;
        this.id1 = player1.getId();
        this.id2 = player2.getId();
        if (player1.isDead()) {
            System.out.println("Player " + player1.getName() + " " + id1 + "is DEAD.");
            System.out.println("Player " + player2.getName() + " " + id2 + " killed him.");
        }
    }
}
