import javax.swing.*;
import java.awt.*;

public class MainGUI extends GUI{
    // Layout that will control when cards are shown
    CardLayout theCardLayout = new CardLayout();
    // Panel to hold the cardlayout
    JPanel theCardPanel = new JPanel(theCardLayout);
    MainGUI(){
        // Adding the cardPanel to the Frame
        this.add(theCardPanel);

        // Create all our content panels
        MenuGUI menuGUI = new MenuGUI();
        CharacterSelectionGUI characterSelectionGUI = new CharacterSelectionGUI();
        DungeonGUI dungeonGUI = new DungeonGUI();
        BattleGUI battleGUI = new BattleGUI();
        BackpackGUI backpackGUI = new BackpackGUI();

        // Add the panels to the cardPanel using string constraints
        theCardPanel.add(menuGUI, "menu");
        theCardPanel.add(characterSelectionGUI, "character");
        theCardPanel.add(dungeonGUI, "dungeon");
        theCardPanel.add(battleGUI, "battle");
        theCardPanel.add(backpackGUI, "backpack");

        // Start by showing menuGUI
        theCardLayout.show(theCardPanel, "menu");
    }

    protected void setCurrentCard(String myCardName){
        switch (myCardName){
            case "character":
                theCardLayout.show(theCardPanel, "character");
        }
    }
}
