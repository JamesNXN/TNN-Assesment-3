package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.gui.RoomTag;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * EXTENDED
 * This class defines the assetLoader that the game uses.
 */
public class AssetLoader {
    public static final String ASSET_FOLDER = "assets/";
    /**
     * Parameters needed for AssetLoader object:
     *
     * manager - a LibGDX AssetManager object used to manage assets
     * splash - The 2 seperate frames for the splash screen
     * arrowAtlas -  These TextureRegions store the 4 different directions that the room changing arrows can face.
     * playerSpriteSheetArray, npcSpriteSheetMapArray - Sprite sheets for abstract person objects
     * mapArray - map Data
     * roomTagBorder - Texture for the RoomTag
     * roomTagFont - Default roomTagFont for the game. Used in RoomTag
     * clueGlint - Sprite sheet for the clue glint to be drawn where a clue is hidden
     * menuSkin - skin for main menu
     * uiskin - skin for UI elements
     * npcJsonData, playerJsonData, clueJsonData - Data loaded in from json for npcs, players and clues
     */
    private AssetManager manager;

    public TextureAtlas splash;

    public TextureAtlas arrowAtlas;

    public ArrayMap<Integer, TextureAtlas> playerSpriteSheetArray;
    public ArrayMap<Integer, TextureAtlas> npcSpriteSheetMapArray;

    public Array<TiledMap> mapArray;

    public Texture roomTagBorder;

    public BitmapFont roomTagFont;

    public TextureAtlas clueGlint;

    public Skin menuSkin;
    public Skin uiSkin;
//    public Skin speechSkin;

    public JsonValue npcJsonData;
    public JsonValue playerJsonData;
    public JsonValue clueJsonData;

    /**
     * Constructor to build AssetLoader object
     */
    public AssetLoader(){
        manager = new AssetManager();
        mapArray = new Array<>();
        npcSpriteSheetMapArray = new ArrayMap<>();
        playerSpriteSheetArray = new ArrayMap<>();
    }

    /**
     * loads asset into manager for the splash screen
     */
    public void loadSplashAssets(){
        manager.load(ASSET_FOLDER + "splash.pack",TextureAtlas.class);
    }

    /**
     * retrieves asset from manager for the splash screen
     */
    public void assignSplashAssets(){
        splash = manager.get(ASSET_FOLDER + "splash.pack");
    }

    /**
     * loads in game assets into manager
     */
    public void loadGameAssets(){
        manager.load(ASSET_FOLDER + "skin/uiskin.json", Skin.class);
        manager.load(ASSET_FOLDER + "skin/comic-ui.json", Skin.class);

        manager.load(ASSET_FOLDER + "arrows.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "clueGlint.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "roomTagBorder.png", Texture.class);

        manager.load(ASSET_FOLDER + "people/player/alfred.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/player/phoebe.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/player/sherlock.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/player/steiner.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/player/victoria.pack", TextureAtlas.class);

        manager.load(ASSET_FOLDER + "people/NPCs/colin.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/diana.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/lily.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/mary.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/mike.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/will.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/david.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/julie.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/sophie.pack", TextureAtlas.class);
        manager.load(ASSET_FOLDER + "people/NPCs/tom.pack", TextureAtlas.class);

        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load(ASSET_FOLDER + "maps/computerRoom.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/island.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/kitchen.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/lakehouse.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/mainRoom.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/outside.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/pod.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/portersOffice.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/rch037.tmx",TiledMap.class);
        manager.load(ASSET_FOLDER + "maps/toilet.tmx",TiledMap.class);
    }

