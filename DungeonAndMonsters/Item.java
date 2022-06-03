import javax.swing.*;
import java.io.Serializable;


/**
 * Abstract Class represents an in game item.
 *
 */

public abstract class Item implements Serializable {

    /**
     * Image of this item
     */
    private final transient ImageIcon myImage;

    /**
     * Constructor for this class. Simply initializes fields.
     * @param theImage the image for the item
     */
    protected Item(final ImageIcon theImage) {

        this.myImage = theImage;
    }


    /**
     * @return the image for the item
     */
    final protected ImageIcon getMyImage() {
        return this.myImage;
    }

    /**
     * Uses this object, each child is used differently.
     * @param theObj the hero
     */
     abstract void use(final Object theObj);
}
