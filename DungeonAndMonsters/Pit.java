import java.awt.image.BufferedImage;

public class Pit extends Item{
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theSpawnChance
     * @param theName
     * @param theImage
     */
    protected Pit(double theSpawnChance, String theName, BufferedImage theImage) {
        super(theSpawnChance, theName, theImage);
    }

    /**
     * Damages Hero.
     *
     * @param obj
     */
    //TODO: Implement the damaging of the hero.
    @Override
    public void use(Object theObj) {
        if(theObj.getClass() == Hero.class) {
            // damage hero here.
        }
    }
}
