import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TitleScreen extends AbstractScreen {

    public TitleScreen(){
        super(new GridBagLayout());
        setBorder(new EmptyBorder(90, 90, 10, 90));
        setBackground(Color.BLACK);

        this.setSupportedButtons(new String[]{"START", "SETTINGS", "HELP", "EXIT"});

        GridBagConstraints gbc = new GridBagConstraints();
        setGBCForTitle(gbc);

        PixelText title = new PixelText("THE MURDERERS LABYRINTH", 0.75f);
        add(title, gbc);

        setGBCForSubtitle(gbc);

        PixelText subtitle = new PixelText("     YOUR ADVENTURE BEGINS HERE", 0.4f);
        add(subtitle, gbc);

        setGBCForButtonPanel(gbc);

        JPanel buttons = this.createButtons(gbc);
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
