import javax.swing.*;

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Skeleton extends Monster {

    /**
     * Stores boolean value for Skeleton object's special.
     */
    private boolean specialActive;
    /**
     * once the skeleton revives once, it cannot revive again
     */
    private boolean revived;


    /**
     * Constructor that has set values for Skeleton object. Calls on super constructor
     * to init fields.
     */
    protected Skeleton(String theType) {

        super(SQLiteDB.getCharacterName("Skeleton"), SQLiteDB.getCharacterHealth("Skeleton", "monsters", theType),
                SQLiteDB.getCharacterSpeed("Skeleton", "monsters", theType), SQLiteDB.getCharacterMaxDamage("Skeleton", "monsters", theType),
                SQLiteDB.getCharacterMinDamage("Skeleton", "monsters", theType), SQLiteDB.getCharacterAccuracy("Skeleton", "monsters", theType),
                SQLiteDB.getCharacterHealChance("Skeleton", theType), SQLiteDB.getCharacterMinHeal("Skeleton", theType), SQLiteDB.getCharacterMaxHeal("Skeleton", theType),
                new ImageIcon(SQLiteDB.getCharacterImage("Skeleton", "monsters")), new ImageIcon(SQLiteDB.getCharacterImage("Skeleton", "monsters")));

        this.setSpecialActive(false);
        this.revived = false;
    }


    /**
     * Sets special active field
     *
     * @param theBool receives a boolean from this class.
     */
    private void setSpecialActive(final boolean theBool) {

        this.specialActive = theBool;
    }

    /**
     * Get method for special active field. Used to check if object can execute special.
     *
     * @return returns special active boolean
     */
    private boolean getSpecialActive() {

        return this.specialActive;
    }

    /**
     * Overrides super class setHealth. Checks to see if special is activated. If so,
     * when the health field is going to be set to zero, sets it to max health instead.
     */
    @Override
    final protected void setHealth(final int theHealth) {

        if (theHealth == 0 && this.getSpecialActive() && !this.revived ) {

            super.setHealth(this.getMaxHealth());
            this.setSpecialActive(false);
            this.revived = true;
        } else {
            super.setHealth(theHealth);
        }
    }

    /**
     * Calls on set special method to active special.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " prepares to reassemble itself after death! "));
        this.setSpecialActive(true);

    }

    @Override
    final String getSpecialInfo(){
        return "Rise! When the Skeleton is about to die, it revives to max health instead.";
    }
}
