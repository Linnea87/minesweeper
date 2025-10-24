package model;

import core.Coordinate;

public class Board {
    public void initialize() {
    }

    public void createBoard() {
    }

    public void calculateAdjacentMines {

    }

    public void revealCell(Cell cell) {
        System.out.println("Hello!");
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



}
