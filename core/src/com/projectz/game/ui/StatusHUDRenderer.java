package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.projectz.game.player.Player;

public class StatusHUDRenderer extends Actor {
    private StatusHUD statusHUD;
    private Player player;
    private OrthographicCamera camera;
    //private Vector2 position;
    private BitmapFont fontDrawer;
    private final float SCALE_FACTOR = 1;
    private float timeCount;
    private int expAddNumber;

    public StatusHUDRenderer(StatusHUD statusHUD, Player player) {
        this.statusHUD = statusHUD;
        this.player = player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fontDrawer = new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"));
        fontDrawer.getData().setScale(0.5f);
        timeCount = 3;
        expAddNumber = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setProjectionMatrix(camera.combined);
        //Update HUD values
        statusHUD.update();
        //Draw Textures for HUD
        Sprite healthSprite = statusHUD.getHealthSprite();
        Sprite expSprite = statusHUD.getExpSprite();
        batch.draw(healthSprite, 0, 0, healthSprite.getWidth()*SCALE_FACTOR, healthSprite.getHeight()*SCALE_FACTOR);
        batch.draw(expSprite, 0, 40, expSprite.getWidth()*SCALE_FACTOR, expSprite.getHeight()*SCALE_FACTOR);
        //Draw Health and Level values
        fontDrawer.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        fontDrawer.draw(batch, Integer.toString(statusHUD.getPlayer().getHealth()), 330, 25);
        fontDrawer.draw(batch, Integer.toString(statusHUD.getPlayer().getExpLevel()), 330, 65);
        //Draw "+x" exp when new exp is collected
        if (timeCount < 1) {
            fontDrawer.setColor(1.0f, 1.0f, 1.0f, 1-timeCount);
            fontDrawer.draw(batch, "+"+Integer.toString(expAddNumber), 400, 65);
        }
    }
    @Override
    public void act(float deltaTime) {
        //Update time
        timeCount += deltaTime;
        //Test code
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            setExpAddNumber(5);
            player.setExpValue(player.getExpValue()+5);
        }
    }
    public void setExpAddNumber(int number) {
        //Set new exp number to be displayed reset time to 0
        expAddNumber = number;
        timeCount = 0;
    }
}