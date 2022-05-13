import javax.swing.*;
import java.io.FileNotFoundException;


/**
 * Abstract Class represents an in game item.
 *
 */

public abstract class Item {

    /**
     * Represents the chance of spawning this item.
     *
     */
    private  double mySpawnChance;

    /**
     * Image of this item
     */
    private ImageIcon myImage;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theSpawnChance
     * @param theImage
     */
    protected Item(final double theSpawnChance, final ImageIcon theImage) {

        this.myImage = theImage;
        this.mySpawnChance = theSpawnChance;
    }


    /**
     * Getters for each field.
     * @return
     */
    final protected double getMySpawnChance() {
        return this.mySpawnChance;
    }

    final protected ImageIcon getMyImage() {
        return this.myImage;
    }

    /**
     * Uses this object, each child is used differently.
     * @param obj
     */
    public abstract void use(Object theObj);
}
