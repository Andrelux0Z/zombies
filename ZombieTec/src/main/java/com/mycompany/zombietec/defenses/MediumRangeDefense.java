package com.mycompany.zombietec.defenses;

/**
 * Medium range weapon (e.g., AK47) that returns fixed damage per attack call.
 */
public class MediumRangeDefense extends DefenseComponent {
    private int damagePerAttack;

    public MediumRangeDefense(String name, String image1, String image2, int life,
            double attacksPerSecond, int level, int fieldsOccupied, int appearanceLevel, int range, int damagePerAttack) {
        super(name, image1, image2, life, attacksPerSecond, level, fieldsOccupied, appearanceLevel, range);
        this.damagePerAttack = damagePerAttack;
    }

    @Override
    public int attack() {
        if (isDestroyed()) return 0;
        return damagePerAttack;
    }
}
