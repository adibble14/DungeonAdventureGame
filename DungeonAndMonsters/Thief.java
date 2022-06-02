import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Thief character. Overrides special attack method.
 */

public class Thief extends Hero {

    /**
     * the image for the character select screen - (resized from original image)
     */
    private static final ImageIcon characterSelectThief = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectThief.png");

    /**
     * Constructor with set values. Calls on super constructor to init fields.
     * Initializes Thief class fields.
     *
     * @param theName value given by user input
     */
    protected Thief(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Thief", "heroes"), SQLiteDB.getCharacterSpeed("Thief", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Thief", "heroes"), SQLiteDB.getCharacterMinDamage("Thief", "heroes"),
                SQLiteDB.getCharacterAccuracy("Thief", "heroes"), SQLiteDB.getCharacterBlockChance("Thief"),
                new ImageIcon(SQLiteDB.getCharacterImage("Thief", "heroes")), new ImageIcon(SQLiteDB.getCharacterInGameImage("Thief")));
    }

    /**
     * Surprise Attack special attack. This special deals more damage than a regular attack. It also
     * allows the Thief to follow up an attack that does even more damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        int specialMaxDamage = 20;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage * 2) + this.getMaxDamage();
        double randAccuracy = Tools.RANDOM.nextDouble();


        double specialAccuracy = .6;
        if (specialAccuracy < randAccuracy) {
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " almost gets caught! Swiftly escapes at the last second. Dealt No damage. "));
        } else if (specialAccuracy > randAccuracy) {

            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + " lands the back stab! Dealt " + damage + " of damage. "));

            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
        }
        if ((specialAccuracy / 2) > randAccuracy && theChar.isAlive()) {

            damage += this.getMaxDamage();
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + " connects another attack for " + damage + " damage. "));

            int secondResult = theChar.getHealth() - damage;
            if (secondResult < 0) {
                secondResult = 0;
            }
            theChar.setHealth(secondResult);
        }
    }

    /**
     * @return the character select image
     */
    public static ImageIcon getImage(){
        return characterSelectThief;
    }


    /**
     * @return info about the character's super
     */
    @Override
    public String getSpecialInfo(){
        return "Surprise Attack. This special deals more damage than a regular attack. It also allows the Thief to follow up with a second attack";
    }

}
