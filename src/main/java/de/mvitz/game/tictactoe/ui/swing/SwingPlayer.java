package de.mvitz.game.tictactoe.ui.swing;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Game.Turn;
import de.mvitz.game.tictactoe.domain.Player;

import javax.swing.*;

final class SwingPlayer implements Player {

    private final SwingView view;
    private final Player player;

    public SwingPlayer(SwingView view, Player player) {
        this.view = view;
        this.player = player;
    }

    @Override
    public Turn nextTurn(Board board) {
        SwingUtilities.invokeLater(() -> view.draw(board));
        return player.nextTurn(board);
    }

}
