import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharacterSelectionGUI extends JPanel{

    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 2);
    CharacterSelectionGUI(Font pixelFont){
        // Settings for our internal panel
        // bg color and how it is laid out.
        // as well as the grid bag constraints
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(new Color(32,42,68));
        this.setLayout(new GridBagLayout());

        // settings for our initial images
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 5, 0, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        // Image placement ----------------------------------------
        // Archer
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel archerLabel = new JLabel(new ImageIcon("DungeonAndMonsters/archer.jpeg"));
        this.add(archerLabel, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel mageLabel = new JLabel(new ImageIcon("DungeonAndMonsters/mage.jpeg"));
        this.add(mageLabel, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel thiefLabel = new JLabel(new ImageIcon("DungeonAndMonsters/thief.jpeg"));
        this.add(thiefLabel, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel warriorLabel = new JLabel(new ImageIcon("DungeonAndMonsters/warrior.jpeg"));
        this.add(warriorLabel, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 0;
        JLabel priestessLabel = new JLabel(new ImageIcon("DungeonAndMonsters/priestess.jpeg"));
        this.add(priestessLabel, gbc);
        // image placement end ----------------------------------------------------------------

        // Character description texts start ----------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,5,0,5);
        //Archer
        gbc.gridx = 0;
        gbc.gridy = 1;
        String archerFormat = DungeonAdventure.heroInfo("Archer").toString().replace("\n", "<br>");
        String finalArcherFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + archerFormat + "</h1></font></html>";
        JLabel archerDesc = new JLabel(finalArcherFormat);
        archerDesc.setFont(pixelFont);
        // Color of text
        archerDesc.setForeground(Color.white);

        // To color a black background with border
        archerDesc.setOpaque(true);
        archerDesc.setBackground(Color.BLACK);
        archerDesc.setBorder(OUTLINE_BORDER);
        this.add(archerDesc, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 1;
        String mageFormat = DungeonAdventure.heroInfo("Mage").toString().replace("\n", "<br>");
        String finalMageFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + mageFormat + "</h1></font></html>";
        JLabel mageDesc = new JLabel(finalMageFormat);
        mageDesc.setFont(pixelFont);
        mageDesc.setForeground(Color.white);

        mageDesc.setOpaque(true);
        mageDesc.setBackground(Color.BLACK);
        mageDesc.setBorder(OUTLINE_BORDER);
        this.add(mageDesc, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 1;
        String thiefFormat = DungeonAdventure.heroInfo("Thief").toString().replace("\n", "<br>");
        String finalThiefFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + thiefFormat + "</h1></font></html>";
        JLabel thiefDesc = new JLabel(finalThiefFormat);
        thiefDesc.setFont(pixelFont);
        thiefDesc.setForeground(Color.white);

        thiefDesc.setOpaque(true);
        thiefDesc.setBackground(Color.BLACK);
        thiefDesc.setBorder(OUTLINE_BORDER);
        this.add(thiefDesc, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 1;
        String warriorFormat = DungeonAdventure.heroInfo("Warrior").toString().replace("\n", "<br>");
        String finalWarriorFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + warriorFormat + "</h1></font></html>";
        JLabel warriorDesc = new JLabel(finalWarriorFormat);
        warriorDesc.setFont(pixelFont);
        warriorDesc.setForeground(Color.white);

        warriorDesc.setOpaque(true);
        warriorDesc.setBackground(Color.BLACK);
        warriorDesc.setBorder(OUTLINE_BORDER);
        this.add(warriorDesc, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 1;
        String priestessFormat = DungeonAdventure.heroInfo("Priestess").toString().replace("\n", "<br>");
        String finalPriestessFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + priestessFormat + "</h1></font></html>";
        JLabel priestessDesc = new JLabel(finalPriestessFormat);
        priestessDesc.setFont(pixelFont);
        priestessDesc.setForeground(Color.white);

        priestessDesc.setOpaque(true);
        priestessDesc.setBackground(Color.BLACK);
        priestessDesc.setBorder(OUTLINE_BORDER);
        this.add(priestessDesc, gbc);
        // Character description texts end ------------------------------------------


        // button placement start -------------------------------------------------------------

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        //Archer
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton archerButton = new JButton("Archer");
        archerButton.setFont(pixelFont);
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
        mageButton.setFont(pixelFont);
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
        thiefButton.setFont(pixelFont);
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
        warriorButton.setFont(pixelFont);
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
        priestessButton.setFont(pixelFont);
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
        mainMenu.setFont(pixelFont);
        mainMenu.setPreferredSize(new Dimension(200,50));
        mainMenu.addActionListener(e ->  DungeonAdventure.sceneController("menu"));
        this.add(mainMenu, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;

        JButton stats = new JButton("Stat Info");
        stats.setFont(pixelFont);
        stats.setPreferredSize(new Dimension(200,50));
        StringBuilder statText = DungeonAdventure.statInfo();
        String statFormat = statText.toString().replace("\n", "<br>");
        String finalStatFormat = "<html><font size='5'>" + statFormat + "</font></htmt>";
        stats.addActionListener(e ->  JOptionPane.showMessageDialog(this, finalStatFormat));
        this.add(stats, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton monsters = new JButton("MONSTERS");
        monsters.setFont(pixelFont);
        monsters.setPreferredSize(new Dimension(200,50));
        //monsters.addActionListener();
        this.add(monsters,gbc);

    }

    private void setUserName() {
        DungeonAdventure.setMyUserName(JOptionPane.showInputDialog(this, "Provide User Name:"));
    }
}
