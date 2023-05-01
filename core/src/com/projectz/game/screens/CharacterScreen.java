package com.projectz.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.Map.GameScreen;
import com.projectz.game.ProjectZ;


public class CharacterScreen extends ScreenAdapter {
    private Stage stage;
    private float delta;
    private Table table;

    private TextureRegion character1;
    private TextureRegion character2;
    private ImageButton.ImageButtonStyle char1_button;
    private ImageButton.ImageButtonStyle char2_button;
    private ImageButton[] buttons;
    private Texture char_background;
    private TextureRegionDrawable char_backgroundDraw;
    private Image background;

    int screenHeight = Gdx.graphics.getHeight() / 2;
    int screenWidth = Gdx.graphics.getWidth() / 2;

    ProjectZ game;
    GameScreen game_screen;

    public CharacterScreen(ProjectZ game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setSize(250,430);
        table.setPosition(screenWidth - 250 / 2, screenHeight - 430 / 2);

        background = new Image(new Texture(Gdx.files.internal("background.png")));
        stage.addActor(background);

        char_background = new Texture(Gdx.files.internal("inventory.png"));
        char_backgroundDraw = new TextureRegionDrawable(new TextureRegion(char_background));
        table.setBackground(char_backgroundDraw);

        character1 = new TextureRegion(new Texture(Gdx.files.internal("player.png")));
        character2 = new TextureRegion(new Texture(Gdx.files.internal("player.png")));

        char1_button = new ImageButton.ImageButtonStyle();
        char2_button = new ImageButton.ImageButtonStyle();
        char1_button.imageUp = new TextureRegionDrawable(character1);
        char2_button.imageUp = new TextureRegionDrawable(character2);
        char1_button.imageUp.setMinWidth(character1.getRegionWidth() * 2);
        char1_button.imageUp.setMinHeight(character1.getRegionHeight() * 2);
        char2_button.imageUp.setMinWidth(character2.getRegionWidth() * 2);
        char2_button.imageUp.setMinHeight(character2.getRegionHeight() * 2);

        buttons = new ImageButton[2];
        buttons[0] = new ImageButton((char1_button));
        buttons[1] = new ImageButton((char2_button));
        Gdx.input.setInputProcessor(stage);

        //If character is selected, load game screen
        char_select_listener();
    }

    public void char_select_listener() {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //Change to game screen
                    game.setScreen(new GameScreen(game));
                }
            });
        }
    }

    @Override
    public void render (float delta) {
        stage.getViewport().update(800, 600, true);
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show () {
        //Create Labels for each character under each character
        Label[] characterLabels = new Label[2];
        characterLabels[0] = new Label("CHARACTER 1", new Label.LabelStyle((new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"))), Color.WHITE));
        characterLabels[1] = new Label("CHARACTER 2", new Label.LabelStyle((new BitmapFont(Gdx.files.internal("fonts/hud_font.fnt"))), Color.WHITE));
        characterLabels[0].setFontScale(0.5f);
        characterLabels[1].setFontScale(0.5f);

        //Adding Buttons to table
        for(int i = 0; i < buttons.length; i++) {
            table.add(buttons[i]).pad(10).width(100).height(100);
            table.row();
            table.add(characterLabels[i]).padBottom(10);
            table.row();
        }

        stage.addActor((table));
    }

    @Override
    public void dispose () {
        stage.dispose();
    }
}