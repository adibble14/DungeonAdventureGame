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
        if(theObj.getClass() == Hero.class) {
            Hero player = (Hero) theObj;
            //TODO: make the player able to use gold.
        }
    }
}
