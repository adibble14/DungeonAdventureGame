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
     * Life Steal super attack. This attack does not fail to land. It halves the enemies' health value. It also
     * adds that value to this object's health value.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        int damage = theChar.getHealth() / 2;
        int result = this.getHealth() + damage;

        this.setHealth(result);

        theChar.setHealth(damage);

        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " absorbs " + damage + " points of " + theChar.getName() + "'s health! "));
    }

    /**
     * @return the character select image
     */
    public static ImageIcon getImage(){
        return characterSelectImage;
    }


    /**
     * @return info about the character's super
     */
    @Override
    public String getSpecialInfo(){
        return "Life Steal. This attack does not fail to land. It halves the enemies' health value. It also adds that value to the mage's health.";
    }

}
