import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * A Test class for the SaveGame class.
 */

public class SaveGameTest {

    // So far, the Hero Object is to be saved.
    @Test
    public void testSaveLoad1() {
        Dungeon dungeon = new Dungeon(10,.4);

        DungeonAdventure.setMyDungeon(dungeon);

        SaveGame.save("DungeonAndMonsters/game.ser");
        System.out.println("Saved Object.");
        SaveGame.load("DungeonAndMonsters/game.ser");
        System.out.println("Loaded Objects");

        assertEquals(dungeon.toString(), DungeonAdventure.getMyDungeon().toString());

    }

    @Test
    public void testSaveLoad2() {
        Hero hero = new Mage("Mario");

        DungeonAdventure.setMyHero(hero);

        SaveGame.save("DungeonAndMonsters/game.ser");
        System.out.println("Saved Object.");
        SaveGame.load("DungeonAndMonsters/game.ser");
        System.out.println("Loaded Objects");

        assertEquals(hero.toString(), DungeonAdventure.getMyHero().toString());

    }

    @Test
    public void testSaveLoad3() {
        Hero hero = new Archer("Mario");
        Dungeon dungeon = new Dungeon(10,.4);

        DungeonAdventure.setMyHero(hero);
        DungeonAdventure.setMyDungeon(dungeon);

        SaveGame.save("DungeonAndMonsters/game.ser");
        System.out.println("Saved Object.");
        SaveGame.load("DungeonAndMonsters/game.ser");
        System.out.println("Loaded Objects");

        System.out.println("Created Hero Object: ");
        System.out.println(hero.toString());
        System.out.println();
        System.out.println("DungeonAdventure Hero Object: ");
        System.out.println(DungeonAdventure.getMyHero().toString());
        System.out.println("\nCreated Dungeon Object:");
        System.out.println(dungeon.toString());
        System.out.println("DungeonAdventure Dungeon Object: ");
        System.out.println(DungeonAdventure.getMyDungeon().toString());

    }
}
