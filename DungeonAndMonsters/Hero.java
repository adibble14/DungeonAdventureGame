import javax.swing.*;

/**
 * This abstract class inherits from DungeonCharacter super class. Adds fields and methods used exclusively
 * by hero objects.
 */

public abstract class Hero extends DungeonCharacter {

    /**
     * Stores hero object's block chance value.
     */
    private double myBlockChance;

    /**
     * Stores hero object's turn count value. Changes depending on enemy speed value.
     */

    private int myTurnCount;

    /**
     * Number of health potions in Hero's inventory
     */
    private int myHealthPotionCount;

    /**
     * Number of Vision potions in Hero's Inventory
     */
    private int myVisionPotionCount;

    /**
     * Number of keys in Hero's inventory
     */
    private int myKeyCount;

    /**
     * Child constructor of Hero class. Calls super class constructor to init fields.
     * Inits Hero class exclusive fields.
     *
     * @param theName        value given by user
     * @param theHealth      value given by child class
     * @param theSpeed       value given by child class
     * @param theMaxDamage   value given by child class
     * @param theMinDamage   value given by child class
     * @param theAccuracy    value given by child class
     * @param theBlockChance value given by child class
     */
    protected Hero(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage,
                   final double theAccuracy, final double theBlockChance, final ImageIcon theSprite) {

        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theSprite);
        this.setBlockChance(theBlockChance);
        this.myHealthPotionCount = 0;
        this.myVisionPotionCount = 0;
        this.myKeyCount = 0;
    }

    /**
     * Get method for turn count field
     *
     * @return returns turn count value
     */
    final protected int getTurnCount() {

        return this.myTurnCount;
    }

    /**
     * Get method for block chance field
     *
     * @return returns block chance value
     */
    final protected double getBlockChance() {

        return this.myBlockChance;
    }

    /**
     * Set method for turn count field
     *
     * @param theChar turn count is influenced by theChar's speed value
     */
    private void setTurnCount(final DungeonCharacter theChar) {

        int result = this.getSpeed() / theChar.getSpeed();
        if (result == 0) {
            result = 1;
        }
        this.myTurnCount = result;

    }

    /**
     * Set method for block chance field
     *
     * @param theBlockChance value given by child classes
     */
    final protected void setBlockChance(final double theBlockChance) {

        if (!(theBlockChance > 0)) {
            throw new IllegalArgumentException("Block Chance value must be greater than zero.");
        }
        this.myBlockChance = theBlockChance;
    }

    /**
     * Override attack method to include turn count and check if enemy is alive.
     */
    @Override
    protected void attack(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        this.setTurnCount(theChar);

        System.out.println(this.getName() + " gets " + this.getTurnCount() + " attack chances!\n\n");

        int turns = this.getTurnCount();

        while (theChar.isAlive() && turns > 0) {

            System.out.println(this.getName() + " goes for the attack....\n\n");
            super.attack(theChar);
            turns--;
        }
    }

    /**
     * Block logic. Calculates if this object is able to block an attack. Depends on block
     * chance value.
     *
     * @return returns true if block chance value is greater than random generated value
     */
    final protected boolean block() {

        return this.getBlockChance() > MY_RAND.nextDouble();
    }

    /**
     * Abstract method for child classes. Each child should have a unique special.
     *
     * @param theChar object to deal damage to
     */
    protected abstract void special(final DungeonCharacter theChar);

    /**
     * Set method for number of health potions
     *
     * @param theCount the number of health potions
     */
    final protected void setHealthPotions(final int theCount) {
        this.myHealthPotionCount = theCount;
    }

    /**
     * Set method for number of vision potions
     *
     * @param theCount the number of vision potions
     */
    final protected void setVisionPotions(final int theCount) {
        this.myVisionPotionCount = theCount;
    }

    /**
     * Depending on String value, increases count of potions and key count
     *
     * @param theString the input
     */
    final protected void addInventory(final String theString) {
        //TODO delete this output once GUI is made, since this is VIEW
        if (theString.equalsIgnoreCase("h")) {
            this.myHealthPotionCount++;
            System.out.println(this.getName() + " found a Health Potion.");
        } else if (theString.equalsIgnoreCase("v")) {
            System.out.println(this.getName() + " found a Vision Potion.");
            this.myVisionPotionCount++;
        } else if (theString.equalsIgnoreCase("k")) {
            this.myKeyCount++;
            System.out.println(this.getName() + " found a key! " + (2 - this.getKeyCount()) + " more keys needed.");
        }
    }

    /**
     * Returns the key count in Hero inventory
     *
     * @return the number of keys
     */
    final protected int getKeyCount() {
        return this.myKeyCount;
    }

    /**
     * Returns the Health Potion count in Hero inventory
     *
     * @return the number of health potions
     */
    final protected int getHealthPotionCount() {
        return this.myHealthPotionCount;
    }

    /**
     * Returns the Vision Potion count in Hero inventory
     *
     * @return the number of vision potions
     */
    final protected int getVisionPotionCount() {
        return this.myVisionPotionCount;
    }

    /**
     * If Health Potion count is at least one. Increases Health depending on
     * Health Potion value generated.
     */
    final protected void useHealthPotion() {
        //TODO delete this output once GUI is made, since this is VIEW
        if (this.getHealth() == this.getMaxHealth()) {
            System.out.println("Health is already full!");
            return;
        }
        if (this.myHealthPotionCount > 0) {

            int healthPotionMinAmount = 10;

            int healthPotionMaxAmount = 25;
            int health = this.MY_RAND.nextInt(healthPotionMaxAmount - healthPotionMinAmount) + healthPotionMinAmount;
            int result = this.getHealth() + health;
            this.setHealth(Math.min(result, this.getMaxHealth()));
            this.myHealthPotionCount--;
            System.out.println(this.getName() + " used a Health Potion. Recovered " + health + " of health.");
        } else {
            System.out.println("No Health Potions in Inventory!");
        }

    }

    /**
     * If Vision Potion count is at least one, interacts with a Dungeon object to
     * uncover Rooms that surround the player's current Room location.
     *
     * @param theDung the dungeon
     */
    final protected void useVisionPotion(final Dungeon theDung) {

        Room room;
        int xCoord = theDung.getCurrentRoom().getXCoord();
        int yCoord = theDung.getCurrentRoom().getYCoord();
        int length = theDung.getDungeon().length;
        if (this.myVisionPotionCount > 0) {
            //TODO delete this output once GUI is made, since this is VIEW
            System.out.println(this.getName() + " used a Vision Potion.");
            // reveal the eight rooms if possible
            if (xCoord + 1 < length) {
                room = theDung.getRoom(xCoord + 1, yCoord);
                assert room != null;
                room.unhide();
            }
            if (xCoord - 1 >= 0) {
                room = theDung.getRoom(xCoord - 1, yCoord);
                assert room != null;
                room.unhide();
            }
            if (yCoord + 1 < length) {
                room = theDung.getRoom(xCoord, yCoord + 1);
                assert room != null;
                room.unhide();
            }
            if (yCoord - 1 >= 0) {
                room = theDung.getRoom(xCoord, yCoord - 1);
                assert room != null;
                room.unhide();
            }
            // revealing diagonal rooms
            if (xCoord - 1 >= 0 && yCoord - 1 >= 0) {
                room = theDung.getRoom(xCoord - 1, yCoord - 1);
                assert room != null;
                room.unhide();
            }
            if (xCoord + 1 < length && yCoord - 1 >= 0) {
                room = theDung.getRoom(xCoord + 1, yCoord - 1);
                assert room != null;
                room.unhide();
            }
            if (xCoord - 1 >= 0 && yCoord + 1 < length) {
                room = theDung.getRoom(xCoord - 1, yCoord + 1);
                assert room != null;
                room.unhide();
            }
            if (xCoord + 1 < length && yCoord + 1 < length) {
                room = theDung.getRoom(xCoord + 1, yCoord + 1);
                assert room != null;
                room.unhide();
            }
            this.myVisionPotionCount--;
        } else {
            System.out.println("No Vision Potions in inventory!");
        }

    }

    /**
     * Returns the Players' status of inventory and health value.
     */
    @Override
    final public String toString() {
        //TODO delete this output once GUI is made, since this is VIEW
        return "HP: " +
                this.getHealth() +
                "\n" +
                this.getName() +
                "'s Inventory: \n" +
                "Health Potions: " +
                this.getHealthPotionCount() +
                "      Vision Potions: " +
                this.getVisionPotionCount() +
                "      Keys: " +
                this.getKeyCount() +
                "\n\n";
    }


}
