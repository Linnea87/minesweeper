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

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isLost(){
        return lost;
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

    // === Validation ==========================================================

    public boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean isValidCell(Coordinate coord) {
        return isValidCell(coord.getRow(), coord.getCol());
    }

    // === Gameplay actions ====================================================

    public boolean revealCell(Coordinate coord) {
        int row = coord.getRow();
        int col = coord.getCol();

        if (!isValidCell(coord)) {
            return false;
        }

        Cell cell = grid[row][col];

        if (cell.isRevealed() || cell.isFlagged()) {
            return false;
        }

        cell.revealCell();

        if (cell.hasMine()) {
            lost = true;
            return true;
        }

        if (cell.getAdjacentMines() == 0) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue;

                    Coordinate neighbor = new Coordinate(row + dr, col + dc);
                    if (isValidCell(neighbor)) {
                        revealCell(neighbor);
                    }
                }
            }
        }
        return true;
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

    public boolean toggleFlag(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        if (!isValidCell(row, col)) {
            return false;
        }

        Cell cell = getCell(coordinate);

        if (cell.isRevealed()){
            return false;
        }

       cell.setFlagged(!cell.isFlagged());
        return true;
        }


    public boolean isGameWon() {
        Cell[][] cells = getGrid();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell currentCell = cells[row][col];
                if (!currentCell.hasMine() && !currentCell.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    // === Getters for UI ==============================================
  
    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(Coordinate coordinate) {
        return grid[coordinate.getRow()][coordinate.getCol()];
    }


}
