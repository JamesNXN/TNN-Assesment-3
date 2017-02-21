package me.lihq.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import me.lihq.game.people.Npc;
import me.lihq.game.people.Player;

/**
 * NEW
 * Controls camera in the game world that follows player.
 */

public class CameraManager{
    private enum CameraMode {FOLLOW, INTERACTION}
    private CameraMode mode;

    /** the higher the values, the faster the camera follow*/
    private final float MOVE_LERP_FACTOR = 0.2f;
    private final float ZOOM_LERP_FACTOR = 0.4f;

    /** the higher the value, the more significant the zoom will be*/
    private final float ZOOM_FACTOR = 0.5f;

    private OrthographicCamera camera;
    private Player player;

    /**camera position that the camera centre needs to move towards.*/
    private Vector2 targetCameraPosition;

    private float defaultZoom;

    /**camera zoom that the camera needs to zoom in/out to*/
    private float targetCameraZoom;

    /**if lerp move is enabled */
    private boolean isLerp = true;

    public CameraManager(OrthographicCamera camera, Player player){
        this.camera = camera;
        this.player = player;

        mode = CameraMode.FOLLOW;

        targetCameraPosition = new Vector2(player.getX() + player.getWidth()/2, player.getY());
        defaultZoom = camera.zoom;
        targetCameraZoom = defaultZoom;
    }

    public void startInteractionMode(Npc interactingNpc){
        mode = CameraMode.INTERACTION;

        targetCameraPosition.set((player.getX() + interactingNpc.getRight())/2, (player.getTop() + interactingNpc.getY())/2);
        targetCameraZoom = defaultZoom - ZOOM_FACTOR;
        camera.update();
    }

    public void haltInteractionMode(){
        mode = CameraMode.FOLLOW;

        targetCameraPosition.set(player.getDefaultCameraFocusX(), player.getDefaultCameraFocusY());
        targetCameraZoom = defaultZoom;
        camera.update();
    }

    public boolean isLerp() {
        return isLerp;
    }

    public void setLerp(boolean lerp) {
        isLerp = lerp;
    }

    public void update() {
        if (mode == CameraMode.FOLLOW){
            targetCameraPosition.x = player.getDefaultCameraFocusX();
            targetCameraPosition.y = player.getDefaultCameraFocusY();
        }

        //camera move lerp effect
        if (isLerp) {
            camera.position.x = camera.position.x + (targetCameraPosition.x - camera.position.x) * MOVE_LERP_FACTOR;
            camera.position.y = camera.position.y + (targetCameraPosition.y - camera.position.y) * MOVE_LERP_FACTOR;
            camera.zoom = camera.zoom + (targetCameraZoom - camera.zoom) * ZOOM_LERP_FACTOR;
            camera.update();
        }
        else{
            camera.position.x = targetCameraPosition.x;
            camera.position.y = targetCameraPosition.y;
            camera.zoom = targetCameraZoom;
            camera.update();
        }
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
