import javax.swing.*;

public class Mimic extends Monster{
    /**
     * Child constructor of Monster class. Calls super class constructor to init fields.
     * Inits Monster class exclusive fields.
     *
     * @param theName       value given by child class
     * @param theHealth     value given by child class
     * @param theSpeed      value given by child class
     * @param theMaxDamage  value given by child class
     * @param theMinDamage  value given by child class
     * @param theAccuracy   value that is static. Does not change.
     * @param theHealChance value given by child class
     * @param theMinHeal    value given by child class
     * @param theMaxHeal    value given by child class
     */
    protected Mimic(String theName, int theHealth, int theSpeed, int theMaxDamage, int theMinDamage, double theAccuracy, double theHealChance, int theMinHeal, int theMaxHeal) {
        super(theName, theHealth, theSpeed, theMaxDamage, theMinDamage, theAccuracy, theHealChance, theMinHeal, theMaxHeal, null,null);
    }
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
    public String getSpecialInfo(){  //TODO
        return "";
    }
}
