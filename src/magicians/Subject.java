package magicians;

import players.Player;

public interface Subject {

    public void register (Observer m);
    public void unregister(Observer m);
    public void notifyObserver(Player enemy);

}
