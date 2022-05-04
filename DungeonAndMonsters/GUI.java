import javax.swing.*;
import java.awt.*;

public abstract class GUI extends JFrame {
    protected static final int MY_WINDOW_WIDTH = 900;
    protected static final int MY_WINDOW_HEIGHT = 600;
    GUI(){
        Icon swordIcon = new ImageIcon("DungeonAndMonsters/sword.png", "Sword");
        ImageIcon swordImageIcon = new ImageIcon("DungeonAndMonsters/sword.png");
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
        JMenu save = new JMenu("SAVE");
        bar.add(help);
        bar.add(save);

        // Instruction submenu
        JMenuItem instr = new JMenuItem("Instructions");
        // Pulling stringbuilder text from DungeonAdventure.gamePlay method
        StringBuilder instructionText = DungeonAdventure.gamePlay();
        // Formatting using built in Java to HTML
        String instFormat = instructionText.toString().replace("\n", "<br>");
        String finalInstFormat = "<html><font size='5'>" + instFormat + "</font></htmt>";
        // Action listener implementing a dialog box
        instr.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, finalInstFormat);
        });

        // About message
        JMenuItem about = new JMenuItem("About");
        StringBuilder aboutText = aboutInfo();
        String aboutFormat = aboutText.toString().replace("\n", "<br>");
        String finalAboutFormat = "<html><font size='5'>" + aboutFormat + "</font></htmt>";
        // pull sword image for icon for dialog popup

        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, finalAboutFormat, "About", JOptionPane.INFORMATION_MESSAGE, swordIcon);
        });

        // Simple quitting button
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(e -> System.exit(0));;

        // Add menu items to help menu
        help.add(instr); help.add(about); help.add(quit);

        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.setEnabled(false);

        JMenuItem loadGame = new JMenuItem("Load Game");
        save.add(saveGame); save.add(loadGame);

    }

    /**
     * Can be moved to wherever we want to store large text data
     * TODO possibly can store in database?
     * @return Large StringBuilder representing our about info for use in GUI menu
     */
    public static StringBuilder aboutInfo() {
        float versionNum = 1.0f;
        StringBuilder aboutInfo = new StringBuilder();
        aboutInfo.append("Dungeons and Monsters - A Game Made for TCSS 360 Spring 2022. \n");
        aboutInfo.append("Alpha version:" + versionNum + "\n\nCreated by:\n");
        aboutInfo.append("- Mario Flores\n");
        aboutInfo.append("- Andrew Dibble\n");
        aboutInfo.append("- Alex Humphries\n");
        return aboutInfo;

    }

}
