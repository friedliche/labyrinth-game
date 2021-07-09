import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private static ExecutorService threadPool;

    //create container for cards
    private static CardLayout cl;
    private static JPanel cardPanel;

    private static JFrame createJFrame(){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 760);

        return frame;
    }

    public static void main(String[] args) {

        threadPool = Executors.newFixedThreadPool(6);

        cl = new CardLayout();
        cardPanel = new JPanel(cl);

        TitleScreen titleScreenPanel = new TitleScreen();
        StartScreen startScreenPanel = new StartScreen();
        SettingsScreen settingsScreenPanel = new SettingsScreen();
        HelpScreen helpScreenPanel = new HelpScreen();
        PlayScreen playScreenPanel = new PlayScreen();
        playScreenPanel.setIsPlayScreen(true);
        WinScreen winScreenPanel = new WinScreen();
        LoseScreen loseScreenPanel = new LoseScreen();
        PauseScreen pauseScreenPanel = new PauseScreen();
        SaveScreen saveScreenPanel = new SaveScreen();
        cardPanel.add(titleScreenPanel, "titleScreen");
        cardPanel.add(playScreenPanel, "playScreen");
        cardPanel.add(settingsScreenPanel, "settingsScreen");
        cardPanel.add(helpScreenPanel, "helpScreen");
        cardPanel.add(startScreenPanel, "startScreen");
        cardPanel.add(winScreenPanel, "winScreen");
        cardPanel.add(loseScreenPanel, "loseScreen");
        cardPanel.add(pauseScreenPanel, "pauseScreen");
        cardPanel.add(saveScreenPanel, "saveScreen");

        JFrame frame = createJFrame();
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static CardLayout getCl(){
        return cl;
    }

    public static JPanel getCardPanel(){
        return cardPanel;
    }

    public static ExecutorService getThreadPool(){
        return threadPool;
    }
}