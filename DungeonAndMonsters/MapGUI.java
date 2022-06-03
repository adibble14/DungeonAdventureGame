/**
 * Dungeons and Monsters Game
 * TCSS 360 final project Spring 2022
 * @authors Andrew Dibble, Mario Vences Flores, Alex Humphries
 * @versions 1.0
 */

import javax.swing.*;
import java.awt.*;

/**
 * this class implements the map GUI
 */
public class MapGUI extends JFrame {

    /**
     * constructor for map GUI
     */
    MapGUI(){
        this.setTitle("Map");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(false);
        this.add(new DrawWindow());
    }


    /**
     * inner class to draw a window in the panel
     */
    protected static class DrawWindow extends JPanel{

        Image currentRoomImage;
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
                    currentRoomImage = null;
                    if( currentRoom== null){
                        g2d.setColor(Color.BLACK);
                    }
                    else if((currentRoom == DungeonAdventure.getMyDungeon().getCurrentRoom())){
                        g2d.setColor(Color.WHITE);
                        currentRoomImage = DungeonAdventure.getMyHero().getMySprite().getImage();
                    }
                    else if(currentRoom.containsMonster() && currentRoom.getMyDiscovery()){
                        g2d.setColor(Color.CYAN);
                        currentRoomImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/MonsterIcon.png");
                    }
                    else{
                        switch (currentRoom.getMyType()){
                            case ENTRANCE ->g2d.setColor(Color.BLUE);
                            case EMPTY -> {
                                // When we have discovered the room we turn its color
                                if(currentRoom.getMyDiscovery())
                                    g2d.setColor(Color.LIGHT_GRAY);
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            case ITEM_ROOM ->{
                                if(currentRoom.getMyDiscovery()){
                                    g2d.setColor(Color.GREEN);
                                    currentRoomImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/gold.png");
                                }
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
                                if(currentRoom.getMyDiscovery()){
                                    g2d.setColor(Color.RED);
                                    currentRoomImage = Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/BossMonsterIcon.png");
                                }
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            case PIT -> {
                                if(currentRoom.getMyDiscovery()) {
                                    g2d.setColor(Color.MAGENTA);
                                    currentRoomImage =Toolkit.getDefaultToolkit().getImage("DungeonAndMonsters/random images/SpikeIcon.png");
                                }
                                else
                                    g2d.setColor(Color.DARK_GRAY);
                            }
                            default -> g2d.setColor(Color.black);
                        }
                    }


                    g2d.fillRect(i * mapRoomWidth, j * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                    g2d.setColor(Color.black);
                    g2d.drawRect(i * mapRoomWidth, j * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                    g2d.drawImage(currentRoomImage,i * mapRoomWidth, j * mapRoomHeight,mapRoomWidth, mapRoomHeight, this);
                }

            }
        }

    }
}
