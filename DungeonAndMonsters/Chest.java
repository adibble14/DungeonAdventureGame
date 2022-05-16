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
     * Flag for if this chest is a mimic
     */
    private boolean myIsMimic;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected Chest() {
        super(.1, new ImageIcon("DungeonAndMonsters/potion.png"));
        this.myContents = new ArrayList<>();
        this.myMimicChance = 0;
        this.myIsMimic = false;
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
        Hero player = (Hero) theObj;
        if(this.myIsMimic) {
            DungeonAdventure.createBattle(new Mimic());
            return;
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
    //new Mimic("Mimic",150,5,30,15,.7,.2,10,15);
    private void addContents() {
        // 17% chance of being a mimic
        if(Tools.RANDOM.nextDouble() < this.myMimicChance) {
            this.myIsMimic = true; //TODO: Create a Mimic monster.
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

    /**
     * Access method for boolean myIsMimic flag
     * @return
     */
    public boolean isMimic() {
        return this.myIsMimic;
    }
}
