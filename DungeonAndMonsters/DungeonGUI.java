import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DungeonGUI extends JPanel {

    /**
     * border used in GUI
     */
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);

    /**
     * instance of dungeon
     */
    private static Dungeon myDungeon;

    /** Health indicator reference
     */
    private static JLabel myHealthLabel;

    /**
     * Console to display what's going on in the dungeon
      */
    private static JTextArea myPlayerConsole;

    /** Room indicator (we are in room 0,0)
     */
    private static JLabel myRoomLabel;

    /** Image for the hero above health
     */
    private static JLabel myHeroImage;

    /** Name for hero
     */
    private static JLabel myHeroName;

    /** Display "window" we have inside the display panel .
     * Used for displaying the hero in game, monsters, items, rooms
     */
    private static drawWindow myDungeonWindow;

    /** Buttons used for movement
     */
    private static JButton myUpMoveButton;
    private static JButton myDownMoveButton;
    private static JButton myLeftMoveButton;
    private static JButton myRightMoveButton;

    /**
     * in game images
     */
    private static final JLabel myInGameSprite = new JLabel();
    private static final JLabel myInGamePit = new JLabel(new ImageIcon("DungeonAndMonsters/random images/smallerpit.png"));


    DungeonGUI(Font thePixelFont) {
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

        // Padding left and right of console and room label
        gbc.insets = new Insets(0,2,0,2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;

        displayPanel.add(myRoomLabel,gbc);

        // Console for player statuses during dungeon traversal
        myPlayerConsole = new JTextArea();
        myPlayerConsole.setBackground(Color.BLACK);
        myPlayerConsole.setFont(thePixelFont);
        myPlayerConsole.setForeground(Color.white);
        myPlayerConsole.setLineWrap(true);
        myPlayerConsole.setWrapStyleWord(true);
        myPlayerConsole.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 1;

        displayPanel.add(myPlayerConsole,gbc);


        // displayWindow panel, what we see inside the game
        gbc.insets = new Insets(20,0,20,10);
        gbc.gridheight = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        myDungeonWindow = new drawWindow();
        myDungeonWindow.setBorder(OUTLINE_BORDER);
        displayPanel.add(myDungeonWindow, gbc);


        // area for putting things into the display window ------------

        // Adding the temporary thief inGame sprite
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        myDungeonWindow.add(myInGameSprite,gbc);


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
        gbc.gridheight = 3;
        buttonPanel.add(playerArea, gbc);

        // Movement, map, guide, etc.
        JPanel buttonArea = new JPanel(new GridBagLayout());
        buttonArea.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        //gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(buttonArea, gbc);


        // playerArea - Place to have user stats and image -------------
        gbc.gridheight = 1;
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
            Music.playSFX("backpack");
            DungeonAdventure.sceneController("backpack");
        });

        // Map button
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton map = new JButton("Map");
        map.setFont(thePixelFont);
        buttonArea.add(map, gbc);
        map.addActionListener(e -> {
            Music.playSFX("openMap");
            DungeonAdventure.sceneController("map");
        });

        // Quit button
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton save = new JButton("Save Game");
        save.setFont(thePixelFont);
        buttonArea.add(save,gbc);
        save.addActionListener(e -> {
            Music.playSFX("save");
            JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
            chooser.setDialogTitle("Save Game");
            int selection = chooser.showSaveDialog(null);
            if(selection == JFileChooser.APPROVE_OPTION) {
                String s = chooser.getSelectedFile().toString();
                SaveGame.save(s);
                System.out.println(s);
            }
        });

        // Left button
        gbc.gridx = 1;
        gbc.gridy = 1;
        myLeftMoveButton = new JButton("Left");
        myLeftMoveButton.setFont(thePixelFont);
        buttonArea.add(myLeftMoveButton, gbc);
        myLeftMoveButton.addActionListener(e->{
            Music.playSFX("changeRoom");
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(), getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord() - 1));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            DungeonAdventure.checkRoom();

            repaint();
        });


        // Up button
        gbc.gridx = 2;
        gbc.gridy = 0;
        myUpMoveButton = new JButton("Up");
        myUpMoveButton.setFont(thePixelFont);
        buttonArea.add(myUpMoveButton, gbc);
        myUpMoveButton.addActionListener(e->{
            Music.playSFX("changeRoom");
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(), getDungeon().getCurrentRoom().getXCoord() - 1, getDungeon().getCurrentRoom().getYCoord()));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            DungeonAdventure.checkRoom();
            repaint();
        });

        // Down button
        gbc.gridx = 2;
        gbc.gridy = 2;
        myDownMoveButton = new JButton("Down");
        myDownMoveButton.setFont(thePixelFont);
        buttonArea.add(myDownMoveButton, gbc);
        myDownMoveButton.addActionListener(e->{
            Music.playSFX("changeRoom");
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(),getDungeon().getCurrentRoom().getXCoord() + 1, getDungeon().getCurrentRoom().getYCoord()));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            DungeonAdventure.checkRoom();
            repaint();
        });

        // Right button
        gbc.gridx = 3;
        gbc.gridy = 1;
        myRightMoveButton = new JButton("Right");
        myRightMoveButton.setFont(thePixelFont);
        buttonArea.add(myRightMoveButton, gbc);
        myRightMoveButton.addActionListener(e->{
            Music.playSFX("changeRoom");
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(), getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord() + 1));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            DungeonAdventure.checkRoom();
            repaint();
        });
    }

    /**
     * Equivalent to DungeonGUI's Start method. We call this when we first swap to
     * DungeonGUI page. Set's the players image, their name, their health, etc.
     * @param theHero Hero that the user chose on CharacterSelect
     * @param theDungeon Dungeon that is generated on createDungeon method.
     */
    public static void setUpVisualDungeon(Hero theHero, Dungeon theDungeon){
        setDungeon(theDungeon);
        setHealthLabel(theHero);
        setMyHeroImage(theHero);
        setMyInGameSprite(theHero);
        setMyHeroName(theHero);

        myDungeonWindow.remove(myInGamePit);

        myDungeonWindow.setWindowImg(DungeonAdventure.setRoomWindow(theDungeon, theDungeon.getCurrentRoom().getXCoord(), theDungeon.getCurrentRoom().getYCoord()));

        myRoomLabel.setText(DungeonAdventure.getRoomLabel(theDungeon));

        enableButtons();
        ArrayList<String> theRooms = Dungeon.availableRooms(theDungeon);
        disableButtons(theRooms);
    }


    /**
     * sets the health label
     * @param theHero the hero
     */
    static void setHealthLabel(final Hero theHero){
        myHealthLabel.setText("Current health: " + theHero.getHealth());
    }

    /**
     * sets the player console
     * @param theMessage the message
     */
    static void setPlayerConsole(StringBuilder theMessage){
        myPlayerConsole.setText(theMessage.toString());
    }

    /**
     * @return the room label
     */
    public static JLabel getMyRoomLabel(){return myRoomLabel;}


    /**
     * @return dungeon instance
     */
    public static Dungeon getDungeon(){
        return myDungeon;
    }

    /**
     * @param theDungeon what to set the dungeon to
     */
    public static void setDungeon(Dungeon theDungeon){
        myDungeon = theDungeon;
    }

    /**
     * Sets the GUI hero image
     * @param theHero user selected hero
     */
    static void setMyHeroImage(final Hero theHero){
        myHeroImage.setIcon(theHero.getMySprite());
    }

    /**
     * @param theHero what to set the image to
     */
    protected static void setMyInGameSprite(final Hero theHero){
        myInGameSprite.setIcon(theHero.getMyInGameSprite());
    }

    /**
     * Sets the GUI hero name
     * @param theHero user selected hero
     */
    private static void setMyHeroName(final Hero theHero){
        myHeroName.setText(theHero.getName());
    }

    /**
     * adds the pit image to the dungeon
     * @param gbc constraints
     */
    public static void addPit(GridBagConstraints gbc){
        gbc.gridx = 0;
        gbc.gridy = 0;
        myDungeonWindow.add(myInGamePit, gbc);
    }

    /**
     * disables the arrow buttons
     * @param theRooms which rooms to disable
     */
    static void disableButtons(ArrayList<String> theRooms){
        if(!theRooms.contains("south")){
            myDownMoveButton.setEnabled(false);
        }
        if(!theRooms.contains("north")){
            myUpMoveButton.setEnabled(false);
        }
        if(!theRooms.contains("west")){
            myLeftMoveButton.setEnabled(false);
        }
        if(!theRooms.contains("east")){
            myRightMoveButton.setEnabled(false);
        }
    }

    /**
     * sets all arrow buttons to enabled
     */
    static void enableButtons(){
        myDownMoveButton.setEnabled(true);
        myUpMoveButton.setEnabled(true);
        myRightMoveButton.setEnabled(true);
        myLeftMoveButton.setEnabled(true);
    }

    /**
     * resets teh dungeon room image
     */
    static void resetDungeonImage(){
        myDungeonWindow.setWindowImg(DungeonAdventure.setRoomWindow(getDungeon(), getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord()));
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
        protected Image getMyWindowImg(){return myWindowImg;}
    }
}
