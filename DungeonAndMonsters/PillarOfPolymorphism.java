import javax.swing.*;

public class PillarOfPolymorphism extends Pillar{
    private static final ImageIcon myImage = new ImageIcon("DungeonAndMonsters/random images/Polymorphism.png");
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfPolymorphism(PillarType theType) {
        super(myImage, theType);
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

    protected final ImageIcon getImage(){
        return myImage;
    }
}
