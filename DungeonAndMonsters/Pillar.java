/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;

public abstract class Pillar extends Item {

    private final PillarType MY_TYPE;
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theImage the image of the pillar
     */
    protected Pillar(final ImageIcon theImage, final PillarType theType) {
        super(theImage);
        this.MY_TYPE = theType;
    }

    /**
     * Get method for MY_TYPE field
     * @return pillar type
     */
    protected final PillarType getMY_TYPE() {
        return this.MY_TYPE;
    }

    /**
     * @return a random pillar
     */
    static PillarType getRandomPillar(){
        int num = Tools.RANDOM.nextInt(1,5);
        if(num == 1) return PillarType.ABSTRACTION;
        else if(num == 2) return PillarType.ENCAPSULATION;
        else if(num == 3) return PillarType.INHERITANCE;
        else return PillarType.POLYMORPHISM;
    }



}
