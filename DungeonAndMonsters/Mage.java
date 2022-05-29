import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Mage character. Overrides special attack method.
 */

public class Mage extends Hero {
    private static final ImageIcon characterSelectImage = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectMage.png");
    /**
     * Constructor that has set values for object. Calls on super constructor to init fields.
     *
     * @param theName name value given by user input
     */
    protected Mage(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Mage", "heroes"), SQLiteDB.getCharacterSpeed("Mage", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Mage", "heroes"), SQLiteDB.getCharacterMinDamage("Mage", "heroes"),
                SQLiteDB.getCharacterAccuracy("Mage", "heroes"), SQLiteDB.getCharacterBlockChange("Mage"),
                new ImageIcon(SQLiteDB.getCharacterImage("Mage", "heroes")), new ImageIcon(SQLiteDB.getCharacterInGameImage("Mage")));
    }

    /**
     * Life Steal super attack. This attack does not fail to land. It halves the enemies' health value. It also
     * adds that value to this object's health value.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " activates life steal!\n\n");

        int damage = theChar.getHealth() / 2;
        int result = this.getHealth() + damage;

        this.setHealth(result);

        theChar.setHealth(damage);

        System.out.println(this.getName() + " absorbs " + damage + " points of " + theChar.getName() + "'s health!!\n\n");
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " absorbs " + damage + " points of " + theChar.getName() + "'s health! "));
    }

    public static ImageIcon getImage(){
        return characterSelectImage;
    }

    @Override
    public String getSpecialInfo(){
        return "Life Steal. This attack does not fail to land. It halves the enemies' health value. It also adds that value to the mage's health.";
    }

}
