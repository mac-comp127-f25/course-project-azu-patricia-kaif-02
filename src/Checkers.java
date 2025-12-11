import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;


public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

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
        chip.setBoardPosition(chip.getRow() - 1, chip.getCol(), board);
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
