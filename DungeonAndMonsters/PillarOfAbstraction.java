import javax.swing.*;
import java.awt.*;

public class PillarOfAbstraction extends Pillar{
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Abstraction.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);


    private static final ImageIcon myImage = new ImageIcon(myPillarImage);

    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theType
     */
    protected PillarOfAbstraction(PillarType theType) {
        super(myImage, theType);
    }

    // Set player to invincible for a few rounds
    @Override
    public void use(Object theObj) {


    }

    protected final ImageIcon getImage(){
        return myImage;
    }
}

