
/**
 * This abstract class inherits from DungeonCharacter super class. Adds fields and methods used
 * exclusively by Monster objects.
 */

public abstract class Monster extends DungeonCharacter {

    /**
     * Stores Monster object heal chance value
     */
    private double myHealChance;
    /**
     * stores Monster object minimum heal value
     */
    private int myMinHeal;
    /**
     * Stores Monster objects maximum heal value
     */
    private int myMaxHeal;

    /**
     * Child constructor of Monster class. Calls super class constructor to init fields.
     * Inits Monster class exclusive fields.
     *
     * @param theName       value given by child class
     * @param theHealth     value given by child class
     * @param theSpeed      value given by child class
     * @param theMaxDamage  value given by child class
     * @param theMinDamage  value given by child class
     * @param theAccuracy   value that is static. Does not change.
     * @param theHealChance value given by child class
     * @param theMinHeal    value given by child class
     * @param theMaxHeal    value given by child class
     */
    protected Monster(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage,
                      final double theAccuracy, final double theHealChance, final int theMinHeal, final int theMaxHeal) {

        // TODO fix, give sprites to every monster
        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, null);
        this.setHealChance(theHealChance);
        this.setMinHeal(theMinHeal);
        this.setMaxHeal(theMaxHeal);

    }

    /**
     * Get method for heal chance field
     *
     * @return returns heal chance value
     */
    final protected double getHealChance() {

        return this.myHealChance;
    }

    /**
     * Set method for heal chance
     *
     * @param theHealChance value given by child class
     */
    final protected void setHealChance(final double theHealChance) {

        if (!(theHealChance > 0)) {
            throw new IllegalArgumentException("Heal chance value must be greater than zero.");
        }
        this.myHealChance = theHealChance;
    }

    /**
     * Set method for minimum heal field
     *
     * @param theMinHeal value given by child class
     */
    final protected void setMinHeal(final int theMinHeal) {

        if (theMinHeal < 0) {
            throw new IllegalArgumentException("Minimum heal value must be a positive value.");
        }
        this.myMinHeal = theMinHeal;
    }

    /**
     * Set method for maximum heal field
     *
     * @param theMaxHeal value given by child class
     */
    final protected void setMaxHeal(final int theMaxHeal) {

        if (theMaxHeal > this.getMaxHealth() || theMaxHeal < 0) {
            throw new IllegalArgumentException("Maximum heal value cannot be negative or greater than maximum health.");
        }
        this.myMaxHeal = theMaxHeal;
    }

    /**
     * Encapsulated method used to add health to Monster object. Calls on setHealth to do so.
     */
    private void heal() {

        int healPoints = 0;

        if (isHealable() && this.getHealChance() > MY_RAND.nextDouble()) {

            healPoints = MY_RAND.nextInt(this.myMaxHeal - this.myMinHeal) + this.myMinHeal;
        }

        this.setHealth(Math.min((this.getHealth() + healPoints), this.getMaxHealth()));
        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " recovers " + healPoints + " of health.\n\n");
    }

    /**
     * Overrides parent attack method to check if special attack activates.
     * Calls on heal() every attack turn
     */
    @Override
    protected void attack(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        if (this.isSpecialActive()) {

            this.special(theChar);
            return;
        }

        Hero hero = (Hero) theChar;

        System.out.println(this.getName() + " goes for the attack!\n\n");

        if (!(hero.block())) {

            super.attack(hero);
        } else {
            System.out.println(hero.getName() + " blocked the attack!\n\n");
        }

        System.out.println(this.getName() + " attempts to heal!\n\n");
        this.heal();

    }

    /**
     * Generates a random value and compares it to special accuracy field
     *
     * @return returns true if special accuracy is greater. This activates the class special attack
     */
    private boolean isSpecialActive() {

        double specialAccuracy = .3;
        return specialAccuracy > this.MY_RAND.nextDouble();
    }

    /**
     * Checks to see if health is less than max health.
     *
     * @return returns true if health is lower than max health. Allows for heal() to add health.
     */
    private boolean isHealable() {

        return this.getHealth() < this.getMaxHealth();
    }

    /**
     * Abstract method for child classes. Each child should have a unique special.
     *
     * @param theChar object to deal damage to
     */
    protected abstract void special(final DungeonCharacter theChar);

}
