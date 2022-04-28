
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the Room class that contains randomly generated items, entrance, and exit. Interacts with
 * Hero object to give items and check for pit damage, win condition.
 * 
 * @author Mario Flores Vences
 * @version 031221
 *
 */
public class Room {
	
	/**
	 * Random number object
	 */
	final private Random MY_RAND;
	
	/**
	 * Chance value to generate a health potion in room
	 */
	final private double MY_HEAL_POTION_CHANCE;
	
	/**
	 * Chance value to generate a vision potion in room
	 */
	final private double MY_VISION_POTION_CHANCE;
	
	/**
	 * Chance value to generate a pit in room. No other items can generate if
	 * this occurs
	 */
	final private double MY_PIT_CHANCE;
	
	/**
	 * Chance value to generate a Monster object in room.
	 */
	final private double MY_MONSTER_CHANCE;
	
	/**
	 * Amount of damage to player if player steps in room
	 */
	final private int MY_PIT_DAMAGE;
	
	/**
	 * Holds all generated items. If room is an exit, entrance, or pit, no
	 * other items should generate
	 */
	private ArrayList <String> myObjectList;
	
	/**
	 * X coord for this room
	 */
	private int myXCoord;
	
	/**
	 * Y coord for this room
	 */
	private int myYCoord;

	/**
	 * Item Legend:
	 * 		X - Monster
	 * 		P - Pit
	 * 		I - Entrance
	 * 		O - Exit
	 * 		V - Vision Potion
	 * 		H - Healing Potion
	 * 		E - Empty Room
	 * 		M - Multiple Items
	 * 		K - key (or crown piece) need two to clear dungeon
	 * 		C - Player Current Location
	 */
	
	/**
	 * Room constructor, initializes fields. Takes coords from Dungeon class
	 * Coords are index values.
	 * 
	 * @param theXCoord
	 * @param theYCoord
	 */
	protected Room(final int theXCoord, final int theYCoord) {
		
		this.myXCoord = theXCoord;
		this.myYCoord = theYCoord;
		this.MY_RAND = new Random();
		this.MY_HEAL_POTION_CHANCE = .1;
		this.MY_VISION_POTION_CHANCE = .05;
		this.MY_PIT_CHANCE = .1;
		this.MY_MONSTER_CHANCE = .1;
		this.myObjectList = new ArrayList <String> () ;
		this.MY_PIT_DAMAGE = 15;
		this.generateItem();
		this.hide();
	}
	
	/**
	 * Generates a random object to be placed in the room
	 * If pit is generated, nothing else can be generated
	 * Other than the pit, other items can be present in room.
	 * 
	 */
	final private void generateItem() {
		
		if(this.MY_RAND.nextDouble() <= this.MY_PIT_CHANCE) {
			this.myObjectList.add("P");
			return;
		}
		if(this.MY_RAND.nextDouble() <= this.MY_MONSTER_CHANCE) {
			this.myObjectList.add("X");
		}
		if(this.MY_RAND.nextDouble() <= this.MY_VISION_POTION_CHANCE) {
			this.myObjectList.add("V");
		}
		if(this.MY_RAND.nextDouble() <= this.MY_HEAL_POTION_CHANCE) {
			this.myObjectList.add("H");
		}
		if(this.myObjectList.isEmpty()) {
			this.setEmpty();
		}
		else if(this.containsMultiple()) {
			this.myObjectList.add(0, "M");
		}
	}
	
	/**
	 * get method for x coord value
	 * @return
	 */
	final protected int getXCoord() {
		return this.myXCoord;
	}
	
	/**
	 * get method for y coord value
	 * @return
	 */
	final protected int getYCoord() {
		return this.myYCoord;
	}
	
	/**
	 * Sets this room as an exit
	 */
	final protected void setExit() {
		
		this.myObjectList.clear();
		this.myObjectList.add("?");
		this.myObjectList.add("O");
		
	}
	
	/**
	 * Sets this room as an entrance
	 */
	final protected void setEntrance() {
		
		this.myObjectList.clear();
		this.myObjectList.add("I");
	}
	
	/**
	 * Sets a win condition key in room.
	 * Key placed with intent for game balance.
	 */
	final protected void setKey() {
		
		this.myObjectList.clear();
		this.myObjectList.add("?");
		this.myObjectList.add("K");
	}
	
	/**
	 * Sets the object of this room to empty, to null? Change this later possibly
	 */
	final protected void setEmpty() {
		
		if(this.myObjectList.contains("I") || this.myObjectList.contains("O")
				|| this.myObjectList.contains("P")) {
			return;
		}
		this.myObjectList.clear();
		this.myObjectList.add("E");
	}
	
	/**
	 * Checks to see if room is an exit
	 * @return
	 */
	final protected boolean isExit() {
		if (this.myObjectList.contains("O")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if room is an entrance
	 * @return
	 */
	final protected boolean isEntrance() {
		if (this.myObjectList.contains("I")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if room is empty
	 * @return
	 */
	final protected boolean isEmpty() {
		return this.myObjectList.contains("E");
	}
	
	/**
	 * Checks to see if room contains more than one item
	 * Only health, vision, potions and monsters can be stacked together
	 * @return
	 */
	final protected boolean containsMultiple() {
		
		if(this.myObjectList.size() > 1) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks to see if room contains a Monster
	 * @return
	 */
	final protected boolean containsMonster() {
		if(this.myObjectList.contains("X")) {
			return true;
		}
		return false;
	}
	/**
	 * Removes Monster item from room
	 */
	final protected void removeMonster() {
		this.myObjectList.remove("X");
	}
	
	/**
	 * Interacts with Hero object. Increases Hero inventory, depending
	 * what item is in room. Damages player if room is a pit.
	 * @param theHero
	 */
	final protected void placePlayer(final Hero theHero) {

		// Start by removing the question mark representing the room
		this.unhide();
		// Adds C (player) to the room
		this.myObjectList.add(0,"C");

		// Empty rooms do nothing
		if(this.isEmpty()) {
			return;
		}
		// Pitfall is triggered
		//TODO delete this output once GUI is made, since this is VIEW
		if(this.myObjectList.contains("P")) {
			theHero.setHealth(theHero.getHealth() - this.MY_PIT_DAMAGE);
			System.out.println(theHero.getName() + " fell into a pit trap! Took " 
			+ this.MY_PIT_DAMAGE + " of damage.");
			return;
		}

		// Adds any objects in the room to the player's inventory
		for(String s : this.myObjectList) {
			theHero.addInventory(s);
		}
	}
	
	/**
	 * Removes player from room and clears room. To simulate
	 * player picking up items.
	 */
	final protected void removePlayer() {
		this.myObjectList.remove("C");
		this.setEmpty();
	}
	
	/**
	 * Appends a question mark at the beginning of list
	 * to hide items in room.
	 * 
	 */
	final protected void hide() {
		this.myObjectList.add(0, "?");
	}
	
	 /**
	  * Removes question mark to uncover contents of room.
	  */
	final protected void unhide() {
		this.myObjectList.remove("?");
	}
	
	/**
	 * Checks to see if player is present in room
	 * @return
	 */
	//TODO not being used
	final private boolean containsHero() {
		
		if(this.myObjectList.get(0).equalsIgnoreCase("C")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Prints the item that is in the first index of object list.
	 */
	@Override
	final public String toString() {
		
		return this.myObjectList.get(0).toString();
	}
}
