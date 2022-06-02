import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for Dungeon Class
 *
 */



public class DungeonTest {

    static Dungeon dungeon = new Dungeon(10,.2, 0,1);

    /**
     * One error that can occur,we pass in a room and then set
     * that room as the currentRoom, without checking if this room exists
     * in the matrix, It is possible to set the current room to a room that
     * is not a part of the dungeon.
     */
    @Test
    public void testGetCurrentRoom() {
        Room room = new Room(7,7,RoomType.EMPTY);
        dungeon.setCurrentRoom(room);
        assertEquals(room, dungeon.getCurrentRoom());
    }

    @Test
    public void testGetRoom() {
        Room room = dungeon.getCurrentRoom();
        Room dungRoom = dungeon.getRoom(room.getXCoord(),room.getYCoord());
        assertEquals(room, dungRoom);
    }

    @Test
    public void testGetDungeon() {
        Room [][] rooms = dungeon.getDungeon();
        StringBuilder roomContents = new StringBuilder();

        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms.length; j++) {
                if(rooms[i][j] == null)
                    roomContents.append("* ");
                else {
                    roomContents.append(rooms[i][j].toString() + " ");
                }
            }
            roomContents.append("\n");
        }
        assertEquals(roomContents.toString(), dungeon.toString());
    }

    @Test
    public void testSetCurrentRoom() {
        Room room = new Room(3,3,RoomType.EMPTY);
        dungeon.setCurrentRoom(room);
        assertEquals(room, dungeon.getCurrentRoom());
    }

    @Test
    public void testRandomGeneration() {

        for(int i = 0; i < 20; i++) {
            Dungeon dung = new Dungeon(10, .7, 0,1);
        }
    }
}
