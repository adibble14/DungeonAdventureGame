import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    private static JLabel myDungeonLabel;

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
        myRoomLabel.setFont(thePixelFont);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20,10,0,0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        displayPanel.add(myRoomLabel,gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        myDungeonLabel = new JLabel();
        displayPanel.add(myDungeonLabel, gbc);



        // TODO Will split up button panel into two areas
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

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        myHeroName = new JLabel();
        myHeroName.setFont(thePixelFont);
        myHeroName.setForeground(Color.WHITE);
        playerArea.add(myHeroName, gbc);

        // Player portrait
        gbc.gridx = 0;
        gbc.gridy = 1;
        myHeroImage = new JLabel("",SwingConstants.CENTER);
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
        gbc.insets = new Insets(10,5,10,5);

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
        setMyDungeonRoom(theDungeon);
        setMyRoomLabel(theDungeon);
    }

    private static void setDungeonHero(Hero theHero){
        myDungeonHero = theHero;
        myHealthLabel.setText("Current health: " + myDungeonHero.getHealth());
    }

    private static void setMyHeroImage(Hero theHero){
        myHeroImage.setIcon(theHero.getMySprite());
    }

    private static void setMyHeroName(Hero theHero){
        myHeroName.setText(theHero.getName());
    }

    public static void setMyRoomLabel(Dungeon theDungeon){
        myRoomLabel.setText("Current room: [" + theDungeon.getCurrentRoom().getXCoord()
                + "," + theDungeon.getCurrentRoom().getYCoord() + "]");
    }
    //TODO issue with the dungeon coordinates
    public static void setMyDungeonRoom(Dungeon theDung){
        Room room = theDung.getCurrentRoom();
        HashMap<int[], Room> roomHashMap = Tools.GET_NEIGHBORS(theDung.getDungeon(), room);
        for(Map.Entry<int[], Room> set: roomHashMap.entrySet()) {
            System.out.println("Key: " + Arrays.toString(set.getKey()) + " " + "Value: " + set.getValue());
        }

        System.out.println("Room: " + room);
        int row = room.getXCoord();
        int col = room.getYCoord();
        System.out.println("Row: " +row);
        System.out.println("Col: " + col);
        Room south = theDung.getRoom(row+1, col);
        Room north = theDung.getRoom(row-1, col);
        Room west = theDung.getRoom(row, col-1);
        Room east = theDung.getRoom(row, col+1);
        System.out.println("South: " + south);
        System.out.println("North: " + north);
        System.out.println("West: " + west);
        System.out.println("East: " + east);

        if(north != null && south != null && east != null && west != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_4.png"));return;
        }else if(north != null && south != null && east != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_3_right.png"));return;
        }else if(north != null && south != null && west != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_3_left.png"));return;
        }else if(south != null && west != null && east != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_3_down.png"));return;
        }else if(north != null && west != null && east!= null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_3_left.png"));return;
        }else if(north != null && west != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_2_topleft.png"));return;
        }else if(north != null && east != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_2_topright.png"));return;
        }else if(south != null && west != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_2_bottomleft.png"));return;
        }else if(south != null && east != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_2_bottomright.png"));return;
        }else if(south != null && north != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_2_updown.png"));return;
        }else if(west != null && east != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/dungeon_2_leftright.png"));return;
        }else if(north != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/Dungeon_1_up.png"));return;
        }else if(south != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/Dungeon_1_down.png"));return;
        }else if(east != null){
            myDungeonLabel.setIcon(new ImageIcon("DungeonAndMonsters/dungeon pics/Dungeon_1_left.png"));return;
        }else
            myDungeonLabel.setText("Closed off room! Error!");

    }
}
