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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static helper.Constants.KEY_COUNT;

public class GameBoard extends JLayeredPane {

    private Grid grid;
    private Player player;

    private DrawWindow currWindow;
    private int borderTop;

    private Map<Collectable, Tuple<Integer, Integer>> collectablesPositions;

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

        collectablesPositions = new HashMap<>();
        for (int i = 0; i < KEY_COUNT; i++){
            Collectable collectable = new Key(27, true);
            collectable.setBounds(0, 0, 1100, 680);
            collectablesPositions.put(collectable, collectable.getPosition());
            add(collectable, Integer.valueOf(4));
        }

        for (int i = 0; i < 1; i++){
            Collectable collectable = new LifeHeart(22, true);
            collectable.setBounds(0, 0, 1100, 680);
            collectablesPositions.put(collectable, collectable.getPosition());
            add(collectable, Integer.valueOf(4));
        }
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

    public Collectable decideToMovePlayer(int direction){
        // check for walls
        if (isNextStepPath(direction)) {
            movePlayerTo(direction);

            if (collectablesPositions.containsValue(player.getPosition())){

                Iterator<Map.Entry<Collectable, Tuple<Integer, Integer>>> iterator = collectablesPositions.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Collectable, Tuple<Integer, Integer>> entry = iterator.next();
                    if(player.getPosition().equals(entry.getValue())){
                        Collectable out = entry.getKey();
                        out.delete();
                        iterator.remove();
                        return out;
                    }
                }
            }
        }
        return null;
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


