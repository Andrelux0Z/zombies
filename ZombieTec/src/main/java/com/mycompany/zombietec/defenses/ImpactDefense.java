package com.mycompany.zombietec.defenses;

/**
 * Impact defenses like mines: attack once with an area range and destroy themselves.
 */
public class ImpactDefense extends DefenseComponent {
    private int explosionDamage;
    private boolean hasExploded;

    public ImpactDefense(String name, String image1, String image2, int life,
            int explosionDamage, int fieldsOccupied, int appearanceLevel, int range) {
        super(name, image1, image2, life, 0.0, 1, fieldsOccupied, appearanceLevel, range);
        this.explosionDamage = explosionDamage;
        this.hasExploded = false;
    }

    @Override
    public int attack() {
        if (hasExploded || isDestroyed()) return 0;
        hasExploded = true;
        // impact devices destroy themselves when they explode
        life = 0;
        return explosionDamage;
    }
}
