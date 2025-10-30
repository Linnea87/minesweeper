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
                Cell cell = grid[r][c];
                char cellChar;

                if (cell.isFlagged()) {
                    cellChar = 'F';
                } else if (!cell.isRevealed()) {
                    cellChar = '·';
                } else if (cell.hasMine()) {
                    cellChar = '*';
                } else if (cell.getAdjacentMines() > 0) {
                    cellChar = Character.forDigit(cell.getAdjacentMines(), 10);
                } else {
                    cellChar = ' ';
                }

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
        for (int c = 0; c < cols; c++) System.out.print("---+");
        System.out.println();
    }

    private void printRowSeparator(int cols, int r, int rows) {
        if (r < rows - 1) {
            System.out.print("  +");
            for (int c = 0; c < cols; c++) System.out.print("---+");
            System.out.println();
        }
    }

    private void printBottomBorder(int cols) {
        System.out.print("  +");
        for (int c = 0; c < cols; c++) System.out.print("---+");
        System.out.println();
    }

    // === Input handling ======================================================
    
    public Command readUserCommand() {
        System.out.print("\nEnter command (e.g. r a1, f b3, or q to quit): ");
        String line = scanner.nextLine().trim().toLowerCase();

        if (line.equals("q")) {
            return new Command(CommandType.QUIT, null);
        }
        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            return new Command(CommandType.INVALID, null);
        }

        String action = parts[0];
        String coordText = parts[1];

        Coordinate coord;
        try {
            coord = new Coordinate(coordText);
        }
        catch (IllegalArgumentException e) {
            return new Command(CommandType.INVALID, null);
        }
        
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

    // === User Guidance & Intro ==================================================

    public void showWelcomeMessage() {
        System.out.println("\uD83D\uDCA3 Welcome to Minesweeper!");
        System.out.println();
        System.out.println("How to play:");
        System.out.println("- The board has rows a–h and columns 1–8.");
        System.out.println("- Pick a cell by combining row + column, e.g. a1 or c5.");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("- r a1 → reveal cell a1");
        System.out.println("- f b3 → flag/unflag cell b3");
        System.out.println("- q    → quit the game");
        System.out.println();
    }

    public boolean askPlayAgain() {
        System.out.println("Play again? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y");
    }

    // === Game State Messages ====================================================

    public void showGameOver() {
        System.out.println("\n💥 Boom! You hit a mine! Game Over!");
    }
    public void showWinMessage() {
        System.out.println("\n🎉 Congratulations! You cleared the board!");
    }

    public void showExitMessage() {
        System.out.println("\n👋 Thanks for playing!");
    }

    // === Error & Validation Messages ===========================================

    public void showInvalidInputMessage() {
        System.out.println("⚠️ Invalid command! Try again (r/f/q).");
    }
}