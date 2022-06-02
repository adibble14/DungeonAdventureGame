import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * vision class that can reveal a part of the dungeon
 */
public class VisionPotion extends Item {


    /**
     * Constructor for this class. Simply initializes fields.
     */
    protected VisionPotion() {
        super(new ImageIcon("DungeonAndMonsters/potion.png"));
    }

    /**
     * uses the potion, reveals some rooms
     * @param theObj the hero
     */
    @Override
    public void use(Object theObj) {
        Dungeon dung = (Dungeon)  theObj;
        HashMap<int[], Room> map = Tools.GET_NEIGHBORS(dung.getDungeon(), dung.getCurrentRoom());
        ArrayList<int[]> keys = new ArrayList<>(map.keySet());
        for(int [] k : keys) {
            if(map.get(k) != null) {
                Room r = map.get(k);
                r.setMyDiscovery();
            }
        }
        map = Tools.GET_CORNER_NEIGHBORS(dung.getDungeon(),dung.getCurrentRoom());
        keys = new ArrayList<>(map.keySet());
        for(int [] k : keys) {
            if(map.get(k) != null) {
                Room r = map.get(k);
                r.setMyDiscovery();
            }
        }

    }

}
