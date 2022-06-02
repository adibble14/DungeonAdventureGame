
import java.io.Serializable;

/**
 * This is the Room class that contains randomly generated items, entrance, and exit. Interacts with
 * Hero object to give items and check for pit damage, win condition.
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
	 * Whether we have discovered the room - for mini map
	 */
	private boolean myDiscovery;

	/**
	 * Room constructor, initializes fields. Takes coords from Dungeon class
	 * Coords are index values.
	 * 
	 * @param theXCoord the x
	 * @param theYCoord the y
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
	}
	
	/**
	 * get method for x coord value
	 * @return the x
	 */
	final protected int getXCoord() {
		return this.myXCoord;
	}
	
	/**
	 * get method for y coord value
	 * @return the y
	 */
	final protected int getYCoord() {
		return this.myYCoord;
	}

	final protected void setXCoord(int theXCoord){
		this.myXCoord = theXCoord;
	}
	final protected void setYCoord(int theYCoord){
		this.myYCoord = theYCoord;
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
	 * Checks to see if room contains a Monster
	 * @return boolean if the room contains a monster
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
	 * Prints the item that is in the first index of object list.
	 */
	@Override
	final public String toString() {
		if(this.myContainsMonster) {
			return "M";
		}
		return switch (this.myType) {
			case EXIT -> "O";
			case ENTRANCE -> "N";
			case EMPTY -> "E";
			case ITEM_ROOM -> "I";
			case BOSS_ROOM -> "B";
			case PIT -> "S";
			default -> "?";
		};
	}

	/**
	 * Returns this Room's type
	 * @return type
	 */
	public RoomType getMyType() {
		return this.myType;
	}

	/**
	 * adds items to inventory
	 * @param theHero the hero
	 */
	public void addItemsToPlayerInventory(final Hero theHero) {
		if(this.myChest != null) {
			this.myChest.use(theHero);
		}
	}
}
