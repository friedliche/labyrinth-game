import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HelpScreen extends AbstractScreen{

    public HelpScreen(){
        super(new GridBagLayout());
        setBorder(new EmptyBorder(90, 90, 0, 90));
        setBackground(Color.BLACK);

        this.setSupportedButtons(new String[]{"BACK"});

        GridBagConstraints gbc = new GridBagConstraints();

        setGBCForTitle(gbc);
        PixelText title = new PixelText("THE MURDERERS LABYRINTH", 0.75f);
        add(title, gbc);

        setGBCForSubtitle(gbc);
        PixelText subtitle = new PixelText("    HELP", 0.4f);
        add(subtitle, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 1000;
        gbc.ipady = 200;
        gbc.insets = new Insets(75,0,0,0);

        // help text
        PixelText helpText = new PixelText("USE A-W-S-D TO MOVE\n\nYOU NEED ALL THREE KEYS TO EXIT THE LABYRINTH\n\nPRESS ESC TO GO BACK TO MENU DURING GAME\n\nTHE GREEN BUBBLES ARE YOUR ENEMIES", Color.CYAN, Color.WHITE, 0.2f);
        add(helpText, gbc);

        setGBCForButtonPanel(gbc);

        JPanel buttons = createButtons(gbc);
        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
