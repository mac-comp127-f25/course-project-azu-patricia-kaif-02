import java.awt.Color;

import edu.macalester.graphics.Ellipse;

public class Chip {
    private Ellipse chip;
    private static final int RADIUS = 20;
    private Color color;

    public Chip(double x, double y, Color color) {
        this.color = color;
        this.chip = new Ellipse(x, y, 2 * RADIUS, 2 * RADIUS);
        this.chip.setFillColor(color);
        // chip.setCenter(x,y);
    }

    public void setColor(Color color) {
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

    public void setPosition(double x, double y) {
        chip.setPosition(x,y);
    }
}
