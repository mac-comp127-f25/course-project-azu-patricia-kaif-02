import java.util.ArrayList;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board extends GraphicsGroup {
    public static final int ROWS = 10;
    public static final int COLS = 10;
    private static final int TOTAL_CELLS = 100;
    private static final int CELL_SIZE = 75;

    public Board() {

    }

    public void drawBoardLayout(CanvasWindow canvas) {
        // 0 = white and 1 = black

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {


                Rectangle cell = new Rectangle(
                    col * CELL_SIZE,
                    row * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE);
                if (isDarkSquare(row, col)) {
                    cell.setFillColor(Color.BLACK);
                } else {
                    cell.setFillColor(Color.WHITE);
                }
                cell.setFilled(true);
                canvas.add(cell);
            }
        }
    }

    /**
     * It checks if the chip is inside the board. Returns true if it is otherwise is false
     */
    public boolean isInside(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }


    public boolean isDarkSquare(int row, int col) {
        return (row + col) % 2 == 1;
    }


    public Point getCellCenter(int row, int col) {
        // Calculating the coordinate of the top-left corner of the grid in screen coordinates
        double topX = col * CELL_SIZE;
        double topY = row * CELL_SIZE;


        // Calculating the center of one cel
        double centerX = topX + (CELL_SIZE / 2);
        double centerY = topY + (CELL_SIZE / 2);

        return new Point(centerX, centerY);
    }

    /**
     * It checks for the existence of an element at within the square on the given coordinates
     * 
     * @param x
     * @param y
     * @return the chip at the specified position, if no chip, it returns null
     */
    public GraphicsObject checkForChip(Point point) {
        if (getElementAt(point) != null) {
            return getElementAt(point);
        } else {
            return null;
        }
    }

}
