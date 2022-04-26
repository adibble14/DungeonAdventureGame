/*
 * TCSS 143 B - Winter 2021
 * Instructor: Tom Capaul
 * Programming Assignment 5 - Dungeon Adventure
 * Due Date 03/17/21
 * 
 */

import java.util.Random;

/**
 * Builds a matrix consisting of Room objects. Interacts with Hero objects as well.
 * 
 * @author Mario Flores Vences
 *
 */

public class Dungeon {
	
	/**
	 * Random Number object
	 */
	final private Random MY_RAND;
	
	/**
	 * Matrix of Room objects
	 */
	private Room [][] myDungeon;
	
	/**
	 * References the current room player is in.
	 */
	private Room myCurrentRoom;
	
	/**
	 * Number of keys needed for win condition.
	 */
	private int myKeyCount;
	
	/**
	 * Constructs Dungeon matrix of Room objects and places Hero object in 
	 * the entrance of the Dungeon. Randomly generates the entrance and exit Rooms
	 * Randomly places keys in rooms.
	 * 
	 * @param theHero
	 */
	//TODO could add room size as parameter as well as key count
	protected Dungeon(final Hero theHero) {
		
		this.MY_RAND = new Random();
		// Rooms are always 5x5
		this.myDungeon = new Room[5][5];
		// Always need 2 keys
		this.myKeyCount = 2;

		//TODO Implement pillars of OO here?

		// Construct dungeon
		for(int i = 0; i < this.myDungeon.length; i++) {
			for(int j = 0; j < this.myDungeon[i].length; j++) {
				
				this.myDungeon[i][j] = new Room(i, j);
			}
		}
		
		
		// set up entrance & exit & keys. Set player position and current room
		Room room = this.myDungeon[0][0];
		room.setEntrance();
		this.setCurrentRoom(room, theHero);
		
		// reassigning room TODO what is this doing?
		room = this.myDungeon[(this.myDungeon.length - 1)][MY_RAND.nextInt(this.myDungeon.length-1)];
		room.setExit();
		// assigning keys, keys should not spawn in exit or entrance
		int count = 0;
		while(count < this.myKeyCount) {
			room = this.myDungeon[MY_RAND.nextInt(this.myDungeon.length)][MY_RAND.nextInt(this.myDungeon.length)];
			if(!room.isEntrance() && !room.isExit()) {
				room.setKey();
				count++;
			}
		}
	}
	
	/**
	 * Returns current room
	 * @return
	 */
	final protected Room getCurrentRoom() {
		return this.myCurrentRoom;
	}
	
	/**
	 * Gets a room in the Dungeon Matrix.
	 * 
	 * @param theRow
	 * @param theColumn
	 * @return
	 */
	final protected Room getRoom(final int theRow, final int theColumn) {
		if(theRow < this.myDungeon.length && theColumn < this.myDungeon.length) {
			return this.myDungeon[theRow][theColumn];
		}
		System.out.println("No such row or column.");
		return null;
		
	}
	
	/**
	 * Returns the Dungeon Matrix
	 * 
	 * @return
	 */
	final protected Room [][] getDungeon() {
		return this.myDungeon;
	}
	
	/**
	 * Sets the current room field. Places Hero in room.
	 * 
	 * @param theRoom
	 * @param theHero
	 */
	final protected void setCurrentRoom(final Room theRoom, final Hero theHero) {
		
		if(this.myCurrentRoom != null) {
			this.myCurrentRoom.removePlayer();
		}
		this.myCurrentRoom = theRoom;
		this.myCurrentRoom.placePlayer(theHero);
	}
	
	/**
	 * Unhides all Rooms in Dungeon matrix. Used for dev cheats
	 */
	final protected void unhideRooms() {
		
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
	 * @param theChoice
	 * @param theHero
	 */
	final protected void movePlayer(final String theChoice, final Hero theHero) {
		
		if(theChoice.equalsIgnoreCase("S")) {
			try {
				this.setCurrentRoom(this.myDungeon[this.myCurrentRoom.getXCoord() + 1][this.myCurrentRoom.getYCoord()], theHero);
			}
			catch(ArrayIndexOutOfBoundsException e) {
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
	@Override
	final public String toString() {
		
		StringBuffer string = new StringBuffer();
		
		string.append("***********");
		string.append("\n");
		string.append("*");
		
		for(int i = 0; i < this.myDungeon.length; i++) {
			for(int j = 0; j < this.myDungeon.length; j++) {
				
				string.append(this.myDungeon[i][j].toString());
				
				if(j < this.myDungeon.length - 1) {
					string.append("|");
				}
				else if(i < this.myDungeon.length - 1) {
					string.append("*");
					string.append("\n");
					string.append("*-*-*-*-*-*");
					string.append("\n");
					string.append("*");
				}
				if(i == this.myDungeon.length - 1 && j == this.myDungeon.length - 1) {
					string.append("*");
					string.append("\n");
					string.append("***********");
				}
			}
		}
		return string.toString();
	}
	

}

