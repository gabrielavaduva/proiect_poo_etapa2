package abilities;

import players.*;

import static common.Constants.*;
import static java.lang.Float.min;

public class Abilities implements AbilityVisitor {
    private float generalBackstab(final Rogue rr) {
        float damage = BS_DMG + rr.getLevel() * BS_DMG_LEVEL;
        if (rr.getCurrentLand().equals("WOODS")) {
            damage = damage * ROGUE_WOODS_DMG;
        }
        if (rr.getNoOfBackstabs() % NO_CRITICAL_BS == 0) {
            if (rr.getCurrentLand().equals("WOODS")) {
                damage = damage * BS_ON_WOODS;
            }
            rr.resetBackStabs();
        }
        return damage;
    }

    /**
     * <p>This method implements the backstab ability. . .
     * @param knight the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void backstab(final Knight knight, final Rogue rr) {
        float damage = generalBackstab(rr);
        damage = damage * BS_RM_KNIGHT;
        int dmg = Math.round(damage);
        rr.increaseBackstabs();
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the backstab ability. . .
     * @param rogue the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void backstab(final Rogue rogue, final Rogue rr) {
        float damage = generalBackstab(rr);
        damage = damage * BS_RM_ROGUE;
        int dmg = Math.round(damage);
        rr.increaseBackstabs();
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the backstab ability. . .
     * @param pyromancer the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void backstab(final Pyromancer pyromancer, final Rogue rr) {
        float damage = generalBackstab(rr);
        damage = damage * BS_RM_PYROMANCER;
        int dmg = Math.round(damage);
        rr.increaseBackstabs();
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the backstab ability. . .
     * @param wizard the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void backstab(final Wizard wizard, final Rogue rr) {
        float damage = generalBackstab(rr);
        damage = damage * BS_RM_WIZARD;
//        System.out.println(damage);
        int dmg = Math.round(damage);
        rr.increaseBackstabs();
        wizard.setHp(wizard.getHp() - dmg);
    }

    private float generalParalysis(final Rogue rr) {
        float damage = PA_DMG + rr.getLevel() * PA_DMG_LEVEL;
        if (rr.getCurrentLand().equals("WOODS")) {
            damage = damage * ROGUE_WOODS_DMG;
        }
        return damage;
    }

    /**
     * <p>This method implements the paralysis ability. . .
     * @param knight the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void paralysis(final Knight knight, final Rogue rr) {
        knight.clearOverTimeStats();
        knight.setParalysed(true);
        float damage = generalParalysis(rr);

        if (rr.getCurrentLand().equals("WOODS")) {
            knight.setNoOfRoundsParalised(ON_WOODS_PA);
        } else {
            knight.setNoOfRoundsParalised(NOT_ON_WOODS_PA);
        }

        damage = damage * PA_RM_KNIGHT;
        int dmg = Math.round(damage);
        knight.setParalysedDamage(dmg);
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the paralysis ability. . .
     * @param rogue the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void paralysis(final Rogue rogue, final Rogue rr) {
        rogue.clearOverTimeStats();
        rogue.setParalysed(true);
        float damage = generalParalysis(rr);

        if (rr.getCurrentLand().equals("WOODS")) {
            rogue.setNoOfRoundsParalised(ON_WOODS_PA);
        } else {
            rogue.setNoOfRoundsParalised(NOT_ON_WOODS_PA);
        }

        damage = damage * PA_RM_ROGUE;
        int dmg = Math.round(damage);
        rogue.setParalysedDamage(dmg);
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the paralysis ability. . .
     * @param pyromancer the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void paralysis(final Pyromancer pyromancer, final Rogue rr) {
        pyromancer.clearOverTimeStats();
        pyromancer.setParalysed(true);
        float damage = generalParalysis(rr);

        if (rr.getCurrentLand().equals("WOODS")) {
            pyromancer.setNoOfRoundsParalised(ON_WOODS_PA);
        } else {
            pyromancer.setNoOfRoundsParalised(NOT_ON_WOODS_PA);
        }
        damage = damage * PA_RM_PYROMANCER;
        int dmg = Math.round(damage);
        pyromancer.setParalysedDamage(dmg);
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the paralysis ability. . .
     * @param wizard the visitable class
     * @param rr needed for calculating the damage
     */
    @Override
    public void paralysis(final Wizard wizard, final Rogue rr) {
        wizard.clearOverTimeStats();
        wizard.setParalysed(true);
        float damage = generalParalysis(rr);
        if (rr.getCurrentLand().equals("WOODS")) {
            wizard.setNoOfRoundsParalised(ON_WOODS_PA);
        } else {
            wizard.setNoOfRoundsParalised(NOT_ON_WOODS_PA);
        }
        damage = damage * PA_RM_WIZARD;
        int dmg = Math.round(damage);
        wizard.setParalysedDamage(dmg);
        wizard.setHp(wizard.getHp() - dmg);
    }

