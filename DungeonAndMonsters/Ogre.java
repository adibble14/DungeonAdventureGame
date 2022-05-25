
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Ogre extends Monster {

    /**
     * Initializes array of names. To be randomly chosen for ogre object.
     */
   // private final String[] MY_NAMES = {"Green Ogre", "Huge Ogre", "Passive Ogre", "Mysterious Ogre", "Strong Ogre"};


    /**
     * Constructor that has set values for Ogre object. Calls on super constructor to init fields.
     */
    protected Ogre() {

        super(SQLiteDB.getMonsterName("Ogre"), SQLiteDB.getMonsterHealth("Ogre"),SQLiteDB.getMonsterSpeed("Ogre"),
                SQLiteDB.getMonsterMaxDamage("Ogre"),SQLiteDB.getMonsterMinDamage("Ogre"),SQLiteDB.getMonsterAccuracy("Ogre"),
                SQLiteDB.getMonsterHealChance("Ogre"),SQLiteDB.getMonsterMinHeal("Ogre"), SQLiteDB.getMonsterMaxHeal("Ogre"));
       // this.setRandomName();
    }

    /**
     * Sets random name for Ogre class
     */
   /* private void setRandomName() {

        this.setName(MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)]);
    }*/

    /**
     * Devastating Blow special method for Ogre object. This special has a guaranteed min value
     * damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        int specialMaxDamage = 100;
        int specialMinDamage = 60;
        int damage = MY_RAND.nextInt(specialMaxDamage) + specialMinDamage;
        double randAccuracy = MY_RAND.nextDouble();

        //System.out.println(this.getName() + " throws a Devastating Blow!\n\n");

        double specialAccuracy = .5;
        if (specialAccuracy < randAccuracy) {

            damage = specialMinDamage;
            //System.out.println(theChar.getName() + " dodges the attack! Splash damage taken.\n");
        }

        int result = theChar.getHealth() - damage;

        if (result < 0) {

            result = 0;
        }
        theChar.setHealth(result);

        System.out.println(this.getName() + " deals a Devastating Blow to " + theChar.getName() + "! Dealt " + damage + " of damage.\n\n");
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + this.getName() + " deals a Devastating Blow to " + theChar.getName() + " of "+ damage + " damage. "));
    }

    @Override
    public String getSpecialInfo(){
        return "Devastating Blow. This special has a guaranteed min value damage.";
    }


}
