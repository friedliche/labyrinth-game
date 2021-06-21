import helper.Tuple;

import javax.swing.*;
import java.awt.*;

public abstract class Collectable extends JComponent {

    private String imagePath;
    private String imageHidePath;
    private ImageIcon image;
    private int scaleImage;
    private boolean inGameBoard; // collectable or not

    private Tuple<Integer, Integer> position;
    private Tuple<Integer, Integer> positionVisual;


    public Collectable(int scaleImage, String imagePath, String imageHidePath, boolean showHidden, boolean inGameBoard){
        this.imagePath = imagePath;

        String firstVisiblePath;
        if (showHidden && !inGameBoard)
            firstVisiblePath = imageHidePath;
        else
            firstVisiblePath = imagePath;
        Image imageSmall = new ImageIcon(firstVisiblePath).getImage();
        Image scaledImage = imageSmall.getScaledInstance(scaleImage, scaleImage, 1);
        this.image = new ImageIcon(scaledImage);
        this.scaleImage = scaleImage;
        this.imageHidePath = imageHidePath;
        this.inGameBoard = inGameBoard;

        position = new Tuple<>(0, 0);
        positionVisual = new Tuple<>(0, 0);
    }

    public Tuple<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Tuple<Integer, Integer> position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Tuple<Integer, Integer> getPositionVisual() {
        return positionVisual;
    }

    public void setPositionVisual(Tuple<Integer, Integer> positionVisual) {
        this.positionVisual.setX(positionVisual.getX());
        this.positionVisual.setY(positionVisual.getY());    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getScaleImage() {
        return scaleImage;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setScaleImage(int scaleImage) {
        this.scaleImage = scaleImage;
    }

    public void add(){
        if (inGameBoard) {
            this.image = new ImageIcon("src/resources/pics/empty.png");
        }else {
            Image image = new ImageIcon(imagePath).getImage();
            Image scaledImage = image.getScaledInstance(this.getScaleImage(), this.getScaleImage(), 1);
            this.image = new ImageIcon(scaledImage);
        }
        repaint();
    }

    public void delete(){
        if (inGameBoard) {
            this.image = new ImageIcon("src/resources/pics/empty.png");

        }else{
            Image image = new ImageIcon(imageHidePath).getImage();
            Image scaledImage = image.getScaledInstance(this.getScaleImage(), this.getScaleImage(), 1);
            this.image = new ImageIcon(scaledImage);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.getImage().paintIcon(this, g, positionVisual.getX(), positionVisual.getY());
    }

    public Dimension getPreferredSize(){
        return new Dimension(scaleImage , 50);
    }
}
