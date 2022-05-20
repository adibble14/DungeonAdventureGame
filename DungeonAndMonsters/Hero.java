import javax.swing.*;
import java.io.Serializable;

/**
 * This abstract class inherits from DungeonCharacter super class. Adds fields and methods used exclusively
 * by hero objects.
 */

public abstract class Hero extends DungeonCharacter implements Serializable {

    /**
     * Stores hero object's block chance value.
     */
    private double myBlockChance;

    /**
     * Stores hero object's turn count value. Changes depending on enemy speed value.
     */

    private int myTurnCount;


    /**
     * Number of Vision potions in Hero's Inventory
     */
    private int myVisionPotionCount;

    /**
     * Number of keys in Hero's inventory
     */
    private int myKeyCount;

    /**
     * Inventory of this player. Underneath data structure is a hashmap
     */
    private PlayerInventory myInventory;

    /**
     * Child constructor of Hero class. Calls super class constructor to init fields.
     * Inits Hero class exclusive fields.
     *  @param theName        value given by user
     * @param theHealth      value given by child class
     * @param theSpeed       value given by child class
     * @param theMaxDamage   value given by child class
     * @param theMinDamage   value given by child class
     * @param theAccuracy    value given by child class
     * @param theBlockChance value given by child class
     */
    protected Hero(final String theName, final int theHealth, final int theSpeed, final int theMaxDamage, final int theMinDamage,
                   final double theAccuracy, final double theBlockChance, final ImageIcon theSprite, final ImageIcon theInGameSprite) {

        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theSprite, theInGameSprite);
        this.setBlockChance(theBlockChance);
        this.myVisionPotionCount = 0;
        this.myKeyCount = 0;
        this.myInventory = new PlayerInventory();
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

        int turns = this.getTurnCount();

        while (theChar.isAlive() && turns > 0) {
            
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
        this.myInventory.addItem(ItemType.HEALTH_POTION, theCount);
    }

    /**
     * Set method for number of vision potions
     *
     * @param theCount the number of vision potions
     */
    final protected void setVisionPotions(final int theCount) {
        this.myInventory.addItem(ItemType.VISION_POTION, theCount);
    }

    /**
     * Depending on String value, increases count of potions and key count
     *
     * @param theItem the input
     */
    final protected void addInventory(final Item theItem, final int theAmount) {
        if(theItem.getClass() == HealthPotion.class) {
            this.myInventory.addItem(ItemType.HEALTH_POTION, theAmount);
        } else if(theItem.getClass() == VisionPotion.class) {
            this.myInventory.addItem(ItemType.VISION_POTION, theAmount);
        } else if (theItem.getClass() == Pillar.class) {
            this.myInventory.addPillar((Pillar) theItem);
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
        return this.myInventory.getItemCount(ItemType.HEALTH_POTION);
    }

    /**
     * Returns the Vision Potion count in Hero inventory
     *
     * @return the number of vision potions
     */
    final protected int getVisionPotionCount() {
        return this.myInventory.getItemCount(ItemType.VISION_POTION);
    }

    /**
     * Returns the amount of gold in Hero's inventory
     * @return gold in integer form
     */
    final protected int getGoldCount(){return this.myInventory.getMyGoldAmount();}

    /**
     * Sets gold amount of player
     */
    final protected void setGoldAmount(final int theValue) {
        this.myInventory.addGold(theValue);
    }
    /**
     * If Health Potion count is at least one. Increases Health depending on
     * Health Potion value generated.
     */
    final protected int useHealthPotion() {
        int healthPotionMinAmount = 10;

        int healthPotionMaxAmount = 25;
        int health = this.MY_RAND.nextInt(healthPotionMaxAmount - healthPotionMinAmount) + healthPotionMinAmount;
        int result = this.getHealth() + health;
        this.setHealth(Math.min(result, this.getMaxHealth()));

        return health;
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

    /**
     * Returns this player's inventory object
     */
    protected PlayerInventory getMyInventory() {
        return this.myInventory;
    }
}
