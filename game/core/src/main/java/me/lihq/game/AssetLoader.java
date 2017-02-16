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
 * This class defines the assetLoader that the game uses.
 */
public class AssetLoader
{
    private AssetManager manager;

    /**
     * The 2 separate frames for the splash screen
     */
    public TextureAtlas splash;

    /**
     * These TextureRegions store the 4 different directions that the room changing
     * arrows can face.
     */
    public TextureAtlas arrowAtlas;

    /**
     *  Sprite sheets for abstract person objects
     */
    public TextureAtlas playerSpriteSheet;
    public ArrayMap<Integer, TextureAtlas> npcSpriteSheetMapArray;

    /**
     * map data
     */
    public Array<TiledMap> mapArray;

    /**
     * Texture for the RoomTag {@link RoomTag}
     */
    public Texture roomTagBorder;

    /**
     * Default roomTagFont for the game. Used in RoomTag
     */
    public BitmapFont roomTagFont;

    /**
     * Sprite sheet for the clue glint to be drawn where a clue is hidden
     */
    public TextureAtlas clueGlint;

    public Skin menuSkin;

    public JsonValue npcJsonData;
    public JsonValue playerJsonData;
    public JsonValue clueJsonData;


    public AssetLoader(){
        manager = new AssetManager();
        mapArray = new Array<>();
        npcSpriteSheetMapArray = new ArrayMap<>();
    }

    /**
     * Load assetLoader used for splash screen
     */
    public void loadSplashAssets(){
        manager.load("splash.pack",TextureAtlas.class);
    }

    public void assignSplashAssets(){
        splash = manager.get("splash.pack");
    }

    /**
     * Load assetLoader used for the game onto asset manager
     */
    public void loadGameAssets(){
        manager.load("arrows.pack", TextureAtlas.class);
        manager.load("clueGlint.pack", TextureAtlas.class);
        manager.load("roomTagBorder.png", Texture.class);

        manager.load("people/player/player.pack", TextureAtlas.class);
        manager.load("people/NPCs/colin.pack", TextureAtlas.class);
        manager.load("people/NPCs/diana.pack", TextureAtlas.class);
        manager.load("people/NPCs/lily.pack", TextureAtlas.class);
        manager.load("people/NPCs/mary.pack", TextureAtlas.class);
        manager.load("people/NPCs/mike.pack", TextureAtlas.class);
        manager.load("people/NPCs/will.pack", TextureAtlas.class);
        manager.load("people/NPCs/david.pack", TextureAtlas.class);
        manager.load("people/NPCs/julie.pack", TextureAtlas.class);
        manager.load("people/NPCs/sophie.pack", TextureAtlas.class);
        manager.load("people/NPCs/tom.pack", TextureAtlas.class);

        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("maps/computerRoom.tmx",TiledMap.class);
        manager.load("maps/island.tmx",TiledMap.class);
        manager.load("maps/kitchen.tmx",TiledMap.class);
        manager.load("maps/lakehouse.tmx",TiledMap.class);
        manager.load("maps/mainRoom.tmx",TiledMap.class);
        manager.load("maps/outside.tmx",TiledMap.class);
        manager.load("maps/pod.tmx",TiledMap.class);
        manager.load("maps/portersOffice.tmx",TiledMap.class);
        manager.load("maps/rch037.tmx",TiledMap.class);
        manager.load("maps/toilet.tmx",TiledMap.class);
    }

    /**
     * Assign all the game assetLoader that have been loaded by asset manager
     */
    public void assignGameAssets()
    {
        //roomTagFont init
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/VT323-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 50;
        roomTagFont = generator.generateFont(parameter);
        generator.dispose();

        playerJsonData = new JsonReader().parse(new FileHandle("player.json"));
        npcJsonData = new JsonReader().parse(new FileHandle("npc.json"));
        clueJsonData = new JsonReader().parse(new FileHandle("clue.json"));

        // sprite sheet assign
        playerSpriteSheet = manager.get("people/player/player.pack");

        //map key is the npc id
        npcSpriteSheetMapArray.put(1, manager.get("people/NPCs/colin.pack"));
        npcSpriteSheetMapArray.put(2, manager.get("people/NPCs/diana.pack"));
        npcSpriteSheetMapArray.put(3, manager.get("people/NPCs/lily.pack"));
        npcSpriteSheetMapArray.put(4, manager.get("people/NPCs/mary.pack"));
        npcSpriteSheetMapArray.put(5, manager.get("people/NPCs/mike.pack"));
        npcSpriteSheetMapArray.put(6, manager.get("people/NPCs/will.pack"));
        npcSpriteSheetMapArray.put(7, manager.get("people/NPCs/david.pack"));
        npcSpriteSheetMapArray.put(8, manager.get("people/NPCs/julie.pack"));
        npcSpriteSheetMapArray.put(9, manager.get("people/NPCs/sophie.pack"));
        npcSpriteSheetMapArray.put(10, manager.get("people/NPCs/tom.pack"));

        // map assign
        mapArray.add(manager.get("maps/computerRoom.tmx"));
        mapArray.add(manager.get("maps/island.tmx"));
        mapArray.add(manager.get("maps/kitchen.tmx"));
        mapArray.add(manager.get("maps/lakehouse.tmx"));
        mapArray.add(manager.get("maps/mainRoom.tmx"));
        mapArray.add(manager.get("maps/outside.tmx"));
        mapArray.add(manager.get("maps/pod.tmx"));
        mapArray.add(manager.get("maps/portersOffice.tmx"));
        mapArray.add(manager.get("maps/rch037.tmx"));
        mapArray.add(manager.get("maps/toilet.tmx"));

        //arrow texture assign
        arrowAtlas = manager.get("arrows.pack");

        //room tag border texture assign
        roomTagBorder = manager.get("roomTagBorder.png");

//        clueSheet = manager.get("clues.pack");

        //clue glint animation init
        clueGlint = manager.get("clueGlint.pack");


        //menu skin init
        initMenuSkin();
    }

    private void initMenuSkin()
    {
        menuSkin = new Skin();

        BitmapFont titleFont = getFontWithSize(70);
        menuSkin.add("title", titleFont);

        BitmapFont menuFont = getFontWithSize(60);
        menuSkin.add("default", menuFont);

        //Create a texture
        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB888);
        pixmap.setColor(Color.ORANGE);
        pixmap.fill();
        menuSkin.add("background", new Texture(pixmap));
        pixmap.dispose();

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = menuSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = menuSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = menuSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = menuSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = menuSkin.getFont("default");
        menuSkin.add("default", textButtonStyle);
    }

    /**
     * This method gets the default roomTagFont but at the requested size
     *
     * @param size - The size you want the roomTagFont to be
     * @return (BitmapFont) the resulting roomTagFont
     */
    public BitmapFont getFontWithSize(int size)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/VT323-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    public AssetManager getManager() {
        return manager;
    }

    public void dispose(){
        manager.dispose();
    }
}
