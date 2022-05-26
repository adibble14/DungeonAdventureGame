
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Beast extends Monster {

    /**
     * Initializes array of names. To be randomly chosen for Beast object.
     */
    //private final String[] MY_NAMES = {"Owl Bear", "Ravager", "Beholder", "plankTON", "Man Beast"};

    /**
     * Constructor that has set values for Beast object. Calls on super constructor to init fields.
     */
    protected Beast() {

        super(SQLiteDB.getCharacterName("Beast"), SQLiteDB.getCharacterHealth("Beast","monsters"),SQLiteDB.getCharacterSpeed("Beast","monsters"),
                SQLiteDB.getCharacterMaxDamage("Beast","monsters"),SQLiteDB.getCharacterMinDamage("Beast","monsters"),
                SQLiteDB.getCharacterAccuracy("Beast","monsters"), SQLiteDB.getCharacterHealChance("Beast"),
                SQLiteDB.getCharacterMinHeal("Beast"), SQLiteDB.getCharacterMaxHeal("Beast"));

        //this.setRandomName();
    }

    /**
     * Sets random name for Beast object.
     */
    /*private void setRandomName() {

        this.setName(MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)]);
    }*/

    /**
     * Feral Swipe special attack. This special always deals half the enemies' health value.
     */
    @Override
    final protected void special(DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " goes for a Feral Swipe!\n\n");

        int damage = theChar.getHealth() / 2;
        theChar.setHealth(damage);
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " halves "+ theChar.getName() + "'s health with the Feral Swipe. "));
        System.out.println(this.getName() + " halves " + theChar.getName() + "'s health!\n\n");
    }

    @Override
    public String getSpecialInfo(){
        return "Feral Swipe. This special always deals half the enemies' health value.";
    }

}
