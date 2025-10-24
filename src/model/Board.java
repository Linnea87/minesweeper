package model;

import core.Coordinate;
import java.util.Random;

public class Board {

    // === Fields ==============================================================

    private int rows;
    private int cols;
    private int mineCount;
    private Cell[][] grid;
    private boolean lost;

    // === Constructor =========================================================
  
    public Board(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.grid = null;
        this.lost = false;
    }
  
    // === Core logic ==========================================================

    public void initialize() {
        grid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell();
            }
        }
    }

    public void createBoard() {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mineCount) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            if (!grid[r][c].hasMine()) {
                grid[r][c].setMine(true);
                placedMines++;
            }
        }
    }

    public void calculateAdjacentMines() {

    }

    
    public void revealCell(Coordinate coord) {
        // Coordinate class should provide:
        // - int getRow()
        // - int getCol()
        int row = coord.getRow();
        int col = coord.getCol();

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        // Cell class should provide:
        // - boolean isRevealed()
        // - boolean isFlagged()
        // - void reveal()
        // - boolean hasMine()
        // - int getAdjacentMines()
        Cell cell = grid[row][col];

        if (cell.isRevealed() || cell.isFlagged()) {
            return;
        }

        cell.reveal();

        if (cell.hasMine()) {
            lost = true;
            return;
        }

        if (cell.getAdjacentMines() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;

                    int newRow = row + dr;
                    int newCol = col + dc;

                    // Coordinate should also have a constructor:
                    // Coordinate(int row, int col)
                    Coordinate neighbor = new Coordinate(newRow, newCol);
                    revealCell(neighbor);
                }
            }
        }
    }


    public void isGameOver() {
    }

    public void toggleFlag(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Invalid coordinate");
            return;
        }

        Cell cell = getCell(coordinate);

        if (cell.isRevealed()){
            System.out.println("You can't flag this cell");
            return;
        }

        if (cell.isFlagged()){
            cell.setFlagged(false);
            System.out.println("Flag deleted from (" + row + "," + col + ")");
        } else {
            cell.setFlagged(true);
            System.out.println("Flag placed on (" + row + "," + col + ")");
        }
    }

    public void isGameWon() {
    }

    // === Getter som UI behöver ==============================================
  
    public Cell[][] getGrid() {
        return grid;
    }



}
