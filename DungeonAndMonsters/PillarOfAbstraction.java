import javax.swing.*;

public class PillarOfAbstraction extends Pillar{
    private static final ImageIcon myImage = new ImageIcon("DungeonAndMonsters/random images/Abstraction.png");

    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theType
     */
    protected PillarOfAbstraction(PillarType theType) {
        super(myImage, theType);
    }

    @Override
    public void use(Object theObj) {


    }

    protected final ImageIcon getImage(){
        return myImage;
    }
}

