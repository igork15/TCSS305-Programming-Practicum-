/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;


import java.util.Map;

/** This class is the behavior of the human vehicle.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public final class Human extends AbstractVehicle {
    
    /**
     * Amount of moves until the human is revived.
     */
    private static final int MY_DEATH_MOVES = 50;
    
    /**
     * Constructor for the human.
     * 
     * @param theX X coordinate of the human.
     * @param theY Y coordinate of the human.
     * @param theDirection Direction the human is facing.
     */
    public Human(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        myDeathTime = MY_DEATH_MOVES;
    }
    
    /**
     * Determines whether it is legal for the human to traverse the terrain
     * around it.
     * 
     * @param theTerrain Terrain around the human.
     * @param theLight Status of the cross walk light.
     * @return boolean value.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.GRASS) {
            return true;
        } else if ((theTerrain == Terrain.CROSSWALK && theLight == Light.RED)
                    || (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Determines which direction the human will go.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Direction value.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        
        if (checkForCrosswalkAhead(theNeighbors)) {
            return getDirection();
        } else if (checkForCrosswalkLeft(theNeighbors)) {
            return getDirection().left();
        } else if (checkForCrosswalkRight(theNeighbors)) {
            return getDirection().right();
        } else if (!canPass(theNeighbors.get(getDirection()), Light.GREEN)
                   && !canPass(theNeighbors.get(getDirection().left()), Light.GREEN)
                   && !canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            return getDirection().reverse();
        } else {
            return randomChoice(theNeighbors);
        }
        
    }
    
    /**
     * Determines whether there is a cross walk ahead of the human.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Boolean value.
     */
    private boolean checkForCrosswalkAhead(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection()) == Terrain.CROSSWALK;
    }
    
    /**
     * Determines whether there is a cross walk to the left of the human.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Boolean value.
     */
    private boolean checkForCrosswalkLeft(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK;
    }
    
    /**
     * Determines whether there is a cross walk to the right of the human.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Boolean value.
     */
    private boolean checkForCrosswalkRight(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK;
    }
    
    /**
     * Helper method for making a random direction decision.
     * 
     * @param theNeighbors Collection of terrain.
     * @return Direction value.
     */
    private Direction randomChoice(final Map<Direction, Terrain> theNeighbors) {
        
        final int x = myRandint.nextInt(2);
        
        if (x == 0 && canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            return getDirection().left();
        } else if (x == 1 && canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            return getDirection().right();
        } else {
            return getDirection();
        }
    }
    
}
