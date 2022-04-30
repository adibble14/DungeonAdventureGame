import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Frame used for displaying Main Menu
 */
public class MenuGUI extends GUI{

    protected boolean isActive;
    /**
     * Constructor which defines what will be inside the Frame
     */
    MenuGUI(){
        // Start by setting the frame to active
        isActive = true;
        // setting an image as the background
        Image img = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/danksouls.jpg");
        // Content pane works as background in this case
        this.setContentPane(new JPanel(){
        @Override
        public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        });

        // Creating a panel to hold menu
        JPanel menuPanel = new JPanel();
        // Remove the background color of panel
        menuPanel.setOpaque(false);

        // Using gridbag layout see https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Change gridbag constraint settings for **Title**
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Create a label for the title
        JLabel titleLabel = new JLabel("Dungeons and Monsters");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel.setForeground(Color.BLACK);

        // Add the label to the menu panel using the grid bag layout constraints
        menuPanel.add(titleLabel,gbc);

        // Change gridbag constraint settings for ***button panel***
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;

        // Create new panel to hold buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        // Button creation / event handling
        JButton play = new JButton("PLAY");
        play.setFont(new Font("Serif", Font.BOLD, 20));
        play.setPreferredSize(new Dimension(200,50));
        /**
         * Observer pattern,
         */
        play.addActionListener(e -> {
            isActive = false;
        });

        JButton quit = new JButton("QUIT");
        quit.setFont(new Font("Serif", Font.BOLD, 20));
        quit.setPreferredSize(new Dimension(200,50));
        quit.addActionListener(e -> System.exit(0));


        // Buttons have their own gridbag constraints setup here
        gbc.insets = new Insets(20,1,20,1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(play, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(quit, gbc);

        // Final constraints when we add the button panel to the menu
        gbc.weighty = 1;
        gbc.insets = new Insets(100, 1, 100, 1);
        menuPanel.add(buttonPanel, gbc);


        // Add menu panel to the frame
        this.add(menuPanel, gbc);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
