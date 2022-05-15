import javax.swing.*;
import java.awt.*;

/**
 * We want the backpack menu to popout, so we
 * implement our GUI frame
 */
public class BackpackGUI extends GUI {
    // How many objects we can hold on the x axis
    private final int myBackpackWidth = 5;
    // How many objects we can hold on the y axis
    private final int myBackpackHeight = 5;

    // Panel to store things
    private static final JPanel myItemPanel = new JPanel();
    private static final JPanel myGoldPanel = new JPanel();

    // Image Icon Creation ---------------------------------------
    private static ImageIcon potionIcon = new ImageIcon("DungeonAndMonsters/random images/potion.png");
    Image potionImage = potionIcon.getImage();
    Image newPotionImage = potionImage.getScaledInstance(48,48, Image.SCALE_SMOOTH);
    // Image Icon Creation End------------------------------------

    private static Hero myHero;
    private JLabel playerGoldCount = new JLabel();
    BackpackGUI(Font pixelFont){
        // Change the name of the frame
        this.setTitle("Backpack!");
        // Size this frame to be slightly smaller
        this.setSize(450, 300);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // Not resizable and starts invisible
        this.setResizable(false);
        this.setVisible(false);
        // Gridbag
        myItemPanel.setLayout(new GridBagLayout());
        myItemPanel.setBackground(Color.BLACK);
        this.add(myItemPanel);
        potionIcon = new ImageIcon(newPotionImage);

        GridBagConstraints gbc = new GridBagConstraints();
        setSlotLayout(gbc, myBackpackWidth, myBackpackHeight);
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 2;
        gbc.gridy = 5;
        playerGoldCount.setFont(pixelFont);
        playerGoldCount.setForeground(Color.white);
        myItemPanel.add(playerGoldCount, gbc);
    }

    /**
     * Creates the content within the backpack frame.
     * Uses gridbaglayout to place buttons along the x and y axis
     * The buttons, if they have content to them, can be clicked to
     * activate things like potions
     * @param theGbc Constraints we place on the buttons
     * @param theBackpackWidth How many objects we can hold on the x axis
     * @param theBackpackHeight How many objects we can hold on the y axis
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
    // TODO update images and or functionality of buttons when we receive items
    public void addItemToBackpack(final String theItem){
        for (Component button:
             myItemPanel.getComponents()) {
            if(button instanceof JButton && ((JButton) button).getIcon() == null){
                button.setEnabled(true);
                switch(theItem){
                    case "HEALTH_POTION":
                        ((JButton) button).setIcon(potionIcon);
                        ((JButton) button).addActionListener(e -> {
                            //TODO use potion functionality
                        });
                        break;
                    case "VISION_POTION":
                        ((JButton) button).setIcon(potionIcon);
                        break;
                    default:
                        System.out.println("Null item");
                        break;
                }
                break;
            }
        }
    }

    public void setMyHero(Hero theHero){
        myHero = theHero;
        playerGoldCount.setText("Gold: "+ myHero.getGoldCount());
    }
}
