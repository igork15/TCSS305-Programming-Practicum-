/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;

/** This class is the behavior of the taxi vehicle.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public final class Taxi extends Car {
   
    /**
     * Number of moves until the taxi can run the red light.
     */
    private static final int MY_RUN_RED_LIGHT = 3;
    
    /**
     * Amount of moves until taxi is revived.
     */
    private static final int MY_DEATH_MOVES = 10;
    
    /**
     * Counter for time waiting at cross walk light.
     */
    private int myClock;
    

    


    /**
     * @param theX X coordinate of the taxi.
     * @param theY Y coordinate of the taxi.
     * @param theDirection Direction the taxi is facing.
     */
    public Taxi(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        myClock = 0;
        myDeathTime = MY_DEATH_MOVES;
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        
        if (theTerrain == Terrain.STREET) {

            return true;
        } else if (theTerrain == Terrain.LIGHT && theLight != Light.RED) {

            return true;
        } else if (theTerrain == Terrain.CROSSWALK && (theLight == Light.GREEN 
                                                       || theLight == Light.YELLOW)) {

            return true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            myClock++;
            if (myClock < MY_RUN_RED_LIGHT) {
                return false;
            } else {
                myClock = 0;
                return true;
            }
            
        } else {
            
            return false;
        }
        
    }

}
