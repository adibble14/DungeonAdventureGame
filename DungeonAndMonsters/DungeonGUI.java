import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DungeonGUI extends JPanel {
    // white outline border to be used in dungeon
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);

    DungeonGUI(Font thePixelFont){
        // regular layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Setting up our display area

        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setOpaque(true);
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(OUTLINE_BORDER);
        this.add(displayPanel);


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(OUTLINE_BORDER);
        this.add(buttonPanel);
    }
}
