package ui;

import model.Board;
import model.Cell;

import java.util.Scanner;

public class ConsoleUI {

    // === Fields ==============================================================
    private final Scanner scanner = new Scanner(System.in);

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

    /**
     * Reads player input from the console.
     * Example commands:
     * - r a1 → reveal cell A1
     * - f b3 → flag cell B3
     * - q → quit game
     */
    public String readUserInput() {
        System.out.print("\nEnter your command (r/f/q): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    /** Displays message when the player loses the game. */
    public void showGameOver() {
        System.out.println("\n💥 Boom! You hit a mine! Game Over!");
    }

    /** Displays message when the player wins the game. */
    public void showWinMessage() {
        System.out.println("\n🎉 Congratulations! You cleared the board!");
    }

    /** Displays message for invalid or unrecognized input. */
    public void showInvalidInputMessage() {
        System.out.println("⚠️ Invalid command! Try again (r/f/q).");
    }

    /** Optional: shows welcome/instructions message at game start. */
    public void showWelcomeMessage() {
        System.out.println("🎮 Welcome to Minesweeper!");
        System.out.println();
    }
}