package com.projectz.game.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projectz.game.player.Bullet;
import com.projectz.game.player.Player;
import com.projectz.game.weapons.WeaponGun;

import static java.lang.Math.abs;

/**
 * Enemy class describing a type of Actor that must be fought within the game.
 */
public class Enemy extends Actor {

    int health;
    protected Texture enemyTexture;
    protected Vector2 position;
    protected Vector2 relativeOrigin;
    protected float speed;
    protected boolean alive;
    protected Player targetedPlayer;
    protected float minDistToChase;
    protected float attackDistance;
    protected int attackCount;
    protected Vector2 direction;
    protected int attackDamage;
    protected long lastAttackTime;
    protected int timeBetweenAttacks;


    /**
     * Initializes values of the Enemy.
     * @param player The instance of the Player object that the enemy will be targeting
     * @param initial_position A Vector2 object describing the enemy's intitial position.
     * @param attackDamage An integer describing the damage given by the enemy upon attack
     */
    public Enemy(Player player, Vector2 initial_position, int attackDamage){
        this.health = 100;
        this.position = initial_position;
        this.relativeOrigin = new Vector2(player.getPosition().x, player.getPosition().y);
        this.alive = true;
        this.targetedPlayer = player;
        this.attackDistance = 5;
        this.enemyTexture = new Texture(createEnemyPixmap());
        this.minDistToChase = 500;
        this.attackCount = 0;
        this.speed = 1f;
        this.direction = new Vector2();
        this.attackDamage = attackDamage;
        this.lastAttackTime = System.currentTimeMillis();
        this.timeBetweenAttacks = 3000;
    }







    /**
     * Updates the state of the Enemy including position and health and carries out attacks
     * @param deltaTime Time in seconds since the last frame.
     */
    public void act(float deltaTime){
        move(deltaTime);
        aliveCheck();
        bulletHitCheck();
        attackUpdate();
    }







    /**
     * Draws the enemy during every game loop.
     * @param batch The SpriteBatch that this Enemy Actor is part of
     * @param parentAlpha From libGDX: The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all children.
     */
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(enemyTexture, position.x - (targetedPlayer.getPosition().x - relativeOrigin.x), position.y - (targetedPlayer.getPosition().y - relativeOrigin.y));
        itemDropAndDeadCheck();
    }







    /**
     * Disposes of the enemy's assets on death. Currently only deletes texture.
     */
    public void dispose() {
        enemyTexture.dispose();
        this.remove();
    }





    /**
     * Determines movement of the enemy on the map. Also resets combo attack counter through attackCount.
     * @param deltaTime Time in seconds since the last frame.
     */
    private void move(float deltaTime){
        float enemyPlayerDisplacement = distanceToPlayer();
        if(enemyPlayerDisplacement <= minDistToChase){
            targetedMove();
        }
        if(enemyPlayerDisplacement >= attackDistance){
            attackCount = 0;
        }
    }







    /**
     * Checks if the enemy is still alive and disposes of it if necessary.
     * @return True if still alive
     */
    public boolean aliveCheck(){
        if(this.health <= 0){
            this.alive = false;
            this.dispose();
            return false;
        }
        else{
            return true;
        }
    }




    /**
     * Determines if one of the targeted player's gun's bullets has hit the Enemy and damages Enemy if so.
     * @return True if enemy is hit by bullet
     */
    public boolean bulletHitCheck(){
        WeaponGun playerGun = this.targetedPlayer.getWeapon();
        ArrayList<Bullet> playerGunBullets = playerGun.getBullets();
        for(Bullet bullet : playerGunBullets){
            if(abs(this.position.x - bullet.getPosition().x - (targetedPlayer.getPosition().x - relativeOrigin.x)) < bullet.getBulletRange() && abs(this.position.y - bullet.getPosition().y - (targetedPlayer.getPosition().y - relativeOrigin.y)) < bullet.getBulletRange()){
                this.health -= bullet.getDamage();
                bullet.dispose();
                return true;
            }
        }
        return false;
    }






    /**
     * EnemyBoss attacks the player if within range and inter-attack time has passed. Also handles making combo attacks if applicable.
     * @return True if an attack is made.
     */
    public boolean attackUpdate(){
        if(System.currentTimeMillis() - this.lastAttackTime >= this.timeBetweenAttacks) {
            if (distanceToPlayer() <= attackDistance) {
                attackCount++;
                if (attackCount >= 3) {
                    targetedPlayer.takeDamage(this.attackDamage * 3);
                    attackCount = 0;
                } else {
                    targetedPlayer.takeDamage(this.attackDamage);
                }
            }
            this.lastAttackTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }







    /**
     * Method should generate an item using the enemy position. Item class requires some changes I need to discuss with my team.
     */
    private void itemDropAndDeadCheck(){
        if(!this.alive){
            // generateRandomItem(this.position);
        }
    }







    /**
     * Computes the distance of the player to the EnemyBoss
     * @return A float with the distance
     */
    public float distanceToPlayer(){
        float playerPosX = targetedPlayer.getPosition().x;
        float playerPosY = targetedPlayer.getPosition().y;
        float enemyPosX = this.position.x;
        float enemyPosY = this.position.y;
        return (float) Math.sqrt(Math.pow(playerPosX-enemyPosX,2)+Math.pow(playerPosY-enemyPosY,2));
    }





    /**
     * Sets the new enemy position when targeting the player
     */
    public void targetedMove(){
        this.direction.x = targetedPlayer.getPosition().x - position.x;
        this.direction.y = targetedPlayer.getPosition().y - position.y;
        this.direction.nor();
        position.x += direction.x * this.speed;
        position.y += direction.y * this.speed;
    }







    /**
     * Getter for the enemy position.
     * @return A Vector2 object with x and y components.
     */
    public Vector2 getPosition(){
        return position;
    }






    /**
     * Returns the texture for EnemyBoss from the pre-determined local file.
     * @return A scaled Pixmap object
     */
    private Pixmap createEnemyPixmap(){
        Pixmap pixmap200 = new Pixmap(Gdx.files.internal("zombie_man.png"));

        Pixmap pixmap100 = new Pixmap(50, 50, pixmap200.getFormat());
        pixmap100.drawPixmap(pixmap200,
                0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                0, 0, pixmap100.getWidth(), pixmap100.getHeight()
        );
        return pixmap100;
    }
}

