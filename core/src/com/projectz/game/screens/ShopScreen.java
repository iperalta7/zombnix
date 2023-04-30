package com.projectz.game.screens;
import com.projectz.game.Map.GameScreen;
import com.projectz.game.player.Player;
import com.projectz.game.inventory.Inventory;
import com.projectz.game.items.Item;
import com.projectz.game.inventory.ItemSlot;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.projectz.game.ProjectZ;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
     * This is the ShopScreen class.
     *This will act as a splash screen for the user to use
     *It will display different options to purchase new items (weapons, potions, upgrades, etc)
     *Must be able to communicate with the player and inventory 
 */
public class ShopScreen extends ScreenAdapter {
    private Stage shopStage; 
    Inventory inventory; 
    Player player;
    Array<Item> ItemList; 
 
    private ImageButton.ImageButtonStyle buttonStyle;
    private TextureRegion genericRegion;
    private ImageButton genericButton;
    private ImageButton closeShopButton;
    private ImageButton BuyHealingPotionButton; 
    private ImageButton BuySwordButton; 
    

    ProjectZ game;
    GameScreen gameScreen;

    int screenHeight = Gdx.graphics.getHeight() / 2;
    int screenWidth = Gdx.graphics.getWidth() / 2;

    //Window elements:
    private Window.WindowStyle inventoryStyle;
    private Window window;

    Label pointLabel; 
    public Table pointTable;


    /**
     * This is the ShopScreen constructor, it will be what initializates everything 
     * we will also take in the inventory and player so that we can update the data within it
     */
    public ShopScreen(ProjectZ game, Player player, Inventory inventory, GameScreen gameScreen){
       this.game = game; 
       this.player = player;
       this.inventory = inventory;  
       this.gameScreen = gameScreen;
       shopStage = new Stage(new ScreenViewport());
       Gdx.input.setInputProcessor(shopStage);
       loadWindow();
       loadItems();
       itemListener();
    }

     /**
     * Function loadWindow:
     * Description:
     * This function will be what creates the screen and displays the background of the screen. It will
     * also create the exit button and the inital user points text on the top right of the screen.
     */
    private void loadWindow(){
        inventoryStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(new Texture("inventory.png")));
        window = new Window("",inventoryStyle);
        window.setVisible(true);
        window.setSize(250,430);
        window.setMovable(true);
        window.setPosition(screenWidth - window.getWidth() / 2, screenHeight - window.getHeight() / 2);
        shopStage.addActor(window);
        closeShopButton = addButton("exitInventory.png", 100, 100); 
        shopStage.addActor(closeShopButton);
        pointTable = new Table();
        pointTable.setPosition(400,400);
        pointTable.setSize(400,300);
        pointLabel = new Label("Points: " + player.points, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
        pointTable.add(pointLabel); 
        shopStage.addActor(pointTable);
       
    }   

    
     /**
     * Function loadItems:
     * Description:
     * This function will talk with the Items class, it will retrieve the items which were created in 
     * the items class and create specific tables for these items. These tables will have a image of the 
     * item, price and a button which says "Buy" on it.
     */
    private void loadItems(){
        Table healingPotionTable = createItem(Item.HealingPotion, "healPotion.png", 200, 250);
        BuyHealingPotionButton = addButton("button_buy.png", 200, 200);
        healingPotionTable.add(BuyHealingPotionButton).colspan(2).center(); 
        Table swordWeaponTable = createItem(Item.sword, "Weapons/sword.png", 200, 150); 
        BuySwordButton = addButton("button_buy.png", 200, 100);
        swordWeaponTable.add(BuySwordButton).colspan(2).center();
        shopStage.addActor(healingPotionTable);
        shopStage.addActor(swordWeaponTable);
    }
     /**
     * Function createItem:
     * Parameters: Item item (Item class item), String itemImg (Img of the item), int x, int y (coords to where the table will be placed)
     * Description:
     * This function is a helper function and will create the table for the "load items". 
     * I did this to generalize the tables, instead of repasting the code each time for a new code 
     * and remaking it, just with different variables. 
     * Returns: the created table
     */
    private Table createItem(Item item, String itemImg, int x, int y){
        Table table = new Table();
        table.setSize(400, 300); 
        table.setPosition(x, y);
        Texture texture = new Texture(Gdx.files.internal(itemImg));
        Image image = new Image(texture);
        table.add(image).colspan(2).center();
        return table; 
    }

