import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.*;
import java.awt.Color;


/**
 * Represents a 10x10 checkers board.
 * The board manages squares, chips, chip placement, chip removal,
 * and tracking the number of remaining chips for each player.
 */
public class Board extends GraphicsGroup {
    public static final int ROWS = 10;
    public static final int COLS = 10;
    private static final int SQUARE_SIZE = 75;

    private List<Chip> chips;
    private List<Square> squares;
    private int numberOfRedChips;
    private int numberOfBlueChips;

    /**
     * Constructs an empty board and initializes internal data structures.
     */
    public Board() {
        this.chips = new ArrayList<>();
        this.squares = new ArrayList<>();
    }

    /**
     * It draws a checkers board of 10x10 with white and black rectangles on the given canvas
     * @param canvas
     */
    public void drawBoardLayout(CanvasWindow canvas) {
        // 0 = white and 1 = black
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Square square = new Square(SQUARE_SIZE, row, col, isDarkSquare(row, col));
                this.add(square.getGraphics());
                squares.add(square);
            }
        }
    }

    /**
     * Determines whether a row and column are within board boundaries.
     *
     * @param row the row index
     * @param col the column index
     * @return true if the position is inside the board
     */
    public boolean isInside(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    /**
     * Determines whether a square at the given position is dark.
     * 
     * @param row
     * @param col
     * @return
     */
    public boolean isDarkSquare(int row, int col) {
        return (row + col) % 2 == 1;
    }

    /**
     * Computes the center point of a board cell in screen coordinates.
     *
     * @param row the row index
     * @param col the column index
     * @return the center point of the specified cell
     */
    public Point getCellCenter(int row, int col) {
        // Calculating the coordinate of the top-left corner of the grid in screen coordinates
        double topX = col * SQUARE_SIZE;
        double topY = row * SQUARE_SIZE;

        // Calculating the center of one cel
        double centerX = topX + (SQUARE_SIZE / 2);
        double centerY = topY + (SQUARE_SIZE / 2);

        return new Point(centerX, centerY);
    }

    /**
    * Places a chip of the specified color at a given board position.
    * Chips are only placed on dark squares within board bounds.
    *
    * @param row the target row
    * @param col the target column
    * @param color the chip color
    */
    private void placeChip(int row, int col, Color color) {
        // inside board bounds check
        if (!this.isInside(row, col)) {
            return;
        }
        // cannot place chip on white square
        if (!this.isDarkSquare(row, col)) {
            return;
        }

        Chip chip = new Chip(color); // new chip object
        chip.setBoardPosition(row, col, this); // positions new chip

        this.add(chip); // add chip to the board
    }

    /**
     * Places all starting chips in their initial positions.
     * Red chips are placed on the top rows and blue chips on the bottom rows.
     * Chip counts are updated accordingly.
     */
    public void placeStartingChips() {
        // top player
        for (int row = 0; row < 4; row++) { // only three
            for (int col = 0; col < Board.COLS; col++) { // all the way till the end of board
                if (this.isDarkSquare(row, col)) {
                    placeChip(row, col, Color.RED);
                    this.numberOfRedChips += 1;
                }
            }
        }
        // bottom player
        for (int row = Board.ROWS - 4; row < Board.ROWS; row++) { // start from 6th row (Boards.ROWS-4)
            for (int col = 0; col < Board.COLS; col++) {
                if (this.isDarkSquare(row, col)) {
                    placeChip(row, col, Color.BLUE);
                    this.numberOfBlueChips +=1; 
                }
            }
        }
    }

    /**
     * It returns a Chip object found at requested row and column. If none found, it returns null.
     * @param row is an integer
     * @param col is an integer
     * @return
     */
    public Chip getChipAtGridPosition(int row, int col) {
        for (Chip chip : chips) {
            if (chip.getRow() == row && chip.getCol() == col) {
                return chip;
            }
        }
        return null;
    }

   /**
     * Determines whether a chip was clicked based on a graphical position.
     *
     * @param point the mouse click position
     * @return the chip at that location, or null if none
     */
    public Chip checkForChipAtGraphicsPosition(Point point) {
        GraphicsObject object = getElementAt(point);
        if (object != null) {
            for (Chip chip : chips) {
                if (object == chip.getGraphics()) {
                    return chip;
                }
            }
        }
        return null;
    }

    /**
     * Determines whether a square was clicked based on a graphical position.
     *
     * @param point the mouse click position
     * @return the square at that location, or null if none
     */
    public Square checkForSquareAtGraphicsPosition(Point point) {
        GraphicsObject object = getElementAt(point);
        if (object != null) {
            for (Square square : squares) {
                if (object == square.getGraphics()) {
                    return square;
                }
            }
        }
        return null;
    }

    /**
     * Adds a chip to the board graphics and internal chip list.
     *
     * @param chip the chip to add
     */
    public void add(Chip chip) {
        this.add(chip.getGraphics());
        chips.add(chip);
    }

   /**
     * Returns an unmodifiable list of all squares on the board.
     *
     * @return the list of board squares
     */
    public List<Square> getSquares() {
        return Collections.unmodifiableList(squares);
    }

    /**
     * Returns the number of remaining blue chips.
     *
     * @return number of blue chips
     */
    public int getNumberOfBlueChips() {
        return numberOfBlueChips;
    }

    /**
     * Returns the number of remaining red chips.
     *
     * @return number of red chips
     */
    public int getNumberOfRedChips() {
        return numberOfRedChips;
    }

    /**
     * Decrements the appropriate chip counter when a chip is captured.
     *
     * @param chip the captured chip
     */
    public void updatedChipCount(Chip chip) {
        Color color = chip.getColor();
        if (color == Color.red) {
            numberOfRedChips -= 1;
        } else {
            numberOfBlueChips -=1;
        }
    }

    /**
     * Removes a chip from the board.
     * This method removes the chip graphics, removes it from the chip list,
     * and updates the corresponding chip count.
     *
     * @param chip the chip to remove
     */
    public void removeChip(Chip chip){
        if (chip == null) {
            return;
        }

        this.remove(chip.getGraphics());
        chips.remove(chip);
        updatedChipCount(chip); // updates chip count with and removes chip with one helper call now
    }
}