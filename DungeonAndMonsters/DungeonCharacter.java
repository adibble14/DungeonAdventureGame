
import javax.swing.*;
import java.io.Serializable;
import java.util.Random;

/**
 * This is the parent abstract class that is inherited from the Hero and Monster class.
 */

public abstract class DungeonCharacter implements Serializable {

    /**
     * Random object that will generate random values throughout the program
     */
    //protected transient final Random MY_RAND;
    /**
     * stores the object's character name
     */
    private String myCharName;
    /**
     * stores the object's current health. Changes as damage is taken.
     */
    private int myCurrentHealth;
    /**
     * stores the object's speed value. Influences the amount of turns the hero class can take.
     */
    private transient int mySpeed;
    /**
     * stores the object's maximum damage value.
     */
    private transient int myMaxDamage;
    /**
     * stores the object's minimum damage value.
     */
    private transient int myMinDamage;
    /**
     * stores the object's accuracy value. Influences whether attack lands or misses.
     */
    private transient double myAccuracy;
    /**
     * stores the object's maximum health value.
     */
    private transient int myMaxHealth;

    /**
     * stores the object's image also called Sprites
     */
    private transient ImageIcon mySprite;

    private transient ImageIcon myInGameSprite;

    /**
     * Parent constructor for all classes in package.
     *
     * @param theName      stores name given by the user
     * @param theHealth    stores health value given by child class
     * @param theSpeed     stores speed value given by child class
     * @param theMaxDamage stores maximum damage value given by child class
     * @param theMinDamage stores minimum damage value given by child class
     * @param theAccuracy  stores minimum damage value given by child class
     * @param theSprite    stores the image associated with the object
     */
    protected DungeonCharacter(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage, final double theAccuracy,
                               final ImageIcon theSprite, final ImageIcon theInGameSprite) {
        this.setName(theName);
        this.setHealth(theHealth);
        this.setMaxHealth(theHealth);
        this.setSpeed(theSpeed);
        this.setMaxDamage(theMaxDamage);
        this.setMinDamage(theMinDamage);
        this.setAccuracy(theAccuracy);
        this.setSprite(theSprite);
        this.setMyInGameSprite(theInGameSprite);
    }

    /**
     * Get method for name value
     *
     * @return access for name value
     */
    final protected String getName() {
        return this.myCharName;
    }

    /**
     * Get method for health value
     *
     * @return access for health value
     */
    final protected int getHealth() {
        return this.myCurrentHealth;
    }

    /**
     * Get method for Maximum health value
     *
     * @return access for max health
     */
    final protected int getMaxHealth() {
        return this.myMaxHealth;
    }

    /**
     * Get method for speed value
     *
     * @return access for speed value
     */
    final protected int getSpeed() {
        return this.mySpeed;
    }

    /**
     * Get method for maximum damage value
     *
     * @return access for max damage value
     */
    final protected int getMaxDamage() {
        return this.myMaxDamage;
    }

    /**
     * Set method for name value
     *
     * @param theName takes string given by player
     */
    final protected void setName(final String theName) {
        this.myCharName = theName;
    }

    /**
     * Set method for health value, used to set current health after damage is taken.
     *
     * @param theHealth takes health value given by attack() method of child classes.
     */
    protected void setHealth(final int theHealth) {

        if (theHealth < 0) {
            this.myCurrentHealth = 0;
        }
        this.myCurrentHealth = theHealth;
    }

    /**
     * Set method for maximum health value. Takes same param as currentHealth when initializing, does not change after that.
     *
     * @param theHealth takes health value given by child classes.
     */
    final protected void setMaxHealth(final int theHealth) {

        if (theHealth == 0) {
            throw new IllegalArgumentException("Max health must be greater than zero.");
        }
        this.myMaxHealth = theHealth;
    }

