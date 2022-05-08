import javax.swing.*;

public class Gold extends Item{


    /**
     * Value of this gold piece
     */
    private int myValue;

    /**
     * Constructor for this class. Simply initializes fields.
     *
     */
    protected Gold() {
        super(.20, new ImageIcon("DungeonAndMonsters/gold.png"));
        this.myValue = Tools.RANDOM.nextInt(5, 51);
    }

    @Override
    public void use(Object theObj) {
        //TODO: gold shouldnt have this, make gold not an Item?
    }
}
