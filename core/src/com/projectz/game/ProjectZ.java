package com.projectz.game;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projectz.game.Map.GameScreen;
import com.projectz.game.screens.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.projectz.game.player.Player;
import com.projectz.game.screens.*;
//import jdk.tools.jmod.Main;

public class ProjectZ extends Game {
    /** Size of the game's world in pixels. Windows of any size are scaled to fit this (letterboxed). */
    public static final int VIRTUAL_WIDTH = 800;
    public static final int VIRTUAL_HEIGHT = 600;

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

