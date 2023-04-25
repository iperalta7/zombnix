package com.projectz.game.Map;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.projectz.game.player.Player;
import com.projectz.game.enemies.EnemyGeneric;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.player.Player;
import com.projectz.game.screens.InventoryScreen;
import com.projectz.game.ui.HotBar;
import com.projectz.game.ui.HotBarRenderer;
import com.projectz.game.ui.StatusHUD;
import com.projectz.game.ui.StatusHUDRenderer;


public class GameScreen implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private boolean isPaused = false;

    Player player;
    EnemyGeneric enemy;
    Stage stage;
    Game game;
    Inventory inventory;
    Batch batch;
    StatusHUDRenderer statusHUDRenderer;
    HotBar hotBar;
    HotBarRenderer hotBarRenderer;
    InventoryScreen inventoryScreen;
    GameScreen currentScreen;

    public GameScreen(ProjectZ game) {
        this.game = game;
        currentScreen = this;
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // update the camera position to follow the player
        camera.position.x = player.getPosition().x;
        camera.position.y = player.getPosition().y;
        camera.update();

        renderer.setView(camera);
        renderer.render();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown (int keyCode) {
                if (keyCode == Input.Keys.E) {
                    game.setScreen(new InventoryScreen((ProjectZ) game, inventory, currentScreen));
                }
                return true;
            }
        });

        //default call to create stage (from documentation page)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height){
        camera.viewportWidth = width; 
        camera.viewportHeight = height;

        camera.position.set(camera.viewportWidth / 3f, camera.viewportHeight / 3f, 0);
        camera.update(); 

    }


    @Override
    public void show(){
        map = new TmxMapLoader().load("maps/zombie_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 3f);
        player = new Player();
        player.setPlayerPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        enemy = new EnemyGeneric(null, 100, player);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        statusHUDRenderer = new StatusHUDRenderer(new StatusHUD(player), player);
        stage = new Stage();
        inventory = new Inventory();
        inventory.addItem(Item.HealingPotion, 5);
        inventory.addItem(Item.SpeedPotion, 5);
        inventory.addItem(Item.sword,1);
        hotBar = new HotBar(inventory);
        hotBarRenderer = new HotBarRenderer(hotBar);
        stage.addActor(player);
        stage.addActor(statusHUDRenderer);
        stage.addActor(hotBarRenderer);
        stage.addActor(enemy);

    }   


    @Override
    public void hide(){
        dispose();
    }


    @Override
    public void pause(){
        
    }

    @Override
    public void resume(){
        
    }

    @Override
    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
}
