package me.lihq.game.people;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.Collidable;
import me.lihq.game.GameWorld;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Door;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.RoomArrow;
import me.lihq.game.models.Score;

/**
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson
{
    private GameWorld gameWorld;

    /**
     * This object stores the clues and hints the player has collected and the npc's they have spoken to.
     */
    private Inventory inventory = new Inventory();
    /**
     * This stores whether the player is in the middle of a conversation or not
     */
    private boolean isInConversation = false;

    /**
     * The personality will be a percent score (0-100) 0 being angry, 50 being neutral, and 100 being happy/nice.
     */
    private int personalityLevel;

    private Rectangle interactionCollisionBox;

    /**
     * This is the constructor for player, it creates a new playable person
     *
     * @param jsonData   - The json data for the new player.
     * @param spriteSheet - The image used to represent it.
     */

    public Player(JsonValue jsonData, TextureAtlas spriteSheet, GameWorld gameWorld) {
        super(jsonData, spriteSheet);

        this.gameWorld = gameWorld;
        interactionCollisionBox = new Rectangle();
        interactionCollisionBox.setSize(collisionBox.getWidth(), collisionBox.getHeight());
    }

    /**
     * constructor for testing
     */

    public Player(JsonValue jsonData){
        super(jsonData);

        interactionCollisionBox = new Rectangle();
        interactionCollisionBox.setSize(collisionBox.getWidth(), collisionBox.getHeight());
    }

    /**
     * This method will change the players personality by the given amount.
     * It will cap the personality between 0 and 100.
     * <p>
     * If the change takes it out of these bounds, it will change it to the min or max.
     *
     * @param change - The amount to change by, can be positive or negative
     */
    public void addToPersonality(int change)
    {
        personalityLevel = personalityLevel + change;

        if (personalityLevel < 0) {
            personalityLevel = 0;
        } else if (personalityLevel > 100) {
            personalityLevel = 100;
        }
    }

    /**
     * This method is called when the player interacts with the map
     */
    public void interact()
    {
        interactionCollisionBox.setPosition(collisionBox.getX() + collisionBox.getWidth() * direction.getDx(),
                collisionBox.getY() + collisionBox.getHeight() * direction.getDy());

        System.out.println(tilePosition);


        Collidable interactingActor = null;

        Array<Collidable> roomObjects = new Array<>();
        roomObjects.addAll(getCurrentRoom().getNpcArray());
        roomObjects.addAll(getCurrentRoom().getClueArray());

        for (Collidable actor : roomObjects){
            if (interactionCollisionBox.overlaps(actor.getCollisionBox())){
                interactingActor = actor;
                break;
            }
        }

        if (interactingActor instanceof NPC) {
            if (!this.inventory.getMetCharacters().contains((NPC)interactingActor, true)) {
                this.inventory.addCharacter((NPC)interactingActor);
                System.out.println(this.inventory.getMetCharacters());
            }
            System.out.println(((NPC)interactingActor).getName());
        }
        else if(interactingActor instanceof Clue) {
            Clue foundClue = (Clue) interactingActor;
            if (!foundClue.isFound()){
                foundClue.setFound(true);
                inventory.addClue(foundClue);

                gameWorld.getGui().displayInfo(foundClue.getDescription());
                System.out.println(((Clue)interactingActor).getName());
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        RoomArrow arrow = roomArrowCollisionDetection(collisionBox);
        if (arrow != null){
            arrow.setVisible(true);
        }

        //prevents colliding with doors multiple times during transition
        if (isCanMove()) {
            Door collidingExit = doorCollisionDetection(collisionBox);
            if (collidingExit != null) {
                setCanMove(false);
                gameWorld.changeRoom(collidingExit.getConnectedRoomId());
            }
        }
    }

    private RoomArrow roomArrowCollisionDetection(Rectangle collisionBox){
        Array<RoomArrow> arrowArray = getCurrentRoom().getRoomArrowArray();

        for (RoomArrow arrow : arrowArray){
            if (collisionBox.overlaps(arrow.getCollisionBox())){
                return arrow;
            }
            arrow.setVisible(false);
        }
        return null;
    }

    /**
     * Detects collision with door
     * @return colliding door
     */
    private Door doorCollisionDetection(Rectangle collisionBox){
        Array<Door> doorArray = getCurrentRoom().getExitArray();

        for (Door door : doorArray){
            if (collisionBox.overlaps(door.getCollisionBox())){
                return door;
            }
        }
        return null;
    }

    /**
     * Getter for personality, it uses the personalityLevel of the player and thus returns either AGGRESSIVE, NEUTRAL or NICE
     *
     * @return - (Personality) Returns the personality of this player.
     */
    @Override
    public Personality getPersonality()
    {
        if (Personality.NICE.isInRange(personalityLevel)){
            return Personality.NICE;
        }
        else if (Personality.NEUTRAL.isInRange(personalityLevel)){
            return Personality.NEUTRAL;
        }
        else{
            return Personality.AGGRESSIVE;
        }
    }

    /**
     * This gets the players personality level; this similar to Personality but a integer representation
     *
     * @return (int) value between 0-100
     */
    public int getPersonalityLevel()
    {
        return this.personalityLevel;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
