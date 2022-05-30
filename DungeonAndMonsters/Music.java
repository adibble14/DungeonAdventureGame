import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    protected static void playMusic(final String theFile) {
        try {
            File songPath = new File (theFile);
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
            System.out.println("Error in playing a song file.");
            e.printStackTrace();
        }
    }
    protected static void playSFX(String theAction) {
        String path = "";
        switch(theAction) {
            case "buttonClicked": path = "DungeonAndMonsters/button_click.wav";
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
