import java.util.*;

class Tools {

    /**
     * Random Object that is used across all classes. Used to get a random numbber
     */
    static final Random RANDOM = new Random();

   /* private static Boolean myNorth = false;
    private static Boolean mySouth = false;
    private static Boolean myEast = false;
    private static Boolean myWest = false;

    public static Boolean getMyEast() {
        return myEast;
    }

    public static Boolean getMyNorth() {
        return myNorth;
    }

    public static Boolean getMySouth() {
        return mySouth;
    }

    public static Boolean getMyWest() {
        return myWest;
    }

    public static void setMyEast(Boolean myEast) {
        Tools.myEast = myEast;
    }

    public static void setMyNorth(Boolean myNorth) {
        Tools.myNorth = myNorth;
    }

    public static void setMySouth(Boolean mySouth) {
        Tools.mySouth = mySouth;
    }

    public static void setMyWest(Boolean myWest) {
        Tools.myWest = myWest;
    }*/

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
