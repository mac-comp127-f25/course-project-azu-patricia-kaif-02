import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;


public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private Chip[][] chipGrid;
    private CanvasWindow canvas;
    private Board board;

    private String playerWhosActive = "red"; // in checkers one always starts with red
    private GraphicsText turnIndicator = new GraphicsText("");

    private Chip selectedChip;


    public Checkers(CanvasWindow canvas) {
        this.canvas = canvas;
        board = new Board();
        board.drawBoardLayout(canvas);
        canvas.add(board);


        setupTurnDisplay();

        chipGrid = new Chip[Board.ROWS][Board.COLS];

        placeStartingChips();
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


    private void placeChip(int row, int col, Color color) {
        // inside board bounds check
        if (!board.isInside(row, col)) {
            return;
        }
        // cannot place chip on white square
        if (!board.isDarkSquare(row, col)) {
            return;
        }
        // already full check
        if (chipGrid[row][col] != null) {
            return;
        }

        Chip chip = new Chip(color); // new chip object
        chip.setBoardPosition(row, col, board); // positions new chip
        chipGrid[row][col] = chip; // adds new chip object to array of chips
        board.add(chip); // chip added to canvas
    }

    private void placeStartingChips() {
        // top player
        for (int row = 0; row < 4; row++) { // only three
            for (int col = 0; col < Board.COLS; col++) { // all the way till the end of board
                if (board.isDarkSquare(row, col)) {
                    placeChip(row, col, Color.RED);
                }
            }
        }
        // bottom player
        for (int row = Board.ROWS - 4; row < Board.ROWS; row++) { // start from 6th row (Boards.ROWS-4)
            for (int col = 0; col < Board.COLS; col++) {
                if (board.isDarkSquare(row, col)) {
                    placeChip(row, col, Color.BLUE);
                }
            }
        }
    }

    public void handleClick(CanvasWindow canvas) {
        canvas.onClick(event -> {
            Chip chip = board.checkForChip(event.getPosition()); // Correctly identifying a chip
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
        chip.setBoardPosition(chip.getRow() - 1, chip.getCol(), board);
    }

    public void game() {
        handleClick(canvas);
    }


    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Checkers Board", CANVAS_WIDTH, CANVAS_HEIGHT);
        Checkers checkers = new Checkers(canvas);
        checkers.game();
    }
}
