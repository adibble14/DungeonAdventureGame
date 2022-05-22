import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MainGUI extends GUI{
    // Layout that will control when cards are shown
    CardLayout myCardLayout = new CardLayout();
    // Panel to hold the cardlayout
    JPanel myCardPanel = new JPanel(myCardLayout);
    // Pixel font
    private Font myCustomFont;
    // Reference to backpack frame
    private BackpackGUI myBackpackGui;
    // Reference to Map Frame
    private MapGUI myMapGui;
    // Reference to the hero and dungeon
    //private Hero theHero;
    private static Dungeon myDungeon;
    MainGUI(){
        try {
            //create the font to use. Specify the size!
            myCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("DungeonAndMonsters/VT323-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(myCustomFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // Adding the cardPanel to the Frame
        this.add(myCardPanel);

        // Create all our content panels
        MenuGUI menuGUI = new MenuGUI(myCustomFont);
        CharacterSelectionGUI characterSelectionGUI = new CharacterSelectionGUI(myCustomFont);
        DungeonGUI dungeonGUI = new DungeonGUI(myCustomFont);
        BattleGUI battleGUI = new BattleGUI(myCustomFont);
        myBackpackGui = new BackpackGUI(myCustomFont);
        myMapGui = new MapGUI(myCustomFont);

        // Add the panels to the cardPanel using string constraints
        myCardPanel.add(menuGUI, "menu");
        myCardPanel.add(characterSelectionGUI, "character");
        myCardPanel.add(dungeonGUI, "dungeon");
        myCardPanel.add(battleGUI, "battle");

        // Start by showing menuGUI
        myCardLayout.show(myCardPanel, "menu");
        Music player = new Music();
        player.playMusic("DungeonAndMonsters/clip_1.wav");
    }

    public BackpackGUI getBackpackGui(){
        return myBackpackGui;
    }

    public void setTheHero(Hero myHero){
        //theHero = myHero;
        myBackpackGui.setMyHero(myHero);
    }

    protected void setMyDungeon(Dungeon theDungeon){
        myDungeon = theDungeon;
    }

    protected static Dungeon getMyDungeon(){
        return myDungeon;
    }

    protected MapGUI getMapGui(){
        return myMapGui;
    }

    protected void setCurrentCard(String myCardName){
        switch (myCardName){
            case "menu":
                myCardLayout.show(myCardPanel,"menu");
                break;
            case "character":
                myCardLayout.show(myCardPanel, "character");
                break;
            case "dungeon":
                myCardLayout.show(myCardPanel, "dungeon");
                break;
            case "map":
                myMapGui.setVisible(true);
                break;
            case "backpack":
                //theCardLayout.show(theCardPanel, "backpack");
                myBackpackGui.setVisible(true);
                break;
            case "battle":
                myCardLayout.show(myCardPanel, "battle");
                break;
        }
    }

    /**
     * returns the current panel visible
     * @return
     */
    JPanel getCurrentCard(){
        for(Component comp : myCardPanel.getComponents()) {
            if (comp.isVisible()) {
                return (JPanel)comp;
            }
        }
        return null;
    }


    void closeBackPack(){
        myBackpackGui.dispatchEvent(new WindowEvent(myBackpackGui, WindowEvent.WINDOW_CLOSING));
    }

    void closeMap(){
        myMapGui.dispatchEvent(new WindowEvent(myMapGui, WindowEvent.WINDOW_CLOSING));
    }

}
