package me.lihq.game;

import me.lihq.game.models.Vector2Int;

public interface TileObject {
    /**
     * Setter for clue tile coordinates.
     *
     * @param x - The x coordinate for where the object is, in terms of tiles.
     * @param y - The y coordinate for where the object is, in terms of tiles.
     *          <p>
     *          all coordinates relative to bottom left of the map
     */

    void setTilePosition(int x, int y);

    /**
     * This method gets the object's current tile location on the map as a Vector2Int
     *
     * @return (Vector2Int) The tile coordinates of the clue
     */
    Vector2Int getTilePosition();

}
