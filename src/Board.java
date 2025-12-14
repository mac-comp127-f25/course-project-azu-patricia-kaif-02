import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.macalester.graphics.*;
import java.awt.Color;


public class Board extends GraphicsGroup {
    public static final int ROWS = 10;
    public static final int COLS = 10;
    private static final int SQUARE_SIZE = 75;

    private List<Chip> chips;
    private List<Square> squares;
    private int numberOfRedChips;
    private int numberOfBlueChips;

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
     * It checks if the chip is inside the board. Returns true if it is otherwise is false
     */
    public boolean isInside(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    /**
     * 
     * @param row
     * @param col
     * @return
     */
    public boolean isDarkSquare(int row, int col) {
        return (row + col) % 2 == 1;
    }

    public Point getCellCenter(int row, int col) {
        // Calculating the coordinate of the top-left corner of the grid in screen coordinates
        double topX = col * SQUARE_SIZE;
        double topY = row * SQUARE_SIZE;

        // Calculating the center of one cel
        double centerX = topX + (SQUARE_SIZE / 2);
        double centerY = topY + (SQUARE_SIZE / 2);

        return new Point(centerX, centerY);
    }

   
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
     * It checks for the existence of an element at within the square on the given coordinates
     * 
     * @param point object
     * @return the chip at the specified position, if no chip, it returns null
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
     * It checks for the existence of the graphics of a square at the indicated point
     * @param point positions where we are looking for a square's graphics 
     * @return
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
     * It add a chip object to the board graphics group and to list of all chips
     * @param chip is a Chip object
     */
    public void add(Chip chip) {
        this.add(chip.getGraphics());
        chips.add(chip);
    }

    /**
     * It returns the list of squares that the board has such that the list cannot be modified by the class getting it
     * @return
     */
    public List<Square> getSquares() {
        return Collections.unmodifiableList(squares);
    }

    public int getNumberOfBlueChips() {
        return numberOfBlueChips;
    }

    public int getNumberOfRedChips() {
        return numberOfRedChips;
    }

    public void updatedChipCount(Chip chip) {
        Color color = chip.getColor();
        if (color == Color.red) {
            numberOfRedChips -= 1;
        } else {
            numberOfBlueChips -=1;
        }
    }


    public void removeChip(Chip chip){
        if (chip == null) {
            return;
        }
        this.remove(chip.getGraphics());
        chips.remove(chip);
        updatedChipCount(chip);
    }
}
