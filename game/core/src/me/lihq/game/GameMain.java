package me.lihq.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.FPSLogger;
import me.lihq.game.living.NPC;
import me.lihq.game.living.NPC.ACCESSORY;
import me.lihq.game.living.NPC.HAIR_COLOR;
import me.lihq.game.living.NPC.WRITING_HAND;
import me.lihq.game.models.Map;
import me.lihq.game.living.Player;
import me.lihq.game.models.Room;
import me.lihq.game.screen.NavigationScreen;

import java.util.*;


/**
 * This is the class responsible for the game as a whole. It manages the current states and entry points of the game
 */
public class GameMain extends Game
{
    //This is a static reference to itself. Comes in REALLY handy when in other classes that don't have a reference to the main game
    public static GameMain me = null;
    //Game wide variables

    /**
     * A list holding NPC objects
     */
    public List<NPC> NPCs = new ArrayList<NPC>();

    /**
     * The game map
     */
    public Map gameMap;

    /**
     * An FPSLogger, FPSLogger allows us to check the game FPS is good enough
     */
    FPSLogger FPS;

    /**
     * A screen to be used
     */
    private NavigationScreen screen1;

    /**
     * A player object
     */
    public Player player;

    /**
     * This is called at start up. It initialises the game.
     */
    @Override
    public void create()
    {
        this.me = this;

        gameMap = new Map(); //instantiate game map

        initialiseAllData(); //calls a function that generates all the NPC's, Players and Rooms and maps.
        
        Assets.load();// Load in the assets the game needs

        initialiseAllData();//call the function again after assets loaded
        //place player in first room
        player.setRoom(gameMap.getRoom(0));
        //set up the screen and display the first room
        screen1 = new NavigationScreen(this);
        screen1.setTiledMapRenderer(player.getRoom().getTiledMap());
        this.setScreen(screen1);
        //Instantiate the FPSLogger to show FPS
        FPS = new FPSLogger();

        gameLoop();
    }

    /**
     * This defines what's rendered on the screen for each frame.
     */
    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FPS.log();//this is where fps is displayed

        super.render(); // This calls the render method of the screen that is currently set

    }

    @Override
    public void dispose()
    {

    }
    /**
     * Change from one room to another
     */
    public void changeMap(Room.Transition to)
    {
        player.setRoom(gameMap.getRoom(to.newRoom));

        screen1.setTiledMapRenderer(player.getRoom().getTiledMap());
    }

    public int ticks = 0;
    public int lastSecond = -1;

    public void gameLoop()
    {
        Timer gameTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                ticks ++;

                Calendar cal = Calendar.getInstance();

                if (cal.get(Calendar.SECOND) != lastSecond)
                {
                    lastSecond = cal.get(Calendar.SECOND);
                    System.out.println("TPSLogger: tps:      " + ticks);
                    ticks = 0;
                }

                screen1.playerController.update();
                player.update();
            }
        };

        gameTimer.schedule(task, 0, 1000 / Settings.TPS);
    }

    /**
     * Generates all the NPC's, Players and Rooms and maps.
     */
    public void initialiseAllData()
    {
        //Add ALL NPCs to the list
        //This is how you initialise an NPC
        player = new Player("Test name","player.png");

        {
            //TODO: Add NPC assets
            NPC npc = new NPC(4, 4, 1, "player.png", true)
                    .setCharacterName("Mr Detective 1")
                    .setAccessory(ACCESSORY.WATCH)
                    .setHairColor(HAIR_COLOR.GINGER)
                    .setHasGlasses(false)
                    .setWritingHand(WRITING_HAND.RIGHT)
                    .setShoeSize(9)
                    .setHasLipstick(false);

            NPCs.add(npc);
        }

        {
            NPC npc = new NPC(4, 4, 2, "player.png", true)
                    .setCharacterName("Mrs Detective 2")
                    .setAccessory(ACCESSORY.HANDBAG)
                    .setHairColor(HAIR_COLOR.BLACK)
                    .setHasGlasses(true)
                    .setWritingHand(WRITING_HAND.RIGHT)
                    .setShoeSize(6)
                    .setHasLipstick(true);

            NPCs.add(npc);
        }
    }
}
