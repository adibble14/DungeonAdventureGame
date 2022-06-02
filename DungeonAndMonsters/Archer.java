import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates an Archer character. Overrides special attack method.
 */

public class Archer extends Hero {

    /**
     * the image for the character select screen - (resized from original image)
     */
    private static final ImageIcon myCharacterSelectImage = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectArcher.png");

    /**
     * Constructor with set values. Calls on super constructor to init fields
     * Initializes class specific fields
     * @param theName value given by user input
     */
    protected Archer(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Archer", "heroes"), SQLiteDB.getCharacterSpeed("Archer", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Archer", "heroes"), SQLiteDB.getCharacterMinDamage("Archer", "heroes"),
                SQLiteDB.getCharacterAccuracy("Archer", "heroes"), SQLiteDB.getCharacterBlockChance("Archer"),
                new ImageIcon(SQLiteDB.getCharacterImage("Archer", "heroes")), new ImageIcon(SQLiteDB.getCharacterInGameImage("Archer")));
    }

    /**
     * Volley special attack. Generates a random number of attack turns, depending on special max turn field.
     * Damage dealt is more than regular attack.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        int specialMaxTurns = 5;
        int count = Tools.RANDOM.nextInt(specialMaxTurns);
        int totalArrows = count;

        if (count == 0) {
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + "'s attack missed! "));
        }
        int totalDamage = 0;
        while (count > 0 && theChar.isAlive()) {

            int specialMaxDamage = 20;
            int damage = Tools.RANDOM.nextInt(specialMaxDamage) + this.getMaxDamage();
            totalDamage += damage;
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
            count--;
        }
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " shoots " + totalArrows + " arrows that deal " + totalDamage + " damage. "));

    }

    /**
     * @return the character select image
     */
    public static ImageIcon getImage(){
        return myCharacterSelectImage;
    }

    /**
     * @return info about the character's super
     */
    @Override
    public String getSpecialInfo(){
        return "Volley special attack. Shoots a random number of arrows with a maximum of 5.";
    }

}
