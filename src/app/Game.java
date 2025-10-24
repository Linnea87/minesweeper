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
        board.createBoard();
        board.calculateAdjacentMines();


        boolean running = true;
        while (running) {
            ui.render(board);

            core.Command cmd = ui.readUserCommand();

            switch(cmd.getType()){
                case QUIT:
                    running = false;
                    break;
                case REVEAL:
                    board.revealCell(cmd.getCoordinate());
                    break;

                case FLAG:
                    board.toggleFlag(cmd.getCoordinate());
                    break;

                case INVALID:
                default:
                    ui.showInvalidInputMessage();
                    break;
            }

            if (board.isGameOver()){
                ui.showGameOver();
                running = false;
            }

        }



    }

}
