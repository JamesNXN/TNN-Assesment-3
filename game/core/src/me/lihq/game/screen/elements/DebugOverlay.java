package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

/**
 * This class allows you to easily debug issues with the map
 */
public class DebugOverlay
{

    private static Sprite greenSprite = getColoredTileSprite(Color.GREEN);

    private static Sprite redSprite = getColoredTileSprite(Color.RED);

    private static Sprite yellowSprite = getColoredTileSprite(Color.GOLD);

    private static BitmapFont font = Assets.getFontWithSize(30);

    public static void renderDebugInfo(Batch batch)
    {
        Sprite border = getColoredTileSprite(Color.BLACK);
        border.setPosition(Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight() - 190);
        border.setSize(390, 180);
        border.draw(batch);

        font.draw(batch, "====== DEBUG MODE ====== \n" +
                "Press H to toggle showHideable\n" +
                "Press J to Toggle showWalkable ", Gdx.graphics.getWidth() - 360, Gdx.graphics.getHeight() - 50);
    }

    public static void renderDebugTiles(Room room, Batch batch) {
         /*
         Draw a filter over showing whether or not a tile is "walkable"
         */

        int roomWidth = ((TiledMapTileLayer) room.getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) room.getTiledMap().getLayers().get(0)).getHeight();

        for (int w = 0; w < roomWidth; w++) {
            for (int h = 0; h < roomHeight; h++) {
                if (Settings.DEBUG_OPTIONS.get("showWalkable")) {
                    if (GameMain.me.player.getRoom().isWalkableTile(w, h)) {
                        greenSprite.setPosition(w * Settings.TILE_SIZE, h * Settings.TILE_SIZE);
                        greenSprite.draw(batch);
                    } else {
                        redSprite.setPosition(w * Settings.TILE_SIZE, h * Settings.TILE_SIZE);
                        redSprite.draw(batch);
                    }
                }
            }
        }

        for (Vector2Int c : room.hidingSpots)
        {
            yellowSprite.setPosition(c.x * Settings.TILE_SIZE, c.y * Settings.TILE_SIZE);
            yellowSprite.draw(batch);
        }
    }


    public static Sprite getColoredTileSprite(Color color)
    {
        Pixmap map = new Pixmap(Settings.TILE_SIZE, Settings.TILE_SIZE, Pixmap.Format.RGBA8888);
        map.setColor(color);
        map.fill();
        Sprite sprite = new Sprite(new Texture(map));
        sprite.setAlpha(0.4f);

        return sprite;
    }

}
