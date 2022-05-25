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
}
