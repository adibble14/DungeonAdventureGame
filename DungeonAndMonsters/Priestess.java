import javax.swing.*;

public class Priestess extends Hero{

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/PriestessFace.png");
    private static final ImageIcon characterSelectImage = new ImageIcon("DungeonAndMonsters/character pics/CharacterSelectPriestess.png");
    protected Priestess(final String theName) {

        super(theName, SQLiteDB.getCharacterHealth("Priestess", "heroes"), SQLiteDB.getCharacterSpeed("Priestess", "heroes"),
                SQLiteDB.getCharacterMaxDamage("Priestess", "heroes"), SQLiteDB.getCharacterMinDamage("Priestess", "heroes"),
                SQLiteDB.getCharacterAccuracy("Priestess", "heroes"), SQLiteDB.getCharacterBlockChange("Priestess"),
                image, null);
    }


    /**
     * Special: Healing. For every point of damage dealt in next attack, priestess will heal 2/3 the amount.
     * @param theChar object to deal damage to
     */
    @Override
    protected void special(DungeonCharacter theChar) {
        double randAccuracy = Tools.RANDOM.nextDouble();
        int specialMaxDamage = 45;
        int damage = Tools.RANDOM.nextInt(specialMaxDamage) + this.getMaxDamage();

        //System.out.println(this.getName() + " goes for a special healing attack!\n\n");

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {
            System.out.println("The healing attack misses!");
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + " The healing attack misses. "));
        } else if (specialAccuracy > randAccuracy) {

            int healing = (int) Math.ceil(damage*.66);

            System.out.println(this.getName() + " lands the attack! Dealt " + damage + " of damage and healed " + healing+"points of health\n");
            BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals " + damage + " of damage and healed " + healing+" points of health. "));

            this.setHealth(this.getHealth() + healing);
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
        }

    }
    public static ImageIcon getImage(){
        return characterSelectImage;
    }

    @Override
    public String getSpecialInfo(){
        return "Healing. For every point of damage dealt, priestess will heal 2/3 the amount.";
    }
}