    /**
     * <p>This method implements the deflect ability. . .
     * @param knight the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void deflect(final Knight knight, final Wizard ww) {
        float damage = generalExecute(ww, knight);
        damage = Math.round(damage);
        damage = damage + generalSlam(knight);
        damage = Math.round(damage);

        float maxPercent = ww.getLevel() * DE_PERCENT_LEVEL;
        if (maxPercent > DE_MAX_PERCENT) {
            maxPercent = DE_MAX_PERCENT;
        }
        float percent = DE_PERCENT + maxPercent;
        damage = damage * percent * DE_RM_KNIGHT;
        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }
        int dmg = Math.round(damage);
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the deflect ability. . .
     * @param rogue the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void deflect(final Rogue rogue, final Wizard ww) {
        float damage = generalBackstab(rogue);
        if (rogue.getNoOfBackstabs() == 1 && rogue.getCurrentLand().equals("WOODS")) {
            damage = damage * BS_ON_WOODS;
        }
        damage = Math.round(damage);
        damage = damage + generalParalysis(rogue);
        damage = Math.round(damage);

        float maxPercent = ww.getLevel() * DE_PERCENT_LEVEL;
        if (maxPercent > DE_MAX_PERCENT) {
            maxPercent = DE_MAX_PERCENT;
        }
        float percent = DE_PERCENT + maxPercent;

        damage = damage * percent * DE_RM_ROGUE;

        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }
        int dmg = Math.round(damage);
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the deflect ability. . .
     * @param pyromancer the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void deflect(final Pyromancer pyromancer, final Wizard ww) {
        float damage = generalFireblast(pyromancer);
        damage = Math.round(damage);
        damage = damage + generalIgnite(pyromancer);
        damage = Math.round(damage);

        float maxPercent = ww.getLevel() * DE_PERCENT_LEVEL;
        if (maxPercent > DE_MAX_PERCENT) {
            maxPercent = DE_MAX_PERCENT;
        }
        float percent = DE_PERCENT + maxPercent;
        damage = damage * percent * DE_RM_PYROMANCER;
        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }
        int dmg = Math.round(damage);
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the deflect ability. . .
     * @param wizard the visitable class
     * @param ww needed for calculating the damage
     *  This doesn't do anything because a wizard can not delfect another wizard
     */
    @Override
    public void deflect(final Wizard wizard, final Wizard ww) {
        // nothing to be done
    }

