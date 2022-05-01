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
     * @param theSpawnChance
     * @param theName
     * @param theImage
     */
    protected HealthPotion(double theSpawnChance, String theName, BufferedImage theImage, final int theAmount) {
        super(theSpawnChance, theName, theImage);
        this.myAffectAmount = theAmount;
    }

    /**
     * Heals Hero, increases health amount by this potions amount.
     * @param theObject
     */
    //TODO: Once we change the useHealthPotion method in Hero, implement this method.
    @Override
    public void use(Object theObject) {
        if(theObject.getClass() == Hero.class) {
            // modify health of hero here.
        }

    }
}
