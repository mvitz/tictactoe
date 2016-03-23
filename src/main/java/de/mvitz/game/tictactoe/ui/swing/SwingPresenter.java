package de.mvitz.game.tictactoe.ui.swing;

import de.mvitz.game.tictactoe.domain.Game;

final class SwingPresenter {

    private final Game game;
    private final SwingView view;
    private final SwingPlayer humanPlayer;

    public SwingPresenter(Game game, SwingView view, SwingPlayer humanPlayer) {
        this.game = game;
        this.view = view;
        this.view.setPresenter(this);
        this.humanPlayer = humanPlayer;
    }

    public void start() {
        new Thread(() -> game.start()).start();
//        view.showResult("DRAW");
//        view.showResult("WON by " + asString(game.getWinner()));
    }

    public void fieldClicked(int row, int column) {
        humanPlayer.take(row, column);
    }


}
