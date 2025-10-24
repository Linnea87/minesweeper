package ui;

import model.Board;
import model.Cell;

public class ConsoleUI {
    // === Core methods ========================================================

    public void render(Board board) {
        Cell[][] grid = board.getGrid();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c].hasMine()){
                    System.out.println("* ");
                }
                else {
                    System.out.println(". ");
                }
            }
            System.out.println();
        }
    }

}
