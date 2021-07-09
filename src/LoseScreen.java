import javax.swing.*;
import java.awt.*;

public class LoseScreen extends AbstractScreen{

    public LoseScreen() {
        super(new GridBagLayout());

        this.setSupportedButtons(new String[]{"MENU", "EXIT"});

        GridBagConstraints gbc = new GridBagConstraints();

        setGBCForTitle(gbc);

        PixelText title = new PixelText("YOU LOSE", 0.75f, Color.RED);
        add(title, gbc);

        setGBCForButtonPanel(gbc);

        JPanel buttons = createButtons(gbc);
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
