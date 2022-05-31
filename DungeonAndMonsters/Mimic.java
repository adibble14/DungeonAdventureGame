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
        super("Mimic",140,5,15,5,.5,.07,0,0,
                new ImageIcon("DungeonAndMonsters/monster pics/Mimic UW Chest.png"),new ImageIcon("DungeonAndMonsters/monster pics/Mimic UW Chest.png"));
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
