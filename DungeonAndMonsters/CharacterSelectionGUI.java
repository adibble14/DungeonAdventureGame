/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


/**
 * GUI class that creates the character selection screen
 */
public class CharacterSelectionGUI extends JPanel{

    /**
     * border we use in the frame
     */
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 2);

    /**
     * the constructor for the panel
     * @param pixelFont a font it uses
     */
    CharacterSelectionGUI(final Font pixelFont){
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());

        // settings for our initial images
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 0.5;

        // Image placement ----------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel archerLabel = new JLabel(Archer.getImage());
        archerLabel.setBorder(OUTLINE_BORDER);
        this.add(archerLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel mageLabel = new JLabel(Mage.getImage());
        mageLabel.setBorder(OUTLINE_BORDER);
        this.add(mageLabel, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel thiefLabel = new JLabel(Thief.getImage());
        thiefLabel.setBorder(OUTLINE_BORDER);
        this.add(thiefLabel, gbc);


        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel warriorLabel = new JLabel(Warrior.getImage());
        warriorLabel.setBorder(OUTLINE_BORDER);
        this.add(warriorLabel, gbc);


        gbc.gridx = 4;
        gbc.gridy = 0;
        JLabel priestessLabel = new JLabel(Priestess.getImage());
        priestessLabel.setBorder(OUTLINE_BORDER);
        this.add(priestessLabel, gbc);
        // image placement end ----------------------------------------------------------------

        // Character description texts start ----------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 1;
        String archerFormat = heroInfo("Archer").toString().replace("\n", "<br>");
        String finalArcherFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + archerFormat + "</h1></font></html>";
        JLabel archerDesc = new JLabel(finalArcherFormat, SwingConstants.CENTER);
        archerDesc.setFont(pixelFont);
        archerDesc.setForeground(Color.white);

        archerDesc.setOpaque(true);
        archerDesc.setBackground(Color.BLACK);
        archerDesc.setBorder(OUTLINE_BORDER);
        this.add(archerDesc, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        String mageFormat = heroInfo("Mage").toString().replace("\n", "<br>");
        String finalMageFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + mageFormat + "</h1></font></html>";
        JLabel mageDesc = new JLabel(finalMageFormat, SwingConstants.CENTER);
        mageDesc.setFont(pixelFont);
        mageDesc.setForeground(Color.white);

        mageDesc.setOpaque(true);
        mageDesc.setBackground(Color.BLACK);
        mageDesc.setBorder(OUTLINE_BORDER);
        this.add(mageDesc, gbc);


        gbc.gridx = 2;
        gbc.gridy = 1;
        String thiefFormat = heroInfo("Thief").toString().replace("\n", "<br>");
        String finalThiefFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + thiefFormat + "</h1></font></html>";
        JLabel thiefDesc = new JLabel(finalThiefFormat, SwingConstants.CENTER);
        thiefDesc.setFont(pixelFont);
        thiefDesc.setForeground(Color.white);

        thiefDesc.setOpaque(true);
        thiefDesc.setBackground(Color.BLACK);
        thiefDesc.setBorder(OUTLINE_BORDER);
        this.add(thiefDesc, gbc);


        gbc.gridx = 3;
        gbc.gridy = 1;
        String warriorFormat = heroInfo("Warrior").toString().replace("\n", "<br>");
        String finalWarriorFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + warriorFormat + "</h1></font></html>";
        JLabel warriorDesc = new JLabel(finalWarriorFormat, SwingConstants.CENTER);
        warriorDesc.setFont(pixelFont);
        warriorDesc.setForeground(Color.white);

        warriorDesc.setOpaque(true);
        warriorDesc.setBackground(Color.BLACK);
        warriorDesc.setBorder(OUTLINE_BORDER);
        this.add(warriorDesc, gbc);


        gbc.gridx = 4;
        gbc.gridy = 1;
        String priestessFormat = heroInfo("Priestess").toString().replace("\n", "<br>");
        String finalPriestessFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + priestessFormat + "</h1></font></html>";
        JLabel priestessDesc = new JLabel(finalPriestessFormat, SwingConstants.CENTER);
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton archerButton = new JButton("Archer");
        archerButton.setFont(pixelFont);
        this.add(archerButton, gbc);
        archerButton.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            String name = setUserName();
            if(name != null && !name.equals("")&& name.length() < 12){
                DungeonAdventure.setMyUserName(name);
                DungeonAdventure.setMyHeroChoice("a");
                DungeonAdventure.sceneController("dungeon");
                DungeonAdventure.setUPGame();
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton mageButton = new JButton("Mage");
        mageButton.setFont(pixelFont);
        this.add(mageButton, gbc);
        mageButton.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            String name = setUserName();
            if(name != null && !name.equals("")&& name.length() < 12){
                DungeonAdventure.setMyUserName(name);
                DungeonAdventure.setMyHeroChoice("m");
                DungeonAdventure.sceneController("dungeon");
                DungeonAdventure.setUPGame();
            }
        });


        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton thiefButton = new JButton("Thief");
        thiefButton.setFont(pixelFont);
        this.add(thiefButton, gbc);
        thiefButton.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            String name = setUserName();
            if(name != null && !name.equals("")&& name.length() < 12){
                DungeonAdventure.setMyUserName(name);
                DungeonAdventure.setMyHeroChoice("t");
                DungeonAdventure.sceneController("dungeon");
                DungeonAdventure.setUPGame();
            }
        });


        gbc.gridx = 3;
        gbc.gridy = 2;
        JButton warriorButton = new JButton("Warrior");
        warriorButton.setFont(pixelFont);
        this.add(warriorButton, gbc);
        warriorButton.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            String name = setUserName();
            if(name != null && !name.equals("") && name.length() < 12){
                DungeonAdventure.setMyUserName(name);
                DungeonAdventure.setMyHeroChoice("w");
                DungeonAdventure.sceneController("dungeon");
                DungeonAdventure.setUPGame();
            }
        });


        gbc.gridx = 4;
        gbc.gridy = 2;
        JButton priestessButton = new JButton("Priestess");
        priestessButton.setFont(pixelFont);
        this.add(priestessButton, gbc);
        priestessButton.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            String name = setUserName();
            if(name != null && !name.equals("")&& name.length() < 12){
                DungeonAdventure.setMyUserName(name);
                DungeonAdventure.setMyHeroChoice("p");
                DungeonAdventure.sceneController("dungeon");
                DungeonAdventure.setUPGame();
            }

        });
        // button placement end -----------------------------------------------

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 1;
        gbc.gridy = 3;

        JButton mainMenu = new JButton("Main Menu");
        mainMenu.setFont(pixelFont);
        mainMenu.setPreferredSize(new Dimension(200,50));
        mainMenu.addActionListener(e -> {
            Music.playSFX("buttonClicked");
            DungeonAdventure.sceneController("menu");
        });
        this.add(mainMenu, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;

        JButton stats = new JButton("Stat Info");
        stats.setFont(pixelFont);
        stats.setPreferredSize(new Dimension(200,50));
        StringBuilder statText = statInfo();
        String statFormat = statText.toString().replace("\n", "<br>");
        String finalStatFormat = "<html><font size='5'>" + statFormat + "</font></html>";

        stats.addActionListener(e ->  {
            Music.playSFX("buttonClicked");
            JOptionPane.showMessageDialog(this, finalStatFormat);
        });
        this.add(stats, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton monsters = new JButton("Monsters");
        monsters.setFont(pixelFont);
        monsters.setPreferredSize(new Dimension(200,50));
        monsters.addActionListener(e ->  {
            Music.playSFX("buttonClicked");
            DungeonAdventure.sceneController("monster");
        });
        this.add(monsters,gbc);


    }

    /**
     * asks the user for their username
     * @return the string of their username
     */
    private String setUserName() {
        return JOptionPane.showInputDialog(this, "Provide User Name (12 Characters Or Less):");
    }

    /**
     * Displays information about the Hero characters on the console screen.
     */
    private static StringBuilder heroInfo(final String theCharacterType) {

        StringBuilder heroInformation = new StringBuilder();
        switch (theCharacterType) {
            case "Warrior" -> {
                heroInformation.append("Stats: \n").append(SQLiteDB.getCharacterHealth("Warrior", "heroes")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Warrior", "heroes")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Warrior", "heroes")).append("-").append(SQLiteDB.getCharacterMaxDamage("Warrior", "heroes")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Warrior", "heroes") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterBlockChance("Warrior") * 100)).append("% block\n");
                heroInformation.append("Special: Crushing Blow\n60-140 damage\n40% accuracy");
            }
            case "Mage" -> {
                heroInformation.append("Stats: \n").append(SQLiteDB.getCharacterHealth("Mage", "heroes")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Mage", "heroes")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Mage", "heroes")).append("-").append(SQLiteDB.getCharacterMaxDamage("Mage", "heroes")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Mage", "heroes") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterBlockChance("Mage") * 100)).append("% block\n");
                heroInformation.append("Special: Life Steal\nHalves enemies health and heals the damage taken\n50% accuracy\nCan't heal more than double of max health");
            }
            case "Thief" -> {
                heroInformation.append("Stats: \n").append(SQLiteDB.getCharacterHealth("Thief", "heroes")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Thief", "heroes")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Thief", "heroes")).append("-").append(SQLiteDB.getCharacterMaxDamage("Thief", "heroes")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Thief", "heroes") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterBlockChance("Thief") * 100)).append("% block\n");
                heroInformation.append("Special: Surprise!\n20-60 damage\nCan also land extra attack\n40-80 damage\n60% accuracy.");
            }
            case "Archer" -> {
                heroInformation.append("Stats: \n").append(SQLiteDB.getCharacterHealth("Archer", "heroes")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Archer", "heroes")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Archer", "heroes")).append("-").append(SQLiteDB.getCharacterMaxDamage("Archer", "heroes")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Archer", "heroes") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterBlockChance("Archer") * 100)).append("% block\n");
                heroInformation.append("Special: Volley\nGenerates random number of\n attack turns\nmax number of attacks: 5\n30-40 damage each attack");
            }
            case "Priestess" -> {
                heroInformation.append("Stats: \n").append(SQLiteDB.getCharacterHealth("Priestess", "heroes")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Priestess", "heroes")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Priestess", "heroes")).append("-").append(SQLiteDB.getCharacterMaxDamage("Priestess", "heroes")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Priestess", "heroes") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterBlockChance("Priestess") * 100)).append("% block\n");
                heroInformation.append("Special: Revive\n35-80 damage\n60% accuracy\n For every point of damage dealt priestess heals 2/3 of the points");
            }
            default -> heroInformation.append("No info stored!");
        }

        return heroInformation;
    }

    /**
     * @return info on what each stat means
     */
    private static StringBuilder statInfo(){
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
