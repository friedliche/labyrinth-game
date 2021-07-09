import javax.swing.*;
import java.awt.*;

public class PauseScreen extends AbstractScreen{

    public PauseScreen(){
        super(new GridBagLayout());

        this.setSupportedButtons(new String[]{"CONTINUE", "NEW GAME", "SAVE GAME", "SETTINGS", "HELP", "EXIT"});

        GridBagConstraints gbc = new GridBagConstraints();
        setGBCForTitle(gbc);

        PixelText title = new PixelText("THE MURDERERS LABYRINTH", 0.75f);
        add(title, gbc);

        setGBCForSubtitle(gbc);

        PixelText subtitle = new PixelText("     PAUSE", 0.4f);
        add(subtitle, gbc);

        setGBCForButtonPanel(gbc);

        JPanel buttons = this.createButtons(gbc);
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
