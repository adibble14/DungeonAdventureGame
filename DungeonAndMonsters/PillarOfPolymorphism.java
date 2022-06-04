/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.awt.*;

public class PillarOfPolymorphism extends Pillar{

    /**
     * the image
     */
    private static final Image myPillarImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/Polymorphism.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);
    /**
     * CareTaker pattern that saves the state of Hero object and reverts to previous state.
     */
    private final CareTaker careTaker;
    /**
     * boolean to check if player morphed
     */
    private transient boolean morphed;

    private static final ImageIcon myImage = new ImageIcon(myPillarImage);
    /**
     * Constructor for this class. Simply initializes fields.
     * @param theType the pillar type
     */
    protected PillarOfPolymorphism(PillarType theType) {
        super(myImage, theType);
        this.careTaker = new CareTaker();
        this.morphed = false;
    }

    /**
     * turning hero into monster
     * @param theObj the hero
     */
    @Override
    final void use(Object theObj) {
        Hero hero = (Hero) theObj;
        if(!morphed) {
            careTaker.saveState(hero);
            // choose random monster sprite

            ImageIcon image = new ImageIcon("DungeonAndMonsters/monster pics/poly" + Tools.RANDOM.nextInt(1,4) +".png");
            hero.setMyInGameSprite(image);
            DungeonGUI.setMyInGameSprite(hero);
            hero.setMaxDamage(hero.getMaxDamage() + 40);
            hero.setAccuracy(hero.getMyAccuracy() + .15);
            hero.setMaxHealth(hero.getMaxHealth() + hero.getHealth()/2);
            morphed = true;
        } else {
            Hero original = careTaker.revertState();
            hero.setMyInGameSprite(original.getMyInGameSprite());
            DungeonGUI.setMyInGameSprite(hero);
            hero.setMaxDamage(original.getMaxDamage());
            hero.setAccuracy(original.getMyAccuracy());
            hero.setMaxHealth(original.getMaxHealth());
            morphed = false;
        }
    }

    /**
     * @return the image
     */
    protected final ImageIcon getImage(){
        return myImage;
    }
}
