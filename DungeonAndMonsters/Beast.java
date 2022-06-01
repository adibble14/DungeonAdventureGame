import javax.swing.*;

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Beast extends Monster {


    /**
     * Constructor that has set values for Beast object. Calls on super constructor to init fields.
     */
    protected Beast(String theType) {

        super(SQLiteDB.getCharacterName("Beast"), SQLiteDB.getCharacterHealth("Beast", "monsters", theType),
                SQLiteDB.getCharacterSpeed("Beast", "monsters", theType), SQLiteDB.getCharacterMaxDamage("Beast", "monsters", theType),
                SQLiteDB.getCharacterMinDamage("Beast", "monsters", theType), SQLiteDB.getCharacterAccuracy("Beast", "monsters", theType),
                SQLiteDB.getCharacterHealChance("Beast", theType), SQLiteDB.getCharacterMinHeal("Beast", theType), SQLiteDB.getCharacterMaxHeal("Beast", theType),
                new ImageIcon(SQLiteDB.getCharacterImage("Beast", "monsters")), new ImageIcon(SQLiteDB.getCharacterImage("Beast", "monsters")));
    }



    /**
     * Feral Swipe special attack. This special always deals half the enemies' health value.
     */
    @Override
    final protected void special(DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " goes for a Feral Swipe!\n\n");

        int damage = theChar.getHealth() / 2;
        theChar.setHealth(damage);
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " halves "+ theChar.getName() + "'s health with the Feral Swipe. "));
        System.out.println(this.getName() + " halves " + theChar.getName() + "'s health!\n\n");
    }

    @Override
    public String getSpecialInfo(){
        return "Feral Swipe. This special always deals half the enemies' health value.";
    }

}
