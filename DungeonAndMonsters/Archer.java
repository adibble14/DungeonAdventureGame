import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates an Archer character. Overrides special attack method.
 */

public class Archer extends Hero {

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/archerpixel.png");

    /**
     * Constructor with set values. Calls on super constructor to init fields
     * Initializes class specific fields
     *
     * @param theName value given by user input
     */
    protected Archer(final String theName) {

        super(theName, 100, 4, 30, 25, .7, .5,image);
    }

    /**
     * Volley special attack. Generates a random number of attack turns, depending on special max turn field.
     * Damage dealt is more than regular attack.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {

        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " shoots a volley of arrows!\n");

        int specialMaxTurns = 5;
        int count = MY_RAND.nextInt(specialMaxTurns);

        if (count == 0) {

            System.out.println("The attack misses!");
        }
        while (count > 0 && theChar.isAlive()) {

            int specialMaxDamage = 20;
            int damage = MY_RAND.nextInt(specialMaxDamage) + this.getMaxDamage();
            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            System.out.println(theChar.getName() + " gets pierced by an arrow. Took " + damage + " of damage.\n\n");
            theChar.setHealth(result);
            count--;
        }
        theChar.attack(this);

    }

    public static ImageIcon getImage(){
        return image;
    }

}