    /**
     * <p>This method implements the drain ability. . .
     * @param knight the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void drain(final Knight knight, final Wizard ww) {
        float percent = DR_PERCENT + ww.getLevel() * DR_PERCENT_LEVEL;

        percent = percent * DR_RM_KNIGHT; //aici


        int maxHp = knight.getSpecificHp() + knight.getLevel() * knight.getHpBonusLevel();
        float damage = percent * min(DRAIN_MUL * maxHp, knight.getHp());

        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }

        int dmg = Math.round(damage);
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the drain ability. . .
     * @param rogue the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void drain(final Rogue rogue, final Wizard ww) {
        float percent = DR_PERCENT + ww.getLevel() * DR_PERCENT_LEVEL;
        percent = percent * DR_RM_ROGUE; //aici

        int maxHp = rogue.getSpecificHp() + rogue.getLevel() * rogue.getHpBonusLevel();

        float damage = percent * min(DRAIN_MUL * maxHp, rogue.getHp());

        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }

        int dmg = Math.round(damage);
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the drain ability. . .
     * @param pyromancer the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void drain(final Pyromancer pyromancer, final Wizard ww) {
        float percent = DR_PERCENT + ww.getLevel() * DR_PERCENT_LEVEL;
        percent = percent * DR_RM_PYROMANCER; //aici
        int maxHp = pyromancer.getSpecificHp() + pyromancer.getLevel()
                * pyromancer.getHpBonusLevel();
        float damage = percent * min(DRAIN_MUL * maxHp, pyromancer.getHp());
        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }
        int dmg = Math.round(damage);
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the drain ability. . .
     * @param wizard the visitable class
     * @param ww needed for calculating the damage
     */
    @Override
    public void drain(final Wizard wizard, final Wizard ww) {
        float percent = DR_PERCENT + ww.getLevel() * DR_PERCENT_LEVEL;
        percent = percent * DR_RM_WIZARD; //aici
        int maxHp = wizard.getSpecificHp() + wizard.getLevel() * wizard.getHpBonusLevel();
        float damage = percent * min(DRAIN_MUL * maxHp, wizard.getHp());
        if (ww.getCurrentLand().equals("DESERT")) {
            damage = damage * WIZARD_DESERT_DMG;
        }
        int dmg = Math.round(damage);
        wizard.setHp(wizard.getHp() - dmg);
    }

    private float generalExecute(final Player knight, final Knight k) {
        float damage = EX_DMG + k.getLevel() * EX_DMG_LEVEL;
        if (k.getCurrentLand().equals("LAND")) {
            damage = damage * KNIGHT_LAND_DMG;
        }

        int maxHp = knight.getSpecificHp() + knight.getLevel() * knight.getHpBonusLevel();
        float percentLevelHp = k.getLevel() * EX_PERCENT;
        if (percentLevelHp > EX_MAX_PERCENT) {
            percentLevelHp = EX_MAX_PERCENT;
        }
        float hpLimit = (EX_HP_LIMIT + percentLevelHp) * maxHp;
//        System.out.println(hpLimit);
        // instant kill and taking damage
//        System.out.println(knight.getHp());
        if (knight.getHp() < hpLimit && !(knight.isDead())) {
            damage = knight.getHp();
            knight.setDead(true);
//            System.out.println("instant kill");
        }

        return damage;
    }

