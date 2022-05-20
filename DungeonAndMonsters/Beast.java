
/**
 * This class inherits from Monster super class. Has fields associated with special attack method.
 * Sets a random name from array of names.
 */

public class Beast extends Monster {

    /**
     * Initializes array of names. To be randomly chosen for Beast object.
     */
    private final String[] MY_NAMES = {"Owl Bear", "Ravager", "Beholder", "plankTON", "Man Beast"};

    /**
     * Constructor that has set values for Beast object. Calls on super constructor to init fields.
     */
    protected Beast() {

        super("", 500, 1, 50, 30, .3, .2, 50, 100);

        this.setRandomName();
    }

    /**
     * Sets random name for Beast object.
     */
    private void setRandomName() {

        this.setName(MY_NAMES[MY_RAND.nextInt(MY_NAMES.length - 1)]);
    }

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

}
