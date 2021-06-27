import javax.swing.*;
import java.awt.*;

public class WinScreen extends AbstractScreen{

    public WinScreen() {
        super(new GridBagLayout());

        this.setSupportedButtons(new String[]{"MENU", "EXIT"});

        GridBagConstraints gbc = new GridBagConstraints();

        setGBCForTitle(gbc);

        PixelText title = new PixelText("YOU WIN", 0.75f);
        add(title, gbc);

        setGBCForButtonPanel(gbc);

        JPanel buttons = createButtons(gbc);
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
