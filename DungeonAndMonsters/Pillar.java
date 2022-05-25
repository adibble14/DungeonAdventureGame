import javax.swing.*;

public abstract class Pillar extends Item{

    private final PillarType MY_TYPE;
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     */
    protected Pillar(final ImageIcon theImage, final PillarType theType) {
        super(theImage);
        this.MY_TYPE = theType;
    }

    /**
     * Get method for MY_TYPE field
     * @return
     */
    protected final PillarType getMY_TYPE() {
        return this.MY_TYPE;
    }
}