    /**
     * <p>This method implements the execute ability. . .
     * @param knight the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void execute(final Knight knight, final Knight kk) {
        // damage basic
        float damage = generalExecute(knight, kk);
        if (!knight.isDead()) {
            damage = EX_RM_KNIGHT * damage;
        }

        int dmg = Math.round(damage);
        // hp limit calculus
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the execute ability. . .
     * @param rogue the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void execute(final Rogue rogue, final Knight kk) {
// damage basic
        float damage = generalExecute(rogue, kk);
        if (!rogue.isDead()) {
            damage = EX_RM_ROGUE * damage;
        }

        int dmg = Math.round(damage);
        // hp limit calculus
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the execute ability. . .
     * @param pyromancer the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void execute(final Pyromancer pyromancer, final Knight kk) {
        // damage basic
        float damage = generalExecute(pyromancer, kk);
        if (!pyromancer.isDead()) {
            damage = EX_RM_PYROMANCER * damage;
        }

        int dmg = Math.round(damage);
        // hp limit calculus
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the execute ability. . .
     * @param wizard the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void execute(final Wizard wizard, final Knight kk) {
        // damage basic
        float damage = generalExecute(wizard, kk);
        if (!wizard.isDead()) {
            damage = EX_RM_WIZARD * damage;
        }

        int dmg = Math.round(damage);
        // hp limit calculus
        wizard.setHp(wizard.getHp() - dmg);
    }

    private float generalSlam(final Knight k) {
        float damage = SL_DMG + k.getLevel() * SL_DMG_LEVEL;
        if (k.getCurrentLand().equals("LAND")) {
            damage = damage * KNIGHT_LAND_DMG;
        }
        return damage;
    }

    /**
     * <p>This method implements the slam ability. . .
     * @param knight the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void slam(final Knight knight, final Knight kk) {
        knight.clearOverTimeStats();
        knight.setSlammed(true);
        knight.setNoSlams(1);
        float damage = generalSlam(kk);
        damage = damage * SL_RM_KNIGHT;
        int dmg = Math.round(damage);
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the slam ability. . .
     * @param rogue the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void slam(final Rogue rogue, final Knight kk) {
        rogue.clearOverTimeStats();
        rogue.setSlammed(true);
        rogue.setNoSlams(1);
        float damage = generalSlam(kk);
        damage = damage * SL_RM_ROGUE;
        int dmg = Math.round(damage);
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the slam ability. . .
     * @param pyromancer the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void slam(final Pyromancer pyromancer, final Knight kk) {
        pyromancer.clearOverTimeStats();
        pyromancer.setSlammed(true);
        pyromancer.setNoSlams(1);
        float damage = generalSlam(kk);
        damage = damage * SL_RM_PYROMANCER;
        int dmg = Math.round(damage);
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the slam ability. . .
     * @param wizard the visitable class
     * @param kk needed for calculating the damage
     */
    @Override
    public void slam(final Wizard wizard, final Knight kk) {
        wizard.clearOverTimeStats();
        wizard.setSlammed(true);
        wizard.setNoSlams(1);
        float damage = generalSlam(kk);
        damage = damage * SL_RM_WIZARD;
        int dmg = Math.round(damage);
        wizard.setHp(wizard.getHp() - dmg);
    }

    private float generalFireblast(final Pyromancer pp) {
        float damage = FB_DMG  + pp.getLevel() * FB_DMG_LEVEL;
        if (pp.getCurrentLand().equals("VOLCANIC")) {
            damage = PYROMANCER_VOLCANIC_DMG * damage;
        }
        return damage;
    }

