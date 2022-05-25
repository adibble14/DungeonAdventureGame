import javax.swing.*;

public class PillarOfPolymorphism extends Pillar{
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage
     * @param theType
     */
    protected PillarOfPolymorphism(ImageIcon theImage, PillarType theType) {
        super(theImage, theType);
    }

    @Override
    public void use(Object theObj) {
        Hero hero = (Hero) theObj;

    }
}
