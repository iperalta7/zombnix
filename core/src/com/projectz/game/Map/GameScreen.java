package com.projectz.game.Map;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.player.Player;
import com.projectz.game.screens.InventoryScreen;
import com.projectz.game.ui.StatusHUD;
import com.projectz.game.ui.StatusHUDRenderer;

public class GameScreen implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer; 
    private OrthographicCamera camera; 

    Player player;
    Game game;
    Stage stage;
    Inventory inventory;
    Batch batch;
    StatusHUDRenderer statusHUDRenderer;

    public GameScreen(ProjectZ game) {
        this.game = game;
    }
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // update the camera position to follow the player
        camera.position.x = player.getPosition().x + player.getWidth()/2;
        camera.position.y = player.getPosition().y + player.getHeight()/2;
        camera.update();

        renderer.setView(camera);
        renderer.render();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown (int keyCode) {
                if (keyCode == Input.Keys.E) {
                    game.setScreen(new InventoryScreen((ProjectZ) game, inventory));
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
        camera.update();

    }


    @Override
    public void show(){

        // TmxMapLoader loader = new TmxMapLoader();
        // map = loader.load("maps/basic_map.tmx");
        map = new TmxMapLoader().load("maps/basic_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 3f);

        player = new Player();
        player.setPlayerPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);


        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        statusHUDRenderer = new StatusHUDRenderer(new StatusHUD(player), player);
        stage = new Stage();
        inventory = new Inventory();
        inventory.printInventory();
        inventory.addItem(Item.HealingPotion, 5);
        //inventory.addItem(Item.SpeedPotion, 5);
        inventory.printInventory();
        stage.addActor(player);
        stage.addActor(statusHUDRenderer);
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
