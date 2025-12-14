import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

/**
 * Authors: Azucena Ventimilla, Patricia Escobar, Kaif Kassam
 */

public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Board board;

    // private String playerWhosActive = "red"; // in checkers one always starts with red
    private Color currentPlayerColor = Color.red; // red player starts playing first
    private GraphicsText turnIndicator = new GraphicsText("");
    private GraphicsText chipCountDisplay = new GraphicsText("");

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

        // chip count on screen:
        chipCountDisplay.setCenter(canvas.getWidth() * 0.79, canvas.getHeight() * 0.20);
        canvas.add(chipCountDisplay);

        updateChipCountDisplay();

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
    
    
    public Color currentPlayer() {
        return currentPlayerColor;
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
        if (chip == null) return null;

        if (!board.isInside(dstRow, dstCol)) return null;
        if (!board.isDarkSquare(dstRow, dstCol)) return null;

        if (board.getChipAtGridPosition(dstRow, dstCol) != null) return null;

        int dr = dstRow - chip.getRow();
        int dc = dstCol - chip.getCol();

        int dir = (chip.getColor() == Color.red) ? 1 : -1;

        // normal move: one step forward diagonally
        if (Math.abs(dc) == 1 && dr == dir) {
            return () -> chip.setBoardPosition(dstRow, dstCol, board);
        }

        // jump: two steps forward diagonally over an opponent
        if (Math.abs(dc) == 2 && dr == 2 * dir) {
            int midRow = chip.getRow() + dir;
            int midCol = chip.getCol() + (dc / 2);

            Chip jumpedChip = board.getChipAtGridPosition(midRow, midCol);
            if (jumpedChip != null && jumpedChip.getColor() != chip.getColor()) {
                return () -> {
                    //runnable only executed when a jump actually happens, when to check for win status
                    board.removeChip(jumpedChip);
                    // board.updatedChipCount(jumpedChip); -- not necessary anymore, with updateChipCountDisplay helper - board.removeChip now also decrements count
                    chip.setBoardPosition(dstRow, dstCol, board);
                    updateChipCountDisplay();
                    checkWinCondition();
                };
            }
        }

        return null;
    }

    private void checkWinCondition() {
        int numberOfRedChips = board.getNumberOfRedChips();
        System.out.println("Number of red chips " + numberOfRedChips);
        int numberOfBlueChips = board.getNumberOfBlueChips();
        System.out.println("Number of blue chips " + numberOfBlueChips);

        if (numberOfBlueChips == 0 || numberOfRedChips == 0) {
            gameOverDisplay().run();
        }
    }

    private void updateChipCountDisplay() {
        chipCountDisplay.setText(
            "Red: " + board.getNumberOfRedChips() +
            "  Blue: " + board.getNumberOfBlueChips()
        );
    }

    
    // Needs to be tested after the bug with the directional movement of chips are fixed!
    private Runnable gameOverDisplay() {
        return () -> {
            GraphicsText gameOverText = new GraphicsText("YOU WIN!!!!");
            canvas.add(gameOverText);
        };
    }

    public void game() {
        handleClick(canvas);
        board.placeStartingChips();
        // checkWinCondition();  // not necessary since no moves have occurred yet --
    }

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Checkers Board", CANVAS_WIDTH, CANVAS_HEIGHT);
        Checkers checkers = new Checkers(canvas);
        checkers.game();
    }




}
