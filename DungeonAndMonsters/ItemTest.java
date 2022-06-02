import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {

    Thief testHero = new Thief("Testy");
    BattleGUI testGui = new BattleGUI(null);
    Dungeon testDungeon = new Dungeon(10,0.2,0,1);


    @Test
    public void testAddItemToInventory(){
        Item healthPotion = new HealthPotion();
        testHero.addInventory(healthPotion,3);
        Item visionPotion = new VisionPotion();
        testHero.addInventory(visionPotion, 5);
        testHero.addInventory(new PillarOfAbstraction(PillarType.ABSTRACTION), 1);
        // Non pillar items
        assertEquals(8,testHero.getMyInventory().getAllItems().length);
        // Pillars in inventory
        assertEquals(1, testHero.getMyInventory().getPillars().length);
    }

    @Test
    public void testInventoryContents(){
        Item healthPotion = new HealthPotion();
        testHero.addInventory(healthPotion,2);
        Item visionPotion = new VisionPotion();
        testHero.addInventory(visionPotion, 5);
        assertEquals("[VISION_POTION, VISION_POTION, VISION_POTION, VISION_POTION, VISION_POTION, HEALTH_POTION, HEALTH_POTION]", Arrays.toString(testHero.getMyInventory().getAllItems()));
    }

    @Test
    public void testRemoveItemFromInventory(){
        Item healthPotion = new HealthPotion();
        testHero.addInventory(healthPotion,3);
        Item visionPotion = new VisionPotion();
        testHero.addInventory(visionPotion, 5);
        assertEquals(8,testHero.getMyInventory().getAllItems().length);

        testHero.getMyInventory().removeItem(ItemType.HEALTH_POTION);
        assertEquals(7, testHero.getMyInventory().getAllItems().length);
        assertEquals("[HEALTH_POTION, HEALTH_POTION, VISION_POTION, VISION_POTION, VISION_POTION, VISION_POTION, VISION_POTION]", Arrays.toString(testHero.getMyInventory().getAllItems()));
    }

    @Test
    public void testHealthPotionUse() {
        int adjustedHealth = testHero.getHealth() + 25;
        Item healthPotion = new HealthPotion();
        testHero.addInventory(healthPotion,1);
        testHero.useHealthPotion();
        assertEquals(adjustedHealth, testHero.getHealth());

        Item healthPotionTwo = new HealthPotion();
        testHero.addInventory(healthPotionTwo, 1);
        int newAdjustedHealth = 50;
        testHero.setHealth(25);
        testHero.useHealthPotion();
        assertEquals(newAdjustedHealth, testHero.getHealth());
    }

    @Test
    public void testUseVisionPotion(){
        Item visionPotion = new VisionPotion();
        for (int i = 0; i < testDungeon.getDungeon().length; i++) {
            for (int j = 0; j < testDungeon.getDungeon()[0].length; j++) {
                if(testDungeon.getRoom(i,j) != null) {
                    testDungeon.setCurrentRoom(testDungeon.getRoom(i, j));
                    assertFalse(testDungeon.getRoom(i, j).getMyDiscovery());
                }
            }

        }

        testHero.addInventory(visionPotion,1);
        testHero.useVisionPotion(testDungeon);
        HashMap<int[], Room> neighbors = Tools.GET_NEIGHBORS(testDungeon.getDungeon(), testDungeon.getCurrentRoom());

        for (Room room:
             neighbors.values()) {
            if (room != null) {
                assertTrue(room.getMyDiscovery());
            }
        }

    }
    @Test
    public void testAddGoldToInventory(){
        assertEquals(0, testHero.getGoldCount());
        testHero.setGoldAmount(15);
        assertEquals(15, testHero.getGoldCount());
    }
}
