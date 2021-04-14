import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;

public abstract class PixelGraphics extends JComponent {

    private final int pixelSize = 10;
    private final int letterWidth = 5 * pixelSize;
    private final int letterHeight = 6 * pixelSize;
    private final int spacebar = 2 * pixelSize;
    private final int lineDistance = 3 * pixelSize;
    private final int spaceBetweenLetters = 1 * pixelSize;

    private String letters;

    // default values
    private float scalingFactor = 0.75f;
    private int xOffset = 0;
    private int yOffset = 0;

    private Color bgColor = Color.WHITE;
    private Color fgColor = Color.BLACK;

    /**
     *  maps save polygon points for each letter
     */
    private Map<String, int[]> xletterPolyMap = Map.ofEntries(
            entry("A", new int[]{0, 0, 1, 1, 4, 4, 5, 5, 1, 1, 4, 4, 1, 1}),
            entry("B", new int[]{1, 4, 4, 0, 0, 4, 4, 5, 5, 1, 1, 5, 5, 1}),
            entry("C", new int[]{0, 0, 5, 5, 4, 4, 1, 1, 4, 4, 5, 5, }),
            entry("D", new int[]{0, 0, 4, 4, 5, 5, 1, 1, 4, 4}),
            entry("E", new int[]{0, 0, 4, 4, 1, 1, 3, 3, 1, 1, 4, 4}),
            entry("G", new int[]{1, 1, 5, 5, 2, 2, 4, 4, 0, 0, 4, 4}),
            entry("H", new int[]{0, 0, 1, 1, 4, 4, 5, 5, 4, 4, 1, 1}),
            entry("I", new int[]{1, 1, 2, 2, 1, 1, 4, 4, 3, 3, 4, 4}),
            entry("K", new int[]{0, 0, 1, 1, 4, 4, 5, 5, 2, 2, 5, 5, 4, 4, 1, 1}),
            entry("L", new int[]{0, 0, 5, 5, 1, 1}),
            entry("M", new int[]{0, 0, 1, 1, 4, 4, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1}),
            entry("N", new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1}),
            entry("O", new int[]{1, 1, 4, 4, 5, 5, 0, 0, 4, 4}),
            entry("P", new int[]{0, 0, 1, 1, 3, 3, 4, 4, 1, 1, 3, 3}),
            entry("R", new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 3, 3, 4, 4, 1, 1, 3, 3}),
            entry("S", new int[]{1, 1, 5, 5, 0, 0, 4, 4, 0, 0, 4, 4}),
            entry("T", new int[]{0, 0, 2, 2, 3, 3, 5, 5}),
            entry("U", new int[]{0, 0, 5, 5, 4, 4, 1, 1}),
            entry("V", new int[]{0, 0, 2, 2, 3, 3, 5, 5, 4, 4, 1, 1}),
            entry("W", new int[]{0, 0, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1}),
            entry("X", new int[]{0, 0, 2, 2, 0, 0, 1, 1, 4, 4, 5, 5, 3, 3, 5, 5, 4, 4, 1, 1}),
            entry("Y", new int[]{0, 0, 2, 2, 3, 3, 5, 5, 4, 4, 1, 1})
    );

    private Map<String, int[]> yletterPolyMap =Map.ofEntries(
            entry("A", new int[]{1, 6, 6, 4, 4, 6, 6, 1, 1, 0, 0, 3, 3, 1}),
            entry("B", new int[]{1, 1, 0, 0, 6, 6, 1, 1, 2, 2, 3, 3, 5, 5}),
            entry("C", new int[]{1, 5, 5, 4, 4, 6, 6, 0, 0, 2, 2, 1, }),
            entry("D", new int[]{0, 6, 6, 1, 1, 5, 5, 1, 1, 0}),
            entry("E", new int[]{0, 6, 6, 5, 5, 3, 3, 2, 2, 1, 1, 0}),
            entry("G", new int[]{0, 6, 6, 3, 3, 4, 4, 5, 5, 1, 1, 0}),
            entry("H", new int[]{0, 6, 6, 3, 3, 6, 6, 0, 0, 2, 2, 0}),
            entry("I", new int[]{0, 1, 1, 5, 5, 6, 6, 5, 5, 1, 1, 0}),
            entry("K", new int[]{0, 6, 6, 4, 4, 6, 6, 5, 5, 1, 1, 0, 0, 2, 2, 0}),
            entry("L", new int[]{0, 6, 6, 5, 5, 0}),
            entry("M", new int[]{0, 6, 6, 3, 3, 6, 6, 0, 0, 1, 1, 5, 5, 1, 1, 0}),
            entry("N", new int[]{0, 6, 6, 2, 2, 4, 4, 5, 5, 6, 6, 0, 0, 4, 4, 2, 2, 1, 1, 0}),
            entry("O", new int[]{0, 6, 6, 1, 1, 5, 5, 1, 1, 0}),
            entry("P", new int[]{0, 6, 6, 4, 4, 1, 1, 3, 3, 1, 1, 0}),
            entry("R", new int[]{0, 6, 6, 4, 4, 5, 5, 6, 6, 5, 5, 1, 1, 3, 3, 1, 1, 0}),
            entry("S", new int[]{0, 3, 3, 5, 5, 6, 6, 2, 2, 1, 1, 0}),
            entry("T", new int[]{0, 1, 1, 6, 6, 1, 1, 0}),
            entry("U", new int[]{0, 5, 5, 0, 0, 6, 6, 0}),
            entry("V", new int[]{0, 3, 3, 6, 6, 3, 3, 0, 0, 5, 5, 0}),
            entry("W", new int[]{0, 5, 5, 0, 0, 6, 6, 2, 2, 6, 6, 0}),
            entry("X", new int[]{0, 1, 1, 5, 5, 6, 6, 4, 4, 6, 6, 5, 5, 1, 1, 0, 0, 2, 2, 0}),
            entry("Y", new int[]{0, 1, 1, 6, 6, 1, 1, 0, 0, 3, 3, 0})
    );


    public PixelGraphics(String letters){
        super();
        this.letters = letters;
    }

    public void paintComponent(Graphics g){

        int totalXOffset = xOffset;
        int totalYOffset = yOffset;

        System.out.println(this.letters);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fgColor);

        // iterate through letters and draw them
        for (int i = 0; i < this.letters.length(); i++) {
            char letter = this.letters.charAt(i);

            switch(letter){
                case ' ':
                    totalXOffset += spacebar;
                    break;
                case '\n':
                    totalXOffset = 0;
                    totalYOffset += lineDistance + letterHeight;
                    break;
                default:
                    String letterAsString = String.valueOf(letter);

                    // move letter points by offset
                    if (!xletterPolyMap.containsKey(letterAsString) || !yletterPolyMap.containsKey(letterAsString)) {
                        System.out.println("Missing representation of " + letterAsString + " in letters map");
                        System.exit(-1);
                    }

                    int [] xletter = xletterPolyMap.get(letterAsString).clone();
                    int [] yletter = yletterPolyMap.get(letterAsString).clone();

                    for (int j = 0; j < xletter.length; j++){
                        xletter[j] = (int)((xletter[j] *  this.pixelSize + totalXOffset) * this.scalingFactor);
                        yletter[j] = (int)((yletter[j] *  this.pixelSize) * this.scalingFactor + totalYOffset*this.scalingFactor);
                    }

                    Polygon poly = new Polygon(xletter, yletter, yletter.length);
                    g2d.fillPolygon(poly);

                    totalXOffset += letterWidth + spaceBetweenLetters;
                    break;
            }
        }
    }

    public String getLetters(){
        return this.letters;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void setFgColor(Color fgColor) {
        this.fgColor = fgColor;
    }

    public void setxOffset(int offset){
        this.xOffset = offset;
    }

    public void setyOffset(int offset){
        this.yOffset = offset;
        System.out.println(this.yOffset);
    }

    public void setScalingFactor(float factor) {
        this.scalingFactor = factor;
    }

}
