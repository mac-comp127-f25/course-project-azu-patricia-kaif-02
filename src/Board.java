import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Board {
    private static final int TOTAL_CELLS = 100;
    private static final int CELL_SIZE = 10;

    private int[] mazeLayout;

    
    public Board() {
        int[] mazeLayout = {0,1,0,1,0,1,0,1,0,1,
                            1,0,1,0,1,0,1,0,1,0,
                            0,1,0,1,0,1,0,1,0,1,
                            1,0,1,0,1,0,1,0,1,0,
                            0,1,0,1,0,1,0,1,0,1,
                            1,0,1,0,1,0,1,0,1,0,
                            0,1,0,1,0,1,0,1,0,1,
                            1,0,1,0,1,0,1,0,1,0,
                            0,1,0,1,0,1,0,1,0,1,
                            1,0,1,0,1,0,1,0,1,0
        };
    }
  
    public void drawMazeLayout(CanvasWindow canvas) {
        int index = 0;

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Rectangle cell = new Rectangle(
                    col * CELL_SIZE,
                    row * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE
                );

                if (mazeLayout[index] == 0) {
                    cell.setFillColor(Color.WHITE);
                } else {
                    cell.setFillColor(Color.BLACK);
                }

                cell.setFilled(true);
                canvas.add(cell);

                index++;
        }
    }
    }

   public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Maze", CELL_SIZE*TOTAL_CELLS, CELL_SIZE*TOTAL_CELLS);
        Board board = new Board();
        board.drawMazeLayout(canvas);      
    }
}
