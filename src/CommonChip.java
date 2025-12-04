import java.awt.Color;

import edu.macalester.graphics.Ellipse;

public class CommonChip {
    private Ellipse chip;
    private static final int RADIUS = 20;
    private Color color;

    public CommonChip(double x, double y, Color color) {
        this.color = color;
        this.chip = new Ellipse(x, y, 2 * RADIUS, 2 * RADIUS);
        this.chip.setFillColor(color);
        // chip.setCenter(x,y);
    }

    public void setColor() {
        chip.setFillColor(color);
    }
 
    public void move() {
        
    }
    public void getX() {
        chip.getX();
    }

    public void getY() {
        chip.getY();
    }

    public Ellipse getChip() {
        return chip;
    }
}
