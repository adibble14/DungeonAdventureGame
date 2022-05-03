import javax.swing.*;
import java.awt.*;

public class MainGUI extends GUI{

    MainGUI(){
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        //this.setLayout(new CardLayout());
        this.add(cardPanel);
        MenuGUI menuGUI = new MenuGUI();
        CharacterSelectionGUI characterSelectionGUI = new CharacterSelectionGUI();
        DungeonGUI dungeonGUI = new DungeonGUI();
        BattleGUI battleGUI = new BattleGUI();
        BackpackGUI backpackGUI = new BackpackGUI();
        cardPanel.add(menuGUI, "menu");
        cardPanel.add(characterSelectionGUI, "character");
        cardPanel.add(dungeonGUI, "dungeon");
        cardPanel.add(battleGUI, "battle");
        cardPanel.add(backpackGUI, "backpack");
        cardLayout.show(cardPanel, "menu");
    }
}
