import javax.swing.*;
import java.util.Random;

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

    public static PillarType getRandomPillar(){
        Random random = new Random();
        int num = random.nextInt(1,5);
        if(num == 1) return PillarType.ABSTRACTION;
        else if(num == 2) return PillarType.ENCAPSULATION;
        else if(num == 3) return PillarType.INHERITANCE;
        else return PillarType.POLYMORPHISM;
    }



}
