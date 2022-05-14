import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BattleGUI extends JPanel {
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);
    private static JLabel myStatsLabel;
    private static JLabel heroImage;
    private static JLabel monsterImage;

    private static Battle myBattle;
    BattleGUI(Font thePixelFont) {
        Battle battle = new Battle(DungeonAdventure.getMyHero());

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

        JPanel displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setOpaque(true);
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(OUTLINE_BORDER);
        this.add(displayPanel, gbc);

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

        heroImage = new JLabel();
        heroImage.setBorder(OUTLINE_BORDER);
        character1.add(heroImage, gbc);


        JPanel stats = new JPanel(new GridBagLayout());
        stats.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        displayPanel.add(stats, gbc);
        myStatsLabel = new JLabel("STATS");
        myStatsLabel.setFont(thePixelFont);
        myStatsLabel.setForeground(Color.WHITE);
        stats.add(myStatsLabel, gbc);

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
                setBattle(myBattle);
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton special = new JButton("Special Attack");
        special.setFont(thePixelFont);
        buttonArea.add(special, gbc);
        special.addActionListener(e->{
            if(myBattle.myHero.getHealth() > 0){
                getMyBattle().myHero.special(getMyBattle().myMonster);
                setBattle(myBattle);
            }
        });


        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton healthPotion = new JButton("Use Health Potion");
        healthPotion.setFont(thePixelFont);
        buttonArea.add(healthPotion, gbc);
        healthPotion.addActionListener(e->{
            myBattle.myHero.useHealthPotion();
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

    public static void setBattle(Battle battle){
        setMyBattle(battle);
        Hero hero = battle.myHero;
        Monster monster = battle.myMonster;

        heroImage.setIcon(hero.getMySprite());
        monsterImage.setIcon(monster.getMySprite());

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


}
