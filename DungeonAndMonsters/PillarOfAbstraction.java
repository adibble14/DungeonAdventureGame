import javax.swing.*;
import java.awt.*;

/**
 * pillar of abstraction class that makes the hero invincible for a time period
 */
public class PillarOfAbstraction extends Pillar{

    /**
     * image of pillar
     */
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Abstraction.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);

    /**
     * original block chance
     */
    private double myHeroOriginalVal;

    /**
     * number of turns invincible
     */
    private int myTurnsPassed;

    /**
     * if the pillar is active
     */
    private boolean myActive;

    private static final ImageIcon myImage = new ImageIcon(myPillarImage);

    /**
     * Constructor for this class. Simply initializes fields.
     * @param theType the type of pillar
     */
    protected PillarOfAbstraction(PillarType theType) {
        super(myImage, theType);
    }

    /** Set player to invincible for a few rounds
     * @param theObj the hero
     */
    @Override
    public void use(Object theObj) {
        Hero hero = (Hero) theObj;
        this.myHeroOriginalVal = hero.getBlockChance();
        this.myTurnsPassed = 0;
        this.myActive = true;
        hero.setBlockChance(1);
    }

    /**
     * counts number of turns passed. When the limit is reached, the hero's block chance is reset.
     */
    protected void tick() {
        this.myTurnsPassed++;
        if(this.myTurnsPassed == 10) {
            Hero hero = DungeonAdventure.getMyHero();
            hero.setBlockChance(this.myHeroOriginalVal);
            this.myActive = false;
        }
    }

    /**
     * if this pillar is active
     * @return boolean
     */
    protected boolean isActive() {
        return this.myActive;
    }

    /**
     * @return the image
     */
    protected final ImageIcon getImage(){
        return myImage;
    }
}

