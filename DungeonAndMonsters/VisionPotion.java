import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class VisionPotion extends Item {


    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected VisionPotion() {
        super(new ImageIcon("DungeonAndMonsters/potion.png"));
    }

    //TODO: Implement this method
    @Override
    public void use(Object theObj) {
        if(theObj.getClass() == Dungeon.class) {
            // uncover adjacent rooms here
        }
    }


}
