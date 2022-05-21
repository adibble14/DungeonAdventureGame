import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MainGUI extends GUI{
    // Layout that will control when cards are shown
    CardLayout theCardLayout = new CardLayout();
    // Panel to hold the cardlayout
    JPanel theCardPanel = new JPanel(theCardLayout);
    // Pixel font
    private Font theCustomFont;
    // Reference to backpack frame
    private BackpackGUI myBackpackGui;
    // Reference to the hero
    private Hero theHero;
    MainGUI(){
        try {
            //create the font to use. Specify the size!
            theCustomFont = Font.createFont(Font.TRUETYPE_FONT, new File("DungeonAndMonsters/VT323-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(theCustomFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // Adding the cardPanel to the Frame
        this.add(theCardPanel);

        // Create all our content panels
        MenuGUI menuGUI = new MenuGUI(theCustomFont);
        CharacterSelectionGUI characterSelectionGUI = new CharacterSelectionGUI(theCustomFont);
        DungeonGUI dungeonGUI = new DungeonGUI(theCustomFont);
        BattleGUI battleGUI = new BattleGUI(theCustomFont);
        myBackpackGui = new BackpackGUI(theCustomFont);
        MapGUI mapGUI = new MapGUI(theCustomFont);

        // Add the panels to the cardPanel using string constraints
        theCardPanel.add(menuGUI, "menu");
        theCardPanel.add(characterSelectionGUI, "character");
        theCardPanel.add(dungeonGUI, "dungeon");
        theCardPanel.add(battleGUI, "battle");
        //theCardPanel.add(backpackGUI, "backpack");
        theCardPanel.add(mapGUI, "map");

        // Start by showing menuGUI
        theCardLayout.show(theCardPanel, "menu");
        Music player = new Music();
        player.playMusic("DungeonAndMonsters/clip_1.wav");
    }

    public BackpackGUI getBackpackGui(){
        return myBackpackGui;
    }

    public void setTheHero(Hero myHero){
        theHero = myHero;
        myBackpackGui.setMyHero(myHero);
    }

    protected void setCurrentCard(String myCardName){
        switch (myCardName){
            case "menu":
                theCardLayout.show(theCardPanel,"menu");
                break;
            case "character":
                theCardLayout.show(theCardPanel, "character");
                break;
            case "dungeon":
                theCardLayout.show(theCardPanel, "dungeon");
                break;
            case "map":
                theCardLayout.show(theCardPanel, "map");
                break;
            case "backpack":
                //theCardLayout.show(theCardPanel, "backpack");
                myBackpackGui.setVisible(true);
                break;
            case "battle":
                theCardLayout.show(theCardPanel, "battle");
                break;
        }
    }

    void closeBackPack(){
        myBackpackGui.dispatchEvent(new WindowEvent(myBackpackGui, WindowEvent.WINDOW_CLOSING));
    }

}
