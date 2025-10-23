package model;

public class Cell {

    // === Fields ==============================================================

    private boolean hasMine;

    // === Constructor =========================================================

    public Cell() {
        this.hasMine = false;
    }

    // === Core methods ========================================================

    public boolean hasMine() {
        return hasMine;
    }
    public void setMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

}
