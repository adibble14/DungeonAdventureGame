import java.awt.image.BufferedImage;

public class VisionPotion extends Item{


    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theSpawnChance
     * @param theName
     * @param theImage
     */
    protected VisionPotion(double theSpawnChance, String theName, BufferedImage theImage) {
        super(theSpawnChance, theName, theImage);
    }

    //TODO: Implement this method
    @Override
    public void use(Object theObj) {
        if(theObj.getClass() == Dungeon.class) {
            // uncover adjacent rooms here
        }
    }


}
