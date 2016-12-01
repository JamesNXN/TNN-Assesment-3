package me.lihq.game.living;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import me.lihq.game.Assets;
import me.lihq.game.Settings;
import me.lihq.game.models.Vector2Int;

/**
 * The abstract person is an abstract representation of a person. A person can be a non playable character or Player.
 * It extends the sprite class which provides methods for the person to be rendered in the game.
 */
public abstract class AbstractPerson extends Sprite
{
    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     */
    protected Vector2Int tileCoordinates = new Vector2Int(0, 0);

    protected Vector2Int startPosition = new Vector2Int(0,0);
    protected Vector2Int destinationPosition = new Vector2Int(0,0);

    protected float animTimer;
    protected float ANIM_TIME = Settings.TPS / 4;

    protected ACTOR_STATE state;
    protected boolean left, right, up, down;

    protected Texture spriteSheet;
    protected TextureRegion currentRegion;

    /**
     * The direction determines the way the character is facing.
     */
    protected DIRECTION direction = DIRECTION.EAST;

    /**
     * This constructs the player calling super on the sprite class
     *
     * @param img this a path to the image
     */
    public AbstractPerson(String img)
    {
        super(new TextureRegion(Assets.loadTexture(img), 0, 0, 32, 37));

        this.spriteSheet = Assets.loadTexture(img);
        this.currentRegion = new TextureRegion(Assets.loadTexture(img), 0, 0, 32, 37);

        this.setPosition(tileCoordinates.getX() * Settings.TILE_SIZE, tileCoordinates.getY() * Settings.TILE_SIZE);
        this.state = ACTOR_STATE.STANDING;
    }

    public void setTileCoordinates(int x, int y)
    {
        tileCoordinates.x = x;
        tileCoordinates.y = y;

        setPosition(x*Settings.TILE_SIZE,y*Settings.TILE_SIZE);
    }

    public void updateMotion()
    {
        if (this.state == ACTOR_STATE.WALKING) {
            this.setPosition(Interpolation.linear.apply(startPosition.x, destinationPosition.x, animTimer / ANIM_TIME), Interpolation.linear.apply(startPosition.y, destinationPosition.y, animTimer / ANIM_TIME));
            updateTextureRegion();
            if (animTimer > ANIM_TIME) {
                this.finishMove();
                this.setTileCoordinates(destinationPosition.x/32, destinationPosition.y/32);
            }
        }
    }

    public void initialiseMove(DIRECTION dir) {

        this.direction = dir;

        this.startPosition.x = this.tileCoordinates.x * Settings.TILE_SIZE;
        this.startPosition.y = this.tileCoordinates.y * Settings.TILE_SIZE;

        this.destinationPosition.x = this.startPosition.x + (dir.getDx() * Settings.TILE_SIZE);
        this.destinationPosition.y = this.startPosition.y + (dir.getDy() * Settings.TILE_SIZE);

        this.state = ACTOR_STATE.WALKING;
    }

    public void finishMove() {
        animTimer = 0f;
        this.state = ACTOR_STATE.STANDING;
        updateTextureRegion();
    }

    public void updateTextureRegion()
    {
        TextureRegion region = currentRegion;

        float quart = ANIM_TIME / 4;
        float half = ANIM_TIME / 2;
        float thir = quart * 3;

        int row = 1;

        switch (direction)
        {
             case NORTH:
                 row = 3;
                 break;
             case EAST:
                 row = 2;
                 break;
             case SOUTH:
                 row = 0;
                 break;
             case WEST:
                 row = 1;
                 break;
        }

        if (animTimer == 0)
        {
            setRegion(new TextureRegion(spriteSheet, 0, row * 37, 32, 37));
        }
        else if (animTimer < quart)
        {
            setRegion(new TextureRegion(spriteSheet, 32, row * 37, 32, 37));
        }
        else if (animTimer < half)
        {
            setRegion(new TextureRegion(spriteSheet, 0, row * 37, 32, 37));
        }
        else if (animTimer < thir)
        {
            setRegion(new TextureRegion(spriteSheet, 64, row * 37, 32, 37));
        }
    }
    
    public DIRECTION getDirection()
    {
        return this.direction;
    }

    public void setDirection(DIRECTION direction)
    {
        this.direction = direction;
    }

    public enum DIRECTION
    {
        NORTH(0,1),
        SOUTH(0,-1),
        EAST(1,0),
        WEST(-1,0);

        private int dx, dy;

        DIRECTION(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return this.dx;
        }

        public int getDy() {
            return this.dy;
        }


    }

    public enum ACTOR_STATE {
        WALKING,
        STANDING;
    }
}
