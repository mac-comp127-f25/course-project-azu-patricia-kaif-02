import java.awt.Color;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

/**
 * Authors: Azucena Ventimilla, Patricia Escobar, Kaif Kassam
 */

public class Chip {
    private static final int RADIUS = 20;

    private Ellipse graphics;
    private Color color;

    // board coordinates
    private int row;
    private int col;

    /**
    * Constructs a chip with the specified color and initializes its graphics.
    *
    * @param color the color of the chip
    */
    public Chip(Color color) {
        this.color = color;
        this.graphics = new Ellipse(0, 0, 2 * RADIUS, 2 * RADIUS);
        this.graphics.setFillColor(color);
    }

    /**
     * Returns the graphical representation of this chip.
     *
     * @return the Ellipse used to draw the chip
     */
    public Ellipse getGraphics() {
        return graphics;
    }

    /**
     * Returns the current row of the chip on the board.
     *
     * @return the row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the current column of the chip on the board.
     *
     * @return the column index
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the board position of the chip and updates its graphical location.
     *
     * @param row the new row index
     * @param col the new column index
     * @param board the board used to compute screen coordinates
     */
    public void setBoardPosition(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        Point position = board.getCellCenter(row, col);
        graphics.setCenter(position);
    }

    /**
     * Visually marks the chip as selected or unselected.
     *
     * @param selected true to highlight the chip
     */
    public void setSelected(boolean selected) {
        if (selected) {
            graphics.setStrokeColor(Color.yellow);
            graphics.setStrokeWidth(5);
        } else {
            graphics.setStroked(false);
        }
    }

    /**
     * Returns the color of this chip.
     *
     * @return the chip color
     */
    public Color getColor() {
        return color;
    }
}
