package me.lihq.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.List;

import me.lihq.game.models.Room;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.screen.elements.DebugOverlay;

/**
 * TiledMapRenderer
 * <p>
 * This class is an extension of the OrthogonalTiledMapRenderer that deals with
 * rendering sprites as well. The last layer of the map is designed to be drawn OVER
 * the player sprite and NPCs. So this controls that by drawing each layer until it comes to the last
 * one, then it draws the sprites, then the final layer.
 */
public class TiledMapRenderer extends OrthogonalTiledMapRenderer
{
    private Room renderingRoom;

    /**
     * Constructor for the map renderer
     *
     * @param room - The room that is to be rendered using this renderer
     */
    public TiledMapRenderer(Room room, SpriteBatch spriteBatch)
    {
        super(room.getTiledMap(), spriteBatch);

        renderingRoom = room;
    }

    /**
     * This overrides the render method in the super class.
     * <p>
     * It draws all the map layers until the final one. The last layer will be drawn after
     * stage is drawn
     */
    @Override
    public void render()
    {
        beginRender();

        int amountOfLayers = map.getLayers().getCount();

        for (int layerIndex = 0; layerIndex < amountOfLayers; layerIndex++) {
            MapLayer layer = map.getLayers().get(layerIndex);

            if (layer.getName().equals("Blood") && !renderingRoom.isMurderRoom()) {
                //Don't draw the layer as its not the murder room
            }
            else if(layer.getName().equals("Over Player")) {
                //Don't draw the layer that has to be drawn after the characters are drawn
            }
            else if(layer.getName().equals("Transition")){
                //Transition layer only contains data
            }
            else if(layer.getName().equals("Collision") || layer.getName().equals("HidingSpot")){
                //collision layer and hiding spot layers are not rendered
            }
            else{
                renderTileLayer((TiledMapTileLayer) layer);
            }
        }

        if (Settings.DEBUG) {
            DebugOverlay.renderDebugTiles(renderingRoom, this.getBatch());
        }

        endRender();
    }

    /**
     * render final layer
     */
    public void renderLastLayer(){
        beginRender();

        MapLayer layer = map.getLayers().get("Over Player");
        renderTileLayer((TiledMapTileLayer) layer);

        endRender();
    }
}
