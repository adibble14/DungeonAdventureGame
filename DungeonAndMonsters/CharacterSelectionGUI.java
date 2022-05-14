import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


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
        JLabel archerLabel = new JLabel(Archer.getImage());
        this.add(archerLabel, gbc);

        //Mage
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel mageLabel = new JLabel(Mage.getImage());
        this.add(mageLabel, gbc);

        //Thief
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel thiefLabel = new JLabel(Thief.getImage());
        this.add(thiefLabel, gbc);

        //Warrior
        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel warriorLabel = new JLabel(Warrior.getImage());
        this.add(warriorLabel, gbc);

        //Priestess
        gbc.gridx = 4;
        gbc.gridy = 0;
        JLabel priestessLabel = new JLabel(Priestess.getImage());
        this.add(priestessLabel, gbc);
        // image placement end ----------------------------------------------------------------

        // Character description texts start ----------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3,5,3,5);
        //Archer
        gbc.gridx = 0;
        gbc.gridy = 1;
        String archerFormat = heroInfo("Archer").toString().replace("\n", "<br>");
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
        String mageFormat = heroInfo("Mage").toString().replace("\n", "<br>");
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
        String thiefFormat = heroInfo("Thief").toString().replace("\n", "<br>");
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
        String warriorFormat = heroInfo("Warrior").toString().replace("\n", "<br>");
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
        String priestessFormat = heroInfo("Priestess").toString().replace("\n", "<br>");
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
            DungeonAdventure.sceneController("dungeon");
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
            DungeonAdventure.sceneController("dungeon");
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
            DungeonAdventure.sceneController("dungeon");
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
            DungeonAdventure.sceneController("dungeon");
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
            DungeonAdventure.sceneController("dungeon");
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
        StringBuilder statText = statInfo();
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

    /**
     * Displays information about the Hero characters on the console screen.
     */
    public static StringBuilder heroInfo(String characterType) {

        // When displaying a lot of information, using StringBuffer
        // Nice choice, however, we will need to change this when implementing GUI
        // TODO When implementing GUI, consider different menus for information
        StringBuilder heroInformation = new StringBuilder();
        switch(characterType){
            case "Warrior":
                heroInformation.append("Stats: \n125 hp\n3 attack speed\n30-50 damage\n80% accuracy\n60% block\n");
                heroInformation.append("Special: Crushing Blow\n75-175 damage\n40% accuracy");
                break;
            case "Mage":
                heroInformation.append("Stats: \n75 hp\n4 attack speed\n50-80 damage\n70% accuracy\n30% block\n");
                heroInformation.append("Special: Life Steal\nHalves enemies health and heals the damage taken\n100% accuracy");
                break;
            case "Thief":
                heroInformation.append("Stats: \n95 hp\n6 attack speed\n10-20 damage\n90% accuracy\n40% block\n");
                heroInformation.append("Special: Surprise!\n20-60 damage\nCan also land extra attack\n40-80 damage\n60% accuracy.");
                break;
            case "Archer":
                heroInformation.append("Stats: \n100 hp\n4 attack speed\n25-30 damage\n70% accuracy\n50% block\n");
                heroInformation.append("Special: Volley\nGenerates random number of attack turns\nmax number of attacks: 5\n30-50 damage");
                break;
            case "Priestess":
                heroInformation.append("Stats: \n75 hp\n5 attack speed\n25-45 damage\n70% accuracy\n30% block\n");
                heroInformation.append("Special: Revive\n45-90 damage\n50% accuracy for every point of damage dealt priestess heals 2/3 of the points");
                break;
            default:
                heroInformation.append("No info stored!");
                break;
        }

        return heroInformation;
    }

    public static StringBuilder statInfo(){
        StringBuilder statInfo = new StringBuilder();
        statInfo.append("Stat Definitions\n");
        statInfo.append("Hit Points (hp): the number of health your hero has\n");
        statInfo.append("Attack Speed: how fast your hero is. \nEx) 4 attack speed fights monsters with 2 attack speed, hero gets two attacks per 1 monster attack \n");
        statInfo.append("Damage: the number of damage dealt to the enemy per attack\n");
        statInfo.append("Accuracy: the chance an attack lands a hit on enemy\n");
        statInfo.append("Block Rate: the chance your hero can defend an attack from an enemy\n");


        return statInfo;
    }
}
