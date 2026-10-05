package com.projectz.game.player;
import com.projectz.game.ProjectZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

//Bullet.java
public class Bullet extends Actor {

    private float damage;
    private Vector2 position;
    private Vector2 direction;
    private float speed;
    private static Texture sharedTexture;
    private boolean active;
    /** Radius (px) around the bullet's centre used for hit detection against enemies. */
    private float hitRadius;
    /** Maximum distance (px) a bullet travels from its spawn point before it is removed. */
    private static final float MAX_TRAVEL_DISTANCE = 600f;
    private final Vector2 spawnPosition;

    /** Lazily loads the single texture shared by all bullets. */
    private static Texture getTexture() {
        if (sharedTexture == null) {
            sharedTexture = new Texture("bullet-blue.png");
        }
        return sharedTexture;
    }

    /** Disposes the shared texture; it is reloaded lazily if a bullet needs it again. */
    public static void disposeSharedTexture() {
        if (sharedTexture != null) {
            sharedTexture.dispose();
            sharedTexture = null;
        }
    }


    public Bullet(float x, float y, Vector2 direction, float speed) {
        position = new Vector2(x, y);
        this.direction = direction;
        this.speed = speed;
        spawnPosition = new Vector2(x, y);
        active = true;
        setBounds(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());
        this.damage = 20;
        this.hitRadius = 4;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void act(float delta) {
        position.add(direction.x * speed * delta, direction.y * speed * delta);
        setBounds(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());

        // deactivate once the bullet has travelled its maximum distance
        if (position.dst2(spawnPosition) > MAX_TRAVEL_DISTANCE * MAX_TRAVEL_DISTANCE) {
            active = false;
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if(this.active) {
            batch.draw(getTexture(), position.x, position.y, 8, 8);
        }
    }

    /** Marks the bullet as inactive so its owner removes it. Does not touch the shared texture. */
    public void deactivate() {
        this.active = false;
    }

    public void dispose() {
        this.active = false;
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public float getDamage(){return this.damage;}

    public float getHitRadius(){
        return this.hitRadius;
    }

}
