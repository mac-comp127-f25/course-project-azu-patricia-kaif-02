import java.util.ArrayList;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board {
   public static final int ROWS = 10;
   public static final int COLS = 10;
   private static final int TOTAL_CELLS = 100;
   private static final int CELL_SIZE = 75;


   private int[] boardLayout = {0,1,0,1,0,1,0,1,0,1,
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
  
   public Board() {
      
   }
    public void drawBoardLayout(CanvasWindow canvas) {
       // 0 = white and 1 = black
     
       int index = 0;
      
       for (int row = 0; row < 10; row++) {
           for (int col = 0; col < 10; col++) {


               Rectangle cell = new Rectangle(
                   col * CELL_SIZE,
                   row * CELL_SIZE,
                   CELL_SIZE,
                   CELL_SIZE
               );
               if (boardLayout[index] == 0) {
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


   // exact index in board layout list of square based on row and column coordinate
   public int indexOf(int row, int col){
       return row * COLS + col;
   }

   /**
    * It checks if the chip is inside the board. Returns true if it is otherwise is false
    */
   public boolean isInside(int row, int col){
       return row >= 0 && row < ROWS && col >= 0 && col < COLS;
   }


   public boolean isDarkSquare(int row, int col) {
       return boardLayout[indexOf(row, col)] == 1;
   }


   public Point getCellCenter(int row, int col) {
       // Calculating the coordinate of the top-left corner of the grid in screen coordinates
       double topX = col * CELL_SIZE;
       double topY = row * CELL_SIZE;


       // Calculating the center of one cel
       double centerX = topX + (CELL_SIZE/2);
       double centerY = topY + (CELL_SIZE/2);


       return new Point(centerX, centerY);
   }

}
