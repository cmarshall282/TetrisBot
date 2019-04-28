package com.tetrisbot.utils.audio;


import com.tetrisbot.game.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;
import java.net.URL;

public class Sound {

    Clip clip;

    public Sound(String fileName, Game game) {
        try {
            //InputStream soundURL = Sound.class.getClassLoader().getResourceAsStream(fileName);
            InputStream soundURL = game.getClass().getClassLoader().getResourceAsStream("audio/" + fileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVolume(double level) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(level));
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

}
