import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Warrior character. Overrides special attack method.
 */

public class Warrior extends Hero {

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/WarriorPixel.png");
    private static final ImageIcon CharacterSelectWarrior = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectWarrior.png");
    private static final ImageIcon inGameImage = new ImageIcon("DungeonAndMonsters/character pics/WarriorInGame.png");
    /**
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
                image, inGameImage);
    }


    /**
     * Crushing blow special attack. this attack does much more damage than a regular attack.
     * If this attack misses, it deals a guaranteed minimum value of damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        int specialMinDamage = 75;
        int specialMaxDamage = 175;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage - specialMinDamage) + specialMinDamage;
        double randAccuracy = Tools.RANDOM.nextDouble();

        //System.out.println(this.getName() + " activates Crushing Blow!\n\n");

        double specialAccuracy = .4;
        if (specialAccuracy < randAccuracy) {

            damage = specialMinDamage;
           //System.out.println(this.getName() + " stumbles the attack...\n");
        }

        int result = theChar.getHealth() - damage;

        if (result < 0) {
            result = 0;
        }

        theChar.setHealth(result);

        System.out.println(this.getName() + " deals a Crushing Blow to " + theChar.getName() + "! Dealt " + damage + " of damage.\n\n");
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals a Crushing Blow that dealt " + damage + " of damage. "));


    }
    public static ImageIcon getImage(){
        return CharacterSelectWarrior;
    }

    @Override
    public String getSpecialInfo(){
        return "Crushing blow. This attack does much more damage than a regular attack. If this attack misses, it deals a guaranteed minimum value of damage.";
    }
}
