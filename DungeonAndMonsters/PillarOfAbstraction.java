import javax.swing.*;

public class PillarOfAbstraction extends Pillar{
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfAbstraction(ImageIcon theImage, PillarType theType) {
        super(theImage, theType);
    }

    @Override
    public void use(Object theObj) {


    }
}

