/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Creates the Backpack implemented as a GUI. Holds items found by the hero in the dungeon.
 */
public class BackpackGUI extends JFrame {

    /**
     * active health potions
     */
    private int myActiveHealthPotions;

    /**
     * active vision potions
     */
    private int myActiveVisionPotions;

    /**
     * panel to store the items retrieved from dungeon
     */
    private static final JPanel myItemPanel = new JPanel();

    /**
     * the image of the health potion
     */
    private static ImageIcon myHealthPotionImage = new ImageIcon("DungeonAndMonsters/random images/ResizedPotion.png");
    Image actualHealthPotionImage = myHealthPotionImage.getImage();
    Image resizedHealthPotionImage = actualHealthPotionImage.getScaledInstance(48,48, Image.SCALE_SMOOTH);

    /**
     * the image of the vision potion
     */
    private static ImageIcon myVisionPotionImage = new ImageIcon("DungeonAndMonsters/random images/VisionPotion.png");
    Image actualVisionPotionImage = myVisionPotionImage.getImage();
    Image resizedVisionPotionImage = actualVisionPotionImage.getScaledInstance(48,48, Image.SCALE_SMOOTH);

    /**
     * the amount of gold, resembled as a JLabel
     */
    private final JLabel myPlayerGoldCount = new JLabel();

    /**
     * the constructor for the backpack
     * @param pixelFont a certain font used in the frame
     */
    BackpackGUI(final Font pixelFont){

        this.setTitle("Backpack");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(false);

        myItemPanel.setLayout(new GridBagLayout());
        myItemPanel.setBackground(Color.BLACK);
        this.add(myItemPanel);
        myHealthPotionImage = new ImageIcon(resizedHealthPotionImage);
        myVisionPotionImage = new ImageIcon(resizedVisionPotionImage);

        GridBagConstraints gbc = new GridBagConstraints();

        int myBackpackWidth = 5;
        int myBackpackHeight = 5;
        setSlotLayout(gbc, myBackpackWidth, myBackpackHeight);
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 2;
        gbc.gridy = 5;
        myPlayerGoldCount.setFont(pixelFont);
        myPlayerGoldCount.setForeground(Color.white);
        myItemPanel.add(myPlayerGoldCount, gbc);
        this.myActiveHealthPotions = 0;
        this.myActiveVisionPotions = 0;
    }

    /**
     * Creates the content within the backpack frame.
     * Uses gridbaglayout to place buttons along the x and y-axis
     * The buttons, if they have content to them, can be clicked to
     * activate things like potions
     * @param theGbc Constraints we place on the buttons
     * @param theBackpackWidth How many objects we can hold on the x-axis
     * @param theBackpackHeight How many objects we can hold on the y-axis
     */
    private static void setSlotLayout(final GridBagConstraints theGbc, final int theBackpackWidth, final int theBackpackHeight){
        theGbc.weightx = 1;
        theGbc.weighty = 1;
        theGbc.fill = GridBagConstraints.BOTH;
        theGbc.insets = new Insets(2,2,2,2);
        for(int i = 0; i < theBackpackWidth; i++){
            for(int j = 0; j < theBackpackHeight; j++){
                theGbc.gridx = i;
                theGbc.gridy = j;
                JButton itemSlot = new JButton();
                itemSlot.setEnabled(false);
                myItemPanel.add(itemSlot, theGbc);
            }
        }
    }

    /**
     * adding the potions to the backpack when found in the dungeon
     * @param thePotion which potion it is
     */
    public void addPotionToBackpack(final String thePotion){
        for (Component button:
             myItemPanel.getComponents()) {
            if(button instanceof JButton && ((JButton) button).getIcon() == null){
                button.setEnabled(true);
                for (ActionListener listener :  ((JButton) button).getActionListeners()) {  //removing all previous action listeners
                    ((JButton) button).removeActionListener(listener);
                }
                switch (thePotion) {
                    case "HEALTH_POTION" -> {
                        this.myActiveHealthPotions++;
                        ((JButton) button).setIcon(myHealthPotionImage);
                        ((JButton) button).setToolTipText("Health Potion");
                        ((JButton) button).addActionListener(e -> {
                            Music.playSFX("healthPotion");
                            int healthAmount = DungeonAdventure.getMyHero().useHealthPotion();
                            DungeonGUI.setHealthLabel(DungeonAdventure.getMyHero());
                            DungeonGUI.setPlayerConsole(new StringBuilder("Healed " + healthAmount + " health points!"));
                            if (BattleGUI.getMyBattle() != null) {
                                BattleGUI.updateBattle();
                                BattleGUI.setBattleConsole(new StringBuilder("Health Potion revived " + healthAmount + " points of health. "));
                            }
                            myActiveHealthPotions--;
                            button.setEnabled(false);
                            ((JButton) button).setIcon(null);
                            repaint();
                        });
                    }
                    case "VISION_POTION" -> {
                        this.myActiveVisionPotions++;
                        ((JButton) button).setIcon(myVisionPotionImage);
                        ((JButton) button).setToolTipText("Vision Potion");
                        ((JButton) button).addActionListener(e -> {
                            Music.playSFX("visionPotion");
                            DungeonAdventure.getMyHero().useVisionPotion(DungeonAdventure.getMyDungeon());
                            DungeonGUI.setPlayerConsole(new StringBuilder(DungeonAdventure.getMyHero().getName() + " used a vision potion."));
                            DungeonAdventure.refreshMap();
                            button.setEnabled(false);
                            ((JButton) button).setIcon(null);
                            myActiveVisionPotions--;
                        });
                    }
                    default -> System.out.println("Null item");
                }
                break;
            }
        }
    }

