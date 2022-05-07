import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DungeonGUI extends JPanel {
    // white outline border to be used in dungeon
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);

    // Hero reference
    private static Hero myDungeonHero;

    // Health indicator reference
    private static JLabel myHealthLabel;

    // Room indicator (we are in room 0,0)
    private static JLabel myRoomLabel;

    // Image for the hero above health
    private static JLabel myHeroImage;

    // Name for hero
    private static JLabel myHeroName;

    // TODO |ANY VALUE THAT WILL CHANGE ON THIS PAGE I.E. HEALTH SHOULD HAVE A STATIC LABEL
    // TODO |OR TEXT OR WHATEVER THAT WE CHANGE OUTSIDE
    DungeonGUI(Font thePixelFont) {
        //Hero hero = DungeonAdventure.getMyHero();
        // regular layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Setting up our display area

        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setOpaque(true);
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(OUTLINE_BORDER);
        this.add(displayPanel, gbc);

        // Room indicator
        myRoomLabel = new JLabel("Room: [0,0]");
        myRoomLabel.setForeground(Color.white);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,20);
        displayPanel.add(myRoomLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel dungeonLabel = new JLabel(new ImageIcon("DungeonAndMonsters/dungeon pics/Dungeon_1_down.png"));
        displayPanel.add(dungeonLabel, gbc);



        // TODO Will split up button panel into to areas
        //--------------------------------buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(OUTLINE_BORDER);
        this.add(buttonPanel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //This area is for player image, name and health
        JPanel playerArea = new JPanel(new GridBagLayout());
        playerArea.setOpaque(false);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        buttonPanel.add(playerArea);

        JPanel buttonArea = new JPanel(new GridBagLayout());
        buttonArea.setOpaque(false);
        gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(buttonArea);
        //gbc.weighty = 1;

        // Player portrait
        gbc.gridx = 0;
        gbc.gridy = 1;
        myHeroImage = new JLabel(new ImageIcon("DungeonAndMonsters/character pics/archerpixel.png"));
        myHeroImage.setBorder(OUTLINE_BORDER);
        playerArea.add(myHeroImage, gbc);

        // Player health text
        gbc.gridx = 0;
        gbc.gridy = 2;
        myHealthLabel = new JLabel();
        myHealthLabel.setFont(thePixelFont);
        myHealthLabel.setForeground(Color.WHITE);
        playerArea.add(myHealthLabel, gbc);


        //end buttons --------------------------------------------
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        myHeroName = new JLabel();
        myHeroName.setFont(thePixelFont);
        myHeroName.setForeground(Color.WHITE);
        buttonArea.add(myHeroName, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton backpack = new JButton("Backpack");
        backpack.setFont(thePixelFont);
        buttonArea.add(backpack, gbc);
        backpack.addActionListener(e -> {
            DungeonAdventure.sceneController("backpack");
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton map = new JButton("Map");
        map.setFont(thePixelFont);
        buttonArea.add(map, gbc);
        map.addActionListener(e -> {
            DungeonAdventure.sceneController("map");
        });




    }

    public static void setUpVisualDungeon(Hero theHero, Dungeon theDungeon){
        setDungeonHero(theHero);
        setMyHeroImage(theHero);
        setMyHeroName(theHero);
        //setMyHeroImage(theHero);
        //setMyRoomLabel(theDungeon);
    }

    private static void setDungeonHero(Hero theHero){
        myDungeonHero = theHero;
        myHealthLabel.setText("Current health: " + myDungeonHero.getHealth());
    }

    private static void setMyHeroImage(Hero theHero){
        //myHeroImage.setIcon(theHero.getImage());
    }

    private static void setMyHeroName(Hero theHero){
        myHeroName.setText(theHero.getName());
    }

    private static void setMyRoomLabel(Dungeon theDungeon){
        myRoomLabel.setText("Current room: " + theDungeon.getCurrentRoom().getXCoord()
                + ", " + theDungeon.getCurrentRoom().getYCoord());
    }
}
