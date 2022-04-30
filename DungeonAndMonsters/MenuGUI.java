import javax.swing.*;
import java.awt.*;

public class MenuGUI extends GUI{
    MenuGUI(){
        Image img = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/danksouls.jpg");
        this.setContentPane(new JPanel(){
        @Override
        public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        });
        pack();
        setVisible(true);
        this.setSize(900, 650);
    }
}
