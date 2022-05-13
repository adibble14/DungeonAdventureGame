import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainGUI extends GUI{
    // Layout that will control when cards are shown
    CardLayout theCardLayout = new CardLayout();
    // Panel to hold the cardlayout
    JPanel theCardPanel = new JPanel(theCardLayout);
    // Pixel font
    private Font customFont;

    private BackpackGUI backpackGUI;
    MainGUI(){
        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("DungeonAndMonsters/VT323-Regular.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // Adding the cardPanel to the Frame
        this.add(theCardPanel);

        // Create all our content panels
        MenuGUI menuGUI = new MenuGUI(customFont);
        CharacterSelectionGUI characterSelectionGUI = new CharacterSelectionGUI(customFont);
        DungeonGUI dungeonGUI = new DungeonGUI(customFont);
        BattleGUI battleGUI = new BattleGUI(customFont);
        backpackGUI = new BackpackGUI(customFont);
        MapGUI mapGUI = new MapGUI(customFont);

        // Add the panels to the cardPanel using string constraints
        theCardPanel.add(menuGUI, "menu");
        theCardPanel.add(characterSelectionGUI, "character");
        theCardPanel.add(dungeonGUI, "dungeon");
        theCardPanel.add(battleGUI, "battle");
        //theCardPanel.add(backpackGUI, "backpack");
        theCardPanel.add(mapGUI, "map");

        // Start by showing menuGUI
        theCardLayout.show(theCardPanel, "menu");
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
                backpackGUI.setVisible(true);
                break;
            case "battle":
                theCardLayout.show(theCardPanel, "battle");
                break;
        }
    }
}
