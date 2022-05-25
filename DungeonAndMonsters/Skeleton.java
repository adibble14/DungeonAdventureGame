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
     * Initializes array of names. To be randomly chosen for Skeleton object.
     */
    //private final String[] MY_NAMES = {"Jack Skellington", "Skeletor", "Funny Bones", "Rattles", "Bone Thug"};

    /**
     * Constructor that has set values for Skeleton object. Calls on super constructor
     * to init fields.
     */
    protected Skeleton() {
        super(SQLiteDB.getMonsterName("Skeleton"), SQLiteDB.getMonsterHealth("Skeleton"),SQLiteDB.getMonsterSpeed("Skeleton"),
                SQLiteDB.getMonsterMaxDamage("Skeleton"),SQLiteDB.getMonsterMinDamage("Skeleton"),SQLiteDB.getMonsterAccuracy("Skeleton"),
                SQLiteDB.getMonsterHealChance("Skeleton"),SQLiteDB.getMonsterMinHeal("Skeleton"), SQLiteDB.getMonsterMaxHeal("Skeleton"));

        //this.setRandomName();
        this.setSpecialActive(false);
    }


    /**
     * Sets random name for Skeleton object.
     */
   /* private void setRandomName() {

        this.setName(MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)]);
    }*/

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

        if (theHealth == 0 && this.getSpecialActive()) {

            super.setHealth(this.getMaxHealth());
            this.setSpecialActive(false);
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
