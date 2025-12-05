import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private List<Chip> chips;
    private CanvasWindow canvas;
    private Board board;

    public Checkers(CanvasWindow canvas) {
      this.canvas = canvas;
      board = new Board();
      board.drawBoardLayout(canvas);
      chips = new ArrayList<>();
    }

    public void generateChips(Color color) {
        double x =0;
        double y =0;
        for (int i = 0; i < 20; i++) {
            Chip chip = new Chip(x, y, color);
            chips.add(chip);
        }
    }

    public void drawRedChips() {
        double x = 0;

    }

    public void game() {
        generateChips(Color.red);
        generateChips(Color.black);
    }

    public static void main(String[] args) {
        
        CanvasWindow canvas = new CanvasWindow("Checkers Board", CANVAS_WIDTH, CANVAS_HEIGHT);
        Checkers checkers = new Checkers(canvas);  
        checkers.game();
    }
}
