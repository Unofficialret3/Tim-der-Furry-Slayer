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

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }

    }


    public void playLaser(){
        try {
            loadSound("sounds/pew.wav");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        play();
    }

    public void playEnemyDeath(){
        try {
            loadSound("sounds/animedeath.wav");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        play();
    }

    public void playThrow(){
        try {
            loadSound("sounds/throw.wav");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        play();
    }

}
