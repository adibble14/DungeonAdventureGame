import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

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
        super(new ImageIcon("DungeonAndMonsters/potion.png"));
        this.myAffectAmount = 25;
    }

    /**
     * Heals Hero, increases health amount by this potions amount.
     * @param theObject
     */
    @Override
    public void use(Object theObject) {
        Hero player = (Hero) theObject;
        player.setHealth(player.getHealth() + this.myAffectAmount);
    }
}
