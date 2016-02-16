package ie.gmit.week11;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sounds {
	BLOP("Audio/Blop.wav"),
	KERCHING("Audio/kerching.wav"),
	KLAXON("Audio/klaxon.wav"),
	ENGINE("Audio/engine.wav");
	private Clip clip;
	Sounds(String soundEffect){
		try{
			File soundFile = new File(soundEffect);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();

		}
	}

	public void play(){
		if (clip.isRunning())
			clip.stop();  
		clip.setFramePosition(0);
		clip.start();

	}
	
	public void stop(){
		if (clip.isRunning())
			clip.stop();
		clip.setFramePosition(0);
		
	}



	static void init() {
		values(); 
	}

	public void loop() {
		  clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
}

