package de.mvitz.game.tictactoe.ui.console;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Game.Turn;
import de.mvitz.game.tictactoe.domain.Player;
import de.mvitz.game.tictactoe.domain.Seed;

import java.util.Scanner;

final class ConsolePlayer implements Player {

    private static final String INPUT_FORMAT = "Player '%s', enter your move (row[1-%s] column[1-%s]: ";

    private static final Scanner in = new Scanner(System.in);

    private final ConsoleBoard out;
    private final Seed seed;

    public ConsolePlayer(ConsoleBoard out, Seed seed) {
        this.out = out;
        this.seed = seed;
    }

    @Override
    public Turn nextTurn(Board board) {
        out.draw(board);
        System.out.print(String.format(INPUT_FORMAT, seed, board.getSize(), board.getSize()));
        final int row = in.nextInt();
        final int column = in.nextInt();
        return new Turn(row - 1, column - 1);
    }

}
