/**
 * This class defines the layout of the game board.
 *
 */

import creator.maze.BinaryTree;
import creator.maze.Grid;
import helper.Tuple;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameBoard extends JLayeredPane {

    private Grid grid;
    private Player player;
    private DrawWindow currWindow;
    private int borderTop;


    public GameBoard(int width, int height){

        setBackground(new Color(1, 1, 1, 0));
        createMaze(width, height);
        this.borderTop = 20;
        setBorder(new EmptyBorder(this.borderTop, 0, 0, 0));

        // initialize sliding window
        this.currWindow = new DrawWindow(new Tuple<>(0, 0), width, height);

        Tuple<Integer, Integer> startPosition =  new Tuple<>(10, 20);
        player = new Player(Color.CYAN,startPosition, 45);
        player.setBounds(startPosition.getX(), startPosition.getY(), 1100, 680);
        add(player, Integer.valueOf(2));
    }

    private void createMaze(int width, int height){

        if (width < 2 || height < 2){
            System.err.println("Specified board game too small");
            System.exit(-1);
        }

        this.grid = new Grid(width, height);
        BinaryTree.on(this.grid); // using BinaryTree

        grid.setBounds(0, 20, 1100, 680);
        add(grid, Integer.valueOf(1));
    }

    public void decideToMovePlayer(int direction){
        // check for walls
        if (isNextStepPath(direction))
            movePlayerTo(direction);
    }

    private void movePlayerTo(int direction){
        player.moveOneStepTo(direction);
    }

    private boolean isNextStepPath(int direction){
        return grid.isLinkIn(player.getPosition(), direction);
    }

    public Dimension getPreferredSize(){
        return new Dimension(1000, 500);
    }

    public boolean isOptimizedDrawingEnabled(){
        return false;
    }

    public String toString(){
        return this.grid.toString();
    }
}


