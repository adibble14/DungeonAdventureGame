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
            // Graphics object used to paint objects
            Graphics2D g2d = (Graphics2D) g;

            // Setting size of map rooms based on how large our dungeon is
            int mapRoomWidth = getSize().width / 10; // How many room columns we have
            int mapRoomHeight = getSize().height / 10;

            for(int i = 0; i < DungeonAdventure.getMyDungeon().getDungeon().length; i++){
                for(int j = 0; j < DungeonAdventure.getMyDungeon().getDungeon()[0].length; j++){
                    Room currentRoom = DungeonAdventure.getMyDungeon().getRoom(j,i);
                    if( currentRoom== null){
                        g2d.setColor(Color.BLACK);
                    }
                    else if((currentRoom == DungeonAdventure.getMyDungeon().getCurrentRoom())){
                        g2d.setColor(Color.WHITE);
                    }
                    else{
                        switch (currentRoom.getMyType()){
                            case ENTRANCE -> g2d.setColor(Color.BLUE);
                            case EMPTY -> {
                                // When we have discovered the room we turn its color
                                if(currentRoom.getMyDiscovery())
                                    g2d.setColor(Color.LIGHT_GRAY);
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            case ITEM_ROOM ->{
                                if(currentRoom.getMyDiscovery())
                                    g2d.setColor(Color.GREEN);
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            case EXIT ->{
                                if(currentRoom.getMyDiscovery())
                                    g2d.setColor(Color.YELLOW);
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            case BOSS_ROOM ->{
                                if(currentRoom.getMyDiscovery())
                                    g2d.setColor(Color.RED);
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            case PIT -> {
                                if(currentRoom.getMyDiscovery())
                                    g2d.setColor(Color.MAGENTA);
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            default -> g2d.setColor(Color.black);
                        }
                    }


                    g2d.fillRect(i * mapRoomWidth, j * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                    g2d.setColor(Color.black);
                    g2d.drawRect(i * mapRoomWidth, j * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                }

            }
        }

    }
}
