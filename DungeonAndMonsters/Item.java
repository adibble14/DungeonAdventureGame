import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.Serializable;


/**
 * Abstract Class represents an in game item.
 *
 */

public abstract class Item implements Serializable {

    /**
     * Image of this item
     */
    private transient ImageIcon myImage;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     */
    protected Item(final ImageIcon theImage) {

        this.myImage = theImage;
    }



    final protected ImageIcon getMyImage() {
        return this.myImage;
    }

    /**
     * Uses this object, each child is used differently.
     * @param theObj
     */
    public abstract void use(Object theObj);
}
