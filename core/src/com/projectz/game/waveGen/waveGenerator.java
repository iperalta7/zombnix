package com.projectz.game.waveGen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.projectz.game.enemies.Enemy;
import com.projectz.game.player.Player;

/**
 * A class to manage waves of zombies.
 */
public class waveGenerator extends ApplicationAdapter {

    private Stage stage;
    private int currRoundNum;
    private int numZombiePerRound;
    private int numZombieRemaining;
    private boolean waveInProgress;
    private long roundEndTime;
    private long breakEndTime;

    private long lastZombieSpawnTime;
    private BitmapFont font;
    private SpriteBatch batch;

    private Player player;
    private Array<Enemy> spawnedZombies;
    private int zombiesSpawnedThisRound;

    /**
     * Constructor for the WaveGenerator class.
     *
     * @param player The player instance.
     * @param stage  The stage to add zombies to.
     */
    public waveGenerator(Player player, Stage stage) {
        this.player = player;
        this.stage = stage;
        initializeVariables();
        font = new BitmapFont();
        batch = new SpriteBatch();
        spawnedZombies = new Array<>();
    }

    /**
     * Initializes variables for the WaveGenerator class.
     */
    private void initializeVariables() {
        currRoundNum = 0;
        numZombiePerRound = 0;
        waveInProgress = false;
        roundEndTime = 0;
        breakEndTime = 0;
        lastZombieSpawnTime = 0;
    }

    /**
     * Update method to be called every frame.
     */
    public void update() {
        if (waveInProgress) {
            handleWaveInProgress();
        } else {
            handleWaveNotInProgress();
        }
        updateRemainingZombies();
    }

    private void handleWaveInProgress() {
        if (numZombieRemaining == 0) {
            endRoundAndBeginBreak();
        } else if (TimeUtils.timeSinceMillis(lastZombieSpawnTime) > 3000 && zombiesSpawnedThisRound < numZombiePerRound) {
            spawnZombie();
            lastZombieSpawnTime = TimeUtils.millis();
            zombiesSpawnedThisRound++;
        }
    }

    /**
     * Ends the current round and begins the break before the next round.
     */
    private void endRoundAndBeginBreak() {
        waveInProgress = false;
        roundEndTime = 0;
        breakEndTime = System.currentTimeMillis() + 15000;
        Gdx.app.log("ZombieWaveGeneratorTest", "Round " + currRoundNum + " over. Next round starting in 20 seconds...");
    }

    /**
     * Handles the logic for when a wave is not in progress.
     */
    private void handleWaveNotInProgress() {
        if (TimeUtils.timeSinceMillis(breakEndTime) >= 0) {
            beginNextRound();
        }
    }

    /**
     * Begins the next round.
     */
    private void beginNextRound() {
        currRoundNum++;
        numZombiePerRound += 3;
        numZombieRemaining = numZombiePerRound;
        waveInProgress = true;
        roundEndTime = TimeUtils.millis() + (numZombiePerRound * 1000);
        Gdx.app.log("ZombieWaveGeneratorTest", "Round " + currRoundNum + " starting...");
        zombiesSpawnedThisRound = 0;
    }

    /**
     * Updates the number of remaining zombies.
     */
    private void updateRemainingZombies() {
        for (Enemy zombie : spawnedZombies) {
            if (!zombie.aliveCheck()) {
                numZombieRemaining--;
                spawnedZombies.removeValue(zombie, true);
            }
        }
    }

    /**
     * Spawns a new zombie in the game.
     */
    private void spawnZombie() {
        Vector2 spawnPosition = calculateSpawnPosition();
        Enemy newZombie = new Enemy(player, spawnPosition, 10);
        spawnedZombies.add(newZombie);
        stage.addActor(newZombie);
    }

    /**
     * Calculates the spawn position for a new zombie.
     *
     * @return Vector2 representing the spawn position.
     */
    private Vector2 calculateSpawnPosition() {
        float distance = 100 + (float) (Math.random() * 200);
        float angle = (float) (Math.random() * 2 * Math.PI);
        float playerX = player.getPosition().x;
        float playerY = player.getPosition().y;
        float spawnX, spawnY;

        // Randomly choose one of four positions around the player
        int pos = (int) (Math.random() * 4);
        switch (pos) {
            case 0:
                spawnX = playerX + distance * (float) Math.cos(angle);
                spawnY = playerY + distance * (float) Math.sin(angle);
                break;
            case 1:
                spawnX = playerX - distance * (float) Math.cos(angle);
                spawnY = playerY + distance * (float) Math.sin(angle);
                break;
            case 2:
                spawnX = playerX + distance * (float) Math.cos(angle);
                spawnY = playerY - distance * (float) Math.sin(angle);
                break;
            default: // pos == 3
                spawnX = playerX - distance * (float) Math.cos(angle);
                spawnY = playerY - distance * (float) Math.sin(angle);
                break;
        }

        return new Vector2(spawnX, spawnY);
    }

    /**
     * Renders the wave information on screen.
     */
    public void render() {
        batch.begin();
        batch.setProjectionMatrix(batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        renderText(batch);
        batch.end();
    }

    /**
     * Renders the wave and remaining zombie information text on screen.
     *
     * @param batch The SpriteBatch used for rendering.
     */
    private void renderText(SpriteBatch batch) {
        String roundText = "Round " + currRoundNum;
        font.draw(batch, roundText, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - font.getLineHeight());

        String remainingZombiesText = "Remaining Zombies: " + numZombieRemaining;
        font.draw(batch, remainingZombiesText, 10, Gdx.graphics.getHeight() - font.getLineHeight());

        if (!waveInProgress) {
            renderCountdown(batch);
        }
    }

    /**
     * Renders the countdown timer before the next round starts on screen.
     *
     * @param batch The SpriteBatch used for rendering.
     */
    private void renderCountdown(SpriteBatch batch) {
        long timeRemaining = (breakEndTime - TimeUtils.millis()) / 1000;
        String countdownText = "Next round in: " + timeRemaining + " seconds";
        font.draw(batch, countdownText, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 2 * font.getLineHeight());
    }

    public boolean isWaveInProgress() {
        return waveInProgress;
    }
}
