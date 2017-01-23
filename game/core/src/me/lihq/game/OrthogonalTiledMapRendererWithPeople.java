package me.lihq.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.AbstractPerson.PersonPositionComparator;
import me.lihq.game.screen.elements.DebugOverlay;

import java.util.*;

/**
 * OrthogonalTiledMapRendererWithPeople
 * <p>
 * This class is an extension of the OrthogonalTiledMapRenderer that deals with
 * rendering sprites aswell. The last layer of the map is designed to be drawn OVER
 * the player sprite and NPCs. So this controls that by drawing each layer until it comes to the last
 * one, then it draws the sprites, then the final layer.
 */
public class OrthogonalTiledMapRendererWithPeople extends OrthogonalTiledMapRenderer
{
    /**
     * This is the list of people that are to be rendered to the map
     */
    public List<AbstractPerson> people;

    /**
     * Constructor for the map renderer
     *
     * @param map - The TiledMap that is to be rendered using this renderer
     */
    public OrthogonalTiledMapRendererWithPeople(TiledMap map)
    {
        super(map);

        people = new ArrayList<>();
    }

    /**
     * This adds sprite to the list of sprites to be rendered before the final layer.
     *
     * @param sprite - Sprite to be added
     */
    public void addPerson(AbstractPerson sprite)
    {
        people.add(sprite);
    }

    /**
     * This adds a list of AbstractPerson to the sprite list
     *
     * @param sprites
     */
    public void addPerson(List<AbstractPerson> sprites)
    {
        for (AbstractPerson a : sprites) {
            people.add(a);
        }
    }

    /**
     * This method clears the list of people to be rendered
     */
    public void clearPeople()
    {
        people.clear();
    }

    /**
     * This overrides the render method in the super class.
     * <p>
     * It draws all the map layers until the final one. Then it draws all the sprites in the
     * sprite list, then it draws the final layer.
     */
    @Override
    public void render()
    {
        beginRender();

        people.sort(new PersonPositionComparator());

        int amountOfLayers = map.getLayers().getCount();

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            MapLayer layer = map.getLayers().get(currentLayer);

            if (layer.getName().equals("Blood") && !GameMain.me.player.getRoom().isMurderRoom()) {
                //Don't draw the layer as its not the murder room
            } else {
                renderTileLayer((TiledMapTileLayer) layer);
            }

            if (currentLayer == amountOfLayers - 2 || amountOfLayers == 1) {

                for (AbstractPerson s : people) {
                    s.draw(this.getBatch());
                }
            }
        }

        if (Settings.DEBUG) {
            DebugOverlay.renderDebugTiles(GameMain.me.player.getRoom(), this.getBatch());
        }

        endRender();
    }
}
