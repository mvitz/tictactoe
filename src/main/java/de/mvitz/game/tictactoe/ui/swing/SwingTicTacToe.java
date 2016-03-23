package de.mvitz.game.tictactoe.ui.swing;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Game;
import de.mvitz.game.tictactoe.ki.RandomPlayer;

import javax.swing.*;
import java.awt.*;

public final class SwingTicTacToe {

    private static final int SIZE = 3;

    private final JFrame frame;

    private final SwingPresenter presenter;

    private SwingTicTacToe() {
        frame = new JFrame("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final SwingView view = new SwingView(SIZE);
        final SwingHumanPlayer humanPlayer = new SwingHumanPlayer();
        presenter = new SwingPresenter(
            new Game(
                new Board(SIZE),
                new SwingPlayer(view, humanPlayer),
                new SwingPlayer(view, new RandomPlayer())),
            view,
            humanPlayer);

        frame.add(view.getPanel(), BorderLayout.CENTER);

        frame.pack();
    }

    private void show() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        presenter.start();
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> new SwingTicTacToe().show());
    }

}
