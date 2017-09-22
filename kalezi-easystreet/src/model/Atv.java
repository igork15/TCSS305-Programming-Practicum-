/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;

import java.util.Map;


/** This class is the behavior of the ATV vehicle.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public final class Atv extends AbstractVehicle {
    
    /**
     * Number of moves until ATV is revived.
     */
    private static final int  MY_ATV_DEATH_TIME = 20;
    
    
    /**
     * Constructor for the ATV vehicle.
     * 
     * @param theX X coordinate of the ATV.
     * @param theY Y coordinate of the ATV.
     * @param theDirection Direction the ATV is facing.
     */
    public Atv(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        myDeathTime = MY_ATV_DEATH_TIME;

    }
    @Override 
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
 
        return theTerrain != Terrain.WALL;
            

    }
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        return Direction.random();
        
               
    }


}
