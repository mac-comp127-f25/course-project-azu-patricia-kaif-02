import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Square {
    private Rectangle graphics;
    private int row;
    private int col;
    private Runnable pendingMove;

    
/**
 * Authors: Azucena Ventimilla, Patricia Escobar, Kaif Kassam
 * Creates the squares for the Checkers game 
 * @param size
 * @param row
 * @param col
 * @param isDark
*/
    public Square(double size, int row, int col, boolean isDark) {
        this.row = row;
        this.col = col;
        this.graphics = new Rectangle(col * size, row * size, size, size);
        if (isDark) {
            graphics.setFillColor(Color.BLACK);
        } else {
            graphics.setFillColor(Color.WHITE);
        }
        graphics.setFilled(true);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Rectangle getGraphics() {
        return graphics;
    }

    public void setPendingMove(Runnable pendingMove) {
        this.pendingMove = pendingMove;

        if (pendingMove != null) {
            graphics.setStrokeWidth(5);
            graphics.setStrokeColor(Color.magenta);
        } else {
            graphics.setStroked(false);
        }
    }

    public Runnable getPendingMove() {
        return pendingMove;
    }
}