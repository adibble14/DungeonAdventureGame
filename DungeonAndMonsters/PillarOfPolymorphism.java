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
            //TODO: for testing, randomly choose an image
            Image myImage = new ImageIcon("DungeonAndMonsters/monster pics/rpgCritterSkelly.png").getImage();
            BufferedImage bufferedImage = new BufferedImage(myImage.getWidth(null), myImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics gb = bufferedImage.getGraphics();
            gb.drawImage(myImage, 0, 0, null);
            gb.dispose();
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-myImage.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            bufferedImage = op.filter(bufferedImage, null);

            hero.setMyInGameSprite(new ImageIcon(bufferedImage));
            hero.setSprite(new ImageIcon("DungeonAndMonsters/random images/MonsterIcon.png"));
            DungeonGUI.setMyInGameSprite(hero);
            morphed = true;
        } else {
            // revert back to original state
            Hero original = careTaker.revertState();
            hero.setMyInGameSprite(original.getMyInGameSprite());
            hero.setSprite(original.getMySprite());
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
