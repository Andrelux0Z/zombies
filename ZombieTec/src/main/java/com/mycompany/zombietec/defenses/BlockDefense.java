package com.mycompany.zombietec.defenses;

/**
 * Block defenses that don't attack but occupy fields and absorb damage.
 */
public class BlockDefense extends DefenseComponent {

    public BlockDefense(String name, String image1, String image2, int life,
            int fieldsOccupied, int appearanceLevel) {
        super(name, image1, image2, life, 0.0, 1, fieldsOccupied, appearanceLevel, 0);
    }

    @Override
    public int attack() {
        return 0; // does not attack
    }
}
