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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of getX method, of class Player.
     */
    @Test
    public void testGetX() {
        System.out.println("* PlayerTest: testGetX");
        Player instance = new Player(10, 10);
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        
        instance.setX(15);
        expResult = 15;
        result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Player.
     */
    @Test
    public void testGetY() {
        System.out.println("* PlayerTest: testGetY");
        Player instance = new Player(10, 10);
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        
        instance.setY(15);
        expResult = 15;
        result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of setX method, of class Player.
     */
    @Test
    public void testSetX() {
        System.out.println("* PlayerTest: testSetX");
        Player instance = new Player(0, 0);
        instance.setX(10);
        int expResult = 10;
        int result = instance.getX();
        assertEquals(expResult, result);
        
        instance.setX(15);
        expResult = 15;
        result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of setY method, of class Player.
     */
    @Test
    public void testSetY() {
        System.out.println("* PlayerTest: testSetY");
        Player instance = new Player(0, 0);
        instance.setY(10);
        int expResult = 10;
        int result = instance.getY();
        assertEquals(expResult, result);
        
        instance.setY(15);
        expResult = 15;
        result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkWin method, of class Player.
     */
    @Test
    public void testCheckWin() {
        System.out.println("* PlayerTest: testCheckWin");
        Player instance = new Player(10, 10);
        boolean expResult = false;
        boolean result = instance.checkWin();
        assertEquals(expResult, result);
        
        instance.setX(10);
        instance.setY(10);
        expResult = true;
        result = instance.checkWin();
        assertEquals(expResult, result);
    }
    
}
