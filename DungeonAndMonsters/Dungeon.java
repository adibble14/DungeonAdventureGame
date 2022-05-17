import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	 */
	protected Dungeon(final int theSize, final double theItemRoomChance) {

		this.myItemRoomChance = theItemRoomChance;
		this.myPitSpawnChance = .1; //TODO: implement arguments after done testing
		this.myDungeon = this.generateDungeon(theSize);
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
	 */
	final protected void setCurrentRoom(final Room theRoom) {
		this.myCurrentRoom = theRoom;
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

	/**
	 * Generates the Dungeon, does a lot so it will be very difficult to write a unit test for.
	 *
	 * @param theSize
	 * @return
	 */
	private  Room[][] generateDungeon(final int theSize) {
		Room[][] dung = new Room[theSize][theSize];
		int x = Tools.RANDOM.nextInt(0, theSize/3-1);
		int y = Tools.RANDOM.nextInt(0, theSize-1);
		Room entrance = new Room(x,y, RoomType.ENTRANCE);
		setCurrentRoom(entrance);
		dung [x][y] = entrance;
		System.out.println("Entrance Coords: " + x + " " + y);
		x = Tools.RANDOM.nextInt(theSize/2, theSize-1);
		y = Tools.RANDOM.nextInt(0, theSize-1);
		Room exit = new Room(x,y, RoomType.EXIT);
		dung[x][y] = exit;
		System.out.println("Exit Coords: " + x + " " + y);
		this.DFSGenerateRooms(dung, entrance, RoomType.EXIT);
		x = theSize-1;
		y = Tools.RANDOM.nextInt(0, theSize-1);
		Room bossRoom = new Room(x,y,RoomType.BOSS_ROOM);
		dung[x][y] = bossRoom;
		x = Tools.RANDOM.nextInt(0,theSize-1);
		y = Tools.RANDOM.nextInt(0,theSize-1);
		Room unique = new Room(x,y,RoomType.UNIQUE);
		dung[x][y] = unique;
		this.DFSGenerateRooms(dung, bossRoom, RoomType.UNIQUE);
		dung[x][y].setEmpty();
		this.placeMonsters();
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

	/**
	 * returns all the possible ways to move
	 * @param theDung
	 * @return
	 */
	public static ArrayList availableRooms(final Dungeon theDung){
		ArrayList theRooms = new ArrayList();
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
	public static Image setMyDungeonRoom(final Dungeon theDung){
		ArrayList theRooms = availableRooms(theDung);
		Boolean north = theRooms.contains("north");
		Boolean south = theRooms.contains("south");
		Boolean east = theRooms.contains("east");
		Boolean west = theRooms.contains("west");
		


		if(north && south && east && west){
			return Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/DungeonTile_4_Exits.png");
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
				if(Tools.RANDOM.nextDouble() < .15 && type != RoomType.EXIT && type != RoomType.ENTRANCE
						&& type != RoomType.PIT && type != RoomType.BOSS_ROOM) { //TODO: Change magic number into a class field
					room.setMonster();
				}
			}
		}

	}
}

