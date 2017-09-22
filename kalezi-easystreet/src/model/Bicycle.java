/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;

import java.util.Map;

/** This class is the behavior of the Bicycle vehicle.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public final class Bicycle extends AbstractVehicle {
    /**
     * Amount of moves until bicycle is revived.
     */
    private static final int MY_DEATH_MOVES = 30;
    
    /**
     * Constructor for bicycle vehicle.
     * 
     * @param theX X coordinate of the bicycle.
     * @param theY Y coordinate of the bicycle.
     * @param theDirection Direction the bicycle is facing.
     */
    public Bicycle(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        myDeathTime = MY_DEATH_MOVES;
    }
    
    /**
     * Determines whether it is legal for the bike to traverse the terrain
     * around it.
     * 
     * @param theTerrain terrain that is around the bicycle.
     * @param theLight status of the traffic and cross walk lights.
     * @return Boolean value.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET) {
            return true;
        } else if ((theTerrain == Terrain.LIGHT && theLight == Light.GREEN) 
                    || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)) {
            return true;
        } else {
            return false;
        }

    }
    
    /**
     * Determines which direction the bicycle will go.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Direction value.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        if (checkForTrailAhead(theNeighbors)) {
            return getDirection();
        } else if (checkForTrailLeft(theNeighbors)) {
            return getDirection().left();
        } else if (checkForTrailRight(theNeighbors)) {
            return getDirection().right();
        } 
        
        if (canPass(theNeighbors.get(getDirection()), Light.GREEN)) {
            return getDirection();
        } else if (canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            return getDirection().left();
        } else if (canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            return getDirection().right();
        } else {
            return getDirection().reverse();
        }
        
    }
    
    /**
     * Returns if there is a trail ahead of the bicycle.
     * 
     * @param theNeighbors Collection of terrain.
     * @return boolean value.
     */
    private boolean checkForTrailAhead(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection()) == Terrain.TRAIL;
    }
    
    /**
     * Returns if there is a trail to the left of the bicycle. 
     * 
     * @param theNeighbors Collection of terrain.
     * @return boolean value.
     */
    private boolean checkForTrailLeft(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection().left()) == Terrain.TRAIL;
    }
    
    /**
     * Returns if there is a trail to the right of the bicycle. 
     * 
     * @param theNeighbors Collection of terrain.
     * @return boolean value.
     */
    private boolean checkForTrailRight(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection().right()) == Terrain.TRAIL;
    }


}
