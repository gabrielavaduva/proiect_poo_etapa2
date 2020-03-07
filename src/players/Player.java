package players;

import abilities.AbilityVisitor;
import common.Constants;
import land.Land;

import static java.lang.Integer.max;

public abstract class Player {
    private int id;
    private int xPos, yPos;
    private String currentLand;
    private String name;

    private boolean paralysed;
    private int noOfRoundsParalised;
    private int paralysedDamage;

    private boolean ignited;
    private int noOfRoundsIgnited;
    private int ignitedDamage;

    private boolean slammed;
    private int noSlams;

    private boolean dead;
    private int hp;
    private int xp;
    private int level;

    private int hpBonusLevel;
    private int specificHp;



    Player(final int id, final int xPos, final int yPos, final String currentLand) {
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentLand = currentLand;
        this.dead = false;
        this.level = Constants.INITIAL_LEVEL;
        this.xp = Constants.INITIAL_XP;
        this.paralysed = false;
        this.slammed = false;
        this.noOfRoundsIgnited = 0;
        this.noOfRoundsParalised = 0;
        this.noSlams = 0;
    }


    public abstract void acceptFireblast(AbilityVisitor a, Pyromancer p);
    public abstract void acceptIgnite(AbilityVisitor a, Pyromancer p);
    public abstract void acceptExecute(AbilityVisitor a, Knight k);
    public abstract void acceptSlam(AbilityVisitor a, Knight k);
    public abstract void acceptDrain(AbilityVisitor a, Wizard w);
    public abstract void acceptDeflect(AbilityVisitor a, Wizard w);
    public abstract void acceptBackstab(AbilityVisitor a, Rogue r);
    public abstract void acceptParalysis(AbilityVisitor a, Rogue r);

    public abstract void fight(AbilityVisitor a, Player enemy);

    /**
     * This is a method that takes the damage from overtime abilities.
     */
    public void takeOvertimeDamage() {
        if (this.isIgnited()) {
            this.setHp(this.getHp() - this.getIgnitedDamage());
            this.setNoOfRoundsIgnited(this.getNoOfRoundsIgnited() - 1);
            if (this.getNoOfRoundsIgnited() == 0) {
                this.setIgnited(false);
            }
        } else if (this.isParalysed()) {
            this.setHp(this.getHp() - this.getParalysedDamage());
        }
    }

    /**
     * This method updates the imobility parameters.
     */
    private void updateIncapacity() {
        if (this.getNoOfRoundsParalised() == 1) {
            this.setNoOfRoundsParalised(0);
            this.setParalysed(false);
        } else if (this.getNoOfRoundsParalised() > 1) {
            this.setNoOfRoundsParalised(this.getNoOfRoundsParalised() - 1);
        }

        if (this.getNoSlams() == 1) {
            this.setNoSlams(0);
            this.setSlammed(false);
        }
    }

    /**
     * This updates the land on which the player is.
     */
    private void updateLand(final Land l) {
        int x = this.getxPos();
        int y = this.getyPos();
//        System.out.println(x + " " + y);
        String land = l.getCells().get(x).get(y).getTypeOfLand();
        this.setCurrentLand(land);
    }

    /**
     * This method updates the x and y coordinates.
     */
    public void move(final char move, final Land l) {
        if (!isSlammed() && !isParalysed()) {
            if (move == 'U') {
                this.setxPos(this.getxPos() - 1);
            } else if (move == 'D') {
                this.setxPos(this.getxPos() + 1);
            } else if (move == 'R') {
                this.setyPos(this.getyPos() + 1);
            } else if (move == 'L') {
                this.setyPos(this.getyPos() - 1);
            }
            this.updateLand(l);
        } else {
            updateIncapacity();
        }
    }

    /**
     * This resets hp after leveling up.
     */
    private void resetHp() {
        this.hp = this.specificHp + this.getLevel() * this.hpBonusLevel;
    }

    /**
     * This calculates the number of points of experience.
     */
    public void calculateExperience(final Player p) {
        int initXp = this.xp;
        int xpNow = initXp + max(0, Constants.XP_MAX - (this.getLevel()
                - p.getLevel()) * Constants.XP_BONUS);
        this.setXp(xpNow);
        updateLevel();
    }

