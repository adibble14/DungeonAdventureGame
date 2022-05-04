import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharacterSelectionGUI extends JPanel{
        Font customFont;
    CharacterSelectionGUI(){

        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("DungeonAndMonsters/VT323-Regular.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Settings for our internal panel
        // bg color and how it is laid out.
        // as well as the grid bag constraints
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(new Color(32,42,68));
        this.setLayout(new GridBagLayout());

        // settings for our initial images
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Image placement ----------------------------------------
        // Archer
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel(new ImageIcon("DungeonAndMonsters/archer.jpeg"));
        this.add(label, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel label2 = new JLabel(new ImageIcon("DungeonAndMonsters/mage.jpeg"));
        this.add(label2, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel label3 = new JLabel(new ImageIcon("DungeonAndMonsters/thief.jpeg"));
        this.add(label3, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel label4 = new JLabel(new ImageIcon("DungeonAndMonsters/warrior.jpeg"));
        this.add(label4, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 0;
        JLabel label5 = new JLabel(new ImageIcon("DungeonAndMonsters/priestess.jpeg"));
        this.add(label5, gbc);
        // image placement end ----------------------------------------------------------------

        // Character description texts start ----------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;

        //Archer
        gbc.gridx = 0;
        gbc.gridy = 1;
        String archerFormat = DungeonAdventure.heroInfo("Archer").toString().replace("\n", "<br>");
        String finalArcherFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + archerFormat + "</h1></font></html>";
        JLabel archerDesc = new JLabel(finalArcherFormat);
        archerDesc.setFont(customFont);
        archerDesc.setForeground(Color.white);
        this.add(archerDesc, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 1;
        String mageFormat = DungeonAdventure.heroInfo("Mage").toString().replace("\n", "<br>");
        String finalMageFormat = "<html><font size='3'>" + mageFormat + "</font></html>";
        JLabel mageDesc = new JLabel(finalMageFormat);
        mageDesc.setForeground(Color.white);
        this.add(mageDesc, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 1;
        String thiefFormat = DungeonAdventure.heroInfo("Thief").toString().replace("\n", "<br>");
        String finalThiefFormat = "<html><font size='3'>" + thiefFormat + "</font></html>";
        JLabel thiefDesc = new JLabel(finalThiefFormat);
        thiefDesc.setForeground(Color.white);
        this.add(thiefDesc, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 1;
        String warriorFormat = DungeonAdventure.heroInfo("Warrior").toString().replace("\n", "<br>");
        String finalWarriorFormat = "<html><font size='3'>" + warriorFormat + "</font></html>";
        JLabel warriorDesc = new JLabel(finalWarriorFormat);
        warriorDesc.setForeground(Color.white);
        this.add(warriorDesc, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 1;
        String priestessFormat = DungeonAdventure.heroInfo("Priestess").toString().replace("\n", "<br>");
        String finalPriestessFormat = "<html><font size='3'>" + priestessFormat + "</font></html>";
        JLabel priestessDesc = new JLabel(finalPriestessFormat);
        priestessDesc.setForeground(Color.white);
        this.add(priestessDesc, gbc);
        // Character description texts end ------------------------------------------


        // button placement start -------------------------------------------------------------
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        //Archer
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton archerButton = new JButton("Archer");
        this.add(archerButton, gbc);
        archerButton.addActionListener(e -> {
            setUserName();
            DungeonAdventure.setMyHeroChoice("a");
            DungeonAdventure.setUPGame();
        });

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton mageButton = new JButton("Mage");
        this.add(mageButton, gbc);
        mageButton.addActionListener(e -> {
            setUserName();
            DungeonAdventure.setMyHeroChoice("m");
            DungeonAdventure.setUPGame();
        });

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton thiefButton = new JButton("Thief");
        this.add(thiefButton, gbc);
        thiefButton.addActionListener(e -> {
            setUserName();
            DungeonAdventure.setMyHeroChoice("t");
            DungeonAdventure.setUPGame();
        });

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 2;
        JButton warriorButton = new JButton("Warrior");
        this.add(warriorButton, gbc);
        warriorButton.addActionListener(e -> {
            setUserName();
            DungeonAdventure.setMyHeroChoice("w");
            DungeonAdventure.setUPGame();
        });

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 2;
        JButton priestessButton = new JButton("Priestess");
        this.add(priestessButton, gbc);
        priestessButton.addActionListener(e -> {
            setUserName();
            DungeonAdventure.setMyHeroChoice("p");
            DungeonAdventure.setUPGame();
        });

        // button placement end -----------------------------------------------


        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 1;
        gbc.gridy = 3;

        JButton mainMenu = new JButton("MAIN MENU");
        mainMenu.setFont(new Font("Serif", Font.BOLD, 20));
        mainMenu.setPreferredSize(new Dimension(200,50));
        mainMenu.addActionListener(e ->  DungeonAdventure.sceneController("menu"));
        this.add(mainMenu, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton monsters = new JButton("MONSTERS");
        monsters.setFont(new Font("Serif", Font.BOLD, 20));
        monsters.setPreferredSize(new Dimension(200,50));
        //monsters.addActionListener();
        this.add(monsters,gbc);

    }

    private void setUserName() {
        DungeonAdventure.setMyUserName(JOptionPane.showInputDialog(this, "Provide User Name:"));
    }
}
