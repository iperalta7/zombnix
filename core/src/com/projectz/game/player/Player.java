/**
 * The Player class represents the player object in the game.
 * It handles movement, animation, weapon, health, and experience values.
 */
package com.projectz.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.items.ItemHealPotion;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.player.Animator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.weapons.WeaponGun;
import com.projectz.game.player.Bullet;

public class Player extends Actor {

    private Vector2 position;
    private TextureRegion playerSprite;

    private Animator playerAnimator;
    private final float speed;
    private WeaponGun weapon;
    private OrthographicCamera camera;
    private Inventory inventory;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    public boolean holdingGun;
    private int health;
    private int expValue;
    private int expLevel;
    public int points;
    private float mapWidth = Float.MAX_VALUE;
    private float mapHeight = Float.MAX_VALUE;
    // Camera centre in world coordinates, set by GameScreen each frame (null = follows player)
    private final Vector2 cameraPosition = new Vector2();
    private boolean hasCameraPosition = false;
    // The sprite is drawn 60x60 from (position - 10), so it spans [-10, +50] around position
    private static final float SPRITE_MARGIN_LEFT = 10f;
    private static final float SPRITE_MARGIN_RIGHT = 50f;

    /**
     * Constructor for the Player class.
     * Initializes the player's position, sprite, speed, weapon, and camera.
     * Also initializes health, experience, and points.
     */
    public Player() {
        playerAnimator = new Animator();
        playerAnimator.setState("RUNNING");
        position = new Vector2();
        playerSprite = playerAnimator.getFrame(0.0f);
        speed = 100f;
        weapon = new WeaponGun(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        health = 100;
        expLevel = 1;
        expValue = 0;
        points = 100;
    }

    /**
     * Returns the position of the player.
     *
     * @return Vector2 representing the player's position.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the position of the player.
     *
     * @param x float representing the x-coordinate.
     * @param y float representing the y-coordinate.
     */
    public void setPlayerPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    /**
     * Sets the world size the player is confined to (the map size in world pixels).
     */
    public void setMapBounds(float mapWidth, float mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        clampToMap();
    }

    private void clampToMap() {
        float minX = SPRITE_MARGIN_LEFT, maxX = mapWidth - SPRITE_MARGIN_RIGHT;
        float minY = SPRITE_MARGIN_LEFT, maxY = mapHeight - SPRITE_MARGIN_RIGHT;
        position.x = maxX < minX ? mapWidth / 2f : Math.max(minX, Math.min(position.x, maxX));
        position.y = maxY < minY ? mapHeight / 2f : Math.max(minY, Math.min(position.y, maxY));
    }

    /**
     * Sets the world position of the (clamped) map camera. Called by GameScreen each frame.
     */
    public void setCameraPosition(float x, float y) {
        cameraPosition.set(x, y);
        hasCameraPosition = true;
    }

    /**
     * Returns the world position of the map camera (the player position until the camera is set).
     * Anything drawn in world space on the stage should be offset by this, not by the player position.
     */
    public Vector2 getCameraPosition() {
        return hasCameraPosition ? cameraPosition : position;
    }

    /**
     * Returns the stage position where the sprite is drawn (its bottom-left corner plus the
     * 10px sprite origin offset, i.e. the former "stage camera centre"): stage centre plus
     * (player - camera). Equals the stage centre when the camera is not clamped.
     */
    public Vector2 getScreenPosition() {
        Vector2 cam = getCameraPosition();
        return new Vector2(getStage().getCamera().position.x + position.x - cam.x,
                           getStage().getCamera().position.y + position.y - cam.y);
    }

    /**
     * Updates the player's position based on user input.
     * Also updates the player's animation and camera position.
     *
     * @param deltaTime float representing the time between frames.
     */
    @Override
    public void act(float deltaTime) {
        Vector2 prevPos = new Vector2();
        prevPos.x = position.x;
        prevPos.y = position.y;
        handleMovement(deltaTime);
        weapon.update(deltaTime);
        updateAnimation(prevPos, deltaTime);
        updateCamera();
    }

    /**
     * Handle the movement for the player
     * @param deltaTime float representing the the time between frames
     */
    public void handleMovement(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltaTime;
            playerAnimator.setFacingRight(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltaTime;
            playerAnimator.setFacingRight(true);
        }
        clampToMap();
    }

    /**
     * Updates the Player Animation based on the prevPos.
     * @param prevPos Vector2 representing the position of the player.
     * @param deltaTime float representing the time between frames.
     */
    public void updateAnimation(Vector2 prevPos, float deltaTime){
        if (prevPos.x == position.x && prevPos.y == position.y) {
            playerAnimator.setState("STANDING");
        } else {
            playerAnimator.setState("RUNNING");
        }
        playerSprite = playerAnimator.getFrame(deltaTime);
    }

    /**
     * Updates the camera position to follow the player.
     */
    public void updateCamera() {
        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();
    }

    /**
     * Draws the player sprite and weapon on the screen.
     *
     * @param batch       the batch used to draw the sprite.
     * @param parentAlpha the alpha value of the parent actor.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        float factor = 3.4f;
        float scale = camera.viewportWidth / Gdx.graphics.getWidth() * factor;
        float width = 20.0f * scale;
        float height = 20.0f * scale;

        Vector2 screenPos = getScreenPosition();
        batch.draw(playerSprite, (screenPos.x - 20 / 2), (screenPos.y - 20 / 2), 60, 60);
        weapon.draw(batch, parentAlpha);
    }

    /**
     * Updates the player's health when they take damage.
     *
     * @param damage the amount of damage taken.
     */
    public void takeDamage(int damage) {
        if (this.health <= 0) {
            this.health = 0;
        } else {
            this.health -= damage;
        }
    }

    /**
     * Disposes of any resources used by the player object.
     */
    public void dispose() {
        weapon.dispose();
    }

    /**
     * Returns the player's current health value.
     *
     * @return the player's current health value.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the player's current experience value.
     *
     * @return the player's current experience value.
     */
    public int getExpValue() {
        return expValue;
    }

    /**
     * Returns the player's current experience level.
     *
     * @return the player's current experience level.
     */
    public int getExpLevel() {
        return expLevel;
    }

    /**
     * Returns the player's current points value.
     *
     * @return the player's current points value.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns the player's current weapon.
     *
     * @return the player's current weapon.
     */
    public WeaponGun getWeapon() {
        return weapon;
    }
}