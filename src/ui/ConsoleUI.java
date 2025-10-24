package ui;

import model.Board;
import model.Cell;

public class ConsoleUI {
    // === Core methods ========================================================

    public void render(Board board) {
        printBoard(board);
    }

    // === Helper method =======================================================

    private void printBoard(Board board) {
        Cell[][] grid = board.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        printColumnHeaders(cols);
        printTopBorder(cols);

        for (int r = 0; r < rows; r++) {
            char rowLabel = (char) (r + 'a');
            System.out.print(rowLabel + " ");

            for (int c = 0; c < cols; c++) {
                char cellChar = grid[r][c].hasMine() ? '*' : ' ';
                System.out.print("| " + cellChar + " ");
            }
            System.out.println("|");
            printRowSeparator(cols, r, rows);
        }

        printBottomBorder(cols);
    }

    // === Sub-helpers for formatting ==========================================

    private void printColumnHeaders(int cols) {
        System.out.print("    ");
        for (int c = 1; c <= cols; c++) {
            System.out.print(c + "   ");
        }
        System.out.println();
    }

    private void printTopBorder(int cols) {
        System.out.print("  +");
        for (int c = 0; c < cols; c++) System.out.print("---+"); // ändrat <= till <
        System.out.println();
    }

    private void printRowSeparator(int cols, int r, int rows) {
        if (r < rows - 1) {
            System.out.print("  +");
            for (int c = 0; c < cols; c++) System.out.print("---+"); // ändrat <= till <
            System.out.println();
        }
    }

    private void printBottomBorder(int cols) {
        System.out.print("  +");
        for (int c = 0; c < cols; c++) System.out.print("---+"); // ändrat <= till <
        System.out.println();
    }
}