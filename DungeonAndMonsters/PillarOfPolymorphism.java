import javax.swing.*;

public class PillarOfPolymorphism extends Pillar{
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfPolymorphism(ImageIcon theImage, PillarType theType) {
        super(theImage, theType);
    }

    @Override
    public void use(Object theObj) {
        Hero hero = (Hero) theObj;
        //TODO: Steps to take
        // save the current hero state using CareTaker class
        // randomly choose a Monster maybe?
        // set that monster to DungeonAdventure hero object
        // other classes may need to be adjusted
        // How to revert back to original hero object?

    }
}
