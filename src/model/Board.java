package model;

import core.Command;
import core.Coordinate;
import java.util.Random;
import core.Command;
import ui.ConsoleUI;

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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
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

        cell.revealCell();

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


    public boolean isGameOver() {
        if (lost) {
            return true;
        }
        if (isGameWon()) {
            return true;
        }
        return false;
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

    public boolean isGameWon() {

        return false;
    }

    // === Getter som UI behöver ==============================================
  
    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(Coordinate coordinate) {
        return grid[coordinate.getRow()][coordinate.getCol()];
    }


}
