import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharacterSelectionGUI extends JPanel{
    //TODO keep or get rid of?
    Image myArcherImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/archer.jpeg");
    Image myMageImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/mage.png");
    Image myThiefImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/thief.png");
    Image myWarriorImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/warrior.jpg");
    CharacterSelectionGUI(){
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("DungeonAndMonsters/VT323-Regular.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Settings for our internal frame
        // bg color and how it is laid out.
        this.setBackground(new Color(32,42,68));
        this.setLayout(new GridBagLayout());

        // Defining a panel for our characters
        // as well as the grid bag constraints
        JPanel characterPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        characterPanel.setBackground(new Color(32,42,68));
        characterPanel.setLayout(new GridBagLayout());

        // settings for our initial images
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Image placement ----------------------------------------
        // Archer
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel(new ImageIcon("DungeonAndMonsters/archer.jpeg"));
        characterPanel.add(label, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel label2 = new JLabel(new ImageIcon("DungeonAndMonsters/mage.jpeg"));
        characterPanel.add(label2, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel label3 = new JLabel(new ImageIcon("DungeonAndMonsters/thief.jpeg"));
        characterPanel.add(label3, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel label4 = new JLabel(new ImageIcon("DungeonAndMonsters/warrior.jpeg"));
        characterPanel.add(label4, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 0;
        JLabel label5 = new JLabel(new ImageIcon("DungeonAndMonsters/priestess.jpeg"));
        characterPanel.add(label5, gbc);
        // image placement end ----------------------------------------------------------------

        // button placement start -------------------------------------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        //Archer
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton archerButton = new JButton("Archer");
        characterPanel.add(archerButton, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton mageButton = new JButton("Mage");
        characterPanel.add(mageButton, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 1;
        JButton thiefButton = new JButton("Thief");
        characterPanel.add(thiefButton, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton warriorButton = new JButton("Warrior");
        characterPanel.add(warriorButton, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 1;
        JButton priestessButton = new JButton("Priestess");
        characterPanel.add(priestessButton, gbc);

        // button placement end -----------------------------------------------

        // Character description texts start ----------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;

        //Archer
        gbc.gridx = 0;
        gbc.gridy = 2;
        String archerFormat = DungeonAdventure.heroInfo("Archer").toString().replace("\n", "<br>");
        String finalArcherFormat = "<html><font size='5'>" + archerFormat + "</font></htmt>";
        JLabel archerDesc = new JLabel(finalArcherFormat);
        archerDesc.setForeground(Color.white);
        characterPanel.add(archerDesc, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 2;
        String mageFormat = DungeonAdventure.heroInfo("Mage").toString().replace("\n", "<br>");
        String finalMageFormat = "<html><font size='5'>" + mageFormat + "</font></htmt>";
        JLabel mageDesc = new JLabel(finalMageFormat);
        mageDesc.setForeground(Color.white);
        characterPanel.add(mageDesc, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 2;
        String thiefFormat = DungeonAdventure.heroInfo("Thief").toString().replace("\n", "<br>");
        String finalThiefFormat = "<html><font size='5'>" + thiefFormat + "</font></htmt>";
        JLabel thiefDesc = new JLabel(finalThiefFormat);
        thiefDesc.setForeground(Color.white);
        characterPanel.add(thiefDesc, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 2;
        String warriorFormat = DungeonAdventure.heroInfo("Warrior").toString().replace("\n", "<br>");
        String finalWarriorFormat = "<html><font size='5'>" + warriorFormat + "</font></htmt>";
        JLabel warriorDesc = new JLabel(finalWarriorFormat);
        warriorDesc.setForeground(Color.white);
        characterPanel.add(warriorDesc, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 2;
        String priestessFormat = DungeonAdventure.heroInfo("Priestess").toString().replace("\n", "<br>");
        String finalPriestessFormat = "<html><font size='5'>" + priestessFormat + "</font></htmt>";
        JLabel priestessDesc = new JLabel(finalPriestessFormat);
        priestessDesc.setForeground(Color.white);
        characterPanel.add(priestessDesc, gbc);
        // Character description texts end ------------------------------------------

        // final settings for placing the character panel in our frame
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        this.add(characterPanel, gbc);

        this.setSize(932, 650);
    }
}
