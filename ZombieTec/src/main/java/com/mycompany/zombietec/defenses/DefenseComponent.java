package com.mycompany.zombietec.defenses;

/**
 * Abstract base class for all defense components.
 */
public abstract class DefenseComponent {
    protected String name;
    protected String image1;
    protected String image2;
    protected int life; // in golpes
    protected double attacksPerSecond; // golpes por unidad de tiempo
    protected int level;
    protected int fieldsOccupied;
    protected int appearanceLevel;
    protected int range; // number of spaces (for those that use it)

    public DefenseComponent(String name, String image1, String image2, int life,
            double attacksPerSecond, int level, int fieldsOccupied, int appearanceLevel, int range) {
        this.name = name;
        this.image1 = image1;
        this.image2 = image2;
        this.life = life;
        this.attacksPerSecond = attacksPerSecond;
        this.level = level;
        this.fieldsOccupied = fieldsOccupied;
        this.appearanceLevel = appearanceLevel;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public boolean isDestroyed() {
        return life <= 0;
    }

    /**
     * Receive a number of golpes (damage).
     */
    public void receiveHits(int golpes) {
        life -= golpes;
        if (life < 0) life = 0;
    }

    /**
     * Returns the damage (golpes) produced per attack event.
     */
    public abstract int attack();

    /**
     * Level up the component: simple default increases life and attack.
     */
    public void levelUp() {
        level++;
        life += Math.max(1, level); // small increase
        // default: increase attacksPerSecond by 10%
        attacksPerSecond *= 1.1;
        // increase range slightly
        range += level % 2; // every other level increases range
    }

    @Override
    public String toString() {
        return String.format("%s (Level %d) - Life: %d, DPS: %.2f, Range: %d, Fields: %d", name, level, life, attacksPerSecond, range, fieldsOccupied);
    }
}