    /**
     * Set method for speed value.
     *
     * @param theSpeed takes speed value given by child classes.
     */
    final protected void setSpeed(final int theSpeed) {

        if (!(theSpeed > 0)) {
            throw new IllegalArgumentException("Speed value must be greater than zero.");
        }
        this.mySpeed = theSpeed;
    }

    /**
     * Set method for max damage value.
     *
     * @param theMaxDamage takes max damage value given by child classes.
     */
    final protected void setMaxDamage(final int theMaxDamage) {

        if (!(theMaxDamage > 0)) {
            throw new IllegalArgumentException("Max damage value must be greater than zero.");
        }
        this.myMaxDamage = theMaxDamage;
    }

    /**
     * Set method for min damage value.
     *
     * @param theMinDamage takes min damage value given by child classes.
     */
    final protected void setMinDamage(final int theMinDamage) {

        if (theMinDamage > this.getMaxDamage() || theMinDamage < 0) {
            throw new IllegalArgumentException("Min damage value cannot be greater than max damage value and must be positive.");
        }
        this.myMinDamage = theMinDamage;
    }

    /**
     * Set method for accuracy value.
     *
     * @param theAccuracy takes accuracy value given by child classes.
     */
    final protected void setAccuracy(final double theAccuracy) {

/*        if (!(theAccuracy > 0)) {
            throw new IllegalArgumentException("Accuracy value must be greater than zero.");
        }*/
        this.myAccuracy = theAccuracy;
    }

    /**
     * Getter for the object's image Sprite
     * @return access to image Sprite
     */
    protected ImageIcon getMySprite(){return this.mySprite;}
    /**
     * Setter for the object's image Sprite
     * @param theSprite takes an ImageIcon to set as the image for the object
     */
    protected void setSprite(final ImageIcon theSprite){
        this.mySprite = theSprite;
    }

    protected ImageIcon getMyInGameSprite(){return this.myInGameSprite;}
    protected void setMyInGameSprite(final ImageIcon theSprite){this.myInGameSprite = theSprite;}




    /**
     * Attack method that handles the game logic. Subtracts health from given DungeonCharacter. Checks
     * if this object is able to attack.
     *
     * @param theChar DungeonCharacter object that is being attacked. calls on the param object's
     *                setHealth to change health value.
     */
    protected void attack(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        if (this.isAttack()) {

            int damage = Tools.RANDOM.nextInt(this.myMaxDamage - this.myMinDamage) + this.myMinDamage;
            int result = theChar.getHealth() - damage;

            if (result < 0) {

                result = 0;
            }

            theChar.setHealth(result);

            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " dealt " + damage + " of damage. "));
            System.out.println(this.getName() + " landed an attack on " + theChar.getName() + "!! Dealt " + damage + " of damage.\n\n");


        } else {
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + "'s attack missed! "));
            System.out.println(this.getName() + "'s attack missed!\n\n");
        }
    }

    /**
     * Determines if the character is able to attack. This method "Rolls the dice" three times. If myAccuracy is bigger than the random generated number,
     * the chance variable increases by one. At the end of the dice rolls. This method should be invisible to child classes.
     *
     * @return If chance is at least two, after "Dice rolls", the character is able to attack.
     */
    private boolean isAttack() {

        int chance = 0;

        for (int i = 0; i < 3; i++) {

            if (this.myAccuracy > Tools.RANDOM.nextDouble()) {

                chance++;
            }
        }
        return chance > 1;
    }

    /**
     * Checks to see if health is above zero. Being above zero indicates that the object is Alive.
     *
     * @return if health is above zero, returns true.
     */
    final protected boolean isAlive() {

        return this.getHealth() > 0;
    }

    /**
     *  Subtracts Argument amount from health
     */

    final protected void takeDamage(final int theAmount) {
        this.myCurrentHealth = Math.max(this.myCurrentHealth-theAmount, 0);

    }

    public double getMyAccuracy(){return myAccuracy;}

    public abstract String getSpecialInfo();


}
