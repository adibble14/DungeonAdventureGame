import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * the main GUI class that holds all the panels of the other GUI classes
 */
public class MainGUI extends GUI{
    /** Layout that will control when cards are shown
     */
    CardLayout myCardLayout = new CardLayout();

    /**
     * panel that controls which cards are shown
     */
    JPanel myCardPanel = new JPanel(myCardLayout);
    /** Pixel font
     */
    private Font myCustomFont;

    /**
     * instance of the backpack
     */
    private final BackpackGUI myBackpackGui;
    /**
     * instance of the map
     */
    private final MapGUI myMapGui;
    /**
     * the dungeon
     */
    private static Dungeon myDungeon;

    /**
     * the constructor of mainGUI
     */
    MainGUI(){
        try {
            myCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("DungeonAndMonsters/VT323-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(myCustomFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.add(myCardPanel);

        // Create all our content panels
        MenuGUI menuGUI = new MenuGUI();
        CharacterSelectionGUI characterSelectionGUI = new CharacterSelectionGUI(myCustomFont);
        DungeonGUI dungeonGUI = new DungeonGUI(myCustomFont);
        BattleGUI battleGUI = new BattleGUI(myCustomFont);
        MonsterGUI monsterGUI = new MonsterGUI(myCustomFont);
        myBackpackGui = new BackpackGUI(myCustomFont);
        myMapGui = new MapGUI();

        // Add the panels to the cardPanel using string constraints
        myCardPanel.add(menuGUI, "menu");
        myCardPanel.add(characterSelectionGUI, "character");
        myCardPanel.add(dungeonGUI, "dungeon");
        myCardPanel.add(battleGUI, "battle");
        myCardPanel.add(monsterGUI, "monster");

        // Start by showing menuGUI
        myCardLayout.show(myCardPanel, "menu");
        Music.playMusic("mainMenu");
    }

    /**
     * @return the backpack
     */
    public BackpackGUI getBackpackGui(){
        return myBackpackGui;
    }

    /**
     * @param theDungeon set the dungeon
     */
    protected void setMyDungeon(Dungeon theDungeon){
        myDungeon = theDungeon;
    }


    /**
     * @return the map
     */
    protected MapGUI getMapGui(){
        return myMapGui;
    }

    /**
     * called by scene controller in DungeonAdventure, switches the card that is shown
     * @param myCardName which card to switch to
     */
    protected void setCurrentCard(String myCardName){
        switch (myCardName) {
            case "menu" -> myCardLayout.show(myCardPanel, "menu");
            case "character" -> myCardLayout.show(myCardPanel, "character");
            case "dungeon" -> {
                myCardLayout.show(myCardPanel, "dungeon");
                Music.playMusic("dungeon");
            }
            case "map" -> myMapGui.setVisible(true);
            case "backpack" -> myBackpackGui.setVisible(true);
            case "battle" -> myCardLayout.show(myCardPanel, "battle");
            case "monster" -> myCardLayout.show(myCardPanel, "monster");
        }
    }

    /**
     * close the backpack
     */
    void closeBackPack(){
        myBackpackGui.dispatchEvent(new WindowEvent(myBackpackGui, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * close the map
     */
    void closeMap(){
        myMapGui.dispatchEvent(new WindowEvent(myMapGui, WindowEvent.WINDOW_CLOSING));
    }

}
