import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Warrior character. Overrides special attack method.
 */

public class Warrior extends Hero {

    /**
     * the image for the character select screen - (resized from original image)
     */
    private static final ImageIcon CharacterSelectWarrior = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectWarrior.png");

    /**
     * Class constructor with set values. Calls on super constructor to init fields.
     * Initializes class specific fields.
     *
     * @param theName value given by user input.
     */
    protected Warrior(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Warrior", "heroes"), SQLiteDB.getCharacterSpeed("Warrior", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Warrior", "heroes"), SQLiteDB.getCharacterMinDamage("Warrior", "heroes"),
                SQLiteDB.getCharacterAccuracy("Warrior", "heroes"), SQLiteDB.getCharacterBlockChange("Warrior"),
                new ImageIcon(SQLiteDB.getCharacterImage("Warrior", "heroes")), new ImageIcon(SQLiteDB.getCharacterInGameImage("Warrior")));
    }


    /**
     * Crushing blow special attack. this attack does much more damage than a regular attack.
     * If this attack misses, it deals a guaranteed minimum value of damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        int specialMinDamage = 75;
        int specialMaxDamage = 175;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage - specialMinDamage) + specialMinDamage;
        double randAccuracy = Tools.RANDOM.nextDouble();


        double specialAccuracy = .4;
        if (specialAccuracy < randAccuracy) {
            damage = specialMinDamage;
        }

        int result = theChar.getHealth() - damage;

        if (result < 0) {
            result = 0;
        }

        theChar.setHealth(result);

        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals a Crushing Blow that dealt " + damage + " of damage. "));


    }

    /**
     * @return the character select image
     */
    public static ImageIcon getImage(){
        return CharacterSelectWarrior;
    }


    /**
     * @return info about the character's super
     */
    @Override
    public String getSpecialInfo(){
        return "Crushing blow. This attack does much more damage than a regular attack. If this attack misses, it deals a guaranteed minimum value of damage.";
    }
}
