/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myswingintegrated;

import java.io.*;
import javax.sound.sampled.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipTest  {
    private AudioInputStream audioIn;

  void playSound(String soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    File f = new File(soundFile);
    audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
    Thread.sleep(4000);
}

   public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
      SoundClipTest ss  = new SoundClipTest();
      ss.playSound("C:\\Users\\sijitend\\FF\\welcome.wav");
   }
}