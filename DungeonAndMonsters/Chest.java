import javax.swing.*;
import java.util.ArrayList;

public class Chest extends Item{

    /**
     * Item Contents that this chest holds
     */
    private ArrayList<Item> myContents;

    /**
     * Chance of this chest being a mimic monster
     */
    private double myMimicChance;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected Chest() {
        super(.1, new ImageIcon("DungeonAndMonsters/potion.png"));
        this.myMimicChance = .17;
        this.addContents();
    }

    /**
     * Places the chests contents into the players inventory.
     * If chest contains a monster, does not add anything to player inventory and
     * Starts a battle.
     * @param theObj
     */
    @Override
    public void use(Object theObj) {
        if(theObj.getClass() == Hero.class) {
            Hero player = (Hero) theObj;
            player.addInventory("Stuff"); //TODO: Fix player inventory
        }
    }

    /**
     * Initiates myContents field by adding a maximum of three Items.
     */
    private void addContents() {

        // 17% chance of being a mimic
        if(Tools.RANDOM.nextDouble() < this.myMimicChance) {
            this.myContents.add(null); //TODO: Create a Mimic monster.
            return;
        }

        // amount of items to add to this chest, max of three
        for(int i = 0; i < Tools.RANDOM.nextInt(1, 4); i++) {
            switch(Tools.RANDOM.nextInt(3)) {
                case 0: this.myContents.add(new HealthPotion());
                        break;
                case 1: this.myContents.add(new VisionPotion());
                        break;
                default: this.myContents.add(new Gold());
            }
        }



    }
}
