package com.projectz.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu extends ScreenAdapter {
    ProjectZ game;
    private Texture background;
    private Texture play_button_unactive;
    private Texture play_button_active;
    private Texture exit_button_unactive;
    private Texture exit_button_active;
    GameScreen game_screen;


    public MainMenu(final ProjectZ game) {
        this.game = game;
        this.background = new Texture("background.png");
        this.play_button_unactive = new Texture("play_button_clear.png");
        this.play_button_active = new Texture("play_button_active.png");
        this.exit_button_unactive = new Texture("exit.png");
        this.exit_button_active = new Texture("exit.png");
    }

    @Override
    public void show() {

    }

    //Draws png play button on main menu
    public boolean drawButton() {
        int x = Gdx.graphics.getWidth() / 2 - play_button_unactive.getWidth() / 2;
        int y = Gdx.graphics.getHeight() / 2 - play_button_unactive.getHeight() / 2;

        //If mouse is hovered our play button, draw active play button, otherwise draw inactive play button
        if((Gdx.input.getX() < x + play_button_unactive.getWidth() && Gdx.input.getX() > x) &&
                (Gdx.input.getY() < y + play_button_unactive.getHeight() && Gdx.input.getY() > y)) {
            game.batch.draw(play_button_active, x,y);
            return true;
        }

        //TODO make else if statement to check for exit button or any other menu button


        else {
            //Always draw main menu buttons
            game.batch.draw(play_button_unactive, x,y);
            return false;
        }
    }

    public void play_button_click() {
        //If mouse clicks play button, load GameScreen
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                if((button == Input.Buttons.LEFT))
                {
                    game.setScreen(new GameScreen(game));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //Draw background for title screen
        game.batch.draw(background, 0,0);

        //draws menu buttons on screen and returns true if button is hovered over
        boolean play_button_active = drawButton();

        //If play button is hovered over and click, load game screen
        if(play_button_active) {
            play_button_click();
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

