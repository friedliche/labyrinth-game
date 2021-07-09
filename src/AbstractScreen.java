import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractScreen  extends JPanel {

    private String[] supportedButtons;
    private float textScale = 0.3f;
    private boolean isPlayScreen;

    public AbstractScreen(GridBagLayout gbl){
        super(gbl);
        isPlayScreen = false;
        setBorder(new EmptyBorder(90, 90, 0, 90));
        setBackground(Color.BLACK);
    }

    public AbstractScreen(GridBagLayout gbl, int topPadding, int sitePadding){
        super(gbl);
        isPlayScreen = false;
        setBorder(new EmptyBorder(topPadding, sitePadding, 0, sitePadding));
        setBackground(Color.BLACK);
    }

    public void setIsPlayScreen(boolean isPlayScreen){
        this.isPlayScreen = isPlayScreen;
    }
    public boolean getIsPlayScreen(){
        return this.isPlayScreen;
    }

    public void setGBCForTitle(GridBagConstraints gbc){
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.ipadx = 1000;
        gbc.ipady = 90;
        gbc.anchor = GridBagConstraints.NORTH;
    }

    public void setGBCForSubtitle(GridBagConstraints gbc){
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 1000;
        gbc.ipady = 45;
    }

    public void setGBCForButtonPanel(GridBagConstraints gbc){
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 160;
        gbc.ipady = 15;
        gbc.insets = new Insets(0,0,20,0);
    }

    public JPanel createButtons(GridBagConstraints gbc){
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(Color.BLACK);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        for(String name: this.supportedButtons){
            PixelButton button = new PixelButton(name, new Color(123, 123, 12), new Color(123, 23, 192), new Dimension(150, 50), textScale);
            gbc.gridy += 1;
            buttons.add(button, gbc);
        }
        return buttons;
    }

    public Dimension getPreferredSize(){
        return new Dimension(1200, 760);
    }

    public void setSupportedButtons(String[] buttons){
        this.supportedButtons = buttons;
    }
}
