package ui;

import core.Command;
import core.CommandType;
import core.Coordinate;
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
                Cell cell  = grid[r][c];
               char cellChar;

               if (cell.isFlagged()) {
                   cellChar = 'F';
               } else if(!cell.isRevealed()) {
                   cellChar = '·';
               } else if (cell.hasMine()) {
                   cellChar = '*';
               } else if (cell.getAdjacentMines() > 0) {
                   cellChar = Character.forDigit(cell.getAdjacentMines(), 10);
               } else {
                   cellChar = ' ';
               }
                System.out.println("| " + cellChar + " ");
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

    // === Input handling ======================================================

    /**
     * Reads player input from the console.
     * Example commands:
     * - r a1 → reveal cell A1
     * - f b3 → flag cell B3
     * - q → quit game
     */
    public Command readUserCommand() {
        System.out.print("\nEnter your command (r/f/q + cell): ");
        String line = scanner.nextLine().trim().toLowerCase();

        if (line.equals("q")) {
            return new Command(Command.CommandType.QUIT, null);
        }
        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            return new Command(Command.CommandType.INVALID);
        }

        String action = parts[0];
        String coordText = parts[1];
        Command coord = new Coordinate(coordText);

        CommandType type;
        switch (action) {
            case "r":
                type = CommandType.REVEAL;
                break;
            case "f":
                type = CommandType.FLAG;
                break;
            default:
                type = CommandType.INVALID;
        }
        return new Command(type, coord);
    }

    // === Messages ============================================================


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
        System.out.println("Commands:");
        System.out.println("- r a3 → reveal cell A3");
        System.out.println("- f b2 → flag/unflag cell B2");
        System.out.println("- q    → quit game");
        System.out.println();
    }
    }
}