import javax.swing.*;

public class PillarOfInheritance extends Pillar{
    private static final ImageIcon myImage = new ImageIcon("DungeonAndMonsters/random images/Inheritance.png");
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfInheritance(PillarType theType) {
        super(myImage, theType);
    }

    @Override
    public void use(Object theObj) {

    }

    protected final ImageIcon getImage(){
        return myImage;
    }
}

