import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Frame used for displaying Main Menu
 */
public class MenuGUI extends GUI{

    /**
     * Constructor which defines what will be inside the Frame
     */
    MenuGUI(){
        // setting an image as the background
        Image img = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/danksouls.jpg");
        this.setContentPane(new JPanel(){
        @Override
        public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        });

        // Creating a panel for the menu
        JPanel menuPanel = new JPanel();



        // Remove the background color of panel
        menuPanel.setOpaque(false);
        // Give borders to the panel
        menuPanel.setBorder(new EmptyBorder(10,10,10,10));


        // Using gridbag layout
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        menuPanel.add(new JLabel("Dungeons and Monsters!"));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton play = new JButton("PLAY");
        JButton quit = new JButton("QUIT");
        buttonPanel.add(play, gbc);
        buttonPanel.add(quit, gbc);

        gbc.weighty = 1;
        menuPanel.add(buttonPanel, gbc);

        // Add panel to the frame
        this.add(menuPanel, gbc);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        this.setSize(900, 650);
    }
}
