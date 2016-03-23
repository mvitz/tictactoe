package de.mvitz.game.tictactoe.ui.swing;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Game.Turn;
import de.mvitz.game.tictactoe.domain.Player;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

final class SwingPlayer implements Player {

    private final BlockingQueue<Turn> queue = new LinkedBlockingDeque<>(1);
    private final SwingView view;

    public SwingPlayer(SwingView view) {
        this.view = view;
    }

    @Override
    public Turn nextTurn(Board board) {
        view.draw(board);
        try {
            return queue.take();
        } catch (final InterruptedException ex) {
            throw new RuntimeException("Error while waiting for a human move.", ex);
        }
    }

    public void take(int row, int column) {
        queue.offer(new Turn(row, column));
    }

}
