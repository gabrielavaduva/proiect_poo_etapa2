package angels;

import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public interface AngelAbilities {
    public void helpKnight(Knight k);
    public void helpPyromancer(Pyromancer p);
    public void helpRogue(Rogue r);
    public void helpWizard(Wizard w);
}
