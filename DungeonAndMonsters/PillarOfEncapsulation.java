import javax.swing.*;
import java.awt.*;

public class PillarOfEncapsulation extends Pillar{
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Encapsulation.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);

    private static final ImageIcon myImage = new ImageIcon(myPillarImage);
    /**
     * Constructor for this class. Simply initializes fields.
     *
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

