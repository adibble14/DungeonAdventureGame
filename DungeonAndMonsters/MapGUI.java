import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MapGUI extends JFrame {

    MapGUI(Font pixelFont){
        // Change the name of the frame
        this.setTitle("Map");
        // Size this frame to be slightly smaller
        this.setSize(450, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // Not resizable and starts invisible
        this.setResizable(false);
        this.setVisible(false);
        this.add(new DrawWindow());
    }
    

    protected static class DrawWindow extends JPanel{

        DrawWindow(){
            this.setBackground(Color.BLACK);
            this.setVisible(true);
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            // Retrieve the graphics context; this object is used to paint shapes

            Graphics2D g2d = (Graphics2D) g;


            int mapRoomWidth = getSize().width / 10; // How many room columns we have


            int mapRoomHeight = getSize().height / 10;

            System.out.println(mapRoomWidth +" " + mapRoomHeight);
            g2d.setColor(Color.RED);
           //g2d.fillRect(x,y, mapRoomWidth, mapRoomHeight);
            for(int i = 1; i < DungeonAdventure.getMyDungeon().getDungeon().length; i++){
                for(int j = 1; j < DungeonAdventure.getMyDungeon().getDungeon()[0].length; j++){
                    switch(DungeonAdventure.getMyDungeon().getRoom(i, j).toString()){
                        case "N": // Entrance
                            g2d.setColor(Color.BLUE);
                            break;
                        case "E": // Empty
                            g2d.setColor(Color.GRAY);
                            break;
                        case "I": // Item
                            g2d.setColor(Color.GREEN);
                            break;
                        case "O": // Exit
                            g2d.setColor(Color.YELLOW);
                            break;
                        case "B": // Boss Room
                            g2d.setColor(Color.RED);
                            break;
                        case "S": // Pit
                            g2d.setColor(Color.MAGENTA);
                            break;
                    }
                    g2d.drawRect(i * mapRoomWidth, j * mapRoomHeight , mapRoomWidth, mapRoomHeight);
                }

            }
        }

    }
}
