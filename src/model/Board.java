package model;

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
    }

    public void calculateAdjacentMines () {

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

    // === Getter som UI behöver ==============================================
    public Cell[][] getGrid() {
        return grid;
    }



}
