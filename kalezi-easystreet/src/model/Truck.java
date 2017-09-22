/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;


import java.util.Map;

/** This class is the behavior of the truck vehicle.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public final class Truck extends AbstractVehicle {
    
    /**
     * Maximum amount of direction choices.
     */
    private static final int MY_MAX_CHOICES = 3;
    
    /**
     * Constructor for the truck vehicle.
     * 
     * @param theX X coordinate of the truck.
     * @param theY Y coordinate of the truck.
     * @param theDirection Direction the truck is facing.
     */
    public Truck(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        myDeathTime = 0;
    }
    
    /**
     * This method determines whether it is legal for the truck to go
     * on the terrain around it.
     * 
     * @param theTerrain The terrain around the truck
     * @param theLight Status of the cross walk and traffic lights.
     * @return Boolean value.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            return false;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW) {
            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method returns which direction the truck will go.
     * @param theNeighbors Collection of different terrain.
     * @return Direction value.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        
        if (numChoices(theNeighbors) == MY_MAX_CHOICES) {
            return threeRandomChoices(theNeighbors);
        } else if (numChoices(theNeighbors) == 2) {
            return twoRandomChoices(theNeighbors);
        } else {
            if (canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
                return getDirection().left();
            } else if (canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
                return getDirection().right();
            } else if (canPass(theNeighbors.get(getDirection()), Light.GREEN)) {
                return getDirection();
            } else {
                return getDirection().reverse();
            }
        }

        
    }
    
    /**
     * This method is used to make a random decision as to where
     * the truck will go. This is used if there are three possible choices.
     * 
     * @param theNeighbors Collection of different terrain.
     * @return Direction value.
     */
    private Direction threeRandomChoices(final Map<Direction, Terrain> theNeighbors) {
        
        final int x = myRandint.nextInt(3);
        
        if (x == 0 && canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            return getDirection().left();
        } else if (x == 1 && canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            return getDirection().right();
        } else {
            return getDirection();
        }
        
    }
    
    /**
     * This method is used to make a random decision as to where
     * the truck will go. This is used if there are two possible choices.
     * 
     * @param theNeighbors Collection of different terrain.
     * @return Direction value.
     */
    private Direction twoRandomChoices(final Map<Direction, Terrain> theNeighbors) {
        
        final int x = myRandint.nextInt(2);
        
        if (x == 0 && canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            return getDirection().left();
        } else if (x == 1 && canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            return getDirection().right();
        } else {
            return getDirection();
        }
        
    }
    
    /**
     * This method returns how many possible directions the truck
     * can go.
     * 
     * @param theNeighbors Collection of different terrain.
     * @return Integer value.
     */
    private int numChoices(final Map<Direction, Terrain> theNeighbors) {
        int choices = 0;
        
        
        if (canPass(theNeighbors.get(getDirection()), Light.GREEN)
            && canPass(theNeighbors.get(getDirection().left()), Light.GREEN)
            && canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            choices += MY_MAX_CHOICES;
        } else if (canPass(theNeighbors.get(getDirection()), Light.GREEN)
                   && canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
            choices += 2;
        } else if (canPass(theNeighbors.get(getDirection()), Light.GREEN)
                   && canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            choices += 2;
        } else if (canPass(theNeighbors.get(getDirection().right()), Light.GREEN)
                   && canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
            choices += 2;
        } else {
            choices += 1;
        }
        return choices;
    }

}
