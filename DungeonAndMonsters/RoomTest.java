/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a unit test class for the Room class.
 */



public class RoomTest {

    @Test
    public void testGetXCoord() {
        int x = 7;
        Room room = new Room(x, 0, RoomType.EMPTY);
        assertEquals(x, room.getXCoord());
    }

    @Test
    public void testGetYCoord() {
        int y = 6;
        Room room = new Room(3, y, RoomType.EMPTY);
        assertEquals(y, room.getYCoord());
    }

    @Test
    public void testSetEmpty() {
        Room room = new Room(0,0, RoomType.BOSS_ROOM);
        room.setEmpty();
        assertEquals(RoomType.EMPTY, room.getMyType());
    }

    @Test
    public void testSetMonster() {
        Room room = new Room(0,0, RoomType.ITEM_ROOM);
        room.setMonster();
        assertEquals(true, room.containsMonster());
    }

    @Test
    public void testSetMonster2() {
        Room room = new Room(0,0, RoomType.ITEM_ROOM);
        assertEquals(false, room.containsMonster());
    }

    @Test
    public void testGetMyType() {
        Room room = new Room(0,0,RoomType.BOSS_ROOM);
        assertEquals(RoomType.BOSS_ROOM, room.getMyType());
    }

    @Test
    public void testGetMyType2() {
        Room room = new Room(0,0,RoomType.EMPTY);
        assertEquals(RoomType.EMPTY, room.getMyType());
    }

    @Test
    public void testGetMyType3() {
        Room room = new Room(0,0,RoomType.EXIT);
        assertEquals(RoomType.EXIT, room.getMyType());
    }

    @Test
    public void testGetMyType4() {
        Room room = new Room(0,0,RoomType.ITEM_ROOM);
        assertEquals(RoomType.ITEM_ROOM, room.getMyType());
    }

    @Test
    public void testGetMyType5() {
        Room room = new Room(0,0,RoomType.PIT);
        assertEquals(RoomType.PIT, room.getMyType());
    }
}
