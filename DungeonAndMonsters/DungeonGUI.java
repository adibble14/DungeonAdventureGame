import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DungeonGUI extends JPanel {
    // white outline border to be used in dungeon
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);

    DungeonGUI(Font thePixelFont){
        Hero hero = DungeonAdventure.getMyHero();
        // regular layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Setting up our display area

        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setOpaque(true);
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(OUTLINE_BORDER);
        this.add(displayPanel, gbc);


        //--------------------------------buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(OUTLINE_BORDER);
        this.add(buttonPanel, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(10, 5, 0, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        JButton backpack = new JButton("Backpack");
        backpack.setFont(thePixelFont);
        buttonPanel.add(backpack, gbc);
        backpack.addActionListener(e -> {
            DungeonAdventure.sceneController("backpack");
        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton map = new JButton("Map");
        map.setFont(thePixelFont);
        buttonPanel.add(map, gbc);
        map.addActionListener(e -> {
            DungeonAdventure.sceneController("map");
        });
        //end buttons --------------------------------------------

        //TODO label not showing up
        try{
            JLabel healthLabel = new JLabel("Current Health: " );//+ hero.getHealth());
            healthLabel.setFont(new Font("Serif", Font.BOLD, 20));
            healthLabel.setForeground(Color.WHITE);
            healthLabel.setBackground(Color.BLACK);
            this.add(healthLabel, gbc);
        }catch (NullPointerException n){
            System.out.println("hero not initialized");
        }

    }
}
