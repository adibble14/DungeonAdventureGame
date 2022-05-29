import javax.swing.*;

public class PillarOfEncapsulation extends Pillar{
    private static final ImageIcon myImage = new ImageIcon("DungeonAndMonsters/random images/Encapsulation.png");
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfEncapsulation(PillarType theType) {
        super(myImage, theType);
    }

    @Override
    public void use(Object theObj) {
        Dungeon dungeon = (Dungeon) theObj;


    }
    protected final ImageIcon getImage(){
        return myImage;
    }
}

