import java.awt.Color;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;


public class Chip {
    private static final int RADIUS = 20;

    private Ellipse graphics;
    private Color color;

    // board coordinates
    private int row;
    private int col;

    public Chip(Color color) {
        this.color = color;
        this.graphics = new Ellipse(0, 0, 2 * RADIUS, 2 * RADIUS);
        this.graphics.setFillColor(color);
    }

    public Ellipse getGraphics() {
        return graphics;
    }

    // board position of chip
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // sets board position given row, col
    public void setBoardPositions(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        Point position = board.getCellCenter(row, col);
        graphics.setCenter(position);
    }
}
