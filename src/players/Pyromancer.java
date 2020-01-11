package players;

import abilities.AbilityVisitor;
import common.Constants;

public class Pyromancer extends Player {
    public Pyromancer(final int id, final int x, final int y, final String currentLand) {
        super(id, x, y, currentLand);
        this.setHp(Constants.PY_HP);
        this.setName("P");

        this.setHp(Constants.PY_HP);
        this.setHpBonusLevel(Constants.PY_HP_LEVEL_BONUS);
        this.setSpecificHp(Constants.PY_HP);
    }

    /**
     * <p>This is the accept method for the fireblast ability. . .
     * @param a the visitor
     * @param p is neccesary for determining the damage
     */
    public void acceptFireblast(final AbilityVisitor a, final Pyromancer p) {
        a.fireblast(this, p);
    }

    /**
     * <p>This is the accept method for the ignite ability. . .
     * @param a the visitor
     * @param p is neccesary for determining the damage
     */
    @Override
    public void acceptIgnite(final AbilityVisitor a, final Pyromancer p) {
        a.ignite(this, p);
    }

    /**
     * <p>This is the accept method for the execute ability. . .
     * @param a the visitor
     * @param k is neccesary for determining the damage
     */
    @Override
    public void acceptExecute(final AbilityVisitor a, final Knight k) {
        a.execute(this, k);
    }

    /**
     * <p>This is the accept method for the slam ability. . .
     * @param a the visitor
     * @param k is neccesary for determining the damage
     */
    @Override
    public void acceptSlam(final AbilityVisitor a, final Knight k) {
        a.slam(this, k);
    }

    /**
     * <p>This is the accept method for the drain ability. . .
     * @param a the visitor
     * @param w is neccesary for determining the damage
     */
    @Override
    public void acceptDrain(final AbilityVisitor a, final Wizard w) {
        a.drain(this, w);
    }

    /**
     * <p>This is the accept method for deflect ability. . .
     * @param a the visitor
     * @param w is neccesary for determining the damage
     */
    @Override
    public void acceptDeflect(final AbilityVisitor a, final Wizard w) {
        a.deflect(this, w);
    }

    /**
     * <p>This is the accept method for the backstab ability. . .
     * @param a the visitor
     * @param r is neccesary for determining the damage
     */
    @Override
    public void acceptBackstab(final AbilityVisitor a, final Rogue r) {
        a.backstab(this, r);
    }

    /**
     * <p>This is the accept method for the paralysis ability. . .
     * @param a the visitor
     * @param r is neccesary for determining the damage
     */
    @Override
    public void acceptParalysis(final AbilityVisitor a, final Rogue r) {
        a.paralysis(this, r);
    }

    /**
     * <p>This is calling the accept method for the damage.
     * @param a the visitor
     * @param enemy that is taking the damage
     */
    @Override
    public void fight(final AbilityVisitor a, final Player enemy) {
        enemy.acceptFireblast(a,  this);
        enemy.acceptIgnite(a, this);
    }
}
