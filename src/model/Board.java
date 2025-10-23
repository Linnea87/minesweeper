package model;

public class Board {
    public void initialize() {
    }

    public void createBoard() {
    }

    public void calculateAdjacentMines

    {

    }

    //
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

    public void toggleFlag {

    }

    public void isGameWon() {
    }



}
