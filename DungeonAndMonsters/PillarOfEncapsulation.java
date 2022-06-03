/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.awt.*;

/**
 * pillar class that makes all rooms visible
 */
public class PillarOfEncapsulation extends Pillar{

    /**
     * image of pillar
     */
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Encapsulation.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);

    private static final ImageIcon myImage = new ImageIcon(myPillarImage);
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theType type of pillar
     */
    protected PillarOfEncapsulation(PillarType theType) {
        super(myImage, theType);
    }

    /**
     * Setting every room to visible
     * @param theObj the dungeon
     */
    @Override
    final void use(Object theObj) {
        Dungeon dungeon = (Dungeon) theObj;
        for (int i = 0; i < dungeon.getDungeon().length; i++) {
            for (int j = 0; j < dungeon.getDungeon()[0].length; j++) {
                if(dungeon.getRoom(i,j) != null){
                    dungeon.getRoom(i,j).setMyDiscovery();
                }
            }
        }
        DungeonAdventure.getMainGui().getMapGui().repaint();
    }

    /**
     * @return the image
     */
    protected final ImageIcon getImage(){
        return myImage;
    }
}