    /**
     * <p>This method implements the fireblast ability. . .
     * @param knight the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void fireblast(final Knight knight, final Pyromancer pp) {
        float damage = generalFireblast(pp);
        damage = damage * FB_RM_KNIGHT;
        int dmg = Math.round(damage);
        knight.setHp(knight.getHp() - dmg);
    }

    /**
     * <p>This method implements the fireblast ability. . .
     * @param rogue the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void fireblast(final Rogue rogue, final Pyromancer pp) {
        float damage = generalFireblast(pp);

        damage = damage * FB_RM_ROGUE;
        int dmg = Math.round(damage);
        rogue.setHp(rogue.getHp() - dmg);
    }

    /**
     * <p>This method implements the fireblast ability. . .
     * @param pyromancer the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void fireblast(final Pyromancer pyromancer, final Pyromancer pp) {
        float damage = generalFireblast(pp);

        damage = damage * FB_RM_PYROMANCER;
        int dmg = Math.round(damage);
        pyromancer.setHp(pyromancer.getHp() - dmg);
    }

    /**
     * <p>This method implements the fireblast ability. . .
     * @param wizard the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void fireblast(final Wizard wizard, final Pyromancer pp) {
        float damage = generalFireblast(pp);
        damage = damage * FB_RM_WIZARD;
        int dmg = Math.round(damage);
        wizard.setHp(wizard.getHp() - dmg);
    }

    private float generalIgnite(final Pyromancer pp) {
        float damage = IG_DMG + pp.getLevel() * IG_DMG_LEVEL;
        if (pp.getCurrentLand().equals("VOLCANIC")) {
            damage = PYROMANCER_VOLCANIC_DMG * damage;
        }
        return damage;
    }

    /**
     * <p>This method implements the ignite ability. . .
     * @param knight the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void ignite(final Knight knight, final Pyromancer pp) {
        knight.clearOverTimeStats();
        knight.setIgnited(true);
        knight.setNoOfRoundsIgnited(2);
        float damage = generalIgnite(pp);
        damage = IG_RM_KNIGHT * damage;
        int dmg = Math.round(damage);
        knight.setHp(knight.getHp() - dmg);

        float igDmg = (IG_OT_DMG + pp.getLevel() * IG_OT_DMG_LEVEL) * IG_RM_KNIGHT;
        if (pp.getCurrentLand().equals("VOLCANIC")) {
            igDmg = igDmg * PYROMANCER_VOLCANIC_DMG;
        }
        int ig = Math.round(igDmg);
        knight.setIgnitedDamage(ig);
    }

    /**
     * <p>This method implements the ignite ability. . .
     * @param rogue the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void ignite(final Rogue rogue, final Pyromancer pp) {
        rogue.clearOverTimeStats();
        rogue.setIgnited(true);
        rogue.setNoOfRoundsIgnited(2);
        float damage = generalIgnite(pp);
        damage = IG_RM_ROGUE * damage;
        int dmg = Math.round(damage);
        rogue.setHp(rogue.getHp() - dmg);

        float igDmg = (IG_OT_DMG + pp.getLevel() * IG_OT_DMG_LEVEL) * IG_RM_ROGUE;
        if (pp.getCurrentLand().equals("VOLCANIC")) {
            igDmg = igDmg * PYROMANCER_VOLCANIC_DMG;
        }
        int ig = Math.round(igDmg);
        rogue.setIgnitedDamage(ig);
    }

    /**
     * <p>This method implements the ignite ability. . .
     * @param pyromancer the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void ignite(final Pyromancer pyromancer, final Pyromancer pp) {
        pyromancer.clearOverTimeStats();
        pyromancer.setIgnited(true);
        pyromancer.setNoOfRoundsIgnited(2);
        float damage = generalIgnite(pp);
        damage = IG_RM_PYROMANCER * damage;
        int dmg = Math.round(damage);
        pyromancer.setHp(pyromancer.getHp() - dmg);

        float igDmg = (IG_OT_DMG + pp.getLevel() * IG_OT_DMG_LEVEL) * IG_RM_PYROMANCER;
        if (pp.getCurrentLand().equals("VOLCANIC")) {
            igDmg = igDmg * PYROMANCER_VOLCANIC_DMG;
        }
        int ig = Math.round(igDmg);
        pyromancer.setIgnitedDamage(ig);
    }

    /**
     * <p>This method implements the ignite ability. . .
     * @param wizard the visitable class
     * @param pp needed for calculating the damage
     */
    @Override
    public void ignite(final Wizard wizard, final Pyromancer pp) {
        wizard.clearOverTimeStats();
        wizard.setIgnited(true);
        wizard.setNoOfRoundsIgnited(2);
        float damage = generalIgnite(pp);
        damage = IG_RM_KNIGHT * damage;
        int dmg = Math.round(damage);
        wizard.setHp(wizard.getHp() - dmg);

        float igDmg = (IG_OT_DMG + pp.getLevel() * IG_OT_DMG_LEVEL) * IG_RM_WIZARD;
        if (pp.getCurrentLand().equals("VOLCANIC")) {
            igDmg = igDmg * PYROMANCER_VOLCANIC_DMG;
        }
        int ig = Math.round(igDmg);
        wizard.setIgnitedDamage(ig);
    }
}
