import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    private Clip clip = null;

    public URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sounds/Amogus.wav");
        soundURL[1] = getClass().getResource("/sounds/daddy.wav");
        soundURL[2] = getClass().getResource("/sounds/fear.wav");
        soundURL[3] = getClass().getResource("/sounds/ha.wav");


    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void play() {
        clip.start();

    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
