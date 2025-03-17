package src;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundSystem {
    private Clip clip;
    private boolean isLooping; // To track looping sounds

    public void play(String source, boolean loop) {
        try {
            URL soundURL = getClass().getResource(source);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                isLooping = true;
            } else {
                isLooping = false;
            }

            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (isPlaying()) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public boolean isLooping() {
        return isLooping;
    }
}
