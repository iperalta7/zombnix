package com.projectz.game.waveGen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class waveGenerator extends ApplicationAdapter {
    private int curr_roundNum;
    private int numZombie_PerRound;
    private int numZombie_remaining;
    private boolean waveInProgress;
    private long round_EndTime;
    private long break_EndTime;

    private BitmapFont font;
    private SpriteBatch batch;

    //constructor
    public waveGenerator() {
        curr_roundNum = 0;
        numZombie_PerRound = 5;
        waveInProgress = false;
        round_EndTime = 0;
        break_EndTime = 0;

        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    public void update() {
        if (waveInProgress) {
            if (numZombie_remaining == 0) {
                // end round and begin 20 sec break
                waveInProgress = false;
                round_EndTime = 0;
                break_EndTime = System.currentTimeMillis() + 20000;
                Gdx.app.log("ZombieWaveGeneratorTest", "Round " + curr_roundNum + " over. Next round starting in 20 seconds...");
            }
        } else {
            // conditional to see if 20 second break has ended
            if (System.currentTimeMillis() >= break_EndTime) {
                // begin the next round
                curr_roundNum++;
                numZombie_PerRound += 3; // increases zombie count by 3
                numZombie_remaining = numZombie_PerRound;
                waveInProgress = true;
                round_EndTime = System.currentTimeMillis() + (numZombie_PerRound * 1000); // to ensure the round is timeless
                Gdx.app.log("ZombieWaveGeneratorTest", "Round " + curr_roundNum + " starting...");
            }
        }
    }

    //private method to spawn zombie
    private void spawnZombie() {
        // Code to spawn a zombie goes here
    }

    public void render(OrthographicCamera camera) {

        // Begin the SpriteBatch and set the projection matrix
        batch.begin();
        batch.setProjectionMatrix(batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        // Draw the round number at the top center of the screen
        //String roundText = "Round " + curr_roundNum;
        //font.draw(batch, roundText, (Gdx.graphics.getWidth()) / 2, Gdx.graphics.getHeight() - font.getLineHeight());

        //Gdx.app.log("ZombieWaveGeneratorTest", "Round " + curr_roundNum + " starting...");
        String starting_roundText = "Round " + curr_roundNum + " starting...";
        font.draw(batch, starting_roundText, (Gdx.graphics.getWidth()) / 2, Gdx.graphics.getHeight() - font.getLineHeight());
        // End the SpriteBatch
        batch.end();
    }
}
