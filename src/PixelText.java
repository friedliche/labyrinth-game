import java.awt.*;

/**
 *
 *
 */
public class PixelText extends PixelGraphics{

    private Dimension preferredSize = new Dimension(100, 20);

    public PixelText(String letters, Color bg, Color fg, float scale){
        super(letters);
        this.setBgColor(bg);
        this.setFgColor(fg);
        this.setScalingFactor(scale);
//        setSize((int) (preferredSize.width*scale), (int) (preferredSize.height*scale));
    }

    public PixelText(String letters){
        super(letters);
        this.setScalingFactor(0.75f);
        this.setBgColor(Color.BLACK);
        this.setFgColor(Color.WHITE);
    }

    public PixelText(String letters, float scale){
        super(letters);
        this.setBgColor(Color.BLACK);
        this.setFgColor(Color.WHITE);
        this.setScalingFactor(scale);
//        setSize((int) (preferredSize.width*scale), (int) (preferredSize.height*scale));
    }

    public PixelText(String letters, float scale, Color color){
        super(letters);
        this.setBgColor(Color.BLACK);
        this.setFgColor(color);
        this.setScalingFactor(scale);
//        setSize((int) (preferredSize.width*scale), (int) (preferredSize.height*scale));
    }

    public Dimension getPreferredSize(){
        return this.preferredSize;
    }
}
