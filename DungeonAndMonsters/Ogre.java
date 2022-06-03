/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Ogre extends Monster {

    /**
     * Constructor that has set values for Ogre object. Calls on super constructor to init fields.
     */
    protected Ogre(String theType) {

        super(SQLiteDB.getCharacterName("Ogre"), SQLiteDB.getCharacterHealth("Ogre", "monsters", theType),
                SQLiteDB.getCharacterSpeed("Ogre", "monsters", theType), SQLiteDB.getCharacterMaxDamage("Ogre", "monsters", theType),
                SQLiteDB.getCharacterMinDamage("Ogre", "monsters", theType), SQLiteDB.getCharacterAccuracy("Ogre", "monsters", theType),
                SQLiteDB.getCharacterHealChance("Ogre", theType), SQLiteDB.getCharacterMinHeal("Ogre", theType), SQLiteDB.getCharacterMaxHeal("Ogre", theType),
                new ImageIcon(SQLiteDB.getCharacterImage("Ogre", "monsters")), new ImageIcon(SQLiteDB.getCharacterImage("Ogre", "monsters")));
    }

    /**
     * Devastating Blow special method for Ogre object. This special has a guaranteed min value
     * damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        int specialMaxDamage = 100;
        int specialMinDamage = 60;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage) + specialMinDamage;
        double randAccuracy = Tools.RANDOM.nextDouble();

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {

            damage = specialMinDamage;
        }

        int result = theChar.getHealth() - damage;

        if (result < 0) {

            result = 0;
        }
        theChar.setHealth(result);

        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals a Devastating Blow to " + theChar.getName() + " of "+ damage + " damage. "));
    }

    @Override
    final String getSpecialInfo(){
        return "Devastating Blow. This special has a guaranteed min value damage.";
    }


}
