/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.awt.*;

/**
 * Frame used for displaying Main Menu
 */
public class MenuGUI extends JPanel{

    /**Setting panel background before constructor
     */
    Image img = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/danksouls.jpg");
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,0,0,getWidth(),getHeight(),this);
    }


    /**
     * Constructor which defines what will be inside the Frame
     */
    MenuGUI(){
        // Using Grid bag layout see https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        // Create a label for the title
        JLabel titleLabel = new JLabel("Dungeons and Monsters");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);

        gbc.insets = new Insets(75, 0, 0, 0);
        // Add the label to the menu panel using the grid bag layout constraints
        this.add(titleLabel,gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;

        // Create new panel to hold buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        // Button creation / event handling
        JButton newGame = new JButton("NEW GAME");
        newGame.setFont(new Font("Serif", Font.BOLD, 20));
        newGame.setPreferredSize(new Dimension(200,50));
        newGame.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            DungeonAdventure.sceneController("character");
        });

        JButton loadGame = new JButton("LOAD GAME");
        loadGame.setFont(new Font("Serif", Font.BOLD, 20));
        loadGame.setPreferredSize(new Dimension(200,50));
        loadGame.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
            chooser.setDialogTitle("Load");
            int selection = chooser.showOpenDialog(null);
            if(selection == JFileChooser.APPROVE_OPTION) {
                String s = chooser.getSelectedFile().toString();
                SaveGame.load(s);
                System.out.println(s);
            }
        });

        JButton instr = new JButton("HOW TO PLAY");
        instr.setFont(new Font("Serif", Font.BOLD, 20));
        instr.setPreferredSize(new Dimension(200,50));
        instr.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            StringBuilder instructionText = GUI.gamePlay();
            String instFormat = instructionText.toString().replace("\n", "<br>");
            String finalInstFormat = "<html><font size='5'>" + instFormat + "</font></html>";
            JOptionPane.showMessageDialog(this, finalInstFormat);
        });

        JButton quit = new JButton("QUIT");
        quit.setFont(new Font("Serif", Font.BOLD, 20));
        quit.setPreferredSize(new Dimension(200,50));
        quit.addActionListener(e -> System.exit(0));


        // Buttons have their own grid bag constraints setup here
        gbc.insets = new Insets(2,0,2,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(newGame, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(loadGame, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(instr, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(quit, gbc);

        // Final constraints when we add the button panel to the menu
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1;
        gbc.insets = new Insets(0,0,20,0);
        this.add(buttonPanel, gbc);

    }
}
