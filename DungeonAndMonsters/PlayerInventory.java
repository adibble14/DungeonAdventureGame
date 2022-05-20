import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents the Players inventory
 */

public class PlayerInventory implements Serializable {

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
     * Removes an Item from the player's inventory
     */
    public void removeItem(final ItemType theItem) {
        if(this.myInventory.get(theItem)  != null && this.myInventory.get(theItem) > 0) {
            this.myInventory.put(theItem, this.myInventory.get(theItem) - 1);
            if(getItemCount(theItem) == 0){
                BattleGUI.disableHealthButton();
            }
        }

    }

    /**
     * Returns the count of the passed in item.
     * @param theItem
     * @return item count or null if item does not exist in inventory map
     */
    public int getItemCount(final ItemType theItem) {
        return this.myInventory.getOrDefault(theItem, 0);
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
     * Returns an array of all items in player inventory, except gold
     * @return A String representation of an item's name.
     */
    public Object [] getAllItems() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<ItemType> itemTypes = new ArrayList<>(this.myInventory.keySet());
        for(ItemType i : itemTypes) {
            for(int j = 0; j < this.myInventory.get(i); j++) {
                result.add(i.toString());
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result.toArray();
    }

    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("Player Inventory: ");
        string.append("\n");
        Object [] items = this.getAllItems();
        for(Object i : items) {
            string.append((String) i);
            string.append("\n");
        }
        return string.toString();
    }
}
