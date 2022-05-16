import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DungeonGUI extends JPanel {
    // white outline border to be used in dungeon
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);

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

    private static JButton upMove;
    private static JButton downMove;
    private static JButton leftMove;
    private static JButton rightMove;



    // TODO temporary variable to test dungeonWindow
    //private JLabel myThiefInGameSprite = new JLabel(new ImageIcon("DungeonAndMonsters/character pics/goblinthief.png"));
    private static JLabel myInGameSprite = new JLabel();
    private static JLabel myInGamePit = new JLabel(new ImageIcon("DungeonAndMonsters/random images/pit.png"));

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
        gbc.insets = new Insets(20,0,20,10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        myDungeonWindow = new drawWindow();
        myDungeonWindow.setBorder(OUTLINE_BORDER);
        displayPanel.add(myDungeonWindow, gbc);


        // area for putting things into the display window ------------

        // Adding the temporary thief inGame sprite
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
        leftMove = new JButton("Left");
        leftMove.setFont(thePixelFont);
        buttonArea.add(leftMove, gbc);
        leftMove.addActionListener(e->{
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(), getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord() - 1));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            char init = DungeonAdventure.checkRoom();
            if(init == 'p'){
                addPit(gbc);
            }
            repaint();
        });


        // Up button
        gbc.gridx = 2;
        gbc.gridy = 0;
        upMove = new JButton("Up");
        upMove.setFont(thePixelFont);
        buttonArea.add(upMove, gbc);
        upMove.addActionListener(e->{
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(), getDungeon().getCurrentRoom().getXCoord() - 1, getDungeon().getCurrentRoom().getYCoord()));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            char init = DungeonAdventure.checkRoom();
            if(init == 'p'){
                addPit(gbc);
            }
            repaint();
        });

        // Down button
        gbc.gridx = 2;
        gbc.gridy = 2;
        downMove = new JButton("Down");
        downMove.setFont(thePixelFont);
        buttonArea.add(downMove, gbc);
        downMove.addActionListener(e->{
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(),getDungeon().getCurrentRoom().getXCoord() + 1, getDungeon().getCurrentRoom().getYCoord()));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            char init = DungeonAdventure.checkRoom();
            if(init == 'p'){
                addPit(gbc);
            }
            repaint();
        });

        // Right button
        gbc.gridx = 3;
        gbc.gridy = 1;
        rightMove = new JButton("Right");
        rightMove.setFont(thePixelFont);
        buttonArea.add(rightMove, gbc);
        rightMove.addActionListener(e->{
            myDungeonWindow.setWindowImg(DungeonAdventure.changeRooms(myDungeon, myDungeonWindow.getMyWindowImg(), getDungeon().getCurrentRoom().getXCoord(), getDungeon().getCurrentRoom().getYCoord() + 1));
            enableButtons();
            disableButtons(Dungeon.availableRooms(getDungeon()));
            myDungeonWindow.remove(myInGamePit);
            char init = DungeonAdventure.checkRoom();
            if(init == 'p'){
                addPit(gbc);
            }
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
        // Sets the Dungeon Hero locally inside DungeonGUI as well as his health
        setHealthLabel(theHero);
        // Sets the image of the hero
        setMyHeroImage(theHero);

        setMyInGameSprite(theHero);
        // Sets the name specified by what the player types in
        setMyHeroName(theHero);
        // Sets the starting room number in the corner of the GUI
        myDungeonWindow.setWindowImg(DungeonAdventure.setRoomWindow(theDungeon, theDungeon.getCurrentRoom().getXCoord(), theDungeon.getCurrentRoom().getYCoord()));

        myRoomLabel.setText(DungeonAdventure.getRoomLabel(theDungeon));

        ArrayList theRooms = Dungeon.availableRooms(theDungeon);
        disableButtons(theRooms);

    }


    static void setHealthLabel(final Hero theHero){
        myHealthLabel.setText("Current health: " + theHero.getHealth());
    }

    public static JLabel getMyRoomLabel(){return myRoomLabel;}


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

    private static void setMyInGameSprite(final Hero theHero){
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
     * @param gbc
     */
    private static void addPit(GridBagConstraints gbc){
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(70,80,70,80);
        gbc.anchor = GridBagConstraints.SOUTH;
        myDungeonWindow.add(myInGamePit, gbc);
    }

    private static void disableButtons(ArrayList theRooms){
        if(!theRooms.contains("south")){
            downMove.setEnabled(false);
        }
        if(!theRooms.contains("north")){
            upMove.setEnabled(false);
        }
        if(!theRooms.contains("west")){
            leftMove.setEnabled(false);
        }
        if(!theRooms.contains("east")){
            rightMove.setEnabled(false);
        }
    }

    private static void enableButtons(){
        downMove.setEnabled(true);
        upMove.setEnabled(true);
        rightMove.setEnabled(true);
        leftMove.setEnabled(true);
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
