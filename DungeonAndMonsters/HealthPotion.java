/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

/**
 * the health potion class, can heal the hero
 */
public class HealthPotion extends Item {

    /**
     * Represents the potency of this potion.
     * Such has healed amount,
     */
    private final int myAffectAmount;

    /**
     * Constructor for this class. Simply initializes fields.
     */
    protected HealthPotion() {
        super(new ImageIcon("DungeonAndMonsters/potion.png"));
        this.myAffectAmount = 25;
    }

    /**
     * Heals Hero, increases health amount by this potions amount.
     * @param theObject the hero
     */
    @Override
    final void use(Object theObject) {
        Hero player = (Hero) theObject;
        player.setHealth(player.getHealth() + this.myAffectAmount);
    }
}
