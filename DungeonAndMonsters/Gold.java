/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

/**
 * Gold class, like a stat for the game
 */
public class Gold extends Item{


    /**
     * Value of this gold piece
     */
    private final int myValue;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected Gold() {
        super(new ImageIcon("DungeonAndMonsters/gold.png"));
        this.myValue = Tools.RANDOM.nextInt(5, 51);
    }

    /**
     * how to use gold
     * @param theObj the hero
     */
    @Override
    final void use(Object theObj) {
        Hero player = (Hero) theObj;
        player.setGoldAmount(this.myValue);
    }
}
