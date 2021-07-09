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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static helper.Constants.DYNAMIC_ENEMY_COUNT;
import static helper.Constants.KEY_COUNT;

public class GameBoard extends JLayeredPane {

    private Grid grid;
    private Player player;
    private DynamicEnemy[] dynamicEnemies;
    private Map<Collectable, Tuple<Integer, Integer>> collectablesPositions;
    private Exit exit;

    private DrawWindow currWindow;
    private int borderTop;

    public GameBoard(int width, int height){

        setBackground(new Color(1, 1, 1, 0));
        createMaze(width, height);
        this.borderTop = 20;
        setBorder(new EmptyBorder(this.borderTop, 0, 0, 0));

        // initialize sliding window
        this.currWindow = new DrawWindow(new Tuple<>(0, 0), width, height);

        // player
        Tuple<Integer, Integer> startPosition =  new Tuple<>(10, 20);
        player = new Player(Color.CYAN,startPosition, 45);
        player.setBounds(startPosition.getX(), startPosition.getY(), 1100, 680);
        add(player, Integer.valueOf(2));

        // dynamic enemies
        dynamicEnemies = new DynamicEnemy[DYNAMIC_ENEMY_COUNT];
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(DYNAMIC_ENEMY_COUNT);
        for (int i = 0; i < DYNAMIC_ENEMY_COUNT; i++){
            dynamicEnemies[i] = new DynamicEnemy(Color.GREEN, this.grid, this.player);
            dynamicEnemies[i].setBounds(startPosition.getX(), startPosition.getY(),  1100, 680);
            add(dynamicEnemies[i], Integer.valueOf(4));
            executor.scheduleAtFixedRate(dynamicEnemies[i], 0, 1, TimeUnit.SECONDS);
        }

        // collectables
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

        // exit
        exit = new Exit(23, 45);
        exit.setBounds(0, 0, 1100, 680);
        add(exit, Integer.valueOf(4));
    }

    public UpperBar getInventory(){
        return player.getInventory();
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
                        updateUpperBar(out);
                    }
                }
            }

            for(int i = 0; i < DYNAMIC_ENEMY_COUNT; i++) {
                if (dynamicEnemies[i]!=null && dynamicEnemies[i].getPosition().equals(this.player.getPosition())){
                    remove(dynamicEnemies[i]);
                    if (dynamicEnemies[i].getIsHarmless()){
                        dynamicEnemies[i].reactToPlayerCollision();
                        dynamicEnemies[i] = null;
                        player.handleEnemyCollision();
                    }
                }
            }

            if (exit.getPosition().equals(this.player.getPosition())){
                exit.handlePlayerReach(player.getKeyCount());
            }

            player.checkForLives();

        }
    }

    private void updateUpperBar(Collectable out){
        this.player.getInventory().updateUpperBar(out);
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


