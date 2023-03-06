package com.projectz.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet {

    private Vector2 position;
    private Vector2 velocity;
    private float speed;
    private Texture bulletTexture;

    public Bullet(Vector2 position, Vector2 direction, float speed, Texture bulletTexture) {
        this.position = position;
        this.velocity = direction.scl(speed);
        this.speed = speed;
        this.bulletTexture = bulletTexture;
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
    }

    public void draw(Batch batch) {
        batch.draw(bulletTexture, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    // other methods
}

public class Weapon {

    private Array<Bullet> bullets;
    private Texture bulletTexture;
    private float bulletSpeed;

    public Weapon(Texture bulletTexture, float bulletSpeed) {
        this.bulletTexture = bulletTexture;
        this.bulletSpeed = bulletSpeed;
        this.bullets = new Array<Bullet>();
    }

    public void fire(Vector2 position, Vector2 direction) {
        Bullet bullet = new Bullet(position, direction, bulletSpeed, bulletTexture);
        bullets.add(bullet);
    }

    public void update(float deltaTime) {
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
        bullets.removeAll(b -> b.getPosition().x < 0 || b.getPosition().x > Gdx.graphics.getWidth()
                || b.getPosition().y < 0 || b.getPosition().y > Gdx.graphics.getHeight(), true);
    }

    public void draw(Batch batch) {
        for (Bullet bullet : bullets) {
            bullet.draw(batch);
        }
    }

    // other methods
}
