
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Gremlin extends Monster {

    /**
     * Initializes array of names. To be randomly chosen for Gremlin object.
     */
    private final String[] MY_NAMES = {"Mogwai", "Gizmo", "Stripe", "Greta", "Daffy"};

    /**
     * Constructor that has set values for Gremlin object. Calls on super constructor to init fields.
     */
    protected Gremlin() {

        super("", 70, 5, 30, 15, .8, .4, 20, 40);

        this.setRandomName();
    }

    /**
     * Set random name for Gremlin object.
     */
    private void setRandomName() {

        this.setName(MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)]);
    }

    /**
     * Gremlin Frenzy special attack. This method generates a random value influenced by
     * special turn field. Gremlin gets to attack that many times. Damage is based on special damage
     * plus a random value.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW

        //System.out.println(this.getName() + " summons a Gremlin Frenzy!");

        int specialMaxTurns = 3;
        int count = MY_RAND.nextInt(specialMaxTurns);

        if (count == 0) {

            //System.out.println("No Gremlins showed up!\n\n");
        }
        int totalDamage = 0;
        while (count > 0 && theChar.isAlive()) {

            int specialMaxDamage = 30;
            int damage = MY_RAND.nextInt(specialMaxDamage / 2) + specialMaxDamage;
            totalDamage+=damage;
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            System.out.println(theChar.getName() + " gets hit by a frenzied Gremlin! Took " + damage + " of damage.\n\n");
            theChar.setHealth(result);
            count--;
        }
        BattleGUI.setBattleConsole(new StringBuilder(BattleGUI.getBattleConsole() + theChar.getName() + " gets hit by " + count + " Gremlin minions! Took " + totalDamage + " of damage. "));
    }

    public static String getSpecialInfo(){
        return "Gremlin Frenzy. Generates a random number of gremlins that each deal their own attack.";
    }

}
