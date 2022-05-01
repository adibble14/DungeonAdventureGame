import java.awt.image.BufferedImage;

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
     * Name of this item
     */
    private  String myName;

    /**
     * Image of this item
     */
    // TODO: 4/30/22  Is this the correct object to use?
    private BufferedImage myImage;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theSpawnChance
     * @param theName
     * @param theImage
     */
    protected Item(final double theSpawnChance, final String theName, final BufferedImage theImage) {
        this.mySpawnChance = theSpawnChance;
        this.myName = theName;
        this.myImage = theImage;
    }


    /**
     * Getters for each field.
     * @return
     */

    final protected String getMyName() {
        return this.myName;
    }

    final protected double getMySpawnChance() {
        return this.mySpawnChance;
    }

    final protected BufferedImage getMyImage() {
        return this.myImage;
    }

    /**
     * Uses this object, each child is used differently.
     * @param obj
     */
    public abstract void use(Object obj);



}
