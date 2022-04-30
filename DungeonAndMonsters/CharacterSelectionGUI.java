import javax.swing.*;
import java.awt.*;

public class CharacterSelectionGUI extends GUI{
    Image myArcherImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/archer.jpeg");
    Image myMageImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/mage.png");
    Image myThiefImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/thief.png");
    Image myWarriorImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/warrior.jpg");
    CharacterSelectionGUI(){


        JLabel label = new JLabel(new ImageIcon("DungeonAndMonsters/archer.jpeg"));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(32,42,68));
        panel.add(label);
        this.add(panel, BorderLayout.WEST);

        JLabel label2 = new JLabel(new ImageIcon("DungeonAndMonsters/mage.jpeg"));
        panel.add(label2);


        JLabel label3 = new JLabel(new ImageIcon("DungeonAndMonsters/thief.jpeg"));
        panel.add(label3);

        JLabel label4 = new JLabel(new ImageIcon("DungeonAndMonsters/warrior.jpeg"));
        panel.add(label4);

        JLabel label5 = new JLabel(new ImageIcon("DungeonAndMonsters/priestess.jpeg"));
        panel.add(label5);


        pack();
        setVisible(true);
        this.setSize(932, 650);

    }
}