    /**
     *  retrieves assets from manager and assigns them correctly
     */
    public void assignGameAssets()
    {
        //roomTagFont init
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(ASSET_FOLDER + "fonts/VT323-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 50;
        roomTagFont = generator.generateFont(parameter);
        generator.dispose();

        playerJsonData = new JsonReader().parse(new FileHandle(ASSET_FOLDER + "player.json"));
        npcJsonData = new JsonReader().parse(new FileHandle(ASSET_FOLDER + "npc.json"));
        clueJsonData = new JsonReader().parse(new FileHandle(ASSET_FOLDER + "clue.json"));

        // sprite sheet assign
        playerSpriteSheetArray.put(1,manager.get(ASSET_FOLDER + "people/player/alfred.pack"));
        playerSpriteSheetArray.put(2,manager.get(ASSET_FOLDER + "people/player/phoebe.pack"));
        playerSpriteSheetArray.put(3,manager.get(ASSET_FOLDER + "people/player/sherlock.pack"));
        playerSpriteSheetArray.put(4,manager.get(ASSET_FOLDER + "people/player/steiner.pack"));
        playerSpriteSheetArray.put(5,manager.get(ASSET_FOLDER + "people/player/victoria.pack"));


        //map key is the npc id
        npcSpriteSheetMapArray.put(1, manager.get(ASSET_FOLDER + "people/NPCs/colin.pack"));
        npcSpriteSheetMapArray.put(2, manager.get(ASSET_FOLDER + "people/NPCs/diana.pack"));
        npcSpriteSheetMapArray.put(3, manager.get(ASSET_FOLDER + "people/NPCs/lily.pack"));
        npcSpriteSheetMapArray.put(4, manager.get(ASSET_FOLDER + "people/NPCs/mary.pack"));
        npcSpriteSheetMapArray.put(5, manager.get(ASSET_FOLDER + "people/NPCs/mike.pack"));
        npcSpriteSheetMapArray.put(6, manager.get(ASSET_FOLDER + "people/NPCs/will.pack"));
        npcSpriteSheetMapArray.put(7, manager.get(ASSET_FOLDER + "people/NPCs/david.pack"));
        npcSpriteSheetMapArray.put(8, manager.get(ASSET_FOLDER + "people/NPCs/julie.pack"));
        npcSpriteSheetMapArray.put(9, manager.get(ASSET_FOLDER + "people/NPCs/sophie.pack"));
        npcSpriteSheetMapArray.put(10, manager.get(ASSET_FOLDER + "people/NPCs/tom.pack"));

        // map assign
        mapArray.add(manager.get(ASSET_FOLDER + "maps/computerRoom.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/island.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/kitchen.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/lakehouse.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/mainRoom.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/outside.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/pod.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/portersOffice.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/rch037.tmx"));
        mapArray.add(manager.get(ASSET_FOLDER + "maps/toilet.tmx"));

        //arrow texture assign
        arrowAtlas = manager.get(ASSET_FOLDER + "arrows.pack");

        //room tag border texture assign
        roomTagBorder = manager.get(ASSET_FOLDER + "roomTagBorder.png");

        //clue glint animation init
        clueGlint = manager.get(ASSET_FOLDER + "clueGlint.pack");

        uiSkin = manager.get(ASSET_FOLDER + "skin/comic-ui.json");

        //menu skin init
        initSkin();
    }

    /**
     * Builds menu Skin and ui skin from assets
     */
    private void initSkin()
    {
        menuSkin = new Skin();

        BitmapFont titleFont = getFontWithSize(70);
        menuSkin.add("title", titleFont);

        BitmapFont menuFont = getFontWithSize(60);
        menuSkin.add("default", menuFont);

        //Create a background for text buttons
        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB888);
        pixmap.setColor(Color.ORANGE);
        pixmap.fill();
        menuSkin.add("background", new Texture(pixmap));
        pixmap.dispose();

        //Create a button style for main menu and pause menu
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = menuSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = menuSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = menuSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = menuSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = menuSkin.getFont("default");
        menuSkin.add("default", textButtonStyle);


        //used in InfoWindow class
        Label.LabelStyle infoStyle = new Label.LabelStyle(getFontWithSize(30), Color.BLACK);
        uiSkin.add("dialog", infoStyle);

        //used in ConversationSpeechBubble class
        Label.LabelStyle speechNameStyle = new Label.LabelStyle(getFontWithSize(20), Color.RED);
        uiSkin.add("speechName", speechNameStyle);

        //used in ConversationSpeechBubble class
        Label.LabelStyle speechDialogueStyle = new Label.LabelStyle(getFontWithSize(25), Color.BLACK);
        uiSkin.add("speechDialogue", speechDialogueStyle);

        //used in InteractionSelectionBubble class and QuestionStyleSelectionBubble class
        TextButton.TextButtonStyle buttonBubbleStyle = new TextButton.TextButtonStyle();
        buttonBubbleStyle.font = getFontWithSize(30);
        buttonBubbleStyle.fontColor = Color.BLACK;
        buttonBubbleStyle.up = uiSkin.getDrawable("button");
        buttonBubbleStyle.over = uiSkin.getDrawable("button-highlighted");
        buttonBubbleStyle.down = uiSkin.getDrawable("button-pressed");
        uiSkin.add("buttonBubble", buttonBubbleStyle);
    }

    /**
     * This method gets the default roomTagFont but at the requested size
     *
     * @param size - The size you want the roomTagFont to be
     * @return (BitmapFont) the resulting roomTagFont
     */
    public BitmapFont getFontWithSize(int size)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle(ASSET_FOLDER + "fonts/VT323-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    /**
     * getter for manager
     * @return returns manager
     */
    public AssetManager getManager() {
        return manager;
    }

    /**
     * Called when AssetLoader is disposed ensures manager object is also disposed
     */
    public void dispose(){
        manager.dispose();
    }
}
