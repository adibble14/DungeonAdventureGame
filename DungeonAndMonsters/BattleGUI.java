import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BattleGUI extends JPanel {
    // Border
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);
    // Stats for player and monster
    private static JLabel myStatsLabel;
    // Hero face image
    private static JLabel heroFaceImage;
    // Hero in game image
    private static JLabel heroInGameImage;
    // Monster image
    private static JLabel monsterImage;
    // Battle variable we set later
    private static Battle myBattle;

    private static JTextArea myPlayerConsole;
    BattleGUI(Font thePixelFont) {

        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        // Constraints for display area
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridx = 0;

        drawWindow displayPanel = new drawWindow();
        displayPanel.setOpaque(true);
        displayPanel.setBorder(OUTLINE_BORDER);
        this.add(displayPanel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridheight = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.weightx = 1;


        JPanel consolePanel = new JPanel(new GridBagLayout());
        consolePanel.setOpaque(true);
        consolePanel.setBackground(Color.BLACK);
        consolePanel.setBorder(OUTLINE_BORDER);
        this.add(consolePanel, gbc);

        // Constraints for Button Area
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridheight = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.weightx = 1;


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(OUTLINE_BORDER);
        this.add(buttonPanel, gbc);

        // Children panel setup ------------------------------------

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0.009;
        gbc.weighty = 0.5;
        JPanel character1 = new JPanel(new GridBagLayout());
        character1.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        displayPanel.add(character1, gbc);

        heroFaceImage = new JLabel();
        heroFaceImage.setOpaque(true);
        heroFaceImage.setBackground(Color.BLACK);
        heroFaceImage.setBorder(OUTLINE_BORDER);
        character1.add(heroFaceImage, gbc);

        heroInGameImage = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(150,20,0,0);
        character1.add(heroInGameImage, gbc);

        gbc.insets = new Insets(0,0,0,0);
        JPanel stats = new JPanel(new GridBagLayout());
        stats.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        displayPanel.add(stats, gbc);
        myStatsLabel = new JLabel("STATS");
        myStatsLabel.setFont(thePixelFont);
        myStatsLabel.setForeground(Color.WHITE);
        stats.add(myStatsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        monsterImage = new JLabel();
        monsterImage.setBorder(OUTLINE_BORDER);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.009;
        gbc.weighty = 0.5;
        JPanel character2 = new JPanel(new GridBagLayout());
        character2.setOpaque(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        displayPanel.add(character2, gbc);

        character2.add(monsterImage, gbc);


        myPlayerConsole = new JTextArea();
        myPlayerConsole.setBackground(Color.BLACK);
        myPlayerConsole.setFont(thePixelFont);
        myPlayerConsole.setForeground(Color.white);
        myPlayerConsole.setLineWrap(true);
        myPlayerConsole.setWrapStyleWord(true);
        myPlayerConsole.setEditable(false);
        myPlayerConsole.setPreferredSize(new Dimension(900,50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        consolePanel.add(myPlayerConsole);

        //-----buttons panel
        JPanel buttonArea = new JPanel(new GridBagLayout());
        buttonArea.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(buttonArea, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,5,10,5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton attack = new JButton("Attack");
        attack.setFont(thePixelFont);
        buttonArea.add(attack, gbc);
        attack.addActionListener(e->{
            if(myBattle.myHero.getHealth() > 0){
                getMyBattle().attackPhase(false);
                updateBattle();
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton special = new JButton("Special Attack");
        special.setFont(thePixelFont);
        buttonArea.add(special, gbc);
        special.addActionListener(e->{
            if(myBattle.myHero.getHealth() > 0){
                getMyBattle().attackPhase(true);
                updateBattle();
            }
        });


        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton healthPotion = new JButton("Use Health Potion");
        healthPotion.setFont(thePixelFont);
        buttonArea.add(healthPotion, gbc);
        healthPotion.addActionListener(e->{
            myBattle.myHero.useHealthPotion();  //TODO this returns an int, set the console message with this health value
            updateBattle();
        });

        gbc.gridx = 4;
        gbc.gridy = 1;
        JButton surrender = new JButton("Surrender");
        surrender.setFont(thePixelFont);
        buttonArea.add(surrender, gbc);
        //TODO
        surrender.addActionListener(e->{

        });

    }

    public static void setBattle(Battle theBattle){
        setMyBattle(theBattle);
        Hero hero = theBattle.myHero;
        Monster monster = theBattle.myMonster;

        heroFaceImage.setIcon(hero.getMySprite());
        heroInGameImage.setIcon(hero.getMyInGameSprite());
        monsterImage.setIcon(monster.getMySprite());

        updateBattle();
    }

    public static void updateBattle(){
        Hero hero = myBattle.myHero;
        Monster monster = myBattle.myMonster;


        StringBuilder string = new StringBuilder("STATS" + "\n"+ hero.getHealth() + " Health "
                + monster.getHealth() +"\n" + hero.getSpeed() + " Attack Speed " + monster.getSpeed() + "\n"
                +hero.getMaxDamage() + " Max Damage " + monster.getMaxDamage() + "\n" + hero.getMyAccuracy() +
                " Accuracy " + monster.getMyAccuracy() + "\n" + hero.getBlockChance() + " Block Chance n/a" + "\n"+
                "n/a Heal Chance " + monster.getHealChance());

        String statsFormat = string.toString().replace("\n", "<br>");
        String finalStatsFormat = "<html><style>" +
                "h1 {text-align: center;}\n" +
                "</style><h1><<font size='5'>" + statsFormat + "</h1></font></html>";

        myStatsLabel.setText(finalStatsFormat);
    }



    public static void setMyBattle(Battle battle){myBattle = battle;}
    public static Battle getMyBattle(){return myBattle;}

    static void setPlayerConsole(StringBuilder theMessage){
        myPlayerConsole.setText(theMessage.toString());
    }

    /**
     * Inner class drawWindow that works as the display panel showing off the
     * current room's image, as well as the inGame player and inGame enemies, items, etc.
     */
    protected static class drawWindow extends JPanel{
        private Image myWindowImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/dungeon pics/FinalFantasyEsqBattle.png");

        drawWindow(){
            this.setLayout(new GridBagLayout());
            this.setVisible(true);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(myWindowImg,0,0,getWidth(),getHeight(),this);
        }
    }

}
