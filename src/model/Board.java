package model;
import model.Cell;
public class Board {

    // === Fields ==============================================================

    private int rows;
    private int cols;
    private int mineCount;
    private Cell[][] grid;
    private boolean lost;

    // === Core logic ==========================================================

    public void initialize() {
    }

    public void createBoard() {
    }

    public void calculateAdjacentMines() {


        // x -1 går upp, x 0 samma rad x +1 går ner
        // y -1 vänster, y 0 samma rad, y +1 höger

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                Cell cell = grid[i][j];
                if (!cell.hasMine()) {
                    int count = 0;

                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (x == 0 && y == 0) {
                                continue;
                            }
                            int newRow = i + x;
                            int newCol = j + y;

                            if (isValidCell(newRow, newCol) && grid[newRow][newCol].hasMine()) {
                                count++;
                            }
                        }
                    }

                    cell.setAdjacentMines(count);
                }
            }
        }

    }

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void revealCell(Cell cell) {
        System.out.println("Hello!");
    }

    public void isGameOver() {
    }

    public void toggleFlag() {
        System.out.println("Hej");

    }

    public void isGameWon() {
    }



}
