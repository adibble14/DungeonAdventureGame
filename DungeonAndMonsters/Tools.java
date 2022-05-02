import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Tools {

    static Random random = new Random();

    /**
     * DFS like algorithm. Randomly walks the matrix starting from given start point.
     * Creating rooms in null spaces as it goes.
     * Until the end point is found.
     *
     * @param theRooms matrix to recurse
     * @param theStart starting point of the recursion
     */
    static boolean dfs(Room[][] theRooms, Room theStart) {
        ArrayList<Room> neighbors = (ArrayList<Room>) getNeighbors(theRooms, theStart);

        while(neighbors.size() > 0) {
            int choice = Tools.random.nextInt(neighbors.size());
            Room chosen = neighbors.get(choice);
            neighbors.remove(choice);
            theRooms[chosen.getXCoord()][chosen.getYCoord()] = chosen;
            if(chosen.isExit())
                return true;
            if(dfs(theRooms, chosen)) return true;

        }
        return false;
    }

    /**
     * Finds the valid neighbors of given room
     * @param theRooms
     * @param theRoom
     * @return
     */
    private static List<Room> getNeighbors(Room [][] theRooms, Room theRoom) {
        List<Room> result = new ArrayList<Room>(); // max of 4 neighbors overall

        if(theRoom.getXCoord()-1 >= 0) {
            if(theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()] == null) {
                result.add(new Room(theRoom.getXCoord()-1, theRoom.getYCoord()));
            } else if(theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()].isExit()) {
                result.add(theRooms[theRoom.getXCoord()-1][theRoom.getYCoord()]);
            }
        }
        if(theRoom.getXCoord()+1 < theRooms.length) {
            if(theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()] == null) {
                result.add(new Room(theRoom.getXCoord()+1, theRoom.getYCoord()));
            } else if(theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()].isExit()) {
                result.add(theRooms[theRoom.getXCoord()+1][theRoom.getYCoord()]);
            }
        }
        if(theRoom.getYCoord()-1 >= 0) {
            if(theRooms[theRoom.getXCoord()][theRoom.getYCoord()-1] == null) {
                result.add(new Room(theRoom.getXCoord(), theRoom.getYCoord()-1));
            } else if(theRooms[theRoom.getXCoord()][theRoom.getYCoord()-1].isExit()) {
                result.add(theRooms[theRoom.getXCoord()][theRoom.getYCoord()-1]);
            }
        }
        if(theRoom.getYCoord()+1 < theRooms.length) {
            if(theRooms[theRoom.getXCoord()][theRoom.getYCoord()+1] == null) {
                result.add(new Room(theRoom.getXCoord(), theRoom.getYCoord()+1));
            } else if(theRooms[theRoom.getXCoord()][theRoom.getYCoord()+1].isExit()) {
                result.add(theRooms[theRoom.getXCoord()][theRoom.getYCoord()+1]);
            }
        }

        return result;

    }
}
