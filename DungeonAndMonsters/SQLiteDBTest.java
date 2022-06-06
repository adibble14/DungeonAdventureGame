/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for SQLiteDB Class
 *
 */

public class SQLiteDBTest {

    @Test
    public void testGetRowCount(){
        SQLiteDB.createHeroesTable();
        assertEquals(5,SQLiteDB.getRowCount("heroes"));
    }

    @Test
    public void testGetRowCount2(){
        SQLiteDB.createMonstersTable();
        assertEquals(25,SQLiteDB.getRowCount("monsters"));
    }

    @Test
    public void testGetCharacterHealth(){
        SQLiteDB.createHeroesTable();
        assertEquals(75,SQLiteDB.getCharacterHealth("Mage", "heroes"));
    }

    @Test
    public void testGetCharacterHealthBoss(){
        SQLiteDB.createMonstersTable();
        assertEquals(550,SQLiteDB.getCharacterHealth("Beast", "monsters", "boss"));
    }

    @Test
    public void testGetCharacterSpeed(){
        SQLiteDB.createMonstersTable();
        assertEquals(2,SQLiteDB.getCharacterSpeed("Ogre", "monsters"));
    }

    @Test
    public void testGetCharacterMaxDamage(){
        SQLiteDB.createHeroesTable();
        assertEquals(45,SQLiteDB.getCharacterMaxDamage("Priestess", "heroes"));
    }

    @Test
    public void testGetCharacterMinDamage(){
        SQLiteDB.createHeroesTable();
        assertEquals(25,SQLiteDB.getCharacterMinDamage("Priestess", "heroes"));
    }

    @Test
    public void testGetCharacterAccuracy(){
        SQLiteDB.createMonstersTable();
        assertEquals(.8,SQLiteDB.getCharacterAccuracy("Gremlin", "monsters"));
    }

    @Test
    public void testGetCharacterHealChanceBoss(){
        SQLiteDB.createMonstersTable();
        assertEquals(.5,SQLiteDB.getCharacterHealChance("Gremlin", "boss"));
    }

    @Test
    public void testGetCharacterMinHeal(){
        SQLiteDB.createMonstersTable();
        assertEquals(20,SQLiteDB.getCharacterMinHeal("Gremlin", "monsters"));
    }

    @Test
    public void testGetCharacterMaxHeal(){
        SQLiteDB.createMonstersTable();
        assertEquals(40,SQLiteDB.getCharacterMaxHeal("Gremlin", "monsters"));
    }

    @Test
    public void testGetCharacterBlockChance(){
        SQLiteDB.createHeroesTable();
        assertEquals(.6,SQLiteDB.getCharacterBlockChance("Warrior"));
    }
}
