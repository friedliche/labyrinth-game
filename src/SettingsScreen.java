import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends AbstractScreen {

    public SettingsScreen(){
        super(new GridBagLayout());

        this.setSupportedButtons(new String[]{"BACK"});

        GridBagConstraints gbc = new GridBagConstraints();

        setGBCForTitle(gbc);

        PixelText title = new PixelText("THE MURDERERS LABYRINTH", 0.75f);
        add(title, gbc);

        setGBCForSubtitle(gbc);

        PixelText subtitle = new PixelText("    SETTINGS", 0.4f);
        add(subtitle, gbc);

        setGBCForButtonPanel(gbc);

        JPanel buttons = createButtons(gbc);
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
