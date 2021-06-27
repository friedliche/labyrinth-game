import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.Map.entry;

public class PixelButton extends PixelGraphics implements MouseListener {
    private Dimension preferredSize = new Dimension(100, 80);
    private Color default_color = new Color(123, 23, 192);

    // default value
    private String sound = "C";
    private String linkedPanel;

    private ExecutorService threadPool;
    private Future<?> threadFuture;

    private Map<String, String[]> nameToAttrMap = Map.ofEntries(
         entry("START", new String[]{"C", "startScreen"}),
         entry("SETTINGS", new String[]{"D", "settingsScreen"}),
         entry("HELP", new String[]{"E", "helpScreen"}),
         entry("EXIT", new String[]{"F", "exitScreen"}),
         entry("BACK", new String[]{"G", "titleScreen"}),
         entry("MENU", new String[]{"G", "titleScreen"})
    );

    public PixelButton(String letters) {
        super(letters);

        this.setScalingFactor(0.75f);
        this.setBgColor(Color.BLACK);
        this.setFgColor(Color.WHITE);

        enableInputMethods(true);
        addMouseListener(this);
    }

    public PixelButton(String letters, Color bg, Color fg, Dimension dim, float scale) {
        super(letters);

        this.setScalingFactor(scale);
        this.setBgColor(bg);
        this.setFgColor(fg);
        this.preferredSize.setSize(dim.width*scale, dim.height*scale);

        this.setxOffset((dim.width-(letters.length()*10))/2*6);
        this.setyOffset(dim.height/2);
        this.sound = nameToAttrMap.get(letters)[0];
        this.linkedPanel = nameToAttrMap.get(letters)[1];

        threadPool = Game.getThreadPool();

        enableInputMethods(true);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        try{
            switch(this.getLetters()){
                case "EXIT":
                    System.exit(0);
                    break;
                default:
                    Game.getCl().show(Game.getCardPanel(), this.linkedPanel);
                    break;
            }
        }
        catch(Exception exp){
            System.out.println(exp.toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setFgColor(Color.CYAN);
        repaint();
        Runnable r = new ButtonSound(this.sound);
        threadFuture = threadPool.submit(r);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setFgColor(default_color);
        repaint();
        threadFuture.cancel(true);
    }

    public Dimension getPreferredSize(){

        return this.preferredSize;
    }
    public Dimension getMinimumSize(){

        return new Dimension(50, 60);
    }
    public Dimension getMaximumSize(){

        return this.preferredSize;
    }

    public void paintComponent(Graphics g){
            super.paintComponent(g);
    }

    private class ButtonSound implements Runnable {

        private String sound;

        public ButtonSound(String s)
        {
            sound = s;
        }

        @Override
        public void run() {
            try{
                Pattern p1 = new Pattern("V0 I[Xylophone] %s", sound);
                Player player = new Player();
                player.play(p1);
            }
            catch(Exception e){
                System.out.println("Couldn't play sound: " + e.toString());
            }
        }
    }
}


