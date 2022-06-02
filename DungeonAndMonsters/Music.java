import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * class that controls which music is playing
 */
public class Music {

    /**
     * the music clap
     */
    private static Clip musicClip;

    /**
     * plays music based on which part in the game we are in
     * @param theFile theFile
     */
    protected static void playMusic(final String theFile) {
        String path = switch (theFile) {
            case "mainMenu" -> "DungeonAndMonsters/soundfx/BGM12dungeon1.wav";
            case "dungeon" -> "DungeonAndMonsters/soundfx/BGM13dungeon2.wav";
            case "battle" -> "DungeonAndMonsters/soundfx/battle.wav";
            default -> "";
        };
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

    /**
     * play sounds for different actions
     * @param theAction the action
     */
    protected static void playSFX(String theAction) {
        String path = switch (theAction) {
            case "buttonClicked" -> "DungeonAndMonsters/soundfx/button_click.wav";
            case "changeRoom" -> "DungeonAndMonsters/soundfx/change_room.wav";
            case "itemPickup" -> "DungeonAndMonsters/soundfx/item_pickup.wav";
            case "openMap" -> "DungeonAndMonsters/soundfx/open_map.wav";
            case "visionPotion" -> "DungeonAndMonsters/soundfx/use_potion.wav";
            case "attack" -> "DungeonAndMonsters/soundfx/attack.wav";
            case "monsterSpawn" -> "DungeonAndMonsters/soundfx/painb.wav";
            case "backpack" -> "DungeonAndMonsters/soundfx/backpack.wav";
            case "healthPotion" -> "DungeonAndMonsters/soundfx/health_potion.wav";
            case "pitTrap" -> "DungeonAndMonsters/soundfx/pit_trap.wav";
            case "runFromBattle" -> "DungeonAndMonsters/soundfx/run_from_battle.wav";
            case "save" -> "DungeonAndMonsters/soundfx/save.wav";
            case "switchDungeon" -> "DungeonAndMonsters/soundfx/switch_dungeon.wav";
            case "usePillar" -> "DungeonAndMonsters/soundfx/use_pillar.wav";
            default -> "";
        };
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
