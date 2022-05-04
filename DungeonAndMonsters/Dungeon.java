
import java.util.Random;

/**
 * Builds a matrix consisting of Room objects. Interacts with Hero objects as well.
 * 
 * @author Mario Flores Vences
 *
 */

public class Dungeon {

	/**
	 * Matrix of Room objects
	 */
	private final Room [][] myDungeon;
	
	/**
	 * References the current room player is in.
	 */
	private Room myCurrentRoom;

	/**
	 * Constructs Dungeon matrix of Room objects and places Hero object in 
	 * the entrance of the Dungeon. Randomly generates the entrance and exit Rooms
	 * Randomly places keys in rooms.
	 * 
	 * @param theHero Hero specified when user starts game, used to set in the first room
	 */
	//TODO could add room size as parameter as well as key count
	protected Dungeon(final Hero theHero) {

		//Random Number object
		Random MY_RAND = new Random();
		// Rooms are always 5x5
		this.myDungeon = GenerateDungeon.GenerateDungeon(10);
		// Number of keys needed for win condition. - Always need 2 keys
	}
	
	/**
	 * Getter for the current room our Hero is in
	 * @return current room
	 */
	final protected Room getCurrentRoom() {
		return this.myCurrentRoom;
	}
	
	/**
	 * Gets a room in the Dungeon Matrix.
	 * 
	 * @param theRow Row specifier for the Matrix, the first array in our 2D array
	 * @param theColumn Column specifier for the Matrix, the second array in our 2D array
	 * @return The room specified by the row and column passed in.
	 */
	final protected Room getRoom(final int theRow, final int theColumn) {
		if(theRow < this.myDungeon.length && theColumn < this.myDungeon.length && theRow > 0 && theColumn > 0) {
			return this.myDungeon[theRow][theColumn];
		}
		//TODO output these errors in the GUI
		System.out.println("No such row or column.");
		return null;
		
	}
	
	/**
	 * Getter for Dungeon variable
	 * @return Returns the full 2D array Matrix
	 */
	final protected Room [][] getDungeon() {
		return this.myDungeon;
	}
	
	/**
	 * Sets the current room field. Places Hero in room.
	 * 
	 * @param theRoom Room we wish to place the Hero in
	 * @param theHero Hero
	 */
	final protected void setCurrentRoom(final Room theRoom, final Hero theHero) {
		
		if(this.myCurrentRoom != null) {
			this.myCurrentRoom.removePlayer();
		}
		this.myCurrentRoom = theRoom;
		this.myCurrentRoom.placePlayer(theHero);
	}
	
	/**
	 * Uncovers all Rooms in Dungeon matrix. Used for dev cheats
	 */
	final protected void revealRooms() {
		for(int i = 0; i < this.myDungeon.length; i++) {
			for(int j = 0; j < this.myDungeon[i].length; j++) {
				this.myDungeon[i][j].unhide();
			}
		}
	}
	
	/**
	 * Places the player's Hero object in the direction the player wants.
	 * Checks to see if move is valid.
	 * 
	 * @param theChoice Direction the user chose to move the Hero
	 * @param theHero The hero
	 */
	//TODO add enums instead?
	final protected void movePlayer(final String theChoice, final Hero theHero) {

		// Trys to set the Hero into the room correlating to theChoice, and if it fails catch the ArrayOutOfBounds
		// exception and prints an error
		if(theChoice.equalsIgnoreCase("S")) {
			try {
				this.setCurrentRoom(this.myDungeon[this.myCurrentRoom.getXCoord() + 1][this.myCurrentRoom.getYCoord()], theHero);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				//TODO output these errors in the GUI
				System.out.println("Cannot go further down!");
			}
		}
		else if(theChoice.equalsIgnoreCase("N")) {
			try {
				this.setCurrentRoom(this.myDungeon[this.myCurrentRoom.getXCoord() - 1][this.myCurrentRoom.getYCoord()], theHero);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Cannot go further up!");
			}
		}
		else if(theChoice.equalsIgnoreCase("E")) {
			try {
				this.setCurrentRoom(this.myDungeon[this.myCurrentRoom.getXCoord()][this.myCurrentRoom.getYCoord() + 1], theHero);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Cannot go further right!");
			}
		}
		else if(theChoice.equalsIgnoreCase("W")) {
			try {
				this.setCurrentRoom(this.myDungeon[this.myCurrentRoom.getXCoord()][this.myCurrentRoom.getYCoord() - 1], theHero);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Cannot go further left!");
			}
		}
	}
	
	/**
	 * Returns the String representation of the Rooms and their contents.
	 * 
	 */
	//TODO delete once GUI is made
	@Override
	final public String toString() {
		
		StringBuilder roomContents = new StringBuilder();
		
		roomContents.append("***********");
		roomContents.append("\n");
		roomContents.append("*");
		
		for(int i = 0; i < this.myDungeon.length; i++) {
			for(int j = 0; j < this.myDungeon.length; j++) {
				
				roomContents.append(this.myDungeon[i][j].toString());
				
				if(j < this.myDungeon.length - 1) {
					roomContents.append("|");
				}
				else if(i < this.myDungeon.length - 1) {
					roomContents.append("*");
					roomContents.append("\n");
					roomContents.append("*-*-*-*-*-*");
					roomContents.append("\n");
					roomContents.append("*");
				}
				if(i == this.myDungeon.length - 1 && j == this.myDungeon.length - 1) {
					roomContents.append("*");
					roomContents.append("\n");
					roomContents.append("***********");
				}
			}
		}
		return roomContents.toString();
	}
	

}

