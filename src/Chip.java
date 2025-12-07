import java.awt.Color;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

public class Chip {
    private Ellipse chip;
    private static final int RADIUS = 20;
    private Color color;

    // board coordinates
    private int row;
    private int col;
    

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

    // board position of chip
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    // sets board position given row, col
    public void setBoardPositions(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        Point position = board.getCellCenter(row, col);
        chip.setCenter(position);
    }
}
