import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Warrior character. Overrides special attack method.
 */

public class Warrior extends Hero {

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/warrior.jpeg");
    /**
     * Class constructor with set values. Calls on super constructor to init fields.
     * Initializes class specific fields.
     *
     * @param theName value given by user input.
     */
    protected Warrior(final String theName) {

        super(theName, 125, 3, 50, 30, .8, .6);
    }


    /**
     * Crushing blow special attack. this attack does much more damage than a regular attack.
     * If this attack misses, it deals a guaranteed minimum value of damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        int specialMinDamage = 75;
        int specialMaxDamage = 175;
        int damage = MY_RAND.nextInt(specialMaxDamage - specialMinDamage) + specialMinDamage;
        double randAccuracy = MY_RAND.nextDouble();

        System.out.println(this.getName() + " activates Crushing Blow!\n\n");

        double specialAccuracy = .4;
        if (specialAccuracy < randAccuracy) {

            damage = specialMinDamage;
            System.out.println(this.getName() + " stumbles the attack...\n");
        }

        int result = theChar.getHealth() - damage;

        if (result < 0) {
            result = 0;
        }

        theChar.setHealth(result);

        System.out.println(this.getName() + " deals a Crushing Blow to " + theChar.getName() + "! Dealt " + damage + " of damage.\n\n");

    }
    public static ImageIcon getImage(){
        return image;
    }
}
