import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Handles the saving of the games state
 *
 * Objects that are saved so far:
 *
 */

public class SaveGame {


    /**
     * save game
     * @param theFile file
     */
    public static void save(final String theFile) {
        try {
            FileOutputStream outputFile = new FileOutputStream(theFile);
            ObjectOutputStream out = new ObjectOutputStream(outputFile);
            // writing objects to output file
            out.writeObject(DungeonAdventure.getMyHero());
            out.writeObject(DungeonAdventure.getMyDungeon());
            // post operation clean up
            out.flush();
            out.close();
            outputFile.close();

        }   catch (Exception e) {
            System.out.println("Error saving file");
            e.printStackTrace();
        }
    }

    /**
     * load game
     * @param theFile file
     */
    public static void load(final String theFile) {

        try {
            FileInputStream inputFile = new FileInputStream(theFile);
            ObjectInputStream in = new ObjectInputStream(inputFile);
            // Reading object data from file
            Hero hero = (Hero) in.readObject();
            Dungeon dungeon = (Dungeon) in.readObject();
            // setting read objects to class fields
            String name = hero.getClass().getSimpleName();
            hero.setSprite(new ImageIcon(SQLiteDB.getCharacterImage(name,"heroes")));
            hero.setMyInGameSprite(new ImageIcon(SQLiteDB.getCharacterInGameImage(name)));
            DungeonAdventure.setMyHero(hero);
            DungeonAdventure.setMyDungeon(dungeon);
            DungeonAdventure.sceneController("dungeon");
            DungeonAdventure.loadUpGame();
            // post operation clean up
            inputFile.close();
            in.close();

        } catch (Exception e) {
            System.out.println("Error loading file");
            e.printStackTrace();
        }
    }
}
