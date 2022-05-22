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

            for(int i = 0; i < DungeonAdventure.getMyDungeon().getDungeon().length; i++){
                for(int j = 0; j < DungeonAdventure.getMyDungeon().getDungeon()[0].length; j++){
                    if(DungeonAdventure.getMyDungeon().getRoom(i,j) == null){
                        g2d.setColor(Color.BLACK);
                    }else{
                        if(DungeonAdventure.getMyDungeon().getRoom(i,j).getMyType() == RoomType.ENTRANCE){
                            g2d.setColor(Color.BLUE);
                        }else if(DungeonAdventure.getMyDungeon().getRoom(i,j).getMyType() == RoomType.EMPTY){
                            g2d.setColor(Color.GRAY);
                        }else if(DungeonAdventure.getMyDungeon().getRoom(i,j).getMyType() == RoomType.ITEM_ROOM){
                            g2d.setColor(Color.GREEN);
                        }else if(DungeonAdventure.getMyDungeon().getRoom(i,j).getMyType() == RoomType.EXIT){
                            g2d.setColor(Color.YELLOW);
                        }else if(DungeonAdventure.getMyDungeon().getRoom(i,j).getMyType() == RoomType.BOSS_ROOM){
                            g2d.setColor(Color.RED);
                        }else if(DungeonAdventure.getMyDungeon().getRoom(i,j).getMyType() == RoomType.PIT){
                            g2d.setColor(Color.MAGENTA);
                        }
                    }

                    g2d.drawRect(i * mapRoomWidth, j * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                }

            }
        }

    }
}
