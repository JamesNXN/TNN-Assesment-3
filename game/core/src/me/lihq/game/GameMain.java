package me.lihq.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.FPSLogger;
import me.lihq.game.living.NPC;
import me.lihq.game.living.NPC.ACCESSORY;
import me.lihq.game.living.NPC.HAIR_COLOR;
import me.lihq.game.living.NPC.WRITING_HAND;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.screen.NavigationScreen;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the class responsible for the game as a whole. It manages the current states and entry points of the game
 */
public class GameMain extends Game
{
    //This is a static reference to itself. Comes in REALLY handy when in other classes that don't have a reference to the main game
    public static GameMain me = null;
    //Game wide variables
    public List<NPC> NPCs = new ArrayList<NPC>();

    public Map gameMap;

    FPSLogger FPS;
    private NavigationScreen screen1;

    @Override
    /**
     * This is called at start up. It initialises the game.
     */
    public void create()
    {
        gameMap = new Map();

        initialiseAllData();

        screen1 = new NavigationScreen(this);

        me = this;

        this.setScreen(screen1);

        FPS = new FPSLogger();
    }

    @Override
    /**
     * This defines what's rendered on the screen for each frame.
     */
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FPS.log();

        super.render(); // This calls the render method of the screen that is currently set

    }

    @Override
    public void dispose()
    {

    }


    /**
     * Generates all the NPC's, Players and Rooms and maps.
     */
    public void initialiseAllData()
    {
        //Add ALL NPCs to the list
        //This is how you initialise an NPC
        {
            NPC npc = new NPC(4, 4, 1, "charName.png", true)
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
            NPC npc = new NPC(4, 4, 2, "charName2.png", true)
                    .setCharacterName("Mrs Detective 2")
                    .setAccessory(ACCESSORY.HANDBAG)
                    .setHairColor(HAIR_COLOR.BLACK)
                    .setHasGlasses(true)
                    .setWritingHand(WRITING_HAND.RIGHT)
                    .setShoeSize(6)
                    .setHasLipstick(true);

            NPCs.add(npc);
        }

        //Add ALL maps to the map list
    }
}
