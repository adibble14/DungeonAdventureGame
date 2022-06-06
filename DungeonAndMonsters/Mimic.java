/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

/**
 * Mimic class that creates a monster that comes from a chest
 */
public class Mimic extends Monster{

    /**
     * constructor for Mimic
     */
    protected Mimic() {
        super(SQLiteDB.getCharacterName("Mimic"), SQLiteDB.getCharacterHealth("Mimic","monsters"),SQLiteDB.getCharacterSpeed("Mimic","monsters"),
                SQLiteDB.getCharacterMaxDamage("Mimic","monsters"),SQLiteDB.getCharacterMinDamage("Mimic","monsters"),
                SQLiteDB.getCharacterAccuracy("Mimic","monsters"), SQLiteDB.getCharacterHealChance("Mimic"),
                SQLiteDB.getCharacterMinHeal("Mimic"), SQLiteDB.getCharacterMaxHeal("Mimic"),
                new ImageIcon(SQLiteDB.getCharacterImage("Mimic", "monsters")), new ImageIcon(SQLiteDB.getCharacterImage("Mimic", "monsters")));
    }

    /**
     * Maybe eats the gold of the player?
     * @param theChar object to deal damage to
     */
    @Override
    protected void special(DungeonCharacter theChar) {
        Hero hero = (Hero) theChar;
        hero.setGoldAmount(Tools.RANDOM.nextInt(-50,-5));
    }

    @Override
    final String getSpecialInfo(){  //TODO
        return "Robin Hood: steals gold from the hero!";
    }
}
