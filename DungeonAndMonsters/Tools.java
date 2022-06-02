import java.util.*;

class Tools {

    /**
     * Random Object that is used across all classes. Used to get a random number
     */
    static final Random RANDOM = new Random();

    /**
     * Finds the valid neighbors of given room
     * @param theRooms Matrix to search from
     * @param theRoom the reference room to use for finding neighbors
     * @return a map of the room and the rooms coordinates
     */
    static HashMap<int[], Room> GET_NEIGHBORS(Room [][] theRooms, Room theRoom) {

        HashMap<int[], Room> result = new HashMap<>(); // max of 4 neighbors overall
        int row = theRoom.getXCoord();
        int col = theRoom.getYCoord();

        boolean north = ((row - 1) >= 0);
        boolean south = ((row + 1)) < theRooms.length;
        boolean west = ((col - 1)) >= 0;
        boolean east = ((col + 1)) < theRooms[row].length;

        if (north) result.put(new int[]{theRoom.getXCoord()-1, theRoom.getYCoord()}, theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()]);
        if (south) result.put(new int[]{theRoom.getXCoord()+1, theRoom.getYCoord()}, theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()]);
        if (west) result.put(new int[]{theRoom.getXCoord(), theRoom.getYCoord()-1}, theRooms[theRoom.getXCoord()][theRoom.getYCoord()-1]);
        if (east) result.put(new int[]{theRoom.getXCoord(), theRoom.getYCoord()+1}, theRooms[theRoom.getXCoord()][theRoom.getYCoord()+1]);


        return result;

    }

    /**
     * Returns a map of rooms that are on each four corners of current room
     *
     * @param theRooms dungeon
     * @param theRoom certain room
     * @return hashmap of rooms around it
     */
    static HashMap<int[], Room> GET_CORNER_NEIGHBORS(Room [][] theRooms, Room theRoom) {

        HashMap<int[], Room> result = new HashMap<>(); // max of 4 neighbors overall
        int row = theRoom.getXCoord();
        int col = theRoom.getYCoord();

        Boolean north = ((row - 1) >= 0);
        Boolean south = ((row + 1)) < theRooms.length;
        Boolean west = ((col - 1)) >= 0;
        Boolean east = ((col + 1)) < theRooms[row].length;

        if (north && west) result.put(new int[]{theRoom.getXCoord()-1, theRoom.getYCoord()-1}, theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()-1]);
        if (north && east) result.put(new int[]{theRoom.getXCoord()-1, theRoom.getYCoord()+1}, theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()+1]);
        if (south && west) result.put(new int[]{theRoom.getXCoord()+1, theRoom.getYCoord()-1}, theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()-1]);
        if (south && east) result.put(new int[]{theRoom.getXCoord()+1, theRoom.getYCoord()+1}, theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()+1]);

        return result;
    }
}