    /**
     * adds pillar to backpack after defeating a boss monster
     * @param theItem the pillar in String form
     * @param itemImage the image
     * @param pillar the pillar
     */
    protected void addPillarToBackpack(final String theItem, final ImageIcon itemImage, Pillar pillar){
        for (Component button:
                myItemPanel.getComponents()) {
            if(button instanceof JButton && ((JButton) button).getIcon() == null){
                button.setEnabled(true);
                for (ActionListener listener :  ((JButton) button).getActionListeners()) {  //removing all previous action listeners
                    ((JButton) button).removeActionListener(listener);
                }
                switch (theItem) {
                    case "abstract" -> {
                        ((JButton) button).setIcon(itemImage);
                        ((JButton) button).setToolTipText("Pillar of Abstraction - Use for temporary invulnerability!");
                        ((JButton) button).addActionListener(e -> {
                            Music.playSFX("usePillar");
                            pillar.use(DungeonAdventure.getMyHero());
                            button.setEnabled(false);
                            //((JButton) button).setIcon(null);
                            repaint();
                        });
                    }
                    case "encapsulation" -> {
                        ((JButton) button).setIcon(itemImage);
                        ((JButton) button).setToolTipText("Pillar of Encapsulation - Use to reveal the whole dungeon!");
                        ((JButton) button).addActionListener(e -> {
                            Music.playSFX("usePillar");
                            pillar.use(DungeonAdventure.getMyDungeon());
                            button.setEnabled(false);
                            //((JButton) button).setIcon(null);
                            repaint();
                        });
                    }
                    case "inheritance" -> {
                        ((JButton) button).setIcon(itemImage);
                        ((JButton) button).setToolTipText("Pillar of Inheritance - Use to inherit some wealth!");
                        ((JButton) button).addActionListener(e -> {
                            Music.playSFX("usePillar");
                            pillar.use(DungeonAdventure.getMyHero());
                            button.setEnabled(false);
                            //((JButton) button).setIcon(null);
                            repaint();
                        });
                    }
                    case "polymorphism" -> {
                        ((JButton) button).setIcon(itemImage);
                        ((JButton) button).setToolTipText("Pillar of Polymorphism - Use for a small change.");
                        ((JButton) button).addActionListener(e -> {
                            Music.playSFX("usePillar");
                            pillar.use(DungeonAdventure.getMyHero());
                            repaint();
                            button.setEnabled(false);
                        });
                    }
                    default -> System.out.println("Null item");
                }
                break;
            }
        }
    }

    /**
     * removes a health potion from the backpack
     */
    static void removeHealthPotion(){
        for (Component button: myItemPanel.getComponents()) {
            if(button.isEnabled()){
                JButton jbutton = (JButton) button;
                if(jbutton.getIcon() == myHealthPotionImage) {
                    ((JButton) button).setToolTipText(null);
                    button.setEnabled(false);
                    ((JButton) button).setIcon(null);
                    break;
                }
            }
        }
    }

    /**
     * when playing again need to remove all items
     */
    static void removeAllItems(){
        for(Component button: myItemPanel.getComponents()){
            if(button instanceof JButton) {
                ((JButton) button).setToolTipText(null);
                button.setEnabled(false);
                ((JButton) button).setIcon(null);
            }
        }
    }


    /**
     * @return count of active health potions
     */
    int getMyActiveHealthPotions() {
        return this.myActiveHealthPotions;
    }
    /**
     * @return count of active vision potions
     */
    int getMyActiveVisionPotions() {
        return this.myActiveVisionPotions;
    }

    void setMyActiveHealthPotions(int theNum){
        this.myActiveHealthPotions = theNum;
    }

    /**
     * refreshes gold label when player received more gold.
     */
    void refreshGoldValue() {
        myPlayerGoldCount.setText("Gold: "+ DungeonAdventure.getMyHero().getGoldCount());
    }

    /**
     * called when loading a saved file
     */
    public void loadPlayerInventory() {
        PlayerInventory inv = DungeonAdventure.getMyHero().getMyInventory();
        while(this.getMyActiveHealthPotions() < inv.getItemCount(ItemType.HEALTH_POTION)) {
            this.addPotionToBackpack(ItemType.HEALTH_POTION.toString());
        }
        while(this.getMyActiveVisionPotions() < inv.getItemCount(ItemType.VISION_POTION)) {
            this.addPotionToBackpack(ItemType.VISION_POTION.toString());
        }
        // adding pillars
        Pillar [] p = inv.getPillars();
        for(Pillar pillar : p) {
            PillarType t = pillar.getMY_TYPE();
            switch(t) {
                case ABSTRACTION -> this.addPillarToBackpack("abstract",new ImageIcon("DungeonAndMonsters/random images/Abstraction.png"),pillar);
                case INHERITANCE -> this.addPillarToBackpack("inheritance",new ImageIcon("DungeonAndMonsters/random images/Inheritance.png"),pillar);
                case ENCAPSULATION -> this.addPillarToBackpack("encapsulation",new ImageIcon("DungeonAndMonsters/random images/Encapsulation.png"),pillar);
                default -> this.addPillarToBackpack("polymorphism",new ImageIcon("DungeonAndMonsters/random images/Polymorphism.png"),pillar);
            }
        }
        this.refreshGoldValue();
    }


}
