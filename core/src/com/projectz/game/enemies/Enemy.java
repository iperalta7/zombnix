package com.projectz.game.enemies;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.items.ItemHealPotion;
import com.projectz.game.player.Bullet;
import com.projectz.game.player.Player;
import com.projectz.game.weapons.WeaponGun;

import static java.lang.Math.abs;

/**
 * Concrete class of type Enemy for a more specific EnemyBoss.
 */
public class Enemy extends Actor {
    
    Item item_dropped;
    int health;
    protected Texture enemyTexture;
    protected Vector2 position;
    protected Vector2 relativeOrigin;
    protected float speed;
    protected boolean alive;
    protected Player targetedPlayer;
    protected float minDistToChase;
    protected float attackDistance;
    protected Bullet[] bullets;
    protected int attackCount;
    protected Vector2 direction;
    private int attackDamage;
    private long lastAttackTime;


    /**
     * Initializes values of the EnemyBoss with the help of the super constructor
     * @param player The instance of the Player object that the enemy will be targeting
     * @param initial_x An integer specifying initial x-position
     * @param initial_y An integer specifying initial y-position
     */
    public Enemy(int health, Player player, float initial_x, float initial_y, int attackDamage){
        this.item_dropped = new ItemHealPotion();
        this.health = health;
        this.position = new Vector2(initial_x,initial_y);
        this.relativeOrigin = new Vector2(player.getPosition().x, player.getPosition().y);
        this.speed = speed;
        this.alive = true;
        this.targetedPlayer = player;
        this.bullets = null;
        this.attackDistance = 5;
        this.enemyTexture = new Texture(createEnemyBossPixmap());
        this.minDistToChase = 100;
        this.attackCount = 0;
        this.speed = 0.2f;
        this.direction = new Vector2();
        this.attackDamage = attackDamage;
        this.lastAttackTime = System.currentTimeMillis();
    }




    /**
     * Updates the state of the EnemyBoss including position, health, and attacks
     * @param deltaTime Time in seconds since the last frame.
     */
    public void act(float deltaTime){
        move(deltaTime);
        bulletHitAndHealthCheck();
        attackUpdate();
    }





    public void draw(Batch batch, float parentAlpha) {
        if(this.alive) {
            batch.draw(enemyTexture, position.x - (targetedPlayer.getPosition().x - relativeOrigin.x), position.y - (targetedPlayer.getPosition().y - relativeOrigin.y));
        }
        itemDropAndDeadCheck(batch);
    }






    //used for memory management
    public void dispose() {
        enemyTexture.dispose();
    }





    /**
     * Determines how to move the enemy on the map
     * @param deltaTime Time in seconds since the last frame.
     */
    private void move(float deltaTime){
        float enemyPlayerDisplacement = distanceToPlayer();
        if(enemyPlayerDisplacement <= minDistToChase){
            targetedMove(deltaTime);
        }
        if(enemyPlayerDisplacement >= attackDistance){
            attackCount = 0;
        }
    }




    /**
     * Determines if one of the on-screen bullets being tracked has hit the EnemyBoss
     * @return true if so
     */
    private boolean bulletHitAndHealthCheck(){

        if(this.health <= 0){
            this.alive = false;
            this.dispose();
            return false;
        }

        WeaponGun playerGun = this.targetedPlayer.getWeapon();
        ArrayList<Bullet> playerGunBullets = playerGun.getBullets();
        for(Bullet bullet : playerGunBullets){
            if(abs(bullet.getPosition().x - this.position.x- (targetedPlayer.getPosition().x - relativeOrigin.x)) < 10 && abs(bullet.getPosition().y - this.position.y- (targetedPlayer.getPosition().y - relativeOrigin.y)) < 10){
                this.health -= bullet.getDamage();
                System.out.println(this.health);
                bullet.dispose();
                return true;
            }
        }
        return false;
    }






    /**
     * EnemyBoss attacks the player if within range
     */
    private void attackUpdate(){

        if(System.currentTimeMillis() - this.lastAttackTime >= 3000) {
            if (distanceToPlayer() <= attackDistance) {
                attackCount++;
                if (attackCount == 3) {
                    targetedPlayer.takeDamage(this.attackDamage * 3);
                    attackCount = 0;
                } else {
                    targetedPlayer.takeDamage(this.attackDamage);
                }
            }
            this.lastAttackTime = System.currentTimeMillis();
        }
    }







    private void itemDropAndDeadCheck(Batch batch){
        if(!this.alive){
            // generateRandomItem();
            // batch.draw(item_dropped.getItemTexture(), position.x - (targetedPlayer.getPosition().x - relativeOrigin.x), position.y- (targetedPlayer.getPosition().y - relativeOrigin.y));
        }
    }









    /**
     * Computes the distance of the player to the EnemyBoss
     * @return A float with the distance
     */
    private float distanceToPlayer(){
        float playerPosX = targetedPlayer.getPosition().x;
        float playerPosY = targetedPlayer.getPosition().y;
        float enemyPosX = this.position.x;
        float enemyPosY = this.position.y;
        return (float) Math.sqrt(Math.pow(playerPosX-enemyPosX,2)+Math.pow(playerPosY-enemyPosY,2));
    }





    /**
     * Moves the enemy while targeting the player
     * @param deltaTime Time in seconds since the last frame.
     */
    private void targetedMove(float deltaTime){
        this.direction.x = (targetedPlayer.getPosition().x + this.speed) - (position.x + this.speed);
        this.direction.y = (targetedPlayer.getPosition().y + this.speed) - (position.y + this.speed);
        this.direction.nor();
        position.x += direction.x * this.speed;
        position.y += direction.y * this.speed;
    }












    public Vector2 getPosition(){
        return position;
    }






    /**
     * Returns the texture for EnemyBoss from the pre-determined local file.
     * @return A scaled Pixmap object
     */
    private Pixmap createEnemyBossPixmap(){
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("zombie_man.png"));

        Pixmap pixmap100 = new Pixmap(50, 50, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        return pixmap100;
    }

    /** Determines if the player has struck the EnemyBoss with a weapon
     *
     * @return true if so
     */
}