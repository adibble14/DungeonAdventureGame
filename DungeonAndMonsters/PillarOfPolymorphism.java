import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class PillarOfPolymorphism extends Pillar{
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Polymorphism.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);
    /**
     * CareTaker pattern that saves the state of Hero object and reverts to previous state.
     */
    private CareTaker careTaker;
    /**
     * boolean to check if player morphed
     */
    private boolean morphed;

    private static final ImageIcon myImage = new ImageIcon(myPillarImage);
    /**
     * Constructor for this class. Simply initializes fields.
     * @param theType
     */
    protected PillarOfPolymorphism(PillarType theType) {
        super(myImage, theType);
        this.careTaker = new CareTaker();
        this.morphed = false;
    }

    @Override
    public void use(Object theObj) {
        Hero hero = (Hero) theObj;
        if(!morphed) {
            careTaker.saveState(hero);
            // choose random monster sprite

            ImageIcon image = new ImageIcon("DungeonAndMonsters/monster pics/poly" + Tools.RANDOM.nextInt(1,4) +".png");
            hero.setMyInGameSprite(image);
            DungeonGUI.setMyInGameSprite(hero);
            hero.setMaxDamage(hero.getMaxDamage() + 40);
            hero.setAccuracy(hero.getMyAccuracy() + .1);
            morphed = true;
        } else {
            // revert back to original state
            Hero original = careTaker.revertState();
            hero.setMyInGameSprite(original.getMyInGameSprite());
            DungeonGUI.setMyInGameSprite(hero);
            hero.setMaxDamage(original.getMaxDamage());
            hero.setAccuracy(original.getMyAccuracy());
            morphed = false;
        }

        //TODO: Steps to take
        // save the current hero state using CareTaker class
        // randomly choose a Monster maybe?
        // set that monster to DungeonAdventure hero object
        // other classes may need to be adjusted
        // How to revert back to original hero object?

    }

    protected final ImageIcon getImage(){
        return myImage;
    }
}
