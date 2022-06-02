import javax.swing.*;
import java.awt.*;

/**
 * abstract class that creates a basis for what all the GUIs have in common
 */
public abstract class GUI extends JFrame {
    /**
     * the width of the window
     */
    protected static final int MY_WINDOW_WIDTH = 950;

    /**
     * the height of the window
     */
    protected static final int MY_WINDOW_HEIGHT = 650;

    /**
     * constructor of GUI
     */
    GUI(){
        Icon swordIcon = new ImageIcon("DungeonAndMonsters/random images/sword.png", "Sword");
        ImageIcon swordImageIcon = new ImageIcon("DungeonAndMonsters/random images/sword.png");
        this.setTitle("Dungeons And Monsters");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(MY_WINDOW_WIDTH, MY_WINDOW_HEIGHT));
        this.setIconImage(swordImageIcon.getImage());
        this.setResizable(true);
        this.setVisible(true);
        pack();
        // Menu bar for help and save options
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        JMenu help = new JMenu("HELP");
        bar.add(help);

        // Instruction submenu
        JMenuItem instr = new JMenuItem("Instructions");
        StringBuilder instructionText = gamePlay();
        String instFormat = instructionText.toString().replace("\n", "<br>");
        String finalInstFormat = "<html><font size='5'>" + instFormat + "</font></html>";
        instr.addActionListener(e -> JOptionPane.showMessageDialog(this, finalInstFormat));

        // About message
        JMenuItem about = new JMenuItem("About");
        StringBuilder aboutText = aboutInfo();
        String aboutFormat = aboutText.toString().replace("\n", "<br>");
        String finalAboutFormat = "<html><font size='5'>" + aboutFormat + "</font></html>";
        about.addActionListener(e -> JOptionPane.showMessageDialog(this, finalAboutFormat, "About", JOptionPane.INFORMATION_MESSAGE, swordIcon));

        // Simple quitting button
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(e -> System.exit(0));

        // Add menu items to help menu
        help.add(instr); help.add(about); help.add(quit);

    }


    /**
     * Can be moved to wherever we want to store large text data
     * @return Large StringBuilder representing our about info for use in GUI menu
     */
    public static StringBuilder aboutInfo() {
        float versionNum = 1.0f;
        StringBuilder aboutInfo = new StringBuilder();
        aboutInfo.append("Dungeons and Monsters - A Game Made for TCSS 360 Spring 2022. \n");
        aboutInfo.append("Alpha version:").append(versionNum).append("\n\nCreated by:\n");
        aboutInfo.append("- Mario Flores\n");
        aboutInfo.append("- Andrew Dibble\n");
        aboutInfo.append("- Alex Humphries\n");
        return aboutInfo;

    }

    /**
     * Displays general information on the console screen.
     */
    public static StringBuilder gamePlay() {

        StringBuilder generalInfo = new StringBuilder();
        generalInfo.append("Dungeons and Monsters - A Dungeon Crawling & Monster Fighting Game. \n\n");
        generalInfo.append("Game Play:\n\n- Choose a Hero to do battle with \n- Choose your character name \n");
        generalInfo.append("- Navigate through the dungeon. Find potions along the way to aid you.\n");
        generalInfo.append("- Find the exit to win. Four Pillars of OO are required to exit. They are somewhere in the dungeon. \n");
        generalInfo.append("- Traps and various monsters await in the Dungeon.\n");
        return generalInfo;

    }



}
