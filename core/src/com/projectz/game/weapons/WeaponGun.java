package com.projectz.game.weapons;

// Weapon.java

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.projectz.game.player.Bullet;
import com.projectz.game.player.Player;

import java.util.ArrayList;

//Weapon.java
public class WeaponGun extends Weapon{

    private Player player;
    private ArrayList<Bullet> bullets;
    private float bulletSpeed;
    private float fireRate;
    private float timeSinceLastShot;



    public WeaponGun(Player player) {
        super("Gun", "Weapons/pistol.png");
        this.player = player;
        bullets = new ArrayList<Bullet>();
        bulletSpeed = 500f;
        fireRate = 0.3f;
        timeSinceLastShot = 0f;
    }

    public void update(float delta) {
        timeSinceLastShot += delta;

        // fire bullet when space is pressed and fire rate allows it
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timeSinceLastShot >= fireRate) {
            fireBullet();
            timeSinceLastShot = 0f;
        }

        // update bullets
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.act(delta);

            if (!bullet.isActive()) {
                bullets.remove(i);
                bullet.dispose();
            }
        }
    }

    public void draw(Batch batch, float alpha) {
        for (Bullet bullet : bullets) {
            bullet.draw(batch, alpha);
        }
    }

    private void fireBullet() {
        Vector2 mousePosition = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        Vector2 bulletDirection = mousePosition
                .sub(player.getPosition().x + player.getWidth() / 2, player.getPosition().y + player.getHeight() / 2)
                .nor();

        float x = player.getPosition().x + player.getWidth() / 2;
        float y = player.getPosition().y + player.getHeight() / 2;

        Bullet bullet = new Bullet(x, y, bulletDirection, bulletSpeed);
        bullets.add(bullet);
    }

    public int getNumBullets() {
        return bullets.size();
    }
    public void dispose() {
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
    }

    @Override
    public void onActivate() {

    }
}
