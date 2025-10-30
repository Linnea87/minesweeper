package core;

public class Coordinate {

    // === Fields ======================================================

    private int row;
    private int col;

    // === Constructors ================================================

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate(String input) {
        input = input.trim().toLowerCase();

        if (input.length() < 2) {
            throw new IllegalArgumentException("Invalid coordinate format: " + input);
        }

        char rowChar = input.charAt(0);
        String colPart = input.substring(1);

        this.row = rowChar - 'a';
        this.col = Integer.parseInt(colPart) - 1;
    }

    // === Getters =======================================================

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
