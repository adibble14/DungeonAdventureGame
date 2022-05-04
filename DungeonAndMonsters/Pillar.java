import javax.swing.*;

public class Pillar extends Item{
    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theSpawnChance
     * @param theImage
     */
    protected Pillar(double theSpawnChance, ImageIcon theImage) {
        super(theSpawnChance, theImage);
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
