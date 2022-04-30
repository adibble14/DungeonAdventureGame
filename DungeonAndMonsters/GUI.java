import javax.swing.*;

public abstract class GUI extends JFrame {
    GUI(){
        this.setTitle("Dungeons And Monsters");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setResizable(true);
        setVisible(true);
    }

}
