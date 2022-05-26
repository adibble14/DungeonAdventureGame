import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates an Archer character. Overrides special attack method.
 */

public class Archer extends Hero {

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/archerpixel.png");
    private static final ImageIcon characterSelectArcher = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectArcher.png");
    private static final ImageIcon inGameImage = new ImageIcon("DungeonAndMonsters/character pics/archerInGame.png");

    /**
     * Constructor with set values. Calls on super constructor to init fields
     * Initializes class specific fields
     *
     * @param theName value given by user input
     */
    protected Archer(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Archer", "heroes"), SQLiteDB.getCharacterSpeed("Archer", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Archer", "heroes"), SQLiteDB.getCharacterMinDamage("Archer", "heroes"),
                SQLiteDB.getCharacterAccuracy("Archer", "heroes"), SQLiteDB.getCharacterBlockChange("Archer"),
                image, inGameImage);
    }

    /**
     * Volley special attack. Generates a random number of attack turns, depending on special max turn field.
     * Damage dealt is more than regular attack.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        //TODO delete this output once GUI is made, since this is VIEW
        //System.out.println(this.getName() + " shoots a volley of arrows!\n");

        int specialMaxTurns = 5;
        int count = Tools.RANDOM.nextInt(specialMaxTurns);
        int totalArrows = count;

        if (count == 0) {

            System.out.println("The attack misses!");
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
            System.out.println(theChar.getName() + " gets pierced by an arrow. Took " + damage + " of damage.\n\n");
            theChar.setHealth(result);
            count--;
        }
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " shoots " + totalArrows + " arrows that deal " + totalDamage + " damage. "));

    }

    public static ImageIcon getImage(){
        return characterSelectArcher;
    }

    @Override
    public String getSpecialInfo(){
        return "Volley special attack. Shoots a random number of arrows with a maximum of 5.";
    }


}
