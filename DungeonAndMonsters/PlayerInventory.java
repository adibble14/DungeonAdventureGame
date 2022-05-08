import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the Players inventory
 */

public class PlayerInventory {

    /**
     * Map data structure that will hold Item and Item count info.
     */
    private HashMap<ItemType, Integer> myInventory;

    /**
     * Map data structure that will hold Pillars
     */
    private HashMap<Pillar,Integer> myPillars;

    /**
     * Gold currency of the player
     */
    private int myGoldAmount;

    /**
     * Constructor of this class
     */
    public PlayerInventory() {
        this.myInventory = new HashMap();
        this.myGoldAmount = 0;
        this.myPillars = new HashMap<>();
    }

    /**
     * adds the passed in argument value to this gold amount
     * @param theValue
     */
    public void addGold(final int theValue) {
        this.myGoldAmount += theValue;
    }

    /**
     * Returns the players gold amount
     * @return
     */
    public int getMyGoldAmount() {
        return this.myGoldAmount;
    }

    /**
     * Adds an Item to the players inventory
     * @param theItem
     */
    public void addItem(final ItemType theItem, final int theAmount) {
        this.myInventory.put(theItem, this.myInventory.getOrDefault(theItem,0) + theAmount);
    }

    /**
     * Returns the count of the passed in item.
     * @param theItem
     * @return item count or null if item does not exist in inventory map
     */
    public int getItemCount(final ItemType theItem) {
        return this.myInventory.get(theItem);
    }

    /**
     * Adds a pillar to pillar inventory
     * @param thePillar
     */
    public void addPillar(final Pillar thePillar) {
        this.myPillars.put(thePillar, 1);
    }

    /**
     * Returns the current Pillars held in pillar inventory
     */
    public Pillar[] getPillars() {
        return this.myPillars.keySet().toArray(new Pillar[this.myPillars.size()]);
    }
    /**
     * Should return all item counts, will probably not be used.
     * Not implemented yet.
     * @return
     */
    public int getAllItemCounts() {
    //TODO: Decide if this is needed.
        return -1;
    }
}
