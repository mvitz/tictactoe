package de.mvitz.game.tictactoe.ui.swing;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Game;
import de.mvitz.game.tictactoe.domain.Player;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

final class SwingHumanPlayer implements Player {

    private final BlockingQueue<Game.Turn> queue = new LinkedBlockingDeque<>(1);

    @Override
    public Game.Turn nextTurn(Board board) {
        try {
            return queue.take();
        } catch (final InterruptedException ex) {
            throw new RuntimeException("Error while waiting for a human move.", ex);
        }
    }

    public void take(int row, int column) {
        queue.offer(new Game.Turn(row, column));
    }


}
