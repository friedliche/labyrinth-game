import helper.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static helper.Constants.*;

public class Exit extends JComponent {

    private String imagePath;
    private ImageIcon image;
    private int scaleImage;
    private int stepSize;

    private Tuple<Integer, Integer> position;
    private Tuple<Integer, Integer> positionVisual;
    private Tuple<Integer, Integer> specificOffset;

    public Exit(int scaleImage, int stepSize){
        this.scaleImage = scaleImage;

        this.imagePath = "src/resources/pics/exit.png";
        Image imageSmall = new ImageIcon(this.imagePath).getImage();
        Image scaledImage = imageSmall.getScaledInstance(this.scaleImage, this.scaleImage, 1);
        this.image = new ImageIcon(scaledImage);

        this.stepSize = stepSize;
        specificOffset = new Tuple<>(19, 38);

        Random rand = new Random();

        int x = rand.nextInt(GAME_BOARD_WIDTH);
        int y = rand.nextInt(GAME_BOARD_HEIGHT);

        this.position = new Tuple<>(x, y);
        this.positionVisual = new Tuple<>(x * this.stepSize + specificOffset.getX(), y * this.stepSize + specificOffset.getY());
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        this.image.paintIcon(this, g, positionVisual.getX(), positionVisual.getY());
    }

}
