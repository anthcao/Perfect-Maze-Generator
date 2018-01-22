/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cao_maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Anthony Cao
 */
public class Maze {

    protected Cell[][] grid;
    protected Cell[][] temp;
    private Cell currentCell;
    protected boolean[][] correctPath;
    private Stack<Cell> cells;
    private ArrayList<Cell> neighbours;
    private int totalCells, visitedCells, length, width;
    protected Player player;
    Random rand;
    File mazeFile;
    FileOutputStream out;
    ObjectOutputStream writeItem;
    FileInputStream in;
    ObjectInputStream readItem;

    /**
     * Creates a new maze object and initializes variables, also generates a new perfect maze
     * pre: none
     * post: new perfect maze is generated with variables initialized
     * @param l
     * @param w 
     */
    public Maze(int l, int w) {
        
        player = new Player(l - 1, w - 1);
        mazeFile = new File("maze.dat");
        length = l;
        width = w;
        grid = new Cell[length][width];
        correctPath = new boolean[length][width];
        rand = new Random();
        visitedCells = 1;
        neighbours = new ArrayList<>();
        cells = new Stack<>();
        totalCells = l * w;
        temp = grid;
//        System.out.println(visitedCells); // for testing, check to see if all cells are visited
    }

    /**
     * Generates a perfect maze (maze without any loops or closed circuits, and
     * without any inaccessible areas) with depth first search, pseudo-code
     * obtained from https://en.wikipedia.org/wiki/Maze_generation_algorithm
     * pre: all cells are not visited 
     * post: perfect maze is generated
     */
    public void generateMaze() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
        
        int x = rand.nextInt(length);
        int y = rand.nextInt(width);
        currentCell = grid[x][y];

        while (visitedCells < totalCells) {
            grid[x][y].setVisited(true);
            neighbours.clear();

            if (y + 1 != width && !grid[x][y + 1].isVisited()) {
                neighbours.add(grid[x][y + 1]);
            }
            if (x + 1 != length && !grid[x + 1][y].isVisited()) {
                neighbours.add(grid[x + 1][y]);
            }
            if (y - 1 != -1 && !grid[x][y - 1].isVisited()) {
                neighbours.add(grid[x][y - 1]);
            }
            if (x - 1 != -1 && !grid[x - 1][y].isVisited()) {
                neighbours.add(grid[x - 1][y]);
            }

            if (neighbours.size() > 0) {
                Cell tempCell = neighbours.get(rand.nextInt(neighbours.size()));

                destroyWallBetween(currentCell, tempCell);

                cells.push(currentCell);

                currentCell = grid[tempCell.getX()][tempCell.getY()];
                x = currentCell.getX();
                y = currentCell.getY();

                visitedCells++;
            } else {
                currentCell = cells.pop();
                x = currentCell.getX();
                y = currentCell.getY();
            } // I DID IT, THE THING DOESENT'T ERROR ANYMORE, VICTORY SCREECH ALOLOLOLOLOLOLOLOLOlO
        }
    }

    /**
     * Returns the maze grid comprised of cells in a 2d array
     * pre: none
     * post: cell grid is returned
     * @return 
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * Returns the int number of visited cells
     * pre: none
     * post: int visited cells is returned
     * @return 
     */
    public int getVisitedCells() {
        return visitedCells;
    }
    
    /**
     * Destroys the wall between two adjacent neighbours depending on relative direction
     * pre: none
     * post: wall between passedd cells are destroyed
     * @param c1
     * @param c2 
     */
    public void destroyWallBetween(Cell c1, Cell c2) {
        int diffX = c1.getX() - c2.getX();
        int diffY = c1.getY() - c2.getY();

        if (diffY == -1) {
            grid[c1.getX()][c1.getY()].walls[2] = 0;
            grid[c2.getX()][c2.getY()].walls[0] = 0;
        } else if (diffX == -1) {
            grid[c1.getX()][c1.getY()].walls[1] = 0;
            grid[c2.getX()][c2.getY()].walls[3] = 0;
        } else if (diffY == 1) {
            grid[c1.getX()][c1.getY()].walls[0] = 0;
            grid[c2.getX()][c2.getY()].walls[2] = 0;
        } else if (diffX == 1) {
            grid[c1.getX()][c1.getY()].walls[3] = 0;
            grid[c2.getX()][c2.getY()].walls[1] = 0;
        } else {
            System.out.println("Invalid neighbour");
        }
    }

    /**
     * Moves the player position upwards if there is no wall in the north direction of the current cell
     * pre: none
     * post: player is either moved or stays in place
     */
    public void moveUp() {
        if (grid[player.getX()][player.getY()].walls[0] == 0) {
            player.setY(player.getY() - 1);
        }

    }

    /**
     * Moves the player position right if there is no wall in the east direction of the current cell
     * pre: none
     * post: player is either moved or stays in place
     */
    public void moveRight() {
        if (grid[player.getX()][player.getY()].walls[1] == 0) {
            player.setX(player.getX() + 1);
        }
    }

    /**
     * Moves the player position down if there is no wall in the south direction of the current cell
     * pre: none
     * post: player is either moved or stays in place
     */
    public void moveDown() {
        if (grid[player.getX()][player.getY()].walls[2] == 0) {
            player.setY(player.getY() + 1);
        }
    }

    /**
     * Moves the player position left if there is no wall in the west direction of the current cell
     * pre: none
     * post: player is either moved or stays in place
     */
    public void moveLeft() {
        if (grid[player.getX()][player.getY()].walls[3] == 0) {
            player.setX(player.getX() - 1);
        }
    }

    /**
     * Attempts to solve the maze with depth first search and recursion, returns true if solvable, false if not, and also marks the oorrect path it took
     * pre: none
     * post: boolean is returned based on solvability, and correct path is marked
     * @param x
     * @param y
     * @return 
     */
    public boolean solveMaze(int x, int y) {
        if (x == length - 1 && y == width - 1) {
            return true;
        }
        if (temp[x][y].isVisited()) {
            return false;
        }

        temp[x][y].setVisited(true);

        if (temp[x][y].walls[0] == 0) {
            if (solveMaze(x, y - 1)) {
                correctPath[x][y] = true;
                return true;
            }
        }
        if (temp[x][y].walls[1] == 0) {
            if (solveMaze(x + 1, y)) {
                correctPath[x][y] = true;
                return true;
            }
        }
        if (temp[x][y].walls[2] == 0) {
            if (solveMaze(x, y + 1)) {
                correctPath[x][y] = true;
                return true;
            }
        }
        if (temp[x][y].walls[3] == 0) {
            if (solveMaze(x - 1, y)) {
                correctPath[x][y] = true;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Saves the maze grid to a maze.dat file
     * pre: none
     * post: grid is saved to file
     */
    public void saveMaze() {
        try {
            out = new FileOutputStream(mazeFile);
            writeItem = new ObjectOutputStream(out);
            writeItem.writeObject(grid);
            writeItem.close();
            out.close();
            System.out.println("Maze written to file.");
        } catch (FileNotFoundException ex) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + ex.getMessage());
        }
    }

    /**
     * Loads a maze grid from a maze.dat file
     * pre: maze.dat exists
     * post: grid is loaded from file
     */
    public void loadMaze() {
        try {
            in = new FileInputStream(mazeFile);
            readItem = new ObjectInputStream(in);
            grid = (Cell[][]) readItem.readObject();
            readItem.close();
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class could not be used to cast object.");
            System.err.println("ClassNotFoundException: " + ex.getMessage());
        }
    }
}
