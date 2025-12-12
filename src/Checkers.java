import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;


public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Board board;

    private String playerWhosActive = "red"; // in checkers one always starts with red
    private Color currentPlayerColor = Color.red;
    private GraphicsText turnIndicator = new GraphicsText("");

    private Chip selectedChip;


    public Checkers(CanvasWindow canvas) {
        this.canvas = canvas;
        board = new Board();
        board.drawBoardLayout(canvas);
        canvas.add(board);


        setupTurnDisplay();
    }

    private void setupTurnDisplay() {
        turnIndicator.setText("Player's Turn: RED");
        turnIndicator.setCenter(850, 100);
        canvas.add(turnIndicator);
    }

    private void switchTurn() {
        if (playerWhosActive.equals("red")) {
            playerWhosActive = "blue";
            turnIndicator.setText("Player's Turn: BLUE");
        } else {
            playerWhosActive = "red";
            turnIndicator.setText("Player's Turn: RED");
        }
    }

    public String playerWhosActive() {
        return playerWhosActive();
    }


    public void handleClick(CanvasWindow canvas) {
        canvas.onClick(event -> {
            Chip chip = board.checkForChipAtGraphicsPosition(event.getPosition()); // Correctly identifying a chip
            if (chip != null) {
                selectChip(chip);
            }
        });
    }

    public void selectChip(Chip chip) {
        if (selectedChip != null) {
            selectedChip.setSelected(false);
        }
        selectedChip = chip;
        chip.setSelected(true);
        for (Square square: board.getSquares()) {
            square.setHighlighted(
                chipCanMove(selectedChip, square.getRow(), square.getCol()));
        }
        // System.out.println(chipCanMove(selectedChip, selectedChip.getRow()-1, selectedChip.getCol() -1)); // It is correctly identifying the possible moves
        // chip.setBoardPosition(selectedChip.getRow()-1, selectedChip.getCol() -1, board);
    }

    /**
     * This method informs about the possibility of a chip to move to the destination row and column.
     * 
     * @param chip
     * @param dstRow
     * @param dstCol
     * @return
     */
    public boolean chipCanMove(Chip chip, int dstRow, int dstCol) {
        // NorthEast direction
        // int xNorthEast = chip.getCol() + 1;
        // int yNorthEast = chip.getRow() - 1;

        // // NorthWest direction
        // int xNorthWest = chip.getCol() - 1;
        // int yNorthWest = chip.getRow() - 1;

        // // SouthEst direction
        // int xSouthEast = chip.getCol() + 1;
        // int ySouthEast = chip.getRow() + 1;

        // // NorthWest direction
        // int xSouthhWest = chip.getCol() - 1;
        // int ySouthWest = chip.getRow() + 1;

        // // Checking for neighbords in the North direction
        // if (this.getChipAtGridPosition(xNorthWest, yNorthWest) != null) {
        // return true;
        // }
        // if (this.getChipAtGridPosition(xNorthWest, yNorthWest) != null) {
        // return true;
        // }

        // // Checking for neighbords in the South direction
        // if (this.getChipAtGridPosition(xSouthEast, ySouthEast) != null) {
        // return true;
        // }

        // if (this.getChipAtGridPosition(xSouthhWest, ySouthWest) != null) {
        // return true;
        // }

        // return false;
        double distance = Math.hypot(dstRow - chip.getRow(), dstCol - chip.getCol());

        // Moving to an adjacent, empty square
        if (distance == Math.sqrt(2) && board.getChipAtGridPosition(dstRow, dstCol) == null) {
            return true;
        }
        // Checking if there is no other chip object on the destination square, then it can move there
        // if (getChipAtGridPosition(dstRow, dstCol) == null) {
        // return true;
        // }
        // Checking the option when we are skipping a square. If there is a chip and it it opposite color
        // then it
        // is possible to move there
        // Checking for chips NorthWest to local chip object
        if (distance <= Math.sqrt(8)) {
            // When chip object wants to move north-east, checking on skipped square
            if (board.getChipAtGridPosition(dstRow + 1, dstCol - 1) != null &&
                board.getChipAtGridPosition(dstRow + 1, dstCol - 1).getColor() != chip.getColor()) {
                return true;
            }
            // When chip object wants to move north-west, checking on skipped square
            if (board.getChipAtGridPosition(dstRow + 1, dstCol + 1) != null &&
                board.getChipAtGridPosition(dstRow + 1, dstCol + 1).getColor() != chip.getColor()) {
                return true;
            }

            // When chip object wants to move south-west, checking on skipped square
            if (board.getChipAtGridPosition(dstRow - 1, dstCol + 1) != null &&
                board.getChipAtGridPosition(dstRow - 1, dstCol + 1).getColor() != chip.getColor()) {
                return true;
            }
            // When chip object wants to move south-east, checking on skipped square
            if (board.getChipAtGridPosition(dstRow - 1, dstCol - 1) != null &&
                board.getChipAtGridPosition(dstRow - 1, dstCol - 1).getColor() != chip.getColor()) {
                return true;
            }
            
        }
        // Otherwise, it cannot move to the destined row and column
        return false;
    }

    public void game() {
        handleClick(canvas);
        board.placeStartingChips();
    }

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Checkers Board", CANVAS_WIDTH, CANVAS_HEIGHT);
        Checkers checkers = new Checkers(canvas);
        checkers.game();
    }
}
