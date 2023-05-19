import javax.sound.sampled.*;


/**
 * This file is for handling the sounds for the game.
 * @author Matthew harris
 */
public class SoundPlayer {
    private Clip clip;
    private AudioInputStream audioInputStream;

    /**
     * Plays sounds immediately when it is initialised.
     * @param filePath Path of sound file in the directory
     */
    public SoundPlayer(String filePath){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getResourceAsStream(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream); 
            clip.start();         
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
