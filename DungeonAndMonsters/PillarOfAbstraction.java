import javax.swing.*;
import java.awt.*;

public class PillarOfAbstraction extends Pillar{
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Abstraction.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);
    private double myHeroOriginalVal;
    private int myTurnsPassed;
    private boolean myActive;

    private static final ImageIcon myImage = new ImageIcon(myPillarImage);

    /**
     * Constructor for this class. Simply initializes fields.
     *
     * @param theType
     */
    protected PillarOfAbstraction(PillarType theType) {
        super(myImage, theType);
    }

    // Set player to invincible for a few rounds
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
        System.out.println("Pillar of Abstraction ticked. Current Hero's block chance: " + DungeonAdventure.getMyHero().getBlockChance());
    }

    protected boolean isActive() {
        return this.myActive;
    }

    protected final ImageIcon getImage(){
        return myImage;
    }
}

