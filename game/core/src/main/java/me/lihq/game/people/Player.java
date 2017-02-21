package me.lihq.game.people;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.Collidable;
import me.lihq.game.GameWorld;
import me.lihq.game.Interaction;
import me.lihq.game.PersonalityMeter;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Door;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.RoomArrow;

/**
 * EXTENDED
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson {

    /**
     * Parameters needed for player:
     *
     * gameWorld - contains a reference to the game world object
     * dialogue - contains the players dialogue information
     * inventory - This object stores the clues and hints the player has collected and the npc's they have spoken to.
     * personalityLevel - The personality will be a percent score (0-100) 0 being angry, 50 being neutral, and 100 being happy/nice.
     * interactionCollisionBox - a collision box used to calculate whether or not an interaction should occur.
     */

    private GameWorld gameWorld;

    private PlayerDialogue dialogue;

    private Inventory inventory = new Inventory();

    private PersonalityMeter personalityMeter;

    private Rectangle interactionCollisionBox;

    /**
     * This is the constructor for player, it creates a new playable person
     *
     * @param jsonData   - The json data for the new player.
     * @param spriteSheet - The image used to represent it.
     */

    public Player(JsonValue jsonData, TextureAtlas spriteSheet) {
        super(jsonData, spriteSheet);

        dialogue = new PlayerDialogue(this);

        personalityMeter = new PersonalityMeter(jsonData.getInt("personalityLevel"));
        interactionCollisionBox = new Rectangle();
        interactionCollisionBox.setSize(collisionBox.getWidth(), collisionBox.getHeight());
    }

    /**
     * This method sets the game world pointer
     * @param gameWorld - game world object
     */
    public void setGameWorld(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    /**
     * This method is called when the player interacts with the map
     */
    public void interact()
    {
        //interaction collision box needs to be positioned in front of the player
        interactionCollisionBox.setPosition(collisionBox.getX() + collisionBox.getWidth() * direction.getDx(),
                collisionBox.getY() + collisionBox.getHeight() * direction.getDy());

        Collidable interactingActor = null;

        // all of the possible interactable game objects
        Array<Collidable> roomObjects = new Array<>();
        roomObjects.addAll(getCurrentRoom().getNpcArray());
        roomObjects.addAll(getCurrentRoom().getClueArray());

        for (Collidable actor : roomObjects){
            if (interactionCollisionBox.overlaps(actor.getCollisionBox())){
                interactingActor = actor;
                break;
            }
        }

        // if the object colliding with the interaction collision box is an npc, start an interaction
        if (interactingActor instanceof Npc) {
            gameWorld.startInteraction((Npc) interactingActor);
            if (!this.inventory.getMetCharacters().contains((Npc)interactingActor, true)) {
                this.inventory.addCharacter((Npc)interactingActor);
                System.out.println(this.inventory.getMetCharacters());
            }
            System.out.println(((Npc)interactingActor).getName());
        }

        // if it is a clue, add the clue to the inventory
        else if(interactingActor instanceof Clue) {
            Clue foundClue = (Clue) interactingActor;
            if (foundClue.isVisible()){
                foundClue.setVisible(false);
                inventory.addClue(foundClue);

                gameWorld.getGui().displayInfo(foundClue.getDescription());
                System.out.println(((Clue)interactingActor).getName());
            }
        }
    }


    /**
     * carry out additional collision detection for the player; room arrow and door for room transition
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        RoomArrow arrow = roomArrowCollisionDetection(collisionBox);
        if (arrow != null){
            arrow.setVisible(true);
        }

        if (isCanMove()) {
            Door collidingExit = doorCollisionDetection(collisionBox);
            if (collidingExit != null) {
                //prevents colliding with doors multiple times during transition
                setCanMove(false);
                gameWorld.changeRoom(collidingExit.getConnectedRoomId());
            }
        }
    }

    /**
     * This method detects collisions between the players collision box
     * and the room arrow objects
     * @param collisionBox - players collision box
     * @return returns an arrow object if a collision is detected
     */
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
     * Getter for player dialogue
     * @return - returns the players dialogue
     */
    @Override
    public PlayerDialogue getDialogue() {
        return dialogue;
    }

    /**
     * Getter for personality, it uses the personalityLevel of the player and thus returns either AGGRESSIVE, NEUTRAL or NICE
     *
     * @return - (Personality) Returns the personality of this player.
     */
    @Override
    public Personality getPersonality()
    {
        if (Personality.NICE.isInRange(personalityMeter.getMeter())){
            return Personality.NICE;
        }
        else if (Personality.NEUTRAL.isInRange(personalityMeter.getMeter())){
            return Personality.NEUTRAL;
        }
        else{
            return Personality.AGGRESSIVE;
        }
    }

    /**
     * Getter for the players inventory
     * @return returns inventory object
     */
    public Inventory getInventory() {
        return inventory;
    }

    public PersonalityMeter getPersonalityMeter() {
        return personalityMeter;
    }

    /**
     * Returns the points that the camera will be centred. Change the values to change the focus points.
     * @return camera focus origin coordinate
     */
    public float getDefaultCameraFocusX(){
        return getX() + getWidth()/2;
    }

    public float getDefaultCameraFocusY(){
        return getY();
    }
}
