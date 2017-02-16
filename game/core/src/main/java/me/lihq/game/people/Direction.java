package me.lihq.game.people;

/**
 * This is used to describe the direction the person is currently facing or moving in.
 * <li>{@link #NORTH}</li>
 * <li>{@link #SOUTH}</li>
 * <li>{@link #EAST}</li>
 * <li>{@link #WEST}</li>
 */

public enum Direction
{
    /**
     * person is facing north
     */
    NORTH(0, 1),

    /**
     * person is facing south
     */
    SOUTH(0, -1),

    /**
     * person is facing east
     */
    EAST(1, 0),

    /**
     * person is facing west
     */
    WEST(-1, 0);

    private int dx, dy;

    Direction(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx()
    {
        return this.dx;
    }

    public int getDy()
    {
        return this.dy;
    }

    /**
     * This method takes the current direction and gets the opposite direction
     *
     * @return (Direction) the opposite direction to this
     */
    public Direction getOpposite()
    {
        if (this == Direction.NORTH) return Direction.SOUTH;
        if (this == Direction.EAST) return Direction.WEST;
        if (this == Direction.SOUTH) return Direction.NORTH;
        if (this == Direction.WEST) return Direction.EAST;

        return null;
    }
}
