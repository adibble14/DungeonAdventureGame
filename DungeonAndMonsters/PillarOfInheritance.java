import javax.swing.*;
import java.awt.*;

public class PillarOfInheritance extends Pillar{
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Inheritance.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);

    private static final ImageIcon myImageIcon = new ImageIcon(myPillarImage);
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theType
     */
    protected PillarOfInheritance(PillarType theType) {
        super(myImageIcon, theType);
    }

    @Override
    public void use(Object theObj) {
        Hero player = (Hero) theObj;
        player.setGoldAmount(player.getGoldCount() + Tools.RANDOM.nextInt(1000, 5000));
        DungeonAdventure.refreshBackPackGoldValue();
    }

    protected final ImageIcon getImage(){
        return myImageIcon;
    }
}

