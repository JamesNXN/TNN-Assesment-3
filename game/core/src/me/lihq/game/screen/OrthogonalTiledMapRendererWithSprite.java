package me.lihq.game.screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by joeshuff on 14/12/2016.
 */
public class OrthogonalTiledMapRendererWithSprite extends OrthogonalTiledMapRenderer {

    public List<Sprite> sprites;

    public OrthogonalTiledMapRendererWithSprite(TiledMap map)
    {
        super(map);

        sprites = new ArrayList<Sprite>();
    }

    public void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
    }

    @Override
    public void render() {
        beginRender();

        int amountOfLayers = map.getLayers().getCount();

        for (int currentLayer = 0 ; currentLayer < amountOfLayers; currentLayer ++)
        {
            MapLayer layer = map.getLayers().get(currentLayer);

            renderTileLayer((TiledMapTileLayer) layer);

            if (currentLayer == amountOfLayers - 2 || amountOfLayers == 1)
            {
                for (Sprite s : sprites)
                {
                    s.draw(this.getBatch());
                }
            }
        }

        endRender();
    }
}
