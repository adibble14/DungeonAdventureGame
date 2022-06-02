import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MonsterGUI extends JPanel {
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 2);
    private static final int IMG_WIDTH = 192;
    private static final int IMG_HEIGHT = 192;
    MonsterGUI(Font pixelFont){
        GridBagConstraints gbc = new GridBagConstraints();
        //this.setBackground(new Color(32,42,68));
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
        //Archer
        gbc.gridx = 0;
        gbc.gridy = 1;
        String beastFormat = monsterInfo("Beast").toString().replace("\n", "<br>");
        String finalBeastFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + beastFormat + "</h1></font></html>";
        JLabel beastDesc = new JLabel(finalBeastFormat, SwingConstants.CENTER);
        beastDesc.setFont(pixelFont);
        // Color of text
        beastDesc.setForeground(Color.white);

        // To color a black background with border
        beastDesc.setOpaque(true);
        beastDesc.setBackground(Color.BLACK);
        beastDesc.setBorder(OUTLINE_BORDER);
        this.add(beastDesc, gbc);

        //Mage
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

        //Thief
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

        //Warrior
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

        //Priestess
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
        String finalStatFormat = "<html><font size='5'>" + statFormat + "</font></htmt>";

        stats.addActionListener(e ->  {
            Music.playSFX("buttonClicked");
            JOptionPane.showMessageDialog(this, finalStatFormat);
        });
        this.add(stats, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton monsters = new JButton("Character Select");
        monsters.setFont(pixelFont);
        monsters.setPreferredSize(new Dimension(200,50));
        monsters.addActionListener(e ->  {
            Music.playSFX("buttonClicked");
            DungeonAdventure.sceneController("character");
        });
        this.add(monsters,gbc);


    }

    public static StringBuilder monsterInfo(String theCharacterType) {

        StringBuilder monsterInformation = new StringBuilder();
        switch(theCharacterType){
            case "Beast":
                monsterInformation.append("Beast\n\nStats: \n500 hp\n1 attack speed\n30-50 damage\n30% accuracy\n20% heal chance\n");
                monsterInformation.append("Special: Feral Swipe\nHalves hero's health value");
                break;
            case "Gremlin":
                monsterInformation.append("Gremlin\n\nStats: \n70 hp\n5 attack speed\n15-30 damage\n80% accuracy\n40% heal chance\n");
                monsterInformation.append("Special: Gremlin Frenzy\nGenerates a random number of gremlins");
                break;
            case "Ogre":
                monsterInformation.append("Ogre\n\nStats: \n200 hp\n2 attack speed\n30-60 damage\n60% accuracy\n10% heal chance\n");
                monsterInformation.append("Special: Devastating Blow\n60-100 damage");
                break;
            case "Skeleton":
                monsterInformation.append("Skeleton\n\nStats: \n100 hp\n3 attack speed\n30-50 damage\n80% accuracy\n30% heal chance\n");
                monsterInformation.append("Special: Rise!\nWhen the Skeleton is about to die, it revives to max health instead");
                break;
            case "Mimic":
                monsterInformation.append("Mimic\n\nStats: \n140 hp\n5 attack speed\n5-15 damage\n50% accuracy\n7% heal chance\n");
                monsterInformation.append("Special: Robin Hood\nSteals some gold from the hero");
                break;
            default:
                monsterInformation.append("No info stored!");
                break;
        }

        return monsterInformation;
    }

    public static StringBuilder statInfo(){
        StringBuilder statInfo = new StringBuilder();
        statInfo.append("Stat Definitions\n");
        statInfo.append("Hit Points (hp): the number of health the monster has\n");
        statInfo.append("Attack Speed: how fast the monster is. \nEx) 4 attack speed fights monsters with 2 attack speed, hero gets two attacks per 1 monster attack \n");
        statInfo.append("Damage: the number of damage dealt to the enemy per attack\n");
        statInfo.append("Accuracy: the chance an attack lands a hit on enemy\n");
        statInfo.append("Heal Chance: the chance the monster can heal after an attack phase\n");
        
        return statInfo;
    }
}
