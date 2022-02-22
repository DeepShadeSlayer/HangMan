import acm.graphics.GRect;

public class Blank extends GRect {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 5;
    public Blank(double x, double y, char targetLetter) {
        super(x, y, WIDTH,HEIGHT);
    }
}
