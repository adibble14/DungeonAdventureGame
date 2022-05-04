
import java.util.ArrayList;

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
	 * Holds all generated items. If room is an exit, entrance, or pit, no
	 * other items should generate
	 */
	private ArrayList <Item> myObjectList;
	
	/**
	 * X coord for this room
	 */
	private int myXCoord;
	
	/**
	 * Y coord for this room
	 */
	private int myYCoord;

	/**
	 * Enum room type
	 */
	private RoomType myType;
	
	/**
	 * Room constructor, initializes fields. Takes coords from Dungeon class
	 * Coords are index values.
	 * 
	 * @param theXCoord
	 * @param theYCoord
	 */
	protected Room(final int theXCoord, final int theYCoord, RoomType theType) {
		
		this.myXCoord = theXCoord;
		this.myYCoord = theYCoord;
		this.myObjectList = new ArrayList <Item> () ;
		this.myType = theType;
		if(theType == RoomType.ITEM_ROOM)
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

		Chest chest = new Chest();
		if(Tools.RANDOM.nextDouble() < chest.getMySpawnChance()) {
			this.myObjectList.add(chest);
		}
		Gold gold = new Gold();
		if(Tools.RANDOM.nextDouble() < gold.getMySpawnChance()) {
			this.myObjectList.add(gold);
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

		
	}
	
	/**
	 * Sets this room as an entrance
	 */
	final protected void setEntrance() {

	}
	
	/**
	 * Sets a win condition key in room.
	 * Key placed with intent for game balance.
	 */
	final protected void setKey() {

	}
	
	/**
	 * Sets the object of this room to empty, to null? Change this later possibly
	 */
	final protected void setEmpty() {

	}
	
	/**
	 * Checks to see if room is an exit
	 * @return
	 */
	final protected boolean isExit() {

	}
	
	/**
	 * Checks to see if room is an entrance
	 * @return
	 */
	final protected boolean isEntrance() {

	}
	
	/**
	 * Checks to see if room is empty
	 * @return
	 */
	final protected boolean isEmpty() {

	}
	
	/**
	 * Checks to see if room contains more than one item
	 * Only health, vision, potions and monsters can be stacked together
	 * @return
	 */
	final protected boolean containsMultiple() {

	}
	
	/**
	 * Checks to see if room contains a Monster
	 * @return
	 */
	final protected boolean containsMonster() {

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
			 + " of damage.");
			return;
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

	}
	
	 /**
	  * Removes question mark to uncover contents of room.
	  */
	final protected void unhide() {

	}

	
	/**
	 * Prints the item that is in the first index of object list.
	 */
	@Override
	final public String toString() {
		
		return this.myObjectList.get(0).toString();
	}
}
