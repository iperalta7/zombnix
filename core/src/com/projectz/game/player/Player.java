package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.weapons.WeaponGun;

/**
 * The Player class represents the main character in the game. It contains
 * position, speed, texture, and other attributes related to the player,
 * as well as methods to handle input, draw the player on the screen, and
 * manage player's inventory and other stats.
 */
public class Player extends Actor {
    private Vector2 position;
    public static final float PLAYER_SPEED = 25;
    private Texture playerTexture;
    private WeaponGun weapon;
    static float SCREEN_WIDTH = Gdx.graphics.getWidth();
    static float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private OrthographicCamera camera;
	Inventory inventory;
    private int health;
    private int expValue;
    private int expLevel;



    /**
     * Default constructor that initializes the player's texture, speed,
     * health, and experience values.
     */
    public Player () {
        setUpPlayer();
    }

    /**
     * Sets up the player by initializing position, texture, camera, and inventory,
     * as well as the initial health and experience values.
     */
    private void setUpPlayer() {
        position = new Vector2();
        playerTexture = new Texture("player.png");
        weapon = new WeaponGun(this);
        setupCamera();
        setupInventory();
        health = 100;
        expLevel = 1;
        expValue = 0;
    }

    /**
     * Sets up the camera for the player.
     */
    private void setupCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * Sets up the inventory for the player.
     */
    private void setupInventory() {
        inventory = new Inventory();
        inventory.addItem(Item.HealingPotion, 5);
    }

    /**
     * @return the current position of the player as a Vector2.
     */
    public Vector2 getPosition(){
        return position;
    }

    /**
     * Sets the player's position to the specified x and y coordinates.
     *
     * @param x The x-coordinate for the player's new position.
     * @param y The y-coordinate for the player's new position.
     */
    public void setPlayerPosition(float x, float y){
        position.x = x;
        position.y = y;
    }

    /**
     * Called every frame to update the player's position and actions.
     *
     * @param deltaTime The time passed since the last frame.
     */
    @Override
    public void act(float deltaTime) {
        handleInput(deltaTime);
        weapon.update(deltaTime);

    }

    /**
     * Changes the position of the player object based on input.
     * Called by the act method.
     *
     * @param deltaTime The time passed since the last frame.
     */
    private void handleInput(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) { //move up
            position.y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { //move left
            position.x -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) { //move down
            position.y -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { //move right
            position.x += speed * deltaTime;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            inventory.useConsumable(Item.HealingPotion);
        }
    }

    /**
     * Draws the player on the screen.
     *
     * @param batch       The Batch used to draw the player.
     * @param parentAlpha The parent alpha value, currently unused.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawPlayer(batch);
        weapon.draw(batch, parentAlpha);
    }

    /**
     * Draws the player texture at the player's current position.
     *
     * @param batch The Batch used to draw the player texture.
     */
    private void drawPlayer(Batch batch) {
        float factor = 2.4F;
        float scale = camera.viewportWidth / Gdx.graphics.getWidth() * factor;
        float width = playerTexture.getWidth() * scale;
        float height = playerTexture.getHeight() * scale;

        batch.draw(playerTexture, (getStage().getWidth() - playerTexture.getWidth() * 2) / 2,
                (getStage().getHeight() - playerTexture.getHeight() * 2) / 2, width, height);
    }

    /**
     * @return The player's current weapon.
     */
    public WeaponGun getWeapon() {
        return weapon;
    }


    /**
     * Disposes resources used by the player, like textures.
     */
    public void dispose() {
        weapon.dispose();
        playerTexture.dispose();
    }


    /**
     * @return The player's current health.
     */
    public int getHealth() {
        return health;
    }
    /**
     * @return The player's current experience value.
     */
    public int getExpValue() {
        return expValue;
    }
    /**
     * @return The player's current experience level.
     */
    public int getExpLevel() {
        return expLevel;
    }
    /**
     * Sets the player's experience value to the specified value.
     *
     * @param expValue The new experience value for the player.
     */
    public void setExpValue(int expValue) {
        this.expValue = expValue;
    }
}

