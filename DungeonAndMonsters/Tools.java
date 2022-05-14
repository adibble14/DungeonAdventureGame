import java.util.*;

class Tools {

    /**
     * Random Object that is used across all classes. Used to get a random numbber
     */
    static final Random RANDOM = new Random();

    /**
     * Finds the valid neighbors of given room
     * @param theRooms Matrix to search from
     * @param theRoom the reference room to use for finding neighbors
     * @return a map of the room and the rooms coordinates
     */
    static final HashMap<int[], Room> GET_NEIGHBORS(Room [][] theRooms, Room theRoom) {

        HashMap<int[], Room> result = new HashMap<int[], Room>(); // max of 4 neighbors overall
        int row = theRoom.getXCoord();
        int col = theRoom.getYCoord();

        Boolean north = ((row - 1) >= 0);
        Boolean south = ((row + 1)) < theRooms.length;
        Boolean west = ((col - 1)) >= 0;
        Boolean east = ((col + 1)) < theRooms[row].length;

        if (north) result.put(new int[]{theRoom.getXCoord()-1, theRoom.getYCoord()}, theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()]);
        if (south) result.put(new int[]{theRoom.getXCoord()+1, theRoom.getYCoord()}, theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()]);
        if (west) result.put(new int[]{theRoom.getXCoord(), theRoom.getYCoord()-1}, theRooms[theRoom.getXCoord()][theRoom.getYCoord()-1]);
        if (east) result.put(new int[]{theRoom.getXCoord(), theRoom.getYCoord()+1}, theRooms[theRoom.getXCoord()][theRoom.getYCoord()+1]);


        return result;

    }
}
