package me.lihq.game.models;

import me.lihq.game.GameTester;
import me.lihq.game.people.Direction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DoorUnitTest extends GameTester {

    private Door testDoor;
    private Vector2Int testVec;


    @Before
    public void setUp() throws Exception {
        testDoor = new Door(Direction.EAST, 1, new Vector2Int(0,2));
        testVec = new Vector2Int();   //// (0,0) by default
    }

    @After
    public void tearDown() throws Exception {
        testDoor = null;
        testVec = null;
    }


    @Test
    public void getCollisionBox() throws Exception {
        Assert.assertNotNull(testDoor.getCollisionBox());
    }

    @Test
    public void getTilePosition() throws Exception {
        Assert.assertNotNull(testDoor.getTilePosition());
        Assert.assertNotEquals(testDoor.getTilePosition(), testVec);
    }

    @Test
    public void getDirection() throws Exception {
        Assert.assertNotNull(testDoor.getDirection());
    }

    @Test
    public void getConnectedRoomId() throws Exception {
        Assert.assertNotNull(testDoor.getConnectedRoomId());
    }

    @Test
    public void setTilePosition() throws Exception {
        testDoor.setTilePosition(0,0);
        Assert.assertNotNull(testDoor.getTilePosition());
        Assert.assertEquals(testDoor.getTilePosition(), testVec);
    }




}