import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Thief character. Overrides special attack method.
 */

public class Thief extends Hero {

    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/thief.jpeg");
    /**
     * Constructor with set values. Calls on super constructor to init fields.
     * Initializes Thief class fields.
     *
     * @param theName value given by user input
     */
    protected Thief(final String theName) {

        super(theName, 95, 6, 20, 10, .9, .4);
    }

    /**
     * Surprise Attack special attack. This special deals more damage than a regular attack. It also
     * allows the Thief to follow up an attack that does even more damage.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        int specialMaxDamage = 20;
        int damage = MY_RAND.nextInt(specialMaxDamage * 2) + this.getMaxDamage();
        double randAccuracy = MY_RAND.nextDouble();

        System.out.println(this.getName() + " goes for a surprise attack!\n\n");

        double specialAccuracy = .6;
        if (specialAccuracy < randAccuracy) {
            System.out.println(this.getName() + " almost gets caught! Swiftly escapes at the last second. Dealt No damage....\n\n");
        } else if (specialAccuracy > randAccuracy) {

            System.out.println(this.getName() + " lands the backstab! Dealt " + damage + " of damage.\n");

            int result = theChar.getHealth() - damage;
            if (result < 0) {
                result = 0;
            }
            theChar.setHealth(result);
        }
        if ((specialAccuracy / 2) > randAccuracy && theChar.isAlive()) {

            damage += this.getMaxDamage();
            System.out.println(this.getName() + " connects another attack! Dealt " + damage + " more of damage.\n\n");

            int secondResult = theChar.getHealth() - damage;
            if (secondResult < 0) {
                secondResult = 0;
            }
            theChar.setHealth(secondResult);
        }
    }

    public static ImageIcon getImage(){
        return image;
    }

}
