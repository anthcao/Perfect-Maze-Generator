/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cao_maze;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anthony Cao
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isVisited method, of class Cell.
     */
    @Test
    public void testIsVisited() {
        System.out.println("* CellTest: testIsVisited");
        Cell instance = new Cell(0,0);
        boolean expResult = false;
        boolean result = instance.isVisited();
        assertEquals(expResult, result);
        
        instance.setVisited(true);
        expResult = true;
        result = instance.isVisited();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisited method, of class Cell.
     */
    @Test
    public void testSetVisited() {
        System.out.println("* CellTest: testSetVisited");
        Cell instance = new Cell(0, 0);
        instance.setVisited(false);
        boolean expResult = false;
        boolean result = instance.isVisited();
        assertEquals(expResult, result);
        
        instance.setVisited(true);
        expResult = true;
        result = instance.isVisited();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class Cell.
     */
    @Test
    public void testGetX() {
        System.out.println("* CellTest: testGetX");
        Cell instance1 = new Cell(0, 0);
        int expResult = 0;
        int result = instance1.getX();
        assertEquals(expResult, result);
        
        Cell instance2 = new Cell(10, 5);
        expResult = 10;
        result = instance2.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Cell.
     */
    @Test
    public void testGetY() {
        System.out.println("* CellTest: testGetY");
        Cell instance1 = new Cell(0, 0);
        int expResult = 0;
        int result = instance1.getY();
        assertEquals(expResult, result);
        
        Cell instance2 = new Cell(10, 5);
        expResult = 5;
        result = instance2.getY();
        assertEquals(expResult, result);
    }
    
}
