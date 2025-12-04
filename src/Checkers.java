import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private List<CommonChip> redChips;
    private List<CommonChip> blackChips;
    private CanvasWindow canvas;
    private Board board;

    public Checkers(CanvasWindow canvas) {
      this.canvas = canvas;
      board = new Board();
      board.drawBoardLayout(canvas);
      redChips = new ArrayList<>();
      redChips.add(new CommonChip(0, 0, Color.red));

      blackChips = new ArrayList<>();
    }

    public void generateRedChips() {
        canvas.add(redChips.get(0).getChip());
    }

    public void game() {
        generateRedChips();
    }

    public static void main(String[] args) {
        
        CanvasWindow canvas = new CanvasWindow("Checkers Board", CANVAS_WIDTH, CANVAS_HEIGHT);
        Checkers checkers = new Checkers(canvas);  
        checkers.game();
    }
}
