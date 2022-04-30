import javax.swing.*;

public abstract class GUI extends JFrame {
    GUI(){
        this.setTitle("Dungeons And Monsters");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 650);
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
