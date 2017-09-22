/* 
 * TCSS 305 – Autumn 2016
 * Assignment 3 – Easy Street
 */

package tests;


import static org.junit.Assert.*;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Test;


/** This class tests the truck class.
 * 
 * 
 * @author Igor Kalezic
 * @version 28 October 2016 
 */
public class TruckTest {
    
    /**
     * Number of iterations to use in for loop for random test.
     */
    private static final int ITERATIONS = 30;
    
    /**
     * Tests the truck constructor.
     */
    @Test
    public void testConstructor() {
        
        final Truck test = new Truck(3, 4, Direction.WEST);
       
        assertEquals("Wrong X parameter", 3, test.getX());
        assertEquals("Wrong y parameter", 4, test.getY());
        assertEquals("Wrong Direction", Direction.WEST, test.getDirection());
        
    }

    /**
     * Tests the canPass method.
     */
    @Test
    public final void testCanPass() {
        final List<Terrain> terrain = new ArrayList<>();
        terrain.add(Terrain.STREET);
        terrain.add(Terrain.CROSSWALK);
        terrain.add(Terrain.LIGHT);
                
        final Truck test = new Truck(0, 0, Direction.WEST);
        
        for (final Terrain t : Terrain.values()) {
            
            for (final Light light : Light.values()) {
                if (t == Terrain.STREET) {
                
                    
                    assertTrue("Trucks can move on streets"
                               + ", with light " + light,
                               test.canPass(t, light));
                } else if (t == Terrain.CROSSWALK) {
                           

                    if (light == Light.GREEN) {
                        assertTrue("Trucks can pass " + t
                            + ", with light " + light,
                            test.canPass(t,
                                          light));
                    } else if (light == Light.YELLOW) {
                        assertTrue("Trucks can pass " + t
                                   + ", with light " + light,
                                   test.canPass(t,
                                                 light));
                    } else { 
                        assertFalse("Trucks can't pass crosswalk " + t
                            + ", with light " + light,
                            test.canPass(t,
                                          light));
                    }
                } else if (t == Terrain.LIGHT) {
 
                    assertTrue("Trucks can pass " + t
                        + ", with any light",
                        test.canPass(t, light));
                }
            } 
        }
    }
    
    /**
     * Tests the choose direction method if there is only a street available.
     */
    @Test
    public final void testChooseDirectionStreetOnly() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (final Direction dir : Direction.values()) {
            newTest.setDirection(dir);
            
            if (dir == Direction.NORTH) {
                assertEquals("Correct", Direction.NORTH, newTest.chooseDirection(neighbors));
            } else {
                assertNotEquals("Wrong decision", newTest.chooseDirection(neighbors));
            }
        }
            
    }
    
    /**
     * Tests the choose direction method if only crosswalk is available.
     */
    @Test
    public final void testChooseDirectionCrossWalk() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (final Direction dir : Direction.values()) {
            newTest.setDirection(dir);
            
            if (dir == Direction.NORTH) {
                assertEquals("Correct", Direction.NORTH, newTest.chooseDirection(neighbors));
            } else {
                assertNotEquals("Wrong decision", newTest.chooseDirection(neighbors));
            }
        }
            
    }
    
    /**
     * Tests the choose direction with three possibilities method.
     */
    @Test
    public final void testChooseDirectionThreeRandom() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.CROSSWALK);

        
        boolean north = false;       
        boolean west = false;
        boolean east = false;
        
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < ITERATIONS; count++) {
            final Direction dir = newTest.chooseDirection(neighbors);
            
            if (dir == Direction.NORTH) {
                north = true;
            } else if (dir == Direction.WEST) {
                west = true;
            } else if (dir == Direction.EAST) {
                east = true;
            } 
            
            assertNotEquals("Wasn't random", north && east && west);
            
            
        }
            
    }
    
    /**
     * Tests the choose direction with two possibilities method.
     */
    @Test
    public final void testChooseDirectionTwoRandom() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.WEST, Terrain.STREET);
        
        boolean north = false;       
        boolean west = false;

        
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < ITERATIONS; count++) {
            final Direction dir = newTest.chooseDirection(neighbors);
            
            if (dir == Direction.NORTH) {
                north = true;
            } else if (dir == Direction.WEST) {
                west = true;
            } 
            
            assertNotEquals("Wasn't random", north && west);
            
            
        }
    }
    
    /**
     * Tests the choose direction method when reverse is correct choice.
     */
    @Test
    public final void testChooseDirectionReverse() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        

        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (final Direction dir : Direction.values()) {
            newTest.setDirection(dir);
            
            if (dir == Direction.SOUTH) {
                assertEquals("Correct", Direction.SOUTH, newTest.chooseDirection(neighbors));
            } else {
                assertNotEquals("Wrong decision", newTest.chooseDirection(neighbors));
            }
        }
            
    }
    /**
     * Tests the method that shows how many directions the truck can go.
     */
    @Test
    public final void testNumChoicesOne() {
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        int choices = 0;
        
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.CROSSWALK);
        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
        
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (final Direction dir : Direction.values()) {
            newTest.setDirection(dir);
            
            if (dir == Direction.SOUTH) {
                choices++;
            } 
        }
        assertEquals("Wrong number of choices", 1, choices);
    }
    
    /**
     * Tests for random choice when only two choices are available.
     */
    @Test
    public final void testNumChoicesTwo() {
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        int choices = 0;
        
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (final Direction dir : Direction.values()) {
            newTest.setDirection(dir);
            
            if (dir == Direction.SOUTH) {
                choices++;
            } else if (dir == Direction.EAST) {
                choices++;
            }
        }
        assertEquals("Wrong number of choices", 2, choices);
    }
    
    /**
     * Tests random selection when three choices are available.
     */
    @Test
    public final void testNumChoicesThree() {
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        int choices = 0;
        
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        
        final Truck newTest = new Truck(0, 0, Direction.NORTH);
        
        for (final Direction dir : Direction.values()) {
            newTest.setDirection(dir);
            
            if (dir == Direction.SOUTH) {
                choices++;
            } else if (dir == Direction.WEST) {
                choices++;
            } else if (dir == Direction.EAST) {
                choices++;
            }
        }
        assertEquals("Wrong number of choices", 3, choices);
    }


}
