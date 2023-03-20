package com.projectz.game.waveGen;
import java.util.Timer;

//import java.util.TimerTask;

public class waveGenerator{
    private int roundNum;
    private int numZombie;
    private boolean waveInProgress;
    private Timer roundTimer;

    public waveGenerator(){
        roundNum = 1;
        numZombie = 5;
        waveInProgress = false;
        roundTimer = new Timer();
    }

    //private method to spawn zombie
    private void spawnZombie() {
        // Code to spawn a zombie goes here
    }

    //method that begins wave
    public void startWave(){
        if (!waveInProgress) {
            waveInProgress = true;
            roundNum++;
            System.out.println("Starting round " + roundNum);
            for (int i = 0; i < numZombie; i++) {
                spawnZombie();
            }
            numZombie += 3; // Increase number of zombies for next round
        }
    }


}
