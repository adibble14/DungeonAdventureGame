
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the Room class that contains randomly generated items, entrance, and exit. Interacts with
 * Hero object to give items and check for pit damage, win condition.
 * 
 * @author Mario Flores Vences
 * @version 031221
 *
 */
public class Room implements Serializable {

	/**
	 * Holds all generated items. If room is an exit, entrance, or pit, no
	 * other items should generate
	 */
	private Chest myChest;
	
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
	 * Boolean, whether a monster is in this room
	 */
	private boolean myContainsMonster;

	/**
	 * Whether or not we have discovered the room - for mini map
	 */
	private boolean myDiscovery;

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
		this.myChest = null;
		this.myContainsMonster = false;
		this.myDiscovery = false;
		this.myType = theType;
		if(theType == RoomType.ITEM_ROOM)
			this.myChest = new Chest();
		this.hide();
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

	final protected boolean getMyDiscovery(){
		return myDiscovery;
	}
	
	/**
	 * Sets the object of this room to empty, to null? Change this later possibly
	 */
	final protected void setEmpty() {
		this.myType = RoomType.EMPTY;
		this.myChest = null;
	}
	
	/**
	 * Checks to see if room is an exit
	 * @return
	 */
	final protected boolean isExit() {
		return this.myType == RoomType.EXIT;
	}

	
	/**
	 * Checks to see if room contains a Monster
	 * @return
	 */
	final protected boolean containsMonster() {
		return this.myContainsMonster;
	}

	/**
	 * Sets a monster into this room.
	 */
	final protected void setMonster() {
		this.myContainsMonster = true;
	}

	/**
	 * Sets the room to discovered
	 */
	final protected void setMyDiscovery(){this.myDiscovery = true;}
	/**
	 * Removes monster from this room by setting boolean field to false
	 */
	final protected void removeMonster() {
		this.myContainsMonster = false;
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
	  */ //TODO empty?
	final protected void unhide() {

	}

	
	/**
	 * Prints the item that is in the first index of object list.
	 */
	@Override
	final public String toString() {
		
		switch(this.myType) {
			case EXIT: return "O";
			case ENTRANCE: return "N";
			case EMPTY: return "E";
			case ITEM_ROOM: return "I";
			case BOSS_ROOM: return "B";
			case PIT: return "S";
			default: return "?";
		}
	}

	/**
	 * Returns this Room's type
	 * @return
	 */
	public RoomType getMyType() {
		return this.myType;
	}

	public void addItemsToPlayerInventory(final Hero theHero) {
		if(this.myChest != null) {
			this.myChest.use(theHero);
		}
	}
}
