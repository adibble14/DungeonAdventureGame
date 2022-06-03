import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Builds a matrix consisting of Room objects. Interacts with Hero objects as well.
 */

public class Dungeon implements Serializable {

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
	private final transient double myItemRoomChance;

	/**
	 * Chance of spawning a pit in room
	 */
	private final transient double myPitSpawnChance;
	/**
	 * Current dungeon that the player is on. Must clear 4 to beat the game
	 */
	private final int myCurrentDungeonNumber;

	/**
	 * number of dungeons completed
	 */
	private final int myDungeonsPassed;

	/**
	 * the chance a monster can spawn in a room
	 */
	private transient final double myMonsterSpawnChance = .05;

	/**
	 * Constructs Dungeon matrix of Room objects and places Hero object in 
	 * the entrance of the Dungeon. Randomly generates the entrance and exit Rooms
	 * Randomly places keys in rooms.
	 */
	protected Dungeon(final int theSize, final double theItemRoomChance, final int theNumDungeonsPassed, final int theCurrentDungeon) {

		this.myItemRoomChance = theItemRoomChance;
		this.myPitSpawnChance = .1;
		this.myDungeon = this.generateDungeon(theSize);
		this.myDungeonsPassed = theNumDungeonsPassed;
		this.myCurrentDungeonNumber = theCurrentDungeon;
		this.placeMonsters();
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
			Room room = this.myDungeon[theRow][theColumn];
			if(room != null){
				room.setXCoord(theRow);
				room.setYCoord(theColumn);
			}
			return room;
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
	 */
	final protected void setCurrentRoom(final Room theRoom) {
		this.myCurrentRoom = theRoom;
	}

	/**
	 * Prints the Dungeon. Shows what the types for each Room.
	 * For debug purposes at this point.
	 * @return the dungeon as a string
	 */
	@Override
	final public String toString() {
		
		StringBuilder roomContents = new StringBuilder();

		for (Room[] rooms : myDungeon) {
			for (int j = 0; j < myDungeon.length; j++) {
				if (rooms[j] == null)
					roomContents.append("* ");
				else {
					roomContents.append(rooms[j].toString()).append(" ");
				}
			}
			roomContents.append("\n");
		}
		return roomContents.toString();
	}

	/**
	 * Generates the Dungeon
	 * @param theSize the size of the dungeon
	 * @return a dungeon
	 */
	private Room[][] generateDungeon(final int theSize) {
		Room[][] dung = new Room[theSize][theSize];

		int entranceXCoord = Tools.RANDOM.nextInt(0, theSize/3-1);
		int entranceYCoord = Tools.RANDOM.nextInt(0, theSize-1);
		int exitXCoord = Tools.RANDOM.nextInt(theSize/2, theSize-1);
		int exitYCoord = Tools.RANDOM.nextInt(0, theSize-1);
		int bossX = Tools.RANDOM.nextInt(0, theSize-1);
		int bossY = Tools.RANDOM.nextInt(0, theSize-1);

		Room entrance = new Room(entranceXCoord,entranceYCoord, RoomType.ENTRANCE);
		Room exit = new Room(exitXCoord,exitYCoord, RoomType.EXIT);
		Room bossRoom = new Room(bossX,bossY,RoomType.BOSS_ROOM);

		setCurrentRoom(entrance);

		dung[entranceXCoord][entranceYCoord] = entrance;
		dung[exitXCoord][exitYCoord] = exit;

		System.out.println("Entrance Coords: " + entranceXCoord + " " + entranceYCoord);
		System.out.println("Exit Coords: " + exitXCoord + " " + exitYCoord);

		this.DFSGenerateRooms(dung, entrance, RoomType.EXIT);
		while(true) {
			if(dung[bossX][bossY] == null) {
				bossX = Tools.RANDOM.nextInt(0,theSize-1);
				bossY = Tools.RANDOM.nextInt(0,theSize-1);
			}
			else if(dung[bossX][bossY].getMyType() != RoomType.ENTRANCE &&
					dung[bossX][bossY].getMyType() != RoomType.EXIT) {
				dung[bossX][bossY] = bossRoom;
				break;
			}
		}
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
	private boolean DFSGenerateRooms(final Room[][] theRooms, final Room theStart, final RoomType theEnd) {
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
		double dub = Tools.RANDOM.nextDouble();
		if(dub < this.myPitSpawnChance) {
			return RoomType.PIT;
		}
		if( dub < this.myItemRoomChance) {
			return RoomType.ITEM_ROOM;
		}
		return RoomType.EMPTY;
	}

	/**
	 * returns all the possible ways to move
	 * @param theDung the dungeon
	 * @return an arraylist containing the ways available to move
	 */
	static ArrayList<String> availableRooms(final Dungeon theDung){
		ArrayList<String> theRooms = new ArrayList<>();
		Room room = theDung.getCurrentRoom();
		int row = room.getXCoord();
		int col = room.getYCoord();
		Room south = theDung.getRoom(row+1, col);
		Room north = theDung.getRoom(row-1, col);
		Room west = theDung.getRoom(row, col-1);
		Room east = theDung.getRoom(row, col+1);

		if(south != null){
			theRooms.add("south");
		}
		if(north != null){
			theRooms.add("north");
		}
		if(west != null){
			theRooms.add("west");
		}
		if(east != null){
			theRooms.add("east");
		}

		return theRooms;
	}

	/**
	 * Fancy way of changing the image representation of the room
	 * we are currently in inside the game. In the GUI we have an image that
	 * changes depending on free rooms around us.
	 * @param theDung Dungeon created after CharacterSelect
	 */
	static Image setMyDungeonRoom(final Dungeon theDung){
		ArrayList<String> theRooms = availableRooms(theDung);
		Boolean north = theRooms.contains("north");
		Boolean south = theRooms.contains("south");
		Boolean east = theRooms.contains("east");
		Boolean west = theRooms.contains("west");


		if(north && south && east && west){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_4.png");
		}else if(north && south && east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_right.png");
		}else if(north && south && west){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_left.png");
		}else if(south && west && east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_down.png");
		}else if(north && west && east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_up.png");
		}else if(north && west){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_topleft.png");
		}else if(north && east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_topright.png");
		}else if(south && west){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_bottomleft.png");
		}else if(south && east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_bottomright.png");
		}else if(south && north){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_updown.png");
		}else if(west && east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_leftright.png");
		}else if(north){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_up.png");
		}else if(south){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_down.png");
		}else if(east){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_right.png");
		} else{
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_left.png");
		}

	}

	/**
	 * Sets Room's boolean containsMonsters to true.
	 */
	private void placeMonsters() {
		if(this.myDungeon == null)
			return;
		// O(n^2) running time of placing monsters
		for(Room [] r : this.myDungeon) {
			for(Room room : r) {
				if(room == null){
					continue;
				}
				RoomType type = room.getMyType();
				if(Tools.RANDOM.nextDouble() < myMonsterSpawnChance && type != RoomType.EXIT && type != RoomType.ENTRANCE
						&& type != RoomType.PIT && type != RoomType.BOSS_ROOM) {
					room.setMonster();
				}
			}
		}

	}

	/**
	 * Getter for the myDungeonNumber field
	 */
	int getMyCurrentDungeonNumber() {
		return this.myCurrentDungeonNumber;
	}

	/**
	 * @return getter for the number of dungeons passed
	 */
	int getMyDungeonsPassed(){
		return this.myDungeonsPassed;
	}

}

