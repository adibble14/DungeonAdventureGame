import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    private static Clip musicClip;

    protected static void playMusic(final String theFile) {
        String path = "";
        switch(theFile) {
            case "mainMenu": path = "DungeonAndMonsters/soundfx/BGM12dungeon1.wav";
            break;
            case "dungeon": path = "DungeonAndMonsters/soundfx/BGM13dungeon2.wav";
            break;
            case "battle": path = "DungeonAndMonsters/soundfx/battle.wav";
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
            case "buttonClicked": path = "DungeonAndMonsters/soundfx/button_click.wav";
            break;
            case "changeRoom": path = "DungeonAndMonsters/soundfx/change_room.wav";
            break;
            case "itemPickup": path = "DungeonAndMonsters/soundfx/item_pickup.wav";
            break;
            case "openMap": path = "DungeonAndMonsters/soundfx/open_map.wav";
            break;
            case"visionPotion": path = "DungeonAndMonsters/soundfx/use_potion.wav";
            break;
            case "attack": path = "DungeonAndMonsters/soundfx/attack.wav";
            break;
            case "monsterSpawn": path = "DungeonAndMonsters/soundfx/painb.wav";
            break;
            case "backpack": path = "DungeonAndMonsters/backpack.wav";
            break;
            case "healthPotion": path = "DungeonAndMonsters/health_potion.wav";
            break;
            case "pitTrap": path = "DungeonAndMonsters/pit_trap.wav";
            break;
            case "runFromBattle": path = "DungeonAndMonsters/run_from_battle.wav";
            break;
            case "save": path = "DungeonAndMonsters/save.wav";
            break;
            case "switchDungeon": path = "DungeonAndMonsters/switch_dungeon.wav";
            break;
            case "usePillar": path = "DungeonAndMonsters/use_pillar.wav";
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
