package abilities;

import players.*;

public interface AbilityVisitor {

    // rogue abilities
    void backstab(Knight knight, Rogue rr);

    void backstab(Rogue rogue, Rogue rr);

    void backstab(Pyromancer pyromancer, Rogue rr);

    void backstab(Wizard wizard, Rogue rr);

    void paralysis(Knight knight, Rogue rr);

    void paralysis(Rogue rogue, Rogue rr);

    void paralysis(Pyromancer pyromancer, Rogue rr);

    void paralysis(Wizard wizard, Rogue rr);


    // wizard abilities
    void deflect(Knight knight, Wizard ww);

    void deflect(Rogue rogue, Wizard ww);

    void deflect(Pyromancer pyromancer, Wizard ww);

    void deflect(Wizard wizard, Wizard ww);


    void drain(Knight knight, Wizard ww);

    void drain(Rogue rogue, Wizard ww);

    void drain(Pyromancer pyromancer, Wizard ww);

    void drain(Wizard wizard, Wizard ww);


    // knight abilities
    void execute(Knight knight, Knight kk);

    void execute(Rogue rogue, Knight kk);

    void execute(Pyromancer pyromancer, Knight kk);

    void execute(Wizard wizard, Knight kk);


    void slam(Knight knight, Knight kk);

    void slam(Rogue rogue, Knight kk);

    void slam(Pyromancer pyromancer, Knight kk);

    void slam(Wizard wizard, Knight kk);



    // pyromancer abilities
    void fireblast(Knight knight, Pyromancer pp);

    void fireblast(Rogue rogue, Pyromancer pp);

    void fireblast(Pyromancer pyromancer, Pyromancer pp);

    void fireblast(Wizard wizard, Pyromancer pp);

    void ignite(Knight knight, Pyromancer pp);

    void ignite(Rogue rogue, Pyromancer pp);

    void ignite(Pyromancer pyromancer, Pyromancer pp);

    void ignite(Wizard wizard, Pyromancer pp);
}
