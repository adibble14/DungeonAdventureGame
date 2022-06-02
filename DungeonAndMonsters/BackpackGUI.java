import javax.swing.*;
import java.awt.*;

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
    BackpackGUI(Font pixelFont){

        this.setTitle("Backpack");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
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
                            //button.setEnabled(false);
                            //((JButton) button).setIcon(null);
                            repaint();
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
    public static void removeHealthPotion(){
        for (Component button: myItemPanel.getComponents()) {
            ((JButton) button).setToolTipText(null);
            button.setEnabled(false);
            ((JButton) button).setIcon(null);
            break;
        }
    }

    /**
     * when playing again need to remove all items
     */
    public static void removeAllItems(){
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
    public int getMyActiveHealthPotions() {
        return this.myActiveHealthPotions;
    }
    /**
     * @return count of active vision potions
     */
    public int getMyActiveVisionPotions() {
        return this.myActiveVisionPotions;
    }

    /**
     * refreshes gold label when player received more gold.
     */
    public void refreshGoldValue() {
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
        this.refreshGoldValue();
    }

}
