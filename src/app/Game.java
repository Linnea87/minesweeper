package app;

import model.Board;
import ui.ConsoleUI;

public class Game {

    // === Fields ==============================================================

    private Board board;
    private ConsoleUI ui;

    // === Constructor =========================================================

    public Game() {
        this.board = new Board(8, 8, 10);
        this.ui = new ConsoleUI();
    }

    // === Core logic ==========================================================
    public void start() {
        board.initialize();
        ui.render(board);
    }

}
