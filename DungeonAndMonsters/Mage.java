import javax.swing.*;

/**
 * This class inherits from the Hero abstract class. Creates a Mage character. Overrides special attack method.
 */

public class Mage extends Hero {
    private static final ImageIcon image = new ImageIcon("DungeonAndMonsters/character pics/mage.jpeg");
    /**
     * Constructor that has set values for object. Calls on super constructor to init fields.
     *
     * @param theName name value given by user input
     */
    protected Mage(final String theName) {

        super(theName, 75, 4, 80, 50, .7, .3, image, image);
    }

    /**
     * Life Steal super attack. This attack does not fail to land. It halves the enemies' health value. It also
     * adds that value to this object's health value.
     */
    @Override
    final protected void special(final DungeonCharacter theChar) {
        //TODO delete this output once GUI is made, since this is VIEW
        System.out.println(this.getName() + " activates life steal!\n\n");

        int damage = theChar.getHealth() / 2;
        int result = this.getHealth() + damage;

        this.setHealth(Math.min(result, this.getMaxHealth()));

        theChar.setHealth(damage);

        System.out.println(this.getName() + " absorbs " + damage + " points of " + theChar.getName() + "'s health!!\n\n");

        theChar.attack(this);
    }

    public static ImageIcon getImage(){
        return image;
    }

}
