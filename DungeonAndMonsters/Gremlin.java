import javax.swing.*;

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Gremlin extends Monster {


    /**
     * Constructor that has set values for Gremlin object. Calls on super constructor to init fields.
     */
    protected Gremlin(String theType) {

        super(SQLiteDB.getCharacterName("Gremlin"), SQLiteDB.getCharacterHealth("Gremlin", "monsters", theType),
                SQLiteDB.getCharacterSpeed("Gremlin", "monsters", theType), SQLiteDB.getCharacterMaxDamage("Gremlin", "monsters", theType),
                SQLiteDB.getCharacterMinDamage("Gremlin", "monsters", theType), SQLiteDB.getCharacterAccuracy("Gremlin", "monsters", theType),
                SQLiteDB.getCharacterHealChance("Gremlin", theType), SQLiteDB.getCharacterMinHeal("Gremlin", theType), SQLiteDB.getCharacterMaxHeal("Gremlin", theType),
                new ImageIcon(SQLiteDB.getCharacterImage("Gremlin", "monsters")), new ImageIcon(SQLiteDB.getCharacterImage("Gremlin", "monsters")));
    }


    /**
     * Gremlin Frenzy special attack. This method generates a random value influenced by
     * special turn field. Gremlin gets to attack that many times. Damage is based on special damage
     * plus a random value.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        int specialMaxTurns = 3;
        int count = Tools.RANDOM.nextInt(specialMaxTurns);

        int totalDamage = 0;
        while (count > 0 && theChar.isAlive()) {

            int specialMaxDamage = 30;
            int damage = Tools.RANDOM.nextInt(specialMaxDamage / 2) + specialMaxDamage;
            totalDamage+=damage;
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
            count--;
        }
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + theChar.getName() + " gets hit by " + count + " Gremlin minions! Took " + totalDamage + " of damage. "));
    }

    @Override
    public String getSpecialInfo(){
        return "Gremlin Frenzy. Generates a random number of gremlins that each deal their own attack.";
    }

}
