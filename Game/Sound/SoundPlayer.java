package Game.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;


    public void loadSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File(soundFile);
        if (!file.exists()) {
            throw new IOException("Datei nicht gefunden: " + soundFile);
        }

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }


    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }


    public void playEnemyDeath(){
        try {
            loadSound("ressources/sounds/animedeath.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        play();
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

}
