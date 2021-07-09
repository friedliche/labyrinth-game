import javax.swing.*;
import java.awt.*;

public class TitleScreen extends AbstractScreen {

    public TitleScreen(){
        super(new GridBagLayout());

        this.setSupportedButtons(new String[]{"NEW GAME", "SETTINGS", "HELP", "EXIT"});

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
