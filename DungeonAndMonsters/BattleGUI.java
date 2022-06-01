import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BattleGUI extends JPanel {
    // Border
    private static final Border OUTLINE_BORDER = BorderFactory.createLineBorder(Color.WHITE, 5);
    // Stats for player and monster
    private static JLabel myStatsLabel;
    // Hero face image
    private static JLabel myHeroFaceImage;
    // Hero in game image
    private static JLabel myHeroInGameImage;
    // Monster face image
    private static JLabel myMonsterFaceImage;
    // Monster in game image
    private static JLabel myMonsterInGameImage;
    // Battle variable we set later
    private static Battle myBattle;
    // Button representing healing in battle
    private static JButton myHealthPotion;
    // Console to print what's going on
    private static JTextArea myBattleConsole;

    // Images for myMonsterFaceImage
    private static final Image myBossMonsterImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/BossMonsterIcon.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);
    private static final Image myMonsterImg = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/MonsterIcon.png").getScaledInstance(96,96, Image.SCALE_SMOOTH);
    private static final ImageIcon myMonsterIcon = new ImageIcon();
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

        // Children of display panel setup ------------------------------------

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 50,5,50);
        myHeroFaceImage = new JLabel(new ImageIcon(), JLabel.CENTER);
        myHeroFaceImage.setOpaque(true);
        myHeroFaceImage.setBackground(Color.BLACK);
        myHeroFaceImage.setBorder(OUTLINE_BORDER);
        displayPanel.add(myHeroFaceImage, gbc);


        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        myStatsLabel = new JLabel("STATS", SwingConstants.CENTER);
        myStatsLabel.setFont(thePixelFont);
        myStatsLabel.setForeground(Color.WHITE);
        displayPanel.add(myStatsLabel, gbc);

        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        myMonsterFaceImage = new JLabel(new ImageIcon(), JLabel.CENTER);
        myMonsterFaceImage.setBorder(OUTLINE_BORDER);
        myMonsterFaceImage.setOpaque(true);
        myMonsterFaceImage.setBackground(Color.BLACK);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0.5;
        displayPanel.add(myMonsterFaceImage, gbc);



        myMonsterInGameImage = new JLabel(new ImageIcon(), JLabel.CENTER);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;

        displayPanel.add(myMonsterInGameImage, gbc);


        myHeroInGameImage = new JLabel(new ImageIcon(), JLabel.CENTER);
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(100,0,0,0);
        displayPanel.add(myHeroInGameImage, gbc);
        // children of display panel end -----------------------------------

        // battle console set up
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        myBattleConsole = new JTextArea();
        myBattleConsole.setBackground(Color.BLACK);
        myBattleConsole.setFont(thePixelFont);
        myBattleConsole.setForeground(Color.white);
        myBattleConsole.setLineWrap(true);
        myBattleConsole.setWrapStyleWord(true);
        myBattleConsole.setEditable(false);
        myBattleConsole.setPreferredSize(new Dimension(900,50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        consolePanel.add(myBattleConsole);

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
            if(myBattle.getMyHero().getHealth() > 0){
                Music.playSFX("attack");
                setBattleConsole(new StringBuilder());
                getMyBattle().attackPhase(false);
                updateBattle();
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton special = new JButton("Special");
        special.setFont(thePixelFont);
        buttonArea.add(special, gbc);
        special.addActionListener(e->{
            if(myBattle.getMyHero().getHealth() > 0){
                setBattleConsole(new StringBuilder());
                getMyBattle().attackPhase(true);
                updateBattle();
            }
        });

        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton attackInfo = new JButton("Info");
        attackInfo.setFont(thePixelFont);
        buttonArea.add(attackInfo, gbc);
        attackInfo.addActionListener(e->{
            JOptionPane.showMessageDialog(this, getAttackInfo());
        });


        gbc.gridx = 4;
        gbc.gridy = 1;
        myHealthPotion = new JButton("Heal");
        myHealthPotion.setFont(thePixelFont);
        buttonArea.add(myHealthPotion, gbc);
        myHealthPotion.addActionListener(e->{
            PlayerInventory inv = getMyBattle().getMyHero().getMyInventory();
            if(inv.getItemCount(ItemType.HEALTH_POTION) > 0){
                int heal = myBattle.getMyHero().useHealthPotion();
                setBattleConsole(new StringBuilder("Health Potion revived " + heal + " points of health. "));
                updateBattle();
                BackpackGUI.removeItemFromBackPack("HEALTH_POTION");
            }
        });

        gbc.gridx = 5;
        gbc.gridy = 1;
        JButton backpack = new JButton("Backpack");
        backpack.setFont(thePixelFont);
        buttonArea.add(backpack, gbc);
        backpack.addActionListener(e->{
            DungeonAdventure.sceneController("backpack");
        });

        gbc.gridx = 6;
        gbc.gridy = 1;
        JButton surrender = new JButton("Run");
        surrender.setFont(thePixelFont);
        buttonArea.add(surrender,gbc);
        surrender.addActionListener(e-> {
            //TODO lose all gold when surrendering????
            int result = JOptionPane.showConfirmDialog(this, "Do you wish to run away? (Lose Gold and Health)");
            if (result == 0){
                Music.playSFX("runFromBattle");
                DungeonAdventure.getMyHero().setGoldAmount(-Tools.RANDOM.nextInt(10, 50));
                DungeonAdventure.getMyHero().setHealth(DungeonAdventure.getMyHero().getHealth() - Tools.RANDOM.nextInt(5, 25));
                DungeonAdventure.refreshBackPackGoldValue();
                DungeonAdventure.refreshDungeonHealthValue();
                DungeonGUI.resetDungeonImage();
                DungeonGUI.enableButtons();
                DungeonGUI.disableButtons(Dungeon.availableRooms(DungeonGUI.getDungeon()));
                DungeonAdventure.sceneController("dungeon");
                if(DungeonAdventure.getMyHero().getHealth() <= 0){  //checking if the player died from running
                    DungeonAdventure.gameOver();
                }
            }
        });

    }

    public static void setBattle(Battle theBattle){
        setMyBattle(theBattle);
        Hero hero = theBattle.getMyHero();
        Monster monster = theBattle.getMyMonster();

        myHeroFaceImage.setIcon(hero.getMySprite());
        myHeroInGameImage.setIcon(hero.getMyInGameSprite());
        if(DungeonAdventure.getMyDungeon().getCurrentRoom().getMyType() == RoomType.BOSS_ROOM)
            myMonsterIcon.setImage(myBossMonsterImg);
        else
            myMonsterIcon.setImage(myMonsterImg);

        myMonsterFaceImage.setIcon(myMonsterIcon);
        myMonsterInGameImage.setIcon(monster.getMyInGameSprite());

        setBattleConsole(new StringBuilder());

        PlayerInventory inv = getMyBattle().getMyHero().getMyInventory();
        if(inv.getItemCount(ItemType.HEALTH_POTION) > 0){
            enableHealthButton();
        }else{
            disableHealthButton();
        }

        updateBattle();
    }

    public static void updateBattle(){
        Hero hero = myBattle.getMyHero();
        Monster monster = myBattle.getMyMonster();


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

    static void setBattleConsole(StringBuilder theMessage){
        myBattleConsole.setText(theMessage.toString());
    }
    
    static StringBuilder getBattleConsole(){return new StringBuilder(myBattleConsole.getText());}

    private static String getAttackInfo(){
        return "BATTLE FORMAT:\nWhoever has the fastest attack speed goes first (tie goes to Hero).\nOnce a character attacks, its opponent attacks afterwards.\nYou can choose to use a special attack and " +
                "any items in your inventory at any time.\nThe monsters also have a special attack that is activated randomly.\nThe battle is to the death!\n" +
                "May the odds be ever in your favor!\n" + specialInfo();
    }

    private static String specialInfo(){
        Hero hero = getMyBattle().getMyHero();
        Monster monster = getMyBattle().getMyMonster();

        return "Special Info:\n" + hero.getName() +": "+hero.getSpecialInfo() +"\n" +monster.getName() +": "+ monster.getSpecialInfo();
    }


    static void enableHealthButton(){
        myHealthPotion.setEnabled(true);}
    static void disableHealthButton(){
        myHealthPotion.setEnabled(false);}


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
