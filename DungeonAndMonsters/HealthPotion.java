import javax.swing.*;
import java.awt.image.BufferedImage;

public class HealthPotion extends Item {

    /**
     * Represents the potency of this potion.
     * Such has heal amount,
     */
    private int myAffectAmount;



    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected HealthPotion() {
        super(.15, new ImageIcon("DungeonAndMonsters/potion.png"));
        this.myAffectAmount = 15;
    }

    /**
     * Heals Hero, increases health amount by this potions amount.
     * @param theObject
     */
    @Override
    public void use(Object theObject) {
        if(theObject.getClass() == Hero.class) {
            Hero player = (Hero) theObject;
            player.setHealth(player.getMaxHealth() + this.myAffectAmount);
        }

    }
}
