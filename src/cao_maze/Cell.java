/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cao_maze;

import java.io.Serializable;

/**
 *
 * @author Anthony Cao
 */
public class Cell implements Serializable{

    protected int[] walls;
    private final int x, y;
    private boolean visited;

    /**
     * Creates a cell object with a x/y position, all walls present, and visited false
     * pre: none
     * post: cell object is created
     * @param x
     * @param y 
     */
    public Cell(int x, int y) {
        visited = false;
        this.x = x;
        this.y = y;
        walls = new int[]{1, 1, 1, 1}; // top, right, bottom, left
    }

    /**
     * Returns if the cell was visited
     * pre: none
     * post: boolean visited is returned
     * @return 
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the visited to a passed value
     * pre: none
     * post: boolean visited is set
     * @param v 
     */
    public void setVisited(boolean v) {
        visited = v;
    }

    /**
     * Returns the current x position of the cell
     * pre: none
     * post: int x is returned
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the current x position of the cell
     * pre: none
     * post: int x is returned
     * @return 
     */
    public int getY() {
        return y;
    }
}
