import java.util.ArrayList;
import java.util.HashMap;

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
	 * Chance of an Item_Room to appear
	 */
	private  double myItemRoomChance;

	/**
	 * Chance of spawning a pit in room
	 */
	private double myPitSpawnChance;

	/**
	 * Constructs Dungeon matrix of Room objects and places Hero object in 
	 * the entrance of the Dungeon. Randomly generates the entrance and exit Rooms
	 * Randomly places keys in rooms.
	 * 
	 * @param theHero Hero specified when user starts game, used to set in the first room
	 */
	protected Dungeon(final Hero theHero, final int theSize, final double theItemRoomChance) {

		this.myItemRoomChance = theItemRoomChance;
		this.myPitSpawnChance = .1; //TODO: implement arguments after done testing
		this.myDungeon = this.generateDungeon(theSize, theHero);
		System.out.println(this);
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
		if(theRow < this.myDungeon.length && theColumn < this.myDungeon.length && theRow >= 0 && theColumn >= 0) {
			return this.myDungeon[theRow][theColumn];
		}

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
		
		/*if(this.myCurrentRoom != null) {
			this.myCurrentRoom.removePlayer();
		}*/
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
	//TODO Modify theChoice parameter so we can compare the x and y coordinates of a Room
	final protected void movePlayer(final String theChoice, final Hero theHero) {

		ArrayList<Room> neighbors = (ArrayList<Room>) Tools.GET_NEIGHBORS(this.myDungeon, this.myCurrentRoom).values();
		for(Room r : neighbors) {
			if(r.getXCoord() == -1 && r.getYCoord() == -1 ) {
				r.placePlayer(theHero);
			}
		}

	}

	/**
	 * Prints the Dungeon. Shows what the types for each Room.
	 * For debug purposes at this point.
	 * @return
	 */
	//TODO delete once GUI is made
	@Override
	final public String toString() {
		
		StringBuilder roomContents = new StringBuilder();

		for(int i = 0; i < myDungeon.length; i++) {
			for(int j = 0; j < myDungeon.length; j++) {
				if(myDungeon[i][j] == null)
					roomContents.append("* ");
				else {
					myDungeon[i][j].unhide();
					roomContents.append(myDungeon[i][j].toString() + " ");
				}
			}
			roomContents.append("\n");
		}
		return roomContents.toString();
	}

	private  Room[][] generateDungeon(final int theSize, Hero theHero) {
		Room[][] dung = new Room[theSize][theSize];
		int x = Tools.RANDOM.nextInt(0, theSize -1);
		int y = Tools.RANDOM.nextInt(0, theSize -1);
		Room entrance = new Room(x,y, RoomType.ENTRANCE);
		setCurrentRoom(entrance, theHero);
		dung [x][y] = entrance;
		System.out.println("Entrance Coords: " + x + " " + y);
		x = Tools.RANDOM.nextInt(0, theSize-1);
		y = Tools.RANDOM.nextInt(0, theSize-1);
		Room exit = new Room(x,y, RoomType.EXIT);
		dung[x][y] = exit;
		System.out.println("Exit Coords: " + x + " " + y);
		this.DFSGenerateRooms(dung, entrance, RoomType.EXIT);
		x = Tools.RANDOM.nextInt(0, theSize-1);
		y = Tools.RANDOM.nextInt(0, theSize-1);
		Room bossRoom = new Room(x,y,RoomType.BOSS_ROOM);
		dung[x][y] = bossRoom;
		x = Tools.RANDOM.nextInt(0,theSize-1);
		y = Tools.RANDOM.nextInt(0,theSize-1);
		Room unique = new Room(x,y,RoomType.UNIQUE);
		dung[x][y] = unique;
		this.DFSGenerateRooms(dung, bossRoom, RoomType.UNIQUE);
		dung[x][y].setEmpty();
		return dung;
	}

	/**
	 * DFS like algorithm. Randomly walks the matrix starting from given start point.
	 * Creating rooms in null spaces as it goes.
	 * Until the end point is found.
	 *
	 * @param theRooms matrix to recurse
	 * @param theStart starting point of the recursion
	 */
	private boolean DFSGenerateRooms(Room[][] theRooms, Room theStart, RoomType theEnd) {
		HashMap<int[], Room> neighbors = Tools.GET_NEIGHBORS(theRooms, theStart);
		ArrayList<int[]> keys = new ArrayList<>(neighbors.keySet());

		while(keys.size() > 0) {
			int choice = Tools.RANDOM.nextInt(keys.size());
			int [] chosen = keys.get(choice);
			keys.remove(choice);
			if(neighbors.get(chosen) == null) {
				Room room = new Room(chosen[0], chosen[1], getRandomRoomType());
				theRooms[chosen[0]][chosen[1]] = room;
				if(DFSGenerateRooms(theRooms, room, theEnd)) return true;
			}
			else if(neighbors.get(chosen).getMyType() == theEnd)
				return true;
		}
		return false;
	}

	/**
	 * Randomly chooses between Empty and Item_Room RoomTypes.
	 * Item_Room type should appear less often.
	 *
	 * @return the RoomType
	 */
	private RoomType getRandomRoomType() {
		Double dub = Tools.RANDOM.nextDouble();
		if(dub < this.myPitSpawnChance) {
			return RoomType.PIT;
		}
		if( dub < this.myItemRoomChance) {
			return RoomType.ITEM_ROOM;
		}
		return RoomType.EMPTY;
	}
}

