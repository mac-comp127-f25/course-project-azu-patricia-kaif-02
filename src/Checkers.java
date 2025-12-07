import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class Checkers {
    public static final int CANVAS_WIDTH = 1000;
    public static final int CANVAS_HEIGHT = 800;

    private Chip[][] chipGrid;
    private CanvasWindow canvas;
    private Board board;

    public Checkers(CanvasWindow canvas) {
      this.canvas = canvas;
      board = new Board();
      board.drawBoardLayout(canvas);

      //
      chipGrid = new Chip[Board.ROWS][Board.COLS];

      //
      placeStartingChips();
    }

    private void placeChip(int row, int col, Color color){
        // inside board bounds check
        if (!board.isInside(row, col)){
            return;
        }
        // cannot place chip on white square
        if(!board.isDarkSquare(row, col)){
            return;
        }
        //already full check
        if(chipGrid[row][col] != null){
            return;
        }

        Chip chip = new Chip(0, 0, color); // new chip object
        chip.setBoardPositions(row, col, board); // positions new chip
        chipGrid[row][col] = chip; // adds new chip object to array of chips
        canvas.add(chip.getChip()); // chip added to canvas
    }

    private void placeStartingChips(){
        //top player
        for (int row=0; row < 4; row++){ // only three 
            for( int col = 0; col < Board.COLS; col++){ // all the way till the end of board
                if (board.isDarkSquare(row, col)) {
                    placeChip(row, col, Color.RED);
                }
            }
        }

        // bottom player
        for (int row = Board.ROWS - 4; row < Board.ROWS; row++){ // start from 6th row (Boards.ROWS-4)
            for(int col = 0; col < Board.COLS; col++){
                if (board.isDarkSquare(row, col)) {
                    placeChip(row, col, Color.BLUE);
                }
            }
        }
    }

    // public void generateChips(Color color) {
    //     for (int i = 0; i < 20; i++) {
    //         Chip chip = new Chip(0, 0, color);
    //         chips.add(chip);
    //     }
    // }

    // public void drawChips() {
    //     generateChips(Color.red);
    //     generateChips(Color.BLUE);


    //     chips.get(0).setCenter(2, 1, board);
    //     canvas.add(chips.get(0).getChip());
    //     System.out.println(board.getCellCenter(2, 1));

    //      chips.get(30).setCenter(0, 1, board);
    //     canvas.add(chips.get(30).getChip());
    //     System.out.println(board.getCellCenter(0, 1));

    //     // for (Chip chip: chips) {     
    //     // for (int row = 0; row < 10; row++) {
    //     //     for (int col = 0; col < 10; col++) {
    //     //         chip.setCenter(row, col, board);
    //     //         System.out.println(board.getCellCenter(row, col));
    //     //         canvas.add(chip.getChip());
    //     //     }
        
    //     // }
    // // }
    // }

    public void game() {
    }

    public static void main(String[] args) {
        
        CanvasWindow canvas = new CanvasWindow("Checkers Board", CANVAS_WIDTH, CANVAS_HEIGHT);
        Checkers checkers = new Checkers(canvas);  
        checkers.game();
    }
}
