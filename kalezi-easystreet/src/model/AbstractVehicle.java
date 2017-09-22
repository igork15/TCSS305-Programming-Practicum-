/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package model;

import java.util.Map;
import java.util.Random;

/** This class provides the body for the Vehicle interface methods.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Random object to be used in certain subclasses for movement.
     */
    protected Random myRandint = new Random();
    
    /**
     * Protected field that hold the time a vehicle has to be dead.
     */
    protected int myDeathTime;
    
    /**
     * Protected field that keeps track of how long a vehicle has been dead.
     */
    protected int myTimeDead;
    
    /**
     * Initial X coordinate of the vehicle.
     */
    private final int myBeginX;
    
    /**
     * Initial Y coordinate of the vehicle.
     */
    private final int myBeginY;
       
    /**
     * X coordinate of the vehicle.
     */
    private int myX;
    
    /**
     * Y coordinate of the vehicle.
     */
    private int myY;
    
    /**
     * Direction the vehicle is facing.
     */
    private Direction myDirection;
    
    /**
     * The life status of the vehicle.
     */
    private boolean myLife;

    /**
     * Constructor for all the vehicles.
     * 
     * @param theX X coordinate of the vehicle.
     * @param theY Y coordinate of the vehicle.
     * @param theDirection Direction the vehicle is facing.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDirection) {
        myLife = true;
        myTimeDead = 0;
        
        myBeginX = theX;
        myBeginY = theY;
        
        
        myX = theX;
        myY = theY;
        myDirection = theDirection;
        
    }
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return false;
    }


    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {


        return myDirection;
    }


    @Override
    public void collide(final Vehicle theOther) {
        if (theOther.isAlive() && getDeathTime() > theOther.getDeathTime()) {
            myLife = false;
        }
    }

    @Override
    public int getDeathTime() {
 
        return myDeathTime;
    }

    @Override
    public String getImageFileName() {
        final String fileType = ".gif";
        final String fileName;
        
        if (isAlive()) {
            fileName = this.getClass().getSimpleName().toLowerCase() + fileType;
        } else {
            fileName = this.getClass().getSimpleName().toLowerCase() + "_dead" + fileType;
        }
        return fileName;
    }

    @Override
    public Direction getDirection() {

        return myDirection;
    }

    @Override
    public int getX() {

        return myX;
    }

    @Override
    public int getY() {
        
        return myY;
    }


    @Override
    public boolean isAlive() {
       
        return myLife;
    }

    @Override
    public void poke() {
        myTimeDead++;
        if (myTimeDead == getDeathTime()) {
            myLife = true;
            myTimeDead = 0;
        }

    }

    @Override
    public void reset() {
        myX = myBeginX;
        myY = myBeginY;
        
        myLife = true;

    }


    @Override
    public void setDirection(final Direction theDirection) {
        myDirection = theDirection;

    }


    @Override
    public void setX(final int theX) {
        myX = theX;

    }

    @Override
    public void setY(final int theY) {
        myY = theY;

    }
    
    /**
     * String representation of vehicles.
     * @return String value.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        
        builder.append(this.getClass().getSimpleName());
        
        return builder.toString();
    }

}
