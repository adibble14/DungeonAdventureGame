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

    /**
     * Setting every room to visible
     * @param theObj
     */
    @Override
    public void use(Object theObj) {
        Dungeon dungeon = (Dungeon) theObj;
        for (int i = 0; i < dungeon.getDungeon().length; i++) {
            for (int j = 0; j < dungeon.getDungeon()[0].length; j++) {
                if(dungeon.getRoom(i,j) != null)
                    dungeon.getRoom(i,j).setMyDiscovery();
            }
        }

    }
    protected final ImageIcon getImage(){
        return myImage;
    }
}

