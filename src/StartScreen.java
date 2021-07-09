import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static helper.Constants.*;

public class StartScreen extends AbstractScreen {

    private static final int CONDITION = JComponent.WHEN_IN_FOCUSED_WINDOW;

    private BufferedImage bloodImage;
    private Timer timer;
    private int yDelta = 3 * 2;
    private int yPos = 100;

    public StartScreen() {
        super(new GridBagLayout());

        try {
            bloodImage = ImageIO.read(StartScreen.class.getResource("/resources/pics/startLabel.png"));
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
        }

        this.getInputMap(CONDITION).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ESC_KEY);
        this.getActionMap().put(ESC_KEY, key_esc);

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                startMovingImage();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                if (!(timer == null)) {
                    timer.stop();
                }
            }
        });
    }

    private Action key_esc = new AbstractAction(ESC_KEY) {
        @Override
        public void actionPerformed(ActionEvent e) {
            yPos = 100;
            Game.getCl().show(Game.getCardPanel(), "playScreen");
        }
    };

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
