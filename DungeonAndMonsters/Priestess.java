/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Priestess character. Overrides special attack method.
 */

public class Priestess extends Hero{

    /**
     * the image for the character select screen - (resized from original image)
     */
    private static final ImageIcon characterSelectImage = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectPriestess.png");

    protected Priestess(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Priestess", "heroes"), SQLiteDB.getCharacterSpeed("Priestess", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Priestess", "heroes"), SQLiteDB.getCharacterMinDamage("Priestess", "heroes"),
                SQLiteDB.getCharacterAccuracy("Priestess", "heroes"), SQLiteDB.getCharacterBlockChance("Priestess"),
                new ImageIcon(SQLiteDB.getCharacterImage("Priestess", "heroes")), new ImageIcon(SQLiteDB.getCharacterInGameImage("Priestess")));
    }


    /**
     * Special: Healing. For every point of damage dealt in next attack, priestess will heal 2/3 the amount.
     * @param theChar object to deal damage to
     */
    @Override
    protected void special(DungeonCharacter theChar) {
        double randAccuracy = Tools.RANDOM.nextDouble();
        int specialMaxDamage = 35;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage) + this.getMaxDamage();

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + " The healing attack misses. "));
        } else{

            int healing = (int) Math.ceil(damage*.66);

            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals " + damage + " of damage and healed " + healing+" points of health. "));

            this.setHealth(this.getHealth() + healing);
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
        }

    }

    /**
     * @return the character select image
     */
    static ImageIcon getImage(){
        return characterSelectImage;
    }


    /**
     * @return info about the character's super
     */
    @Override
    final String getSpecialInfo(){
        return "Healing. For every point of damage dealt, priestess will heal 2/3 the amount.";
    }
}
