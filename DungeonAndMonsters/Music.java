import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    private static Clip musicClip;

    protected static void playMusic(final String theFile) {
        String path = "";
        switch(theFile) {
            case "mainMenu": path = "DungeonAndMonsters/BGM12dungeon1.wav";
            break;
            case "dungeon": path = "DungeonAndMonsters/BGM13dungeon2.wav";
            break;
            case "battle": path = "DungeonAndMonsters/battle.wav";
            break;
            default: path = "";
        }
        try {
            File songPath = new File (path);
            if(songPath.exists()) {
                AudioInputStream input = AudioSystem.getAudioInputStream(songPath);
                if(musicClip != null) {
                    musicClip.stop();
                    musicClip.close();
                }
                musicClip = AudioSystem.getClip();
                musicClip.open(input);
                musicClip.start();
                musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                System.out.println("File not found");
            }
        } catch(Exception e) {
            System.out.println("Error in playing a song file.");
            e.printStackTrace();
        }
    }
    protected static void playSFX(String theAction) {
        String path = "";
        switch(theAction) {
            case "buttonClicked": path = "DungeonAndMonsters/button_click.wav";
            break;
            case "changeRoom": path = "DungeonAndMonsters/change_room.wav";
            break;
            case "itemPickup": path = "DungeonAndMonsters/item_pickup.wav";
            break;
            case "openMap": path = "DungeonAndMonsters/open_map.wav";
            break;
            case"visionPotion": path = "DungeonAndMonsters/use_potion.wav";
            break;
            case "attack": path = "DungeonAndMonsters/attack.wav";
            break;
            default: path = "";
        }
        try {
            File songPath = new File (path);
            if(songPath.exists()) {
                AudioInputStream input = AudioSystem.getAudioInputStream(songPath);
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                clip.start();
            }
            else {
                System.out.println("File not found");
            }
        } catch(Exception e) {
            System.out.println("Error in playing sound effect.");
            e.printStackTrace();
        }
    }
}
