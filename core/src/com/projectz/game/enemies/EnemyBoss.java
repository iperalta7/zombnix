package com.projectz.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.projectz.game.items.Item;
import com.badlogic.gdx.math.Vector2;
import com.projectz.game.player.Bullet;
import com.projectz.game.player.Player;
import java.lang.Math;


/**
 * Concrete class of type Enemy for a more specific EnemyBoss.
 */
public class EnemyBoss extends Enemy{

    int attackCount;

    /**
     * Initializes values of the EnemyBoss with the help of the super constructor
     * @param player The instance of the Player object that the enemy will be targeting
     * @param initial_x An integer specifying initial x-position
     * @param initial_y An integer specifying initial y-position
     */
    public EnemyBoss(Player player, float initial_x, float initial_y){
        super(new Item[] {Item.HealingPotion}, 100, 50f, player, initial_x, initial_y);
        enemyTexture = new Texture(createEnemyBossPixmap());
        minDistToChase = 100;
        attackCount = 0;
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

    /**
     * Updates the state of the EnemyBoss including position, health, and attacks
     * @param deltaTime Time in seconds since the last frame.
     */
    @Override
    public void act(float deltaTime){
        move(deltaTime);
        damageHealthUpdate();
        attackUpdate();
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
     * Determines how to move the enemy on the map
     * @param deltaTime Time in seconds since the last frame.
     */
    private void move(float deltaTime){
        float enemyPlayerDisplacement = distanceToPlayer();
        if(enemyPlayerDisplacement <= minDistToChase){
            targetedMove(deltaTime);
        }
        else{
            randomMove(deltaTime);
        }
        if(enemyPlayerDisplacement >= attackDistance){
            attackCount = 0;
        }
    }

    /**
     * Moves the enemy while targeting the player
     * @param deltaTime Time in seconds since the last frame.
     */
    private void targetedMove(float deltaTime){
        // Move toward player within bounds
    }

    /**
     * Moves the enemy randomly without a target
     * @param deltaTime Time in seconds since the last frame.
     */
    private void randomMove(float deltaTime){
        // Randomly move within bounds
    }

    /**
     * Determines changes in health from received attacks
     */
    private void damageHealthUpdate(){
        if(this.isHitByBullet()){
            Bullet hitBullet = findHitBullet();
            health -= 0; // health -= hitBullet.damage;
        }

        if(this.isHitByWeapon()){
            health -= 0; // health -= player.weapon.damage;
        }
    }

    /**
     * Determines if one of the on-screen bullets being tracked has hit the EnemyBoss
     * @return true if so
     */
    private boolean isHitByBullet(){
        return true;
    }

    /** Determines if the player has struck the EnemyBoss with a weapon
     *
     * @return true if so
     */
    private boolean isHitByWeapon(){
        return true;
    }

    /**
     * Determines which bullet has hit the EnemyBoss
     * @return a Bullet object
     */
    private Bullet findHitBullet(){
        // Dispose of bullet
        return new Bullet(0,0,new Vector2(0,0),50f);
    }

    /**
     * EnemyBoss attacks the player if within range
     */
    private void attackUpdate(){
        if(distanceToPlayer() <= attackDistance){
            dealDamageToPlayer();
        }
    }

    /**
     * Deals damage to the player if the EnemyBoss can attack
     */
    private void dealDamageToPlayer(){
        // Deal damage and more if a combo attack (more than one attack in a row)
        attackCount++;
    }
}