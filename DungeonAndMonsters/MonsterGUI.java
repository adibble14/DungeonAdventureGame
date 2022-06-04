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
 * this class creates a GUI similar to character selection but instead with monsters
 * the hero can see who they could fight in the dungeon
 */
public class MonsterGUI extends JPanel {

    /**
     * the border used in the GUI
     */
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 2);

    /**
     * width of the character images
     */
    private static final int IMG_WIDTH = 192;

    /**
     * height of the character images
     */
    private static final int IMG_HEIGHT = 192;

    /**
     * constructor for the monster GUI screen
     * @param pixelFont the font used in the GUI
     */
    MonsterGUI(final Font pixelFont){
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 0.5;

        // Image placement ----------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        Image beastImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/monster pics/Beast.png").getScaledInstance(IMG_WIDTH,IMG_HEIGHT, Image.SCALE_SMOOTH);
        JLabel beastLabel = new JLabel(new ImageIcon(beastImg));
        beastLabel.setBorder(OUTLINE_BORDER);
        this.add(beastLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        Image gremlinImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/monster pics/GremlinInGame.png").getScaledInstance(IMG_WIDTH,IMG_HEIGHT, Image.SCALE_SMOOTH);
        JLabel gremlinLabel = new JLabel(new ImageIcon(gremlinImg));
        gremlinLabel.setBorder(OUTLINE_BORDER);
        this.add(gremlinLabel, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        Image ogreImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/monster pics/Ogre.png").getScaledInstance(IMG_WIDTH,IMG_HEIGHT, Image.SCALE_SMOOTH);
        JLabel ogreLabel = new JLabel(new ImageIcon(ogreImg));
        ogreLabel.setBorder(OUTLINE_BORDER);
        this.add(ogreLabel, gbc);


        gbc.gridx = 3;
        gbc.gridy = 0;
        Image skelImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/monster pics/rpgCritterSkelly.png").getScaledInstance(IMG_WIDTH,IMG_HEIGHT, Image.SCALE_SMOOTH);
        JLabel skelLabel = new JLabel(new ImageIcon(skelImg));
        skelLabel.setBorder(OUTLINE_BORDER);
        this.add(skelLabel, gbc);


        gbc.gridx = 4;
        gbc.gridy = 0;
        Image mimicImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/monster pics/Mimic UW Chest.png").getScaledInstance(IMG_WIDTH,IMG_HEIGHT, Image.SCALE_SMOOTH);
        JLabel mimicLabel = new JLabel(new ImageIcon(mimicImg));
        mimicLabel.setBorder(OUTLINE_BORDER);
        this.add(mimicLabel, gbc);
        // image placement end ----------------------------------------------------------------

        // Character description texts start ----------------------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        String beastFormat = monsterInfo("Beast").toString().replace("\n", "<br>");
        String finalBeastFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + beastFormat + "</h1></font></html>";
        JLabel beastDesc = new JLabel(finalBeastFormat, SwingConstants.CENTER);
        beastDesc.setFont(pixelFont);
        beastDesc.setForeground(Color.white);

        beastDesc.setOpaque(true);
        beastDesc.setBackground(Color.BLACK);
        beastDesc.setBorder(OUTLINE_BORDER);
        this.add(beastDesc, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        String gremlinFormat = monsterInfo("Gremlin").toString().replace("\n", "<br>");
        String finalGremlinFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + gremlinFormat + "</h1></font></html>";
        JLabel gremlinDesc = new JLabel(finalGremlinFormat, SwingConstants.CENTER);
        gremlinDesc.setFont(pixelFont);
        gremlinDesc.setForeground(Color.white);

        gremlinDesc.setOpaque(true);
        gremlinDesc.setBackground(Color.BLACK);
        gremlinDesc.setBorder(OUTLINE_BORDER);
        this.add(gremlinDesc, gbc);


        gbc.gridx = 2;
        gbc.gridy = 1;
        String ogreFormat = monsterInfo("Ogre").toString().replace("\n", "<br>");
        String finalOgreFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + ogreFormat + "</h1></font></html>";
        JLabel ogreDesc = new JLabel(finalOgreFormat, SwingConstants.CENTER);
        ogreDesc.setFont(pixelFont);
        ogreDesc.setForeground(Color.white);

        ogreDesc.setOpaque(true);
        ogreDesc.setBackground(Color.BLACK);
        ogreDesc.setBorder(OUTLINE_BORDER);
        this.add(ogreDesc, gbc);


        gbc.gridx = 3;
        gbc.gridy = 1;
        String skelFormat = monsterInfo("Skeleton").toString().replace("\n", "<br>");
        String finalSkelFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + skelFormat + "</h1></font></html>";
        JLabel skelDesc = new JLabel(finalSkelFormat, SwingConstants.CENTER);
        skelDesc.setFont(pixelFont);
        skelDesc.setForeground(Color.white);

        skelDesc.setOpaque(true);
        skelDesc.setBackground(Color.BLACK);
        skelDesc.setBorder(OUTLINE_BORDER);
        this.add(skelDesc, gbc);


        gbc.gridx = 4;
        gbc.gridy = 1;
        String mimicFormat = monsterInfo("Mimic").toString().replace("\n", "<br>");
        String finalMimicFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + mimicFormat + "</h1></font></html>";
        JLabel mimicDesc = new JLabel(finalMimicFormat, SwingConstants.CENTER);
        mimicDesc.setFont(pixelFont);
        mimicDesc.setForeground(Color.white);

        mimicDesc.setOpaque(true);
        mimicDesc.setBackground(Color.BLACK);
        mimicDesc.setBorder(OUTLINE_BORDER);
        this.add(mimicDesc, gbc);
        // Character description texts end ------------------------------------------


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
        JButton monsters = new JButton("Heroes");
        monsters.setFont(pixelFont);
        monsters.setPreferredSize(new Dimension(200,50));
        monsters.addActionListener(e ->  {
            Music.playSFX("buttonClicked");
            DungeonAdventure.sceneController("character");
        });
        this.add(monsters,gbc);


    }

    /**
     * info about the monsters stats
     * @param theCharacterType which monster
     * @return a string of their stats
     */
    private static StringBuilder monsterInfo(String theCharacterType) {

        StringBuilder monsterInformation = new StringBuilder();
        switch(theCharacterType){
            case "Beast":
                monsterInformation.append("Beast\nStats: \n").append(SQLiteDB.getCharacterHealth("Beast", "monsters")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Beast", "monsters")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Beast", "monsters")).append("-").append(SQLiteDB.getCharacterMaxDamage("Beast", "monsters")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Beast", "monsters") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterHealChance("Beast") * 100)).append("% heal chance\n");
                monsterInformation.append("Special: Feral Swipe\nHalves hero's health value");
                break;
            case "Gremlin":
                monsterInformation.append("Gremlin\nStats: \n").append(SQLiteDB.getCharacterHealth("Gremlin", "monsters")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Gremlin", "monsters")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Gremlin", "monsters")).append("-").append(SQLiteDB.getCharacterMaxDamage("Gremlin", "monsters")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Gremlin", "monsters") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterHealChance("Gremlin") * 100)).append("% heal chance\n");
                monsterInformation.append("Special: Gremlin Frenzy\nGenerates a random number of gremlins");
                break;
            case "Ogre":
                monsterInformation.append("Ogre\nStats: \n").append(SQLiteDB.getCharacterHealth("Ogre", "monsters")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Ogre", "monsters")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Ogre", "monsters")).append("-").append(SQLiteDB.getCharacterMaxDamage("Ogre", "monsters")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Ogre", "monsters") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterHealChance("Ogre") * 100)).append("% heal chance\n");
                monsterInformation.append("Special: Devastating Blow\n60-140 damage");
                break;
            case "Skeleton":
                monsterInformation.append("Skeleton\nStats: \n").append(SQLiteDB.getCharacterHealth("Skeleton", "monsters")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Skeleton", "monsters")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Skeleton", "monsters")).append("-").append(SQLiteDB.getCharacterMaxDamage("Skeleton", "monsters")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Skeleton", "monsters") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterHealChance("Skeleton") * 100)).append("% heal chance\n");
                monsterInformation.append("Special: Rise!\nWhen the Skeleton is about to die, it revives to half of max health instead");
                break;
            case "Mimic":
                monsterInformation.append("Mimic\nStats: \n").append(SQLiteDB.getCharacterHealth("Mimic", "monsters")).append(" hp\n").append(SQLiteDB.getCharacterSpeed("Mimic", "monsters")).append(" attack speed").append("\n").append(SQLiteDB.getCharacterMinDamage("Mimic", "monsters")).append("-").append(SQLiteDB.getCharacterMaxDamage("Mimic", "monsters")).append(" damage\n").append(Math.round(SQLiteDB.getCharacterAccuracy("Mimic", "monsters") * 100)).append("% accuracy\n").append(Math.round(SQLiteDB.getCharacterHealChance("Mimic") * 100)).append("% heal chance\n");
                monsterInformation.append("Special: Robin Hood\nSteals some gold from the hero\n");
                monsterInformation.append("Note: Mimic appears randomly in some chests!");
                break;
            default:
                monsterInformation.append("No info stored!");
                break;
        }

        return monsterInformation;
    }

    /**
     * info on what each stat means
     * @return a string
     */
    private static StringBuilder statInfo(){
        StringBuilder statInfo = new StringBuilder();
        statInfo.append("Stat Definitions\n");
        statInfo.append("Hit Points (hp): the number of health the monster has\n");
        statInfo.append("Attack Speed: how fast the monster is. \nEx) 4 attack speed fights monsters with 2 attack speed, hero gets two attacks per 1 monster attack \n");
        statInfo.append("Damage: the number of damage dealt to the enemy per attack\n");
        statInfo.append("Accuracy: the chance an attack lands a hit on enemy\n");
        statInfo.append("Heal Chance: the chance the monster can heal after an attack phase\n");
        statInfo.append("Note: Boss monsters have boosted stats!");
        
        return statInfo;
    }
}
