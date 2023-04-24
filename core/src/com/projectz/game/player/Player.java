package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.projectz.game.weapons.WeaponGun;

/**
 * The Player class represents the main character in the game. It contains
 * position, speed, texture, and other attributes related to the player,
 * as well as methods to handle input, draw the player on the screen, and
 * manage player's inventory and other stats.
 */
public class Player extends Actor {
    private Vector2 position;
    public static final float PLAYER_SPEED = 35f;
    private Texture playerTexture;
    private TextureRegion playerSprite;
    private Animator playerAnimator;
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
    public Player() {
        setUpPlayer();
    }

    /**
     * Sets up the player by initializing position, texture, camera, and inventory,
     * as well as the initial health and experience values.
     */
    private void setUpPlayer() {
        setUpAnimation();
        playerTexture = new Texture("player.png");
        weapon = new WeaponGun(this);
        setupCamera();
        setupInventory();
        // Initialize health and xp
        health = 100;
        expLevel = 1;
        expValue = 0;
    }

    /**
     * Sets up the animation for the player.
     */
    private void setUpAnimation() {
        playerAnimator = new Animator();
        playerAnimator.setState("RUNNING");
        position = new Vector2();
        playerSprite = playerAnimator.getFrame(0.0f);
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
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the player's position to the specified x and y coordinates.
     *
     * @param x The x-coordinate for the player's new position.
     * @param y The y-coordinate for the player's new position.
     */
    public void setPlayerPosition(float x, float y) {
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
        // update the camera position to follow the player
        camera.position.x = 0;//position.x;
        camera.position.y = 0;//position.y;
        camera.update();
    }

    /**
     * Changes the position of the player object based on input.
     * Called by the act method.
     *
     * @param deltaTime The time passed since the last frame.
     */
    private void handleInput(float deltaTime) {
        setUpAnimation(deltaTime);
        handleMovementInput(deltaTime);
        handleInventoryInput();
    }

    /**
     * Changes the position with the animation of the player object based on input.
     * Called by the handleInput method.
     *
     * @param deltaTime The time passed since the last frame.
     */
    private void handleMovementInput(float deltaTime){
        super.act(deltaTime);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += PLAYER_SPEED * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= PLAYER_SPEED * deltaTime;
            playerAnimator.setFacingRight(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= PLAYER_SPEED * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += PLAYER_SPEED * deltaTime;
            playerAnimator.setFacingRight(true);
        }
        weapon.update(deltaTime);
    }

    /**
     * Sets up animation
     *
     * @param deltaTime The time passed since the last frame.
     */
    private void setUpAnimation(float deltaTime){
        Vector2 prevPos = new Vector2();
        prevPos.x = position.x;
        prevPos.y = position.y;
        //Gets the current frame of our Animation
        if (prevPos.x == position.x && prevPos.y == position.y)
            playerAnimator.setState("STANDING");
        else
            playerAnimator.setState("RUNNING");
        playerSprite = playerAnimator.getFrame(deltaTime);
    }

    /**
     * Hanldes inventory input
     */
    private void handleInventoryInput(){
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
        // Draw the bullets
        weapon.draw(batch, parentAlpha);
    }

    /**
     * Draws the player texture at the player's current position.
     *
     * @param batch The Batch used to draw the player texture.
     */
    private void drawPlayer (Batch batch){
        float factor = 3.4F;
        float scale = camera.viewportWidth / Gdx.graphics.getWidth() * factor;
        float width = 20.0f * scale;
        float height = 20.0f * scale;

        // Draw the player sprite at the current position
        batch.draw(playerSprite, (getStage().getWidth() - 20 * 2) / 2, (getStage().getHeight() - 20 * 2) / 2, width, height);
    }

    /**
     * @return The player's current weapon.
     */
    public WeaponGun getWeapon () {
        return weapon;
    }


    /**
     * Disposes resources used by the player, like textures.
     */
    public void dispose () {
        weapon.dispose();
        //playerSprite.dispose();
    }

    /**
     * @param damage the integeer that will be taken off current player health.
     */
    public void takeDamage(int damage){
        if(this.health <= 0) {
            this.health = 0;
        }
        else{
            this.health -= damage;
        }
    }

    /**
     * @return The player's current health.
     */
    public int getHealth () {
        return health;
    }
    /**
     * @return The player's current experience value.
     */
    public int getExpValue () {
        return expValue;
    }
    /**
     * @return The player's current experience level.
     */
    public int getExpLevel () {
        return expLevel;
    }
    /**
     * Sets the player's experience value to the specified value.
     *
     * @param expValue The new experience value for the player.
     */
    public void setExpValue ( int expValue){
        this.expValue = expValue;
    }
}

