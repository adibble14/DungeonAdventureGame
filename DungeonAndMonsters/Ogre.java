import javax.swing.*;

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Ogre extends Monster {


    private static final ImageIcon ogreImage = new ImageIcon("DungeonAndMonsters/monster pics/Ogre.png");

    /**
     * Constructor that has set values for Ogre object. Calls on super constructor to init fields.
     */
    protected Ogre() {

        super(SQLiteDB.getCharacterName("Ogre"), SQLiteDB.getCharacterHealth("Ogre","monsters"),
                SQLiteDB.getCharacterSpeed("Ogre","monsters"), SQLiteDB.getCharacterMaxDamage("Ogre","monsters"),
                SQLiteDB.getCharacterMinDamage("Ogre","monsters"), SQLiteDB.getCharacterAccuracy("Ogre","monsters"),
                SQLiteDB.getCharacterHealChance("Ogre"), SQLiteDB.getCharacterMinHeal("Ogre"), SQLiteDB.getCharacterMaxHeal("Ogre"),
                new ImageIcon(SQLiteDB.getCharacterImage("Ogre", "monsters")), new ImageIcon(SQLiteDB.getCharacterImage("Ogre", "monsters")));
    }

    /**
     * Devastating Blow special method for Ogre object. This special has a guaranteed min value
     * damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        int specialMaxDamage = 100;
        int specialMinDamage = 60;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage) + specialMinDamage;
        double randAccuracy = Tools.RANDOM.nextDouble();

        //System.out.println(this.getName() + " throws a Devastating Blow!\n\n");

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {

            damage = specialMinDamage;
            //System.out.println(theChar.getName() + " dodges the attack! Splash damage taken.\n");
        }

        int result = theChar.getHealth() - damage;

        if (result < 0) {

            result = 0;
        }
        theChar.setHealth(result);

        System.out.println(this.getName() + " deals a Devastating Blow to " + theChar.getName() + "! Dealt " + damage + " of damage.\n\n");
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals a Devastating Blow to " + theChar.getName() + " of "+ damage + " damage. "));
    }

    @Override
    public String getSpecialInfo(){
        return "Devastating Blow. This special has a guaranteed min value damage.";
    }


}
