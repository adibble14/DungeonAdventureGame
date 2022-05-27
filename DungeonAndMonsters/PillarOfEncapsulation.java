import javax.swing.*;

public class PillarOfEncapsulation extends Pillar{
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfEncapsulation(ImageIcon theImage, PillarType theType) {
        super(theImage, theType);
    }

    @Override
    public void use(Object theObj) {


    }
}

