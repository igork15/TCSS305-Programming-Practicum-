/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;

import java.util.Map;

/** This class is the behavior of the car vehicle.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public class Car extends AbstractVehicle {
    /**
     * Amount of moves the car has to wait before it's revived.
     */
    private static final int MY_DEATH_WAIT = 10;
    
    /**
     * Constructor for the car vehicle.
     * 
     * @param theX X coordinate of the car.
     * @param theY Y coordinate of the car.
     * @param theDirection Direction the car is facing.
     */
    public Car(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        myDeathTime = MY_DEATH_WAIT;
    }
    
    /**
     * Determines whether it is legal for the car to pass.
     * 
     * @param theTerrain the terrain that is around the vehicle.
     * @param theLight the status of the traffic or cross walk light.
     * @return boolean value.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.STREET) {
            return true;
        } else if (theTerrain == Terrain.LIGHT && theLight != Light.RED) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            return false;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW) {
            
            return false;
        } else {
            return false;
        }
    }
    
    /**
     * This method returns which direction the car will go.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Direction value.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        if (canPass(theNeighbors.get(getDirection()), Light.GREEN)) {
            return getDirection();
        } else if (canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            return getDirection().left();
        } else {
            return getDirection().right();
        }

    } 
    

}
