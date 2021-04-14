import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class AbstractScreen  extends JPanel {

    private String[] supportedButtons;
    private float textScale = 0.3f;

    public AbstractScreen(GridBagLayout gbl){
        super(gbl);
        setBorder(new EmptyBorder(90, 90, 0, 90));
        setBackground(Color.BLACK);
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
        gbc.ipadx = 130;
        gbc.ipady = 15;
        gbc.insets = new Insets(0,0,20,0);
    }

    public JPanel createButtons(GridBagConstraints gbc){
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(Color.BLACK);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        for(String name: this.supportedButtons){
            PixelButton button = new PixelButton(name, new Color(123, 123, 12), new Color(123, 23, 192), new Dimension(80, 50), textScale);
            buttons.add(button, gbc);
        }
        return buttons;
    }

    public Dimension getPreferredSize(){
        return new Dimension(1150, 760);
    }

    public void setSupportedButtons(String[] buttons){
        this.supportedButtons = buttons;
    }
}