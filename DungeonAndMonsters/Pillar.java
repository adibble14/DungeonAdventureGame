import javax.swing.*;

public class Pillar extends Item{

    private final PillarType MY_TYPE;
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theSpawnChance
     * @param theImage
     */
    protected Pillar(final double theSpawnChance, final ImageIcon theImage, final PillarType theType) {
        super(theSpawnChance, theImage);
        this.MY_TYPE = theType;
    }

    /**
     * Not sure how to use yet. Stat boosts maybe?
     * Ideas so far: Encapsulation you are able to see more rooms,
     * Polymorphism you can shape shift into a monster once in a battle,
     *
     * @param obj
     */
    @Override
    public void use(Object obj) {

    }
}
