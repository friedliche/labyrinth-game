import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

public class StartScreen extends AbstractScreen {

    private BufferedImage bloodImage;
    private Timer timer;
    private int yDelta = 3 * 2;
    private int yPos = 100;

    public StartScreen(){
        super(new GridBagLayout());

        try {
            bloodImage =  ImageIO.read(StartScreen.class.getResource("/resources/pics/startLabel.png"));
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
        }

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) { }

            @Override
            public void componentMoved(ComponentEvent e) { }

            @Override
            public void componentShown(ComponentEvent e) {
                startMovingImage();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                if (!(timer==null)){
                    timer.stop();
                }
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(300, 100);
    }

    private void startMovingImage(){

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yPos += yDelta;
                if (yPos + bloodImage.getHeight() > getHeight() - 200) {
                    yPos = 100;
                    Game.getCl().show(Game.getCardPanel(), "playScreen");
                    setFocusable(true);
                }
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (bloodImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(bloodImage, 50, yPos, this);
            g2d.dispose();
        }
    }
}
