import javax.swing.*;
import java.awt.*;

public abstract class GUI extends JFrame {
    protected static final int MY_WINDOW_WIDTH = 900;
    protected static final int MY_WINDOW_HEIGHT = 650;
    GUI(){
        this.setTitle("Dungeons And Monsters");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(MY_WINDOW_WIDTH, MY_WINDOW_HEIGHT));
        this.setResizable(true);
        setVisible(true);

        //TODO add action listeners to these items and do the appropriate things
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        JMenu help = new JMenu("HELP");
        JMenu save = new JMenu("SAVE");
        bar.add(help);
        bar.add(save);

        JMenuItem instr = new JMenuItem("Instructions");
        JMenuItem about = new JMenuItem("About");
        JMenuItem quit = new JMenuItem("Quit");
        help.add(instr); help.add(about); help.add(quit);

        JMenuItem saveGame = new JMenuItem("Save Game");
        save.add(saveGame);

    }

}
