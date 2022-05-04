import javax.swing.*;
import java.awt.image.BufferedImage;

public class Pit extends Item{

    /**
     * Amount of damage falling into a pit does to the player.
     */
    private int myDamage;
    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected Pit() {
        super(.1, new ImageIcon("DungeonAndMonsters/pit.png"));
        this.myDamage = 10;

    }

    /**
     * Damages Hero.
     *
     * @param theObj
     */
    //TODO: Implement the damaging of the hero.
    @Override
    public void use(Object theObj) {
        if(theObj.getClass() == Hero.class) {
            Hero player = (Hero) theObj;
            player.takeDamage(this.myDamage);
        }
    }
}
