import javax.swing.*;
import java.awt.*;

public abstract class Collectable extends JLabel {

    private String imagePath;
    private String imageHidePath;
    private ImageIcon image;
    private int scaleImage;


    public Collectable(int scaleImage, String imagePath, String imageHidePath, boolean showHidden){
        this.imagePath = imagePath;

        String firstVisiblePath;
        if (showHidden)
            firstVisiblePath = imageHidePath;
        else
            firstVisiblePath = imagePath;
        Image imageSmall = new ImageIcon(firstVisiblePath).getImage();
        Image scaledImage = imageSmall.getScaledInstance(scaleImage, scaleImage, 1);
        this.image = new ImageIcon(scaledImage);
        this.scaleImage = scaleImage;
        this.imageHidePath = imageHidePath;
    }

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
        Image image = new ImageIcon(imagePath).getImage();
        Image scaledImage = image.getScaledInstance(this.getScaleImage(), this.getScaleImage(), 1);
        this.image = new ImageIcon(scaledImage);
    }

    public void delete(){
        Image image = new ImageIcon(imageHidePath).getImage();
        Image scaledImage = image.getScaledInstance(this.getScaleImage(), this.getScaleImage(), 1);
        this.image = new ImageIcon(scaledImage);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.getImage().paintIcon(this, g, 0, 0);
    }

    public Dimension getPreferredSize(){
        return new Dimension(scaleImage , 50);
    }
}
