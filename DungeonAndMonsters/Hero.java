import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

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
     * Number of keys in Hero's inventory
     */
    private final int myKeyCount;

    /**
     * Inventory of this player. Underneath data structure is a hashmap
     */
    private final PlayerInventory myInventory;

    /**
     * the health potions
     */
    private final ArrayList<HealthPotion> myHealthPotions;

    /**
     * the vision potions
     */
    private final ArrayList<VisionPotion> myVisionPotions;


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
        this.myKeyCount = 0;
        this.myInventory = new PlayerInventory();
        this.myHealthPotions = new ArrayList<>();
        this.myVisionPotions = new ArrayList<>();
    }

    /**
     * Get method for turn count field
     * @return returns turn count value
     */
    final protected int getTurnCount() {

        return this.myTurnCount;
    }

    /**
     * Get method for block chance field
     * @return returns block chance value
     */
    final protected double getBlockChance() {

        return this.myBlockChance;
    }

    /**
     * Set method for turn count field
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
     * @return returns true if block chance value is greater than random generated value
     */
    final protected boolean block() {
        return this.getBlockChance() > Tools.RANDOM.nextDouble();
    }

    /**
     * Abstract method for child classes. Each child should have a unique special.
     * @param theChar object to deal damage to
     */
    protected abstract void special(final DungeonCharacter theChar);

    /**
     * adds item to hero inventory from chest
     * @param theItem which items to add
     * @param theAmount the number of items
     */
    final protected void addInventory(final Item theItem, final int theAmount) {
        if(theItem.getClass() == HealthPotion.class) {
            this.myInventory.addItem(ItemType.HEALTH_POTION, theAmount);
            this.myHealthPotions.add((HealthPotion) theItem);
        } else if(theItem.getClass() == VisionPotion.class) {
            this.myInventory.addItem(ItemType.VISION_POTION, theAmount);
            this.myVisionPotions.add((VisionPotion) theItem);
        }
    }

    /**
     * Returns the key count in Hero inventory
     * @return the number of keys
     */
    final protected int getKeyCount() {
        return this.myKeyCount;
    }

    /**
     * Returns the Health Potion count in Hero inventory
     * @return the number of health potions
     */
    final protected int getHealthPotionCount() {
        return this.myInventory.getItemCount(ItemType.HEALTH_POTION);
    }

    /**
     * Returns the Vision Potion count in Hero inventory
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
        int currentHealth = this.getHealth();
        if(this.myInventory.getItemCount(ItemType.HEALTH_POTION) > 0) {
            this.myHealthPotions.get(0).use(this);
            this.myHealthPotions.remove(0);
            this.myInventory.removeItem(ItemType.HEALTH_POTION);
        }
        return this.getHealth() - currentHealth;
    }

    /**
     * If Vision Potion count is at least one, interacts with a Dungeon object to
     * uncover Rooms that surround the player's current Room location.
     * @param theDung the dungeon
     */
    final protected void useVisionPotion(final Dungeon theDung) {
        if(this.myInventory.getItemCount(ItemType.VISION_POTION) > 0) {
            this.myVisionPotions.get(0).use(theDung);
            this.myVisionPotions.remove(0);
            this.myInventory.removeItem(ItemType.VISION_POTION);
        }
    }

    /**
     * Returns the Players' status of inventory and health value.
     */
    @Override
    final public String toString() {
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

    /**
     * @return special info about the hero
     */
    public abstract String getSpecialInfo();
}
