package model;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMines;


    //Constructor
    public Cell() {
        this.isMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentMines = 0;
    }

    // Getters
    public boolean isMine() {
        return isMine;
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

    // Setters
    public void revealCell(boolean revealed) {
        isRevealed = true;
    }
    public void isFlagged(boolean flagged) {
        isFlagged = true;
    }
    public void toggleFlag() {
        isFlagged = !this.isFlagged;
    }
    public void placeMine() {
        this.isMine = true;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = count;
    }
}
