import helper.Constants;
import helper.Tuple;

import javax.swing.*;
import java.awt.*;

public class Player extends JComponent{

    private Color color;
    private Tuple<Integer, Integer> positionVisual;
    private Tuple<Integer, Integer> position; // position in grid
    private int scale;
    private int stepSize;
    private UpperBar inventory;

    public Player(Color color, Tuple<Integer, Integer> positionVisual, int stepSize){
        this.color = color;
        this.positionVisual = positionVisual;
        this.position = new Tuple<>(0, 0);
        this.scale = 20;
        this.stepSize = stepSize;
        this.inventory = new UpperBar();
    }

    public UpperBar getInventory(){
        return this.inventory;
    }

    public int getKeyCount(){
        return this.inventory.getKeyCount();
    }

    public Tuple<Integer, Integer> getPositionVisual() {
        return positionVisual;
    }

    public Tuple<Integer, Integer> getPosition() {
        return position;
    }

    public void moveOneStepTo(int direction){
        switch(direction){
            case Constants.N:
                positionVisual.setY(positionVisual.getY() - this.stepSize);
                position.setY(position.getY() - 1);
                break;
            case Constants.S:
                positionVisual.setY(positionVisual.getY() + this.stepSize);
                position.setY(position.getY() + 1);
                break;
            case Constants.E:
                positionVisual.setX(positionVisual.getX() + this.stepSize);
                position.setX(position.getX() + 1);
                break;
            case Constants.W:
                positionVisual.setX(positionVisual.getX() - this.stepSize);
                position.setX(position.getX() - 1);
                break;
            default:
                System.err.println("Unsupported movement");
                System.exit(-1);
        }
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(this.color);
        g.fillRect(positionVisual.getX(), positionVisual.getY(), this.scale, this.scale);
    }

    public void handleEnemyCollision() {

    }

    public void checkForLives(){
        if (getHeartCount() <= 0) {
            Game.getCl().show(Game.getCardPanel(), "loseScreen");
            StartState startState = new StartState();
            startState.changeContext(Context.getContext());
        }
    }

    private int getHeartCount() {
        return this.inventory.getHeartCount();
    }
}
