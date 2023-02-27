package com.projectz.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.projectz.game.ProjectZ;
import com.projectz.game.screens.*;

public class MainMenu extends ScreenAdapter {
    private Texture background;
    private Texture play_button_unactive;
    private Texture play_button_active;
    private Texture exit_button_active;
    private Texture exit_button_unactive;

    private TextureRegion play_button_region;
    private TextureRegion exit_button_region;

    private boolean play_button_hover;
    private boolean exit_button_hover;

    ProjectZ game;
    GameScreen game_screen;
    CharacterScreen char_screen;


    public MainMenu(final ProjectZ game) {
        this.game = game;
        this.background = new Texture("background.png");
        this.play_button_unactive = new Texture("play_button_clear.png");
        this.play_button_active = new Texture("play_button_active.png");
        this.exit_button_unactive = new Texture("exit_button_unactive.png");
        this.exit_button_active = new Texture("exit_button_active.png");
        this.play_button_region = new TextureRegion(play_button_active);
        this.exit_button_region = new TextureRegion(exit_button_active);
    }

    @Override
    public void show() {

    }

    //Draws png play button on main menu
    public void drawButton() {
        int x = Gdx.graphics.getWidth() / 2 - play_button_unactive.getWidth() / 2;
        int y = Gdx.graphics.getHeight() / 2 - play_button_unactive.getHeight() / 2;

        int play_width = play_button_region.getRegionWidth();
        int play_height = play_button_region.getRegionHeight();
        int exit_width = exit_button_region.getRegionWidth();
        int exit_height = exit_button_region.getRegionHeight();

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        this.play_button_hover = mouseX >= x && mouseX <= x + play_width && mouseY >= y && mouseY <= y + play_height;
        this.exit_button_hover = mouseX >= x && mouseX <= x + exit_width && mouseY >= y - exit_button_unactive.getHeight()
                && mouseY <= y - exit_button_unactive.getHeight() + exit_height;

        game.batch.draw(play_button_region, x, y);
        game.batch.draw(exit_button_region, x, y - exit_button_unactive.getHeight());

        //If mouse is hovered our play button, draw active play button, otherwise draw inactive play button
        if(play_button_hover) {
            game.batch.draw(play_button_active, x,y);
        }
        else {
            game.batch.draw(play_button_unactive, x,y);
        }

        if(exit_button_hover) {
            game.batch.draw(exit_button_active, x, y-exit_button_unactive.getHeight());
        }
        else {
            game.batch.draw(exit_button_unactive, x, y-exit_button_unactive.getHeight());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //Draw background for title screen
        game.batch.draw(background, 0,0);

        //draws buttons on screen and checks if moused is hovered over
        drawButton();

        game.batch.end();

        //If the play button is hovered and clicked, go to game screen
        if(play_button_hover && Gdx.input.isButtonJustPressed((Input.Buttons.LEFT))) {
            game.setScreen(new CharacterScreen(game));
            dispose();
        }

        //If the exit button is hovered and clicked, close application
        if(exit_button_hover && Gdx.input.isButtonJustPressed((Input.Buttons.LEFT))) {
            Gdx.app.exit();
        }
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

