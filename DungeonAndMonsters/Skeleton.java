import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

//TODO don't understand Skeleton Super logic, think there might be an error
public class Skeleton extends Monster {

    /**
     * Stores boolean value for Skeleton object's special.
     */
    private boolean specialActive;
    /**
     * once the skeleton revives once, it cannot revive again
     */
    private boolean revived;
    private static final ImageIcon skeletonImage = new ImageIcon("DungeonAndMonsters/monster pics/rpgCritterSkelly.png");


    /**
     * Constructor that has set values for Skeleton object. Calls on super constructor
     * to init fields.
     */
    protected Skeleton() {
        super(SQLiteDB.getCharacterName("Skeleton"), SQLiteDB.getCharacterHealth("Skeleton","monsters"),
                SQLiteDB.getCharacterSpeed("Skeleton","monsters"), SQLiteDB.getCharacterMaxDamage("Skeleton","monsters"),
                SQLiteDB.getCharacterMinDamage("Skeleton","monsters"), SQLiteDB.getCharacterAccuracy("Skeleton","monsters"),
                SQLiteDB.getCharacterHealChance("Skeleton"), SQLiteDB.getCharacterMinHeal("Skeleton"), SQLiteDB.getCharacterMaxHeal("Skeleton"),
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

        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " prepares to reassemble itself after death! \n\n");
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " prepares to reassemble itself after death! "));
        this.setSpecialActive(true);

    }

    @Override
    public String getSpecialInfo(){
        return "Rise! When the Skeleton is about to die, it revives to max health instead.";
    }
}
