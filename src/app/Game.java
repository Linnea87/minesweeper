package app;

import core.Command;
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
        ui.showWelcomeMessage();


        boolean running = true;
        while (running) {
            ui.render(board);

          Command cmd = ui.readUserCommand();

            switch(cmd.getType()){
                case QUIT:
                    ui.showExitMessage();
                    running = false;
                    break;

                case REVEAL:
                    boolean revealed = board.revealCell(cmd.getCoordinate());
                    if(!revealed){
                        ui.showInvalidInputMessage();
                    }
                    break;

                case FLAG:
                    boolean ok = board.toggleFlag(cmd.getCoordinate());
                    if(!ok){
                        ui.showInvalidInputMessage();
                    }
                    break;

                case INVALID:
                default:
                    ui.showInvalidInputMessage();
                    break;
            }

            if (running && board.isGameOver()){
                ui.render(board);
                if (board.isGameWon()){
                    ui.showWinMessage();
                } else {
                    ui.showGameOver();
                }
                running = false;
            }
            System.out.println();
        }
    }
}