    /**
     * This updates the level.
     */
    private void updateLevel() {
        int initLevel = this.getLevel();
        if (this.getXp() >= Constants.LEVEL_MIN) {
            int lev = (this.getXp() - Constants.LEVEL_MIN) / Constants.LEVEL_UP + 1;
            this.setLevel(lev);
        }
        if (initLevel < this.getLevel()) {
            this.resetHp();
        }
//        System.out.println("level: " + this.getLevel());
    }

    /**
     * This clears the overtime parameters.
     * It is called by every ovetime ability.
     */
    public void clearOverTimeStats() {
        this.setNoSlams(0);
        this.setSlammed(false);
        this.setNoOfRoundsParalised(0);
        this.setParalysed(false);
        this.setNoOfRoundsIgnited(0);
        this.setIgnited(false);
    }

    /**
     * This verifies if the player is alive.
     */
    public void verifyIfStillAlive() {
        if (this.getHp() <= 0) {
            this.setDead(true);
        }
    }

    /**
     * This prints the players stats.
     */
    public void printPlayer() {
        if (this.isDead()) {
            System.out.println(this.getName() + " dead");
        } else {
            System.out.println(this.getName() + " " + this.getLevel() + " "
                    + this.getXp() + " " + this.getHp() + " " + this.getxPos() + " "
                    + this.getyPos());
        }
    }

    // setters and getter
    public final int getId() {
        return id;
    }

    public final int getxPos() {
        return xPos;
    }

    private void setxPos(final int xPos) {
        this.xPos = xPos;
    }

    public final int getyPos() {
        return yPos;
    }

    private void setyPos(final int yPos) {
        this.yPos = yPos;
    }

    private boolean isIgnited() {
        return ignited;
    }

    public final void setIgnited(final boolean ignited) {
        this.ignited = ignited;
    }


    public final String getCurrentLand() {
        return currentLand;
    }

    private void setCurrentLand(final String currentLand) {
        this.currentLand = currentLand;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final int getXp() {
        return xp;
    }

    private void setXp(final int xp) {
        this.xp = xp;
    }

    public final int getLevel() {
        return level;
    }

    private void setLevel(final int level) {
        this.level = level;
    }

    public final int getHpBonusLevel() {
        return hpBonusLevel;
    }

    final void setHpBonusLevel(final int hpBonusLevel) {
        this.hpBonusLevel = hpBonusLevel;
    }

    public final boolean isDead() {
        return dead;
    }

    public final void setDead(final boolean dead) {
        this.dead = dead;
    }

    public final int getSpecificHp() {
        return specificHp;
    }

    final void setSpecificHp(final int specificHp) {
        this.specificHp = specificHp;
    }

    private boolean isParalysed() {
        return paralysed;
    }

    public final void setParalysed(final boolean paralysed) {
        this.paralysed = paralysed;
    }

    private int getNoOfRoundsParalised() {
        return noOfRoundsParalised;
    }

    public final void setNoOfRoundsParalised(final int noOfRoundsParalised) {
        this.noOfRoundsParalised = noOfRoundsParalised;
    }

    private boolean isSlammed() {
        return slammed;
    }

    public final void setSlammed(final boolean slammed) {
        this.slammed = slammed;
    }

    private int getNoSlams() {
        return noSlams;
    }

    public final void setNoSlams(final int noSlams) {
        this.noSlams = noSlams;
    }

    private int getNoOfRoundsIgnited() {
        return noOfRoundsIgnited;
    }

    public final void setNoOfRoundsIgnited(final int noOfRoundsIgnited) {
        this.noOfRoundsIgnited = noOfRoundsIgnited;
    }

    public final String getName() {
        return name;
    }

    final void setName(final String name) {
        this.name = name;
    }

    private int getIgnitedDamage() {
        return ignitedDamage;
    }

    public final void setIgnitedDamage(final int ignitedDamage) {
        this.ignitedDamage = ignitedDamage;
    }

    private int getParalysedDamage() {
        return paralysedDamage;
    }

    public final void setParalysedDamage(final int paralysedDamage) {
        this.paralysedDamage = paralysedDamage;
    }
}
