import javax.swing.*;
import java.awt.*;

public class UpperBar extends JPanel {

    private LifeHeart[] lifeHearts;
    private Key[] keys;

    private final int HEART_COUNT = 3;
    private final int KEY_COUNT = 3;

    private int currHeartCount = 3;
    private int currKeyCount = 0;

    private int scaleImage = 50;


    public UpperBar() {
        super(new GridBagLayout());

        setBackground(Color.BLACK);

        lifeHearts = new LifeHeart[HEART_COUNT];
        keys = new Key[KEY_COUNT];

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 0, 0);

        try {

            for (int i = 0; i < KEY_COUNT; i++) {
                keys[i] = new Key(scaleImage);
                gbc.gridx = i;
                add(keys[i], gbc);
            }

            for (int i = 0; i < HEART_COUNT; i++) {
                if (i == 0){
                    gbc.insets = new Insets(0, 597, 0, 0);
                }else{
                gbc.insets = new Insets(0, 20, 0, 0);

                }
                lifeHearts[i] = new LifeHeart(scaleImage);
                gbc.gridx = i + 3;
                add(lifeHearts[i], gbc);

            }
        } catch (Exception ex) {
            System.err.println(ex);
            System.exit(-1);
        }
    }

    public void deleteHeart(){
        lifeHearts[--currHeartCount].delete();
    }

    public void addKey(){
        keys[currKeyCount++].add();
    }

    public int getWidthHearts(){
        return scaleImage * HEART_COUNT + (20 * ( HEART_COUNT - 1))  + 3 * 20;
    }

    public int getWidthKeys(){
        return scaleImage * KEY_COUNT + (20 * ( KEY_COUNT - 1));
    }

    public int getBarWidth(){
        return getWidthHearts() + getWidthKeys() + 20;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public Dimension getPreferredSize(){
        return new Dimension(getBarWidth(), 50);
    }
}