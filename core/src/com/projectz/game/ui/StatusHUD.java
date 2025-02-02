package com.projectz.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.projectz.game.player.Player;

/**
 * This class contains the contents of the status HUD.
 * It performs the operations when certain parameters
 * such as player's health and experience are updated.
 */
public class StatusHUD {
    private Player player;

    protected Sprite healthSprite;
    protected Sprite expSprite;
    protected Sprite pistolSprite;
    protected int numBullets;

    /**
     * The constructor initializes the sprites for the
     * health bar, experience bar, and pistol.
     * @param player
     */
    public StatusHUD(Player player) {
        this.player = player;
        //Default Textures
        healthSprite = StatusHUDSprites.HEALTH_SPRITE_10;
        expSprite = StatusHUDSprites.EXP_SPRITE_10;
        pistolSprite = StatusHUDSprites.PISTOL_SPRITE;
    }

    /**
     * This method gets the updated health and experience
     * values from the player object.
     */
    public void update() {
        int health = player.getHealth();
        int expValue = player.getExpValue();
        updateHealth(health);
        updateExp(expValue);
        numBullets = player.getWeapon().getNumBullets();
    }

    /**
     * This method updates the health sprite which is shown
     * based on the number of health points the player has.
     * @param health the updated health value
     */
    private void updateHealth(int health) {
        if(health == 0) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_0;
        } else if(health > 0 && health < 15) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_1;
        } else if(health >= 15 && health < 25) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_2;
        } else if(health >= 25 && health < 35) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_3;
        } else if(health >= 35 && health < 45) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_4;
        } else if(health >= 45 && health < 55) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_5;
        } else if(health >= 55 && health < 65) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_6;
        } else if(health >= 65 && health < 75) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_7;
        } else if(health >= 75 && health < 85) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_8;
        } else if(health >= 85 && health < 95) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_9;
        } else if(health >= 95 && health <= 100) {
            healthSprite = StatusHUDSprites.HEALTH_SPRITE_10;
        }
    }

    /**
     * This method updates the experience sprite which is shown
     * based on the number of experience points the player has.
     * @param expValue the updated experience value
     */
    public void updateExp(int expValue) {

    }
    public Player getPlayer() {
        return player;
    }

}
