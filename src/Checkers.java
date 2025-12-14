import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;


public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Board board;

    // private String playerWhosActive = "red"; // in checkers one always starts with red
    private Color currentPlayerColor = Color.red; // red player starts playing first
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
        turnIndicator.setCenter(canvas.getWidth() * 0.85, canvas.getHeight() * 0.15);
        canvas.add(turnIndicator);
    }

    private void switchTurn() {
        if (currentPlayerColor == Color.red) {
            currentPlayerColor = Color.blue;
            turnIndicator.setText("Player's Turn: BLUE");
        } else {
            currentPlayerColor = Color.red;
            turnIndicator.setText("Player's Turn: RED");
        }
    }

    public Color currentPlayer() {
        return currentPlayer();
    }

    public void handleClick(CanvasWindow canvas) {
        canvas.onClick(event -> {
            Chip chip = board.checkForChipAtGraphicsPosition(event.getPosition()); // Correctly identifying a chip
            if (chip != null && chip.getColor() == currentPlayerColor) {
                selectChip(chip);
                return;
            }
            Square square = board.checkForSquareAtGraphicsPosition(event.getPosition());
            if (square == null) {
                return;
            } else {
                Runnable pendingMove = square.getPendingMove();
                if (pendingMove != null) {
                    pendingMove.run();
                    selectChip(null);
                    switchTurn();
                }
            }
        });
    }

    public void selectChip(Chip chip) {
        if (selectedChip != null) {
            selectedChip.setSelected(false);
        }
        selectedChip = chip;
        if (selectedChip != null) {
            chip.setSelected(true);
        }
        for (Square square : board.getSquares()) {
            square.setPendingMove(
                chipCanMove(selectedChip, square.getRow(), square.getCol()));
        }
    }

    /**
     * This method informs about the possibility of a chip to move to the destination row and column.
     * 
     * @param chip
     * @param dstRow
     * @param dstCol
     * @return
     */
    public Runnable chipCanMove(Chip chip, int dstRow, int dstCol) {
        // Chip cannont move if it is null
        if (chip == null) {
            return null;
        }
        
        // Can only move into an empty space
        if (board.getChipAtGridPosition(dstRow, dstCol) != null) {
            return null;
        }

        // Moving to an adjacent square
        if (Math.abs(chip.getRow() - dstRow) == 1 && Math.abs(chip.getCol() - dstCol) == 1) {
            return () -> {
                // change chip position
                chip.setBoardPosition(dstRow, dstCol, board);
            };
        }
        
        // Jumping over opponent piece, check for piece in the middle and is opponent's color
        if (Math.abs(chip.getRow() - dstRow) == 2 && Math.abs(chip.getCol() - dstCol) == 2) {
            Chip jumpedChip = board.getChipAtGridPosition(
                (dstRow + chip.getRow()) / 2,
                (dstCol + chip.getCol()) / 2);
            if (jumpedChip != null && jumpedChip.getColor() != chip.getColor()) {
                return () -> {
                    // remove the jumpedChip
                    board.remove(jumpedChip.getGraphics());
                    // update the chip position
                    chip.setBoardPosition(dstRow, dstCol, board);
                };
            }
        }

        // If none of the conditions were met, then chip cannot move to destination row, column
        return null;
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
