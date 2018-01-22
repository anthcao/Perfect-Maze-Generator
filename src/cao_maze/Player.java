/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cao_maze;

/**
 *
 * @author Anthony Cao
 */
public class Player {

    private int x, y;
    private final int goalX, goalY;

    /**
     * Creates a player object starting at 0, 0 (top left) and with a goal
     * pre: none
     * post: player object is created
     * @param gx
     * @param gy 
     */
    public Player(int gx, int gy) {
        x = 0;
        y = 0;
        goalX = gx;
        goalY = gy;
    }

    /**
     * Returns the x position of the player
     * pre: none
     * post: int x is returned
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the player
     * pre: none
     * post: int y is returned
     * @return 
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the player x position to a passed int value
     * pre: none
     * post: new x value is set
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the player y position to a passed int value
     * pre: none
     * post: new y value is set 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns based on if the player has reached its set goal
     * pre: none
     * post: boolean is returned based on position
     * @return 
     */
    public boolean checkWin() {
        return (x == goalX && y == goalY);
    }
}
