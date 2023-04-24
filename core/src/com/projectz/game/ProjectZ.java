package com.projectz.game;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projectz.game.screens.MainMenu;

public class ProjectZ extends Game {
    public SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        //Libgdx said this is how I should do it (required for multiple scenes)
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}

