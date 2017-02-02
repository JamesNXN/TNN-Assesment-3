package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * This class defines the assets that the game uses.
 */
public class Assets
{
    private AssetManager manager;
    /**
     * This is the asset sheet for clues
     */
    public Texture clueSheet;

    /**
     * The 2 separate frames for the splash screen
     */
    public TextureAtlas splash;

    /**
     * These TextureRegions store the 4 different directions that the room changing
     * arrows can face.
     */
    private TextureRegion upArrow;
    private TextureRegion downArrow;
    private TextureRegion leftArrow;
    private TextureRegion rightArrow;

    /**
     *  Sprite sheets for abstract person objects
     */
    public TextureAtlas playerSpriteSheet;
    public TextureAtlas colinSpriteSheet;
    public TextureAtlas dianaSpriteSheet;
    public TextureAtlas lilySpriteSheet;
    public TextureAtlas marySpriteSheet;
    public TextureAtlas mikeSpriteSheet;
    public TextureAtlas willSpriteSheet;

    /**
     * map data
     */

    public TiledMap mainRoomMap;
    public TiledMap rch037Map;
    public TiledMap portersOfficeMap;
    public TiledMap kitchenMap;
    public TiledMap islandMap;
    public TiledMap toiletMap;
    public TiledMap computerRoomMap;
    public TiledMap lakehouseMap;
    public TiledMap outsideMap;
    public TiledMap podMap;

    /**
     * Texture for the RoomTag {@link me.lihq.game.screen.elements.RoomTag}
     */
    public Texture roomTagBorder;

    /**
     * Default roomTagFont for the game. Used in RoomTag
     */
    public BitmapFont roomTagFont;

    /**
     * Animation for the clue glint to be drawn where a clue is hidden
     */
    public Animation<TextureRegion> clueGlint;

    public Skin menuSkin;


    public Assets(){
        manager = new AssetManager();
    }

    /**
     * Load assets used for splash screen
     */
    public void loadSplashAssets(){
        manager.load("splash.pack",TextureAtlas.class);
    }

    public void assignSplashAssets(){
        splash = manager.get("splash.pack");
    }

    /**
     * Load assets used for the game onto asset manager
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


        manager.load("clueSheet.png", Texture.class);
    }

    /**
     * Assign all the game assets that have been loaded by asset manager
     */
    public void assignGameAssets()
    {
        //roomTagFont init
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/VT323-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 45;
        roomTagFont = generator.generateFont(parameter);
        generator.dispose();

        // sprite sheet assign
        playerSpriteSheet = manager.get("people/player/player.pack");
        colinSpriteSheet = manager.get("people/NPCs/colin.pack");
        dianaSpriteSheet = manager.get("people/NPCs/diana.pack");
        lilySpriteSheet = manager.get("people/NPCs/lily.pack");
        marySpriteSheet = manager.get("people/NPCs/mary.pack");
        mikeSpriteSheet = manager.get("people/NPCs/mike.pack");
        willSpriteSheet = manager.get("people/NPCs/will.pack");

        // map assign
        computerRoomMap = manager.get("maps/computerRoom.tmx");
        islandMap = manager.get("maps/island.tmx");
        kitchenMap = manager.get("maps/kitchen.tmx");
        lakehouseMap = manager.get("maps/lakehouse.tmx");
        mainRoomMap = manager.get("maps/mainRoom.tmx");
        outsideMap = manager.get("maps/outside.tmx");
        podMap = manager.get("maps/pod.tmx");
        portersOfficeMap = manager.get("maps/portersOffice.tmx");
        rch037Map = manager.get("maps/rch037.tmx");
        toiletMap = manager.get("maps/toilet.tmx");

        //arrow texture assign
        TextureAtlas arrows = manager.get("arrows.pack");
        rightArrow = new TextureRegion(arrows.findRegion("rightArrow"));
        upArrow = new TextureRegion(arrows.findRegion("upArrow"));

        leftArrow = new TextureRegion(rightArrow);
        leftArrow.flip(true, false);

        downArrow = new TextureRegion(upArrow);
        downArrow.flip(false, true);

        // clue sheet assign
        clueSheet = manager.get("clueSheet.png");

        //room tag border texture assign
        roomTagBorder = manager.get("roomTagBorder.png");

//        clueSheet = manager.get("clues.pack");

        //clue glint animation init
        TextureAtlas glintAtlas = manager.get("clueGlint.pack");

        clueGlint = new Animation<>(0.1f, glintAtlas.findRegions("glint"));


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
     * This method takes a direction and returns the corresponding arrow asset for that direction
     *
     * @param direction - The direction to fetch
     * @return (TextureRegion) the corresponding TextureRegion
     */
    public TextureRegion getArrowDirection(String direction)
    {
        if (direction.equals("NORTH")) {
            return upArrow;
        } else if (direction.equals("SOUTH")) {
            return downArrow;
        } else if (direction.equals("WEST")) {
            return leftArrow;
        } else if (direction.equals("EAST")) {
            return rightArrow;
        }

        return null;
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
}
