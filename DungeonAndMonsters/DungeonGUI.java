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

    private static Dungeon myDungeon;

    // Health indicator reference
    private static JLabel myHealthLabel;

    // Room indicator (we are in room 0,0)
    private static JLabel myRoomLabel;

    // Image for the hero above health
    private static JLabel myHeroImage;

    // Name for hero
    private static JLabel myHeroName;

    // Display "window" we have inside the display panel
    // Used for displaying the hero in game, monsters, items, rooms
    private static drawWindow myDungeonWindow;



    // TODO temporary variable to test dungeonWindow
    private JLabel myThiefInGameSprite = new JLabel(new ImageIcon("DungeonAndMonsters/character pics/goblinRogueChar.png"));

    // TODO Any variables that are updated outside this class make static?
    DungeonGUI(Font thePixelFont) {
        // Set up DungeonGUI Layout, it contains two panels
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        // Constraints for display area
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridx = 0;

        // Panel 1) Display area containing the window showing what's going on in the game
        // as well as the room indicator. TAKES UP 3/4 of the window
        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setOpaque(true);
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(OUTLINE_BORDER);
        this.add(displayPanel, gbc);

        // Constraints for Button Area
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridheight = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.weightx = 1;

        // Panel 2) buttons - buttons representing how the user interacts with the display
        // should take up 1/4th of the window
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(OUTLINE_BORDER);
        this.add(buttonPanel, gbc);

        // Children panel setup ------------------------------------

        // Room indicator
        myRoomLabel = new JLabel("Room: [0,0]");
        myRoomLabel.setForeground(Color.white);
        myRoomLabel.setFont(thePixelFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.10;
        gbc.weighty = 1;

        displayPanel.add(myRoomLabel,gbc);

        // displayWindow panel, what we see inside the game
        gbc.insets = new Insets(20,10,20,10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        myDungeonWindow = new drawWindow();
        myDungeonWindow.setBorder(OUTLINE_BORDER);
        displayPanel.add(myDungeonWindow, gbc);


        // area for putting things into the display window ------------

        // Adding the temporary thief inGame sprite
        myDungeonWindow.add(myThiefInGameSprite,gbc);


        // Resetting some constraints
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,0,10);


        //This area is for player image, name and health
        JPanel playerArea = new JPanel(new GridBagLayout());
        playerArea.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        buttonPanel.add(playerArea, gbc);

        // Movement, map, guide, etc.
        JPanel buttonArea = new JPanel(new GridBagLayout());
        buttonArea.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        //gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(buttonArea, gbc);


        // playerArea - Place to have user stats and image -------------
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


        //end buttons - for use in buttonArea panel ----------------
        gbc.insets = new Insets(10,5,10,5);

        // Backpack button
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton backpack = new JButton("Backpack");
        backpack.setFont(thePixelFont);
        buttonArea.add(backpack, gbc);
        backpack.addActionListener(e -> {
            DungeonAdventure.sceneController("backpack");
        });

        // Map button
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton map = new JButton("Map");
        map.setFont(thePixelFont);
        buttonArea.add(map, gbc);
        map.addActionListener(e -> {
            DungeonAdventure.sceneController("map");
        });

        // Quit button
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton quit = new JButton("Quit");
        quit.setFont(thePixelFont);
        buttonArea.add(quit,gbc);
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Left button
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton leftMove = new JButton("Left");
        leftMove.setFont(thePixelFont);
        buttonArea.add(leftMove, gbc);
        leftMove.addActionListener(e->{
           changeRooms(getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord() - 1);
            repaint();
        });


        // Up button
        gbc.gridx = 2;
        gbc.gridy = 0;
        JButton upMove = new JButton("Up");
        upMove.setFont(thePixelFont);
        buttonArea.add(upMove, gbc);
        upMove.addActionListener(e->{
            changeRooms(getDungeon().getCurrentRoom().getXCoord() - 1, getDungeon().getCurrentRoom().getYCoord());
            repaint();
        });

        // Down button
        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton downMove = new JButton("Down");
        downMove.setFont(thePixelFont);
        buttonArea.add(downMove, gbc);
        downMove.addActionListener(e->{
            changeRooms(getDungeon().getCurrentRoom().getXCoord() + 1, getDungeon().getCurrentRoom().getYCoord());
            repaint();
        });

        // Right button
        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton rightMove = new JButton("Right");
        rightMove.setFont(thePixelFont);
        buttonArea.add(rightMove, gbc);
        rightMove.addActionListener(e->{
            changeRooms(getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord() + 1);
            repaint();
        });
    }

    public static void changeRooms(int theX, int theY){
        Room newCurrent = myDungeon.getRoom(theX, theY);
        if(newCurrent != null){
            myDungeon.setCurrentRoom(newCurrent, myDungeonHero);
            setMyRoomLabel(myDungeon);
            setMyDungeonRoom(myDungeon);
        }else{
            System.out.println("can't go that way!");
        }

    }


    /**
     * Equivalent to DungeonGUI's Start method. We call this when we first swap to
     * DungeonGUI page. Set's the players image, their name, their health, etc.
     * @param theHero Hero that the user chose on CharacterSelect
     * @param theDungeon Dungeon that is generated on createDungeon method.
     */
    public static void setUpVisualDungeon(Hero theHero, Dungeon theDungeon){
        setDungeon(theDungeon);
        // Sets the Dungeon Hero locally inside DungeonGUI as well as his health
        setDungeonHero(theHero);
        // Sets the image of the hero
        setMyHeroImage(theHero);
        // Sets the name specified by what the player types in
        setMyHeroName(theHero);
        // Sets the starting room number in the corner of the GUI
        setMyDungeonRoom(theDungeon);
        // Sets the image associated with where we are.
        setMyRoomLabel(theDungeon);
    }

    /**
     * sets the Dungeon Hero locally, as well as set's the health label
     * with the hero's health
     * @param theHero
     */
    private static void setDungeonHero(final Hero theHero){
        myDungeonHero = theHero;
        myHealthLabel.setText("Current health: " + myDungeonHero.getHealth());
    }

    public static Dungeon getDungeon(){
        return myDungeon;
    }

    public static void setDungeon(Dungeon theDungeon){
        myDungeon = theDungeon;
    }

    /**
     * Sets the GUI hero image
     * @param theHero user selected hero
     */
    private static void setMyHeroImage(final Hero theHero){
        myHeroImage.setIcon(theHero.getMySprite());
    }

    /**
     * Sets the GUI hero name
     * @param theHero user selected hero
     */
    private static void setMyHeroName(final Hero theHero){
        myHeroName.setText(theHero.getName());
    }

    /**
     * Sets the GUI room location, i.e. the 0th row and 0th column
     * room would be 'Current room: [0,0]'
     * @param theDungeon Dungeon created after CharacterSelect
     */
    public static void setMyRoomLabel(final Dungeon theDungeon){
        myRoomLabel.setText("Current room: [" + theDungeon.getCurrentRoom().getXCoord()
                + "," + theDungeon.getCurrentRoom().getYCoord() + "]");
    }

    /**
     * Fancy way of changing the image representation of the room
     * we are currently in inside the game. In the GUI we have an image that
     * changes depending on free rooms around us.
     * @param theDung Dungeon created after CharacterSelect
     */
    public static void setMyDungeonRoom(final Dungeon theDung){
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
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_4.png"));
        }else if(north != null && south != null && east != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_right.png"));
        }else if(north != null && south != null && west != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_left.png"));
        }else if(south != null && west != null && east != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_down.png"));
        }else if(north != null && west != null && east!= null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_3_left.png"));
        }else if(north != null && west != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_topleft.png"));
        }else if(north != null && east != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_topright.png"));
        }else if(south != null && west != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_bottomleft.png"));
        }else if(south != null && east != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_bottomright.png"));
        }else if(south != null && north != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_updown.png"));
        }else if(west != null && east != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/dungeon_2_leftright.png"));
        }else if(north != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_up.png"));
        }else if(south != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_down.png"));
        }else if(east != null){
            myDungeonWindow.setWindowImg(Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/Dungeon_1_left.png"));
        }else
            System.out.println("Error! Room with no exits!");

    }

    /**
     * Inner class drawWindow that works as the display panel showing off the
     * current room's image, as well as the inGame player and inGame enemies, items, etc.
     */
    protected static class drawWindow extends JPanel{
        private Image myWindowImg;

        drawWindow(){
            this.setLayout(new GridBagLayout());
            this.setVisible(true);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(myWindowImg,0,0,getWidth(),getHeight(),this);
        }

        /**
         * changes the image background of this display (which is the room image).
         * @param theWindowImg Image we wish to display as the background
         */
        protected void setWindowImg(final Image theWindowImg){
            myWindowImg = theWindowImg;
        }
    }
}
