/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.awt.*;

/**
 * pillar class that gives the hero a lot of gold
 */
public class PillarOfInheritance extends Pillar{

    /**
     * pillar image
     */
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Inheritance.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);

    private static final ImageIcon myImageIcon = new ImageIcon(myPillarImage);
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theType the pillar type
     */
    protected PillarOfInheritance(PillarType theType) {
        super(myImageIcon, theType);
    }

    /**
     * gives the hero a lot of gold
     * @param theObj the hero
     */
    @Override
    final void use(Object theObj) {
        Hero player = (Hero) theObj;
        player.setGoldAmount(player.getGoldCount() + Tools.RANDOM.nextInt(1000, 5000));
        DungeonAdventure.refreshBackPackGoldValue();
    }

    /**
     * @return the image
     */
    protected final ImageIcon getImage(){
        return myImageIcon;
    }
}

