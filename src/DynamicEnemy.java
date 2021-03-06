import creator.maze.Grid;
import helper.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static helper.Constants.*;

public class DynamicEnemy extends JComponent  implements Runnable{

    private Grid grid;
    private Player player;
    private Color color;
    private Tuple<Integer, Integer> positionVisual;
    private Tuple<Integer, Integer> position; // position in grid
    private int scale;
    private int stepSize;
    private Tuple<Integer, Integer> specificOffset;
    private Random rand;
    private volatile boolean stop;
    private boolean isHarmless;

    public DynamicEnemy(Color color, Grid grid, Player player){
        this.color = color;
        this.grid = grid;
        this.player = player;
        this.scale = 20;
        this.stepSize = 45;
        this.stop = false;
        this.isHarmless = true;

        specificOffset = new Tuple<>(10, 20);
        rand = new Random();
        int x = rand.nextInt(GAME_BOARD_WIDTH);
        int y = rand.nextInt(GAME_BOARD_HEIGHT);

        this.position = new Tuple<>(x, y);
        this.positionVisual = new Tuple<>(x * this.stepSize + specificOffset.getX(), y * this.stepSize + specificOffset.getY());
    }

    @Override
    public void run() {

        if (!stop && Context.getContext().getState() instanceof PlayState){
            decideToMove();
            if (collisionWithPlayer()){
                reactToPlayerCollision();
            }
            repaint();
        }
    }

    public Tuple<Integer, Integer> getPosition() {
        return position;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(this.color);
        g.fillOval(positionVisual.getX(), positionVisual.getY(), this.scale, this.scale);
    }

    private void decideToMove(){

        ArrayList<Grid.Cell> possiblePaths =  this.grid.getCellAt(position.getX(), position.getY()).getLinkedCells();

        int index = rand.nextInt(possiblePaths.size());
        Tuple<Integer, Integer> nextPosition = possiblePaths.get(index).getPosition();

        this.positionVisual.setX((nextPosition.getX() - this.position.getX()) * this.stepSize + this.positionVisual.getX());
        this.positionVisual.setY((nextPosition.getY() - this.position.getY()) * this.stepSize + this.positionVisual.getY());
        this.position.setX(nextPosition.getX());
        this.position.setY(nextPosition.getY());
    }

    private boolean collisionWithPlayer(){
        return this.player.getPosition().equals(this.position);
    }

    public boolean getIsHarmless(){
        return this.isHarmless;
    }

    public void reactToPlayerCollision(){
        player.getInventory().deleteHeart();
        this.color = new Color(1, 1, 1, 0);
        this.isHarmless = false;
        stop();
    }

    public void stop(){
        this.stop = true;
    }
}
