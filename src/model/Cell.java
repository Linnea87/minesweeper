package model;

public class Cell {
  
  // === Fields ==============================================================

    private boolean hasMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMines;
  
    // === Constructor =========================================================

    public Cell() {
        this.hasMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentMines = 0;
    }

    // === Core methods ========================================================

    // === Getters =============================================================

    public boolean hasMine() {
        return hasMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }
    public boolean isFlagged() {
        return isFlagged;
    }
    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void revealCell() {
        this.isRevealed = true;
    }

    // === Setters =============================================================

    public void setFlagged(boolean flagged) {
        this.isFlagged = flagged;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

}
