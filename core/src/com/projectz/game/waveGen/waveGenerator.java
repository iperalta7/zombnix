package com.projectz.game.waveGen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

//import java.util.TimerTask;

public class waveGenerator{
    private int curr_roundNum;
    private int numZombie_PerRound;
    private int numZombie_remaining;
    //private ArrayList<Zombie> zombies;
    private boolean waveInProgress;
    private long round_EndTime;
    private long break_EndTime;

    private BitmapFont font;
    private SpriteBatch batch;

    //constructor
    public waveGenerator(){
        curr_roundNum = 1;
        numZombie_PerRound = 5;
        //zombies = new ArrayList<>();
        waveInProgress = false;
        round_EndTime = 0;
        break_EndTime = 0;
        //roundTimer = new Timer();

        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    public void update(){
        if(waveInProgress){
            if(numZombie_remaining == 0){
                //end round and begin 20 sec
                waveInProgress = false;
                round_EndTime = 0;
                break_EndTime = System.currentTimeMillis() + 20000;
                Gdx.app.log("ZombieWaveGeneratorTest","Round " + curr_roundNum + " over. Round " + (curr_roundNum + 1) + " beginning soon...");
            }
        }
        else{
            //conditional to see if 20 second break has ended
            if(System.currentTimeMillis() >= break_EndTime){
                //begin the next round
                curr_roundNum++;
                numZombie_PerRound += 3; //increases zombie count by 3
                numZombie_remaining = numZombie_PerRound;
                waveInProgress = true;
                round_EndTime = System.currentTimeMillis() + (numZombie_PerRound * 1000); //to ensure the round is timeless
                Gdx.app.log("ZombieWaveGeneratorTest", "Round " + curr_roundNum);
            }
        }
    }

    //private method to spawn zombie
    private void spawnZombie() {
        // Code to spawn a zombie goes here
    }

    public void render(){
        batch.begin();
        font.draw(batch,"Round " + curr_roundNum, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        batch.end();
    }





}
