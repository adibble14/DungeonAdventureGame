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
    BackpackGUI(Font pixelFont){
        // Change the name of the frame
        this.setTitle("Backpack!");
        // TODO not changing background color
        this.setBackground(Color.BLACK);
        // Size this frame to be slightly smaller
        this.setSize(450, 300);
        // Not resizable and starts invisible
        this.setResizable(false);
        this.setVisible(false);
        // Gridbag
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        setSlotLayout(gbc, this, myBackpackWidth, myBackpackHeight);
    }

    /**
     * Creates the content within the backpack frame.
     * Uses gridbaglayout to place buttons along the x and y axis
     * The buttons, if they have content to them, can be clicked to
     * activate things like potions
     * @param theGbc Constraints we place on the buttons
     * @param theFrame Backpack frame reference
     * @param theBackpackWidth How many objects we can hold on the x axis
     * @param theBackpackHeight How many objects we can hold on the y axis
     */
    private static void setSlotLayout(final GridBagConstraints theGbc, final JFrame theFrame, final int theBackpackWidth, final int theBackpackHeight){
        theGbc.weightx = 1;
        theGbc.weighty = 1;
        for(int i = 0; i < theBackpackWidth; i++){
            for(int j = 0; j < theBackpackHeight; j++){
                theGbc.gridx = i;
                theGbc.gridy = j;
                theFrame.add(new JButton("Potion"), theGbc);
            }
        }
    }
    // TODO update images and or functionality of buttons when we receive items
    private static void addItemToBackpack(){}
}
