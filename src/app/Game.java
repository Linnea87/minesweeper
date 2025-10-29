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
        this.ui = new ConsoleUI();
        setupNewBoard();
    }

    // === Helpers / internal init ============================================

    private void setupNewBoard() {
        board = new Board(8, 8, 10);
        board.initialize();
        board.createBoard();
        board.calculateAdjacentMines();
    }

    // === Core logic ==========================================================

    public void start() {

        boolean firstRound = true;
        boolean keepPlaying = true;

        while (keepPlaying) {

            if (!firstRound) {
                setupNewBoard();
            }

            if (firstRound) {
                ui.showWelcomeMessage();
                firstRound = false;
            }

            boolean running = true;
            while (running) {
                ui.render(board);

                Command cmd = ui.readUserCommand();

                switch (cmd.getType()) {
                    case QUIT:
                        ui.showExitMessage();
                        running = false;
                        break;

                    case REVEAL:
                        boolean revealed = board.revealCell(cmd.getCoordinate());
                        if (!revealed) {
                            ui.showInvalidInputMessage();
                        }
                        break;

                    case FLAG:
                        boolean ok = board.toggleFlag(cmd.getCoordinate());
                        if (!ok) {
                            ui.showInvalidInputMessage();
                        }
                        break;

                    case INVALID:
                    default:
                        ui.showInvalidInputMessage();
                        break;
                }

                if (running && board.isGameOver()) {
                    ui.render(board);
                    if (board.isGameWon()) {
                        ui.showWinMessage();
                    } else {
                        ui.showGameOver();
                    }
                    running = false;
                }

                System.out.println();
            }

            keepPlaying = ui.askPlayAgain();
            if (!keepPlaying) {
                ui.showExitMessage();
            }
        }
    }
}
