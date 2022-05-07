import java.util.*;

class Tools {

    /**
     * Random Object that is used across all classes. Used to get a random numbber
     */
    static final Random RANDOM = new Random();

    private static Boolean myNorth = false;
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
    }

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

        myNorth = ((row - 1) >= 0);
        mySouth = ((row + 1)) < theRooms.length;
        myWest = ((col - 1)) >= 0;
        myEast = ((col + 1)) < theRooms[row].length;

        if (myNorth) result.put(new int[]{theRoom.getXCoord()-1, theRoom.getYCoord()}, theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()]);
        if (mySouth) result.put(new int[]{theRoom.getXCoord()+1, theRoom.getYCoord()}, theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()]);
        if (myWest) result.put(new int[]{theRoom.getXCoord(), theRoom.getYCoord()-1}, theRooms[theRoom.getXCoord()][theRoom.getYCoord()-1]);
        if (myEast) result.put(new int[]{theRoom.getXCoord(), theRoom.getYCoord()+1}, theRooms[theRoom.getXCoord()][theRoom.getYCoord()+1]);

        return result;

    }
}
