/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Mage character. Overrides special attack method.
 */

public class Mage extends Hero {

    /**
     * the image for the character select screen - (resized from original image)
     */
    private static final ImageIcon characterSelectImage = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectMage.png");

    /**
     * Constructor that has set values for object. Calls on super constructor to init fields.
     * @param theName name value given by user input
     */
    protected Mage(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Mage", "heroes"), SQLiteDB.getCharacterSpeed("Mage", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Mage", "heroes"), SQLiteDB.getCharacterMinDamage("Mage", "heroes"),
                SQLiteDB.getCharacterAccuracy("Mage", "heroes"), SQLiteDB.getCharacterBlockChance("Mage"),
                new ImageIcon(SQLiteDB.getCharacterImage("Mage", "heroes")), new ImageIcon(SQLiteDB.getCharacterInGameImage("Mage")));
    }

    /**
     * Life Steal super attack. It halves the enemies' health value. It also
     * adds that value to this object's health value.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        double randAccuracy = Tools.RANDOM.nextDouble();

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + " The life steal attack missed. "));
        }else if((this.getMaxHealth() * 2) < this.getHealth()){  //dealing damage but not healing
            int damage = theChar.getHealth() / 2;
            theChar.setHealth(damage);
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals " + damage + " points of " + theChar.getName() + " damage. Max health limit reached."));
        } else{
            int damage = theChar.getHealth() / 2;
            int result = this.getHealth() + damage/2;

            this.setHealth(result);

            theChar.setHealth(damage);

            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " absorbs " + damage + " points of " + theChar.getName() + "'s health! "));
        }

    }

    /**
     * @return the character select image
     */
    static ImageIcon getImage(){
        return characterSelectImage;
    }


    /**
     * @return info about the character's super
     */
    @Override
    final String getSpecialInfo(){
        return "Life Steal. This attack does not fail to land. It halves the enemies' health value. It also adds that value to the mage's health.";
    }

}
