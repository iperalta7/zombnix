package com.projectz.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class StatusHUDRenderer extends Actor {
    private StatusHUD statusHUD;
    private OrthographicCamera camera;
    //private Vector2 position;
    private BitmapFont fontDrawer;
    private final float SCALE_FACTOR = 1;

    public StatusHUDRenderer(StatusHUD statusHUD) {
        this.statusHUD = statusHUD;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fontDrawer = new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"));
        fontDrawer.getData().setScale(0.5f);
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
        fontDrawer.draw(batch, Integer.toString(statusHUD.getPlayer().getHealth()), 330, 25);
        fontDrawer.draw(batch, Integer.toString(statusHUD.getPlayer().getExpLevel()), 330, 65);
    }
}
