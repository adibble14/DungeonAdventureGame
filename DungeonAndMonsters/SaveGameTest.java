import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * A Test class for the SaveGame class.
 */

public class SaveGameTest {

    // So far, the Hero Object is to be saved.
    @Test
    public void testSave() {
        Hero hero = new Archer("Mario");
        DungeonAdventure.setMyHero(hero);
        SaveGame.save("DungeonAndMonsters/game.ser");
        System.out.println("Saved Object.");
        SaveGame.load("DungeonAndMonsters/game.ser");
        assertEquals(hero.toString(), DungeonAdventure.getMyHero().toString());

    }
}