    /**
     * Params that would likely have to be included:
     * You will need some type of click listener to recieve input
     * You will need to have the weapons class to determine which weapon you are selecting
     * Description:
     * This function will wait for the user to click any item, once that is done it will retrieve
     * the information of the item. From there, the user would be able to 
     * make a further decision on whether they want to purchase the weapon or not. 
     * Returns:
     * What item the user is clicking 
     */
    public void itemListener(){
        closeShopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Change to game screen
                game.setScreen(gameScreen);
            }
        }); 

        BuyHealingPotionButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Player points: " + player.points);
                inventory.printInventory();
                purchaseItem(Item.HealingPotion);
                pointTable.removeActor(pointLabel); 
                pointLabel = new Label("Points: " + player.points, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
                pointTable.add(pointLabel); 

            }
        });


        BuySwordButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Player points: " + player.points);
                inventory.printInventory();
                purchaseItem(Item.sword);
                pointTable.removeActor(pointLabel); 
                pointLabel = new Label("Points: " + player.points, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
                pointTable.add(pointLabel); 
            }
        });
    }
    
    /**
     * Params that would likely have to be included:
     * You will need the player object to recieve the points
     * You will need the inventory class to add the weapon
     * You will need the weapon class to determine which weapon
     * Description:
     * This function will use checkPoints() function to check the users points, 
     * then the user will be able to purchase the item
     * once purchased, it will call the addToInventory() to add the item to the inventory
     * Returns:
     * This will do multiple functions, it will not have a speific return
     */
    public void purchaseItem(Item item){
        if(checkPoints(item)){
            inventory.addItem(item, 1); 
            System.out.println("Player points: " + player.points);
            inventory.printInventory();
        }
        else{
            System.out.println("Not enough points");

        }
    }

    /**
     * Params that would likely have to be included:
     * You will need the player class to check the player's points
     * You will the weapons class to check the weapon's points
     * Description:
     * This function will check the users points
     * If the on point is meet it will return true, if not then it will return false
     * Returns:
     * a boolean on whether the weapon can be purchased or not 
     */
    public boolean checkPoints(Item item){
        if(player.points >= item.price){
            player.points -= item.price; 
            return true; 
        }
        else{
            return false; 
        }
    }
    
     /**
     * Function addButton:
     * Params: String path (path to the button img), int x, int y(where you want the buttons to be placed)
     * Description:
     * This function is a helper function and is used to help create a specific button with the paramters 
     * passed through. It is useful as we will not have redundent code for each button being made. 
     * Returns: A "genericButton" which was created with the specific parameters
     */
    private ImageButton addButton(String path, int x, int y){
        genericRegion = new TextureRegion(new Texture(Gdx.files.internal(path)));
        buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(genericRegion);
        genericButton = new ImageButton(buttonStyle);
        genericButton.setPosition(x,y);
        return genericButton; 
    }

    /**
     * This is the show function, right now there is no uses
     */
    @Override
    public void show() {
    
    }

    /**
     * This is the drawButton function, it will be responsible for creating buttons to display
     * on the users screen. This button may appear once activated or we might have it forever 
     * on the screen and have it functional during the correct conditions. 
     */
    public void drawButton() {
      
    }

    /**
     * This is the render function, it will be responsible for displaying everything on the screen
     * The render will also check to see what is being displayed on the screen, this could be buttons,
     * items or anything else that the shop screen might have to. 
     */
    @Override
    public void render (float delta) {
        shopStage.getViewport().update(800, 600, true);
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shopStage.act();
        shopStage.draw();
    }
    /**
     * This is the resize function, it will be responsible for determing based off of the application
     * screen, what size should this screen be. 
     */
    @Override
    public void resize(int width, int height) {

    }
    /**
     * This is the pause function, right now there is no uses
     */
    @Override
    public void pause() {

    }
    /**
     * This is the resume function, right now there is no uses
     */
    @Override
    public void resume() {

    }
    /**
     * This is the hide function, right now there is no uses
     */
    @Override
    public void hide() {

    }
    /**
     * This is the dispose function, it will be responsible for ended a process
     */
    @Override
    public void dispose() {

    }
}