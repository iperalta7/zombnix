package com.projectz.game.Map;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.projectz.game.ProjectZ;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.player.Player;
import com.projectz.game.screens.InventoryScreen;
import com.projectz.game.screens.ShopScreen;
import com.projectz.game.ui.HotBar;
import com.projectz.game.ui.HotBarRenderer;
import com.projectz.game.ui.StatusHUD;
import com.projectz.game.ui.StatusHUDRenderer;
import com.projectz.game.waveGen.waveGenerator;

import com.projectz.game.enemies.Enemy;

public class GameScreen implements Screen{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private FitViewport mapViewport;
    private boolean isPaused = false;
    Player player;
    Enemy enemy;
    Stage stage;
    waveGenerator wave;
    Game game;
    Inventory inventory;
    Batch batch;
    StatusHUDRenderer statusHUDRenderer;
    HotBar hotBar;
    HotBarRenderer hotBarRenderer;
    InventoryScreen inventoryScreen;
    GameScreen currentScreen;
    Label pointLabel; 
    public Table pointTable;
    private float mapWidth;
    private float mapHeight;

    public GameScreen(ProjectZ game) {
        this.game = game;
        currentScreen = this;

        map = new TmxMapLoader().load("maps/zombie_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 4f);
        player = new Player();
        player.setPlayerPosition(ProjectZ.VIRTUAL_WIDTH/2, ProjectZ.VIRTUAL_HEIGHT/2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ProjectZ.VIRTUAL_WIDTH, ProjectZ.VIRTUAL_HEIGHT);
        computeMapSize();
        player.setMapBounds(mapWidth, mapHeight);
        mapViewport = new FitViewport(ProjectZ.VIRTUAL_WIDTH, ProjectZ.VIRTUAL_HEIGHT, camera);
        statusHUDRenderer = new StatusHUDRenderer(new StatusHUD(player), player);
        Enemy enemy = new Enemy(player, new Vector2(player.getPosition().x-100, player.getPosition().y-100), 10);
        stage = new Stage(new FitViewport(ProjectZ.VIRTUAL_WIDTH, ProjectZ.VIRTUAL_HEIGHT));
        wave = new waveGenerator();
        inventory = new Inventory(player);
        inventory.addItem(Item.HealingPotion, 5);
        inventory.addItem(Item.SpeedPotion, 5);
        inventory.addItem(Item.sword,1);
        hotBar = new HotBar(inventory);
        hotBarRenderer = new HotBarRenderer(hotBar, player);
        pointTable = new Table();
        pointTable.setPosition(400,400);
        pointTable.setSize(400,300);
        pointLabel = new Label("Points: " + player.points, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
        pointTable.add(pointLabel); 
        stage.addActor(player);
        stage.addActor(statusHUDRenderer);
        stage.addActor(hotBarRenderer);
        stage.addActor(enemy);
        stage.addActor(pointTable);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // update the camera position to follow the player
        updateCameraPosition();

        mapViewport.apply();
        renderer.setView(camera);
        renderer.render();
        wave.update();

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            game.setScreen(new InventoryScreen((ProjectZ) game, inventory, currentScreen));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            game.setScreen(new ShopScreen((ProjectZ) game, player, inventory, currentScreen));
        }
        pointTable.removeActor(pointLabel); 
        pointLabel = new Label("Points: " + player.points, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
        pointTable.add(pointLabel); 
        stage.addActor(pointTable);
        wave.render(camera);
        stage.getViewport().apply();
        //default call to create stage (from documentation page)
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Derives the world size of the map (in world pixels) from the loaded map properties
     * and the renderer unit scale.
     */
    private void computeMapSize() {
        int tilesX = map.getProperties().get("width", Integer.class);
        int tilesY = map.getProperties().get("height", Integer.class);
        int tileW = map.getProperties().get("tilewidth", Integer.class);
        int tileH = map.getProperties().get("tileheight", Integer.class);
        mapWidth = tilesX * tileW * renderer.getUnitScale();
        mapHeight = tilesY * tileH * renderer.getUnitScale();
    }

    /**
     * Centres the camera on the player but keeps the visible area inside the map.
     * If the map is smaller than the view on an axis, the map is centred on that axis.
     * The resulting camera position is shared with the player so the sprite is drawn
     * at its offset from the camera (the sprite moves on screen when the camera is clamped).
     */
    private void updateCameraPosition() {
        camera.position.x = clampAxis(player.getPosition().x, camera.viewportWidth, mapWidth);
        camera.position.y = clampAxis(player.getPosition().y, camera.viewportHeight, mapHeight);
        player.setCameraPosition(camera.position.x, camera.position.y);
        camera.update();
    }

    private static float clampAxis(float target, float viewSize, float mapSize) {
        if (mapSize <= viewSize) {
            return mapSize / 2f;
        }
        return Math.max(viewSize / 2f, Math.min(target, mapSize - viewSize / 2f));
    }

    @Override
    public void resize(int width, int height){
        // The map camera keeps following the player, so only the viewport rectangle is updated
        mapViewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }


    @Override
    public void show(){

    }   


    @Override
    public void hide(){

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
