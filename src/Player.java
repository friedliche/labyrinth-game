import helper.Tuple;

import javax.swing.*;
import java.awt.*;

public class Player extends JComponent {

    private Color color;
    private Tuple<Integer, Integer> position; // position in grid (= game board)
    private int scale;

    private int width, height;

    public Player(Color color, Tuple<Integer, Integer> position){
        this.color = color;
        this.position = position;
        this.scale = 150;

        this.width = this.scale;
        this.height = this.scale;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(this.color);
        g.fillRect(position.getX(), position.getY(), this.scale, this.scale);
    }

    public Dimension getPreferredSize(){
        return new Dimension(this.scale, this.scale);
    }
}
