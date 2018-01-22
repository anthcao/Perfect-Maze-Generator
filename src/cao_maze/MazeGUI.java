/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cao_maze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author caoa5300
 */
public class MazeGUI extends KeyAdapter {

    JFrame frame;
    JPanel contentPane;
    JLabel[][] labelGrid;
    JLabel moves;
    JButton save, load, reset, solve;
    Maze maze;
    int numMoves;

    public MazeGUI() {
        maze = new Maze(20, 20);
        maze.generateMaze();
        
        frame = new JFrame("Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creates a new panel with gridbaglayout
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // sets up the label grid representing the maze
        labelGrid = new JLabel[20][20];
        for (int i = 0; i < labelGrid.length; i++) {
            for (int j = 0; j < labelGrid[0].length; j++) {
                labelGrid[i][j] = new JLabel();
                labelGrid[i][j].setOpaque(true);
                labelGrid[i][j].setPreferredSize(new Dimension(20, 20));
                labelGrid[i][j].setBackground(Color.LIGHT_GRAY);
                c.gridx = i;
                c.gridy = j;
                contentPane.add(labelGrid[i][j], c);
            }
        }

        updateWalls();
        labelGrid[0][0].setBackground(Color.YELLOW);
        labelGrid[19][19].setBackground(Color.RED);

        // creates a new button save
        save = new JButton("Save");
        save.addActionListener(new ButtonSave());
        c.gridx = 0;
        c.gridy = 20;
        c.gridwidth = 4;
        c.insets = new Insets(10, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        save.setFocusable(false);
        contentPane.add(save, c);

        // creates a new button load
        load = new JButton("Load");
        load.addActionListener(new ButtonLoad());
        c.gridx = 4;
        load.setFocusable(false);
        contentPane.add(load, c);

        // creates a new button reset
        reset = new JButton("Reset");
        reset.addActionListener(new ButtonReset());
        c.gridx = 8;
        reset.setFocusable(false);
        contentPane.add(reset, c);

        // creates a new button solve
        solve = new JButton("Solve");
        solve.addActionListener(new ButtonSolve());
        c.gridx = 12;
        solve.setFocusable(false);
        contentPane.add(solve, c);

        // creates a new button moves
        moves = new JLabel("Moves: " + numMoves);
        c.gridx = 16;
        c.insets = new Insets(10, 15, 0, 0);
        contentPane.add(moves, c);

        frame.setContentPane(contentPane);

        frame.pack(); // sets components to their preferred sizes
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(screenSize.width / 2 - frame.getWidth() / 2, screenSize.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
        // Creates the frame in the middle of the screen
        frame.addKeyListener(this);
        frame.setFocusable(true); // makes frame accept keyboard input
        frame.setResizable(false); // prevents resizing
        frame.setVisible(true); // sets frame to be visable

    }

    public class ButtonSave implements ActionListener {

        @Override
        /**
         * Saves the current maze to a file by calling the saveMaze() method
         * pre: none
         * post: maze is saved to file
         */
        public void actionPerformed(ActionEvent e) {
            maze.saveMaze();
        }
    }

    public class ButtonLoad implements ActionListener {

        @Override
        /**
         * Loads a maze from a file by calling the loadMaze() method
         * pre: file exists
         * post: maze is loaded and game is updated
         */
        public void actionPerformed(ActionEvent e) {
            maze.loadMaze();
            updateWalls();
            resetMaze();
        }
    }

    public class ButtonReset implements ActionListener {

        @Override
        /**
         * Resets the maze by calling the resetMaze() method
         * pre: none
         * post: maze is reset
         */
        public void actionPerformed(ActionEvent e) {
            resetMaze();
        }
    }

    public class ButtonSolve implements ActionListener {

        @Override
        /**
         * Solves the maze by displaying the correct path
         * pre: none
         * post: maze is solved
         */
        public void actionPerformed(ActionEvent e) {
            maze.temp = maze.getGrid();
            for (int i = 0; i < maze.temp.length; i++) {
                for (int j = 0; j < maze.temp[0].length; j++) {
                    maze.correctPath[i][j] = false;
                }
            }
            
            for (int i = 0; i < maze.temp.length; i++) {
                for (int j = 0; j < maze.temp[0].length; j++) {
                    maze.temp[i][j].setVisited(false);
                }
            }
            maze.solveMaze(maze.player.getX(), maze.player.getY());
            for (int i = 0; i < maze.correctPath.length; i++) {
                for (int j = 0; j < maze.correctPath[0].length; j++) {
                    if (maze.correctPath[i][j]) {
                        labelGrid[i][j].setBackground(Color.GREEN);
                    }
                }
            }

            labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.YELLOW);
        }
    }

    /**
     * Updates the GUI walls to match the console counterpart
     * pre: none
     * post: walls are updated
     */
    private void updateWalls() {
        int top, left, bottom, right;

        for (int i = 0; i < labelGrid.length; i++) {
            for (int j = 0; j < labelGrid[0].length; j++) {
                top = maze.getGrid()[i][j].walls[0];
                left = maze.getGrid()[i][j].walls[3];
                bottom = maze.getGrid()[i][j].walls[2];
                right = maze.getGrid()[i][j].walls[1];
                labelGrid[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.DARK_GRAY));
            }
        }
    }

    /**
     * Resets the player position, number of moves, and the board GUI
     * pre: none
     * post: game is reset
     */
    private void resetMaze() {
        numMoves = 0;
        moves.setText("Moves: " + numMoves);
        maze.player.setX(0);
        maze.player.setY(0);
        for (int i = 0; i < labelGrid.length; i++) {
            for (int j = 0; j < labelGrid[0].length; j++) {
                labelGrid[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }
        labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.YELLOW);
        labelGrid[19][19].setBackground(Color.RED);
    }

    /**
     * Moves the player depending on key pressed and if move is valid, increments the number of moves, and updates the graphical player counterpart
     * pre: arrow key is pressed
     * post: Player movement is processed, updated, and numMoves is incremented
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                numMoves++;
                moves.setText("Moves: " + numMoves);
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.LIGHT_GRAY);
                maze.moveUp();
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.YELLOW);
                break;
            case KeyEvent.VK_RIGHT:
                numMoves++;
                moves.setText("Moves: " + numMoves);
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.LIGHT_GRAY);
                maze.moveRight();
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.YELLOW);
                break;
            case KeyEvent.VK_DOWN:
                numMoves++;
                moves.setText("Moves: " + numMoves);
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.LIGHT_GRAY);
                maze.moveDown();
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.YELLOW);
                break;
            case KeyEvent.VK_LEFT:
                numMoves++;
                moves.setText("Moves: " + numMoves);
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.LIGHT_GRAY);
                maze.moveLeft();
                labelGrid[maze.player.getX()][maze.player.getY()].setBackground(Color.YELLOW);
                break;
            default:
                break;
        }

        if (maze.player.checkWin()) {
            win();
        }
    }

    /**
     * Displays a popup with number of moves performed and asks if the player wants to play again, closes if no, resets game if yes
     * pre: none
     * post: popup is displayed
     */
    public void win() {
        int result = JOptionPane.showConfirmDialog(frame, "Congratulations! You won in " + numMoves + " moves" + "\nPlay again?", "Winner!", JOptionPane.YES_NO_OPTION);
        switch (result) {
            case 0:
                resetMaze();
                break;
            case 1:
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                break;
        }
    }

    /**
     * Create and show the GUI pre: none post: GUI is created and shown
     */
    private static void runGUI() {
        MazeGUI mazeGame = new MazeGUI();
    }

    /**
     * Runs GUI from a event dispatching thread pre: none post: GUI is ran
     *
     * @param args
     */
    public static void main(String[] args) {
        /* methods that create and show a GUI should be
         run from an event-dispatching thread */
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
    }
}
