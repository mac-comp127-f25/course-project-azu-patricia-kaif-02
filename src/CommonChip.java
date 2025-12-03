import java.awt.Color;

import edu.macalester.graphics.Ellipse;

public class CommonChip {
    private Ellipse chip;
    private double radius;
    private double width;
    private double height;
    private Color color;

    public CommonChip(double radius, double width, double height) {
        this.chip = new Ellipse(0, 0, width, height);
    }

    public void setCenter(double x, double y) {
        chip.setCenter(x,y);
    }

    public void setColor() {
        chip.setFillColor(color);
    }
 
    public void move() {
        
    }
}
