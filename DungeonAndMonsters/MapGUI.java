import javax.swing.*;
import java.awt.*;

public class MapGUI extends JPanel {

    MapGUI(Font pixelFont){

    }

    static void setSaveLoad() {
        GUI.saveGame.setEnabled(false);
        GUI.loadGame.setEnabled(false);
    }
}
