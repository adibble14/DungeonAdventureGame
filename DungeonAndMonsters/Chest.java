/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.util.ArrayList;

/**
 * this class creates a chest that a hero can find that contains various items in it
 */
public class Chest extends Item {

    /**
     * Item Contents that this chest holds
     */
    private final ArrayList<Item> myContents;

    /**
     * Chance of this chest being a mimic monster
     */
    private final double myMimicChance;

    /**
     * Flag for if this chest is a mimic
     */
    private boolean myIsMimic;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected Chest() {
        super(new ImageIcon("DungeonAndMonsters/potion.png"));
        this.myContents = new ArrayList<>();
        this.myMimicChance = .1;
        this.myIsMimic = false;
        this.addContents();
    }

    /**
     * Places the chests contents into the players inventory.
     * If chest contains a monster, does not add anything to player inventory and
     * Starts a battle.
     * @param theObj the hero
     */
    @Override
    void use(final Object theObj) {
        Hero player = (Hero) theObj;
        if(this.myIsMimic) {
            DungeonAdventure.createBattle(new Mimic());
        }else {
            for(Item i : this.myContents) {
                if(i.getClass() == Gold.class) {
                    i.use(player);
                }
                player.addInventory(i, 1);
            }
        }
    }

    /**
     * Initiates myContents field by adding a maximum of three Items.
     */
    private void addContents() {
        if(Tools.RANDOM.nextDouble() < this.myMimicChance) {
            this.myIsMimic = true;
            return;
        }

        // amount of items to add to this chest, max of three
        for(int i = 0; i < Tools.RANDOM.nextInt(1, 4); i++) {
            switch (Tools.RANDOM.nextInt(3)) {
                case 0 -> this.myContents.add(new HealthPotion());
                case 1 -> this.myContents.add(new VisionPotion());
                default -> this.myContents.add(new Gold());
            }
        }

    }
}
