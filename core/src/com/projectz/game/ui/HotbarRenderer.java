package com.projectz.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HotBarRenderer extends Actor {
    HotBar hotBar;
    public HotBarRenderer(HotBar hotBar) {
        this.hotBar = hotBar;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }
    @Override
    public void act(float deltaTime) {

    }
}
