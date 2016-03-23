package de.mvitz.game.tictactoe.ui.console;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Game;
import de.mvitz.game.tictactoe.domain.Player;
import de.mvitz.game.tictactoe.domain.Seed;
import de.mvitz.game.tictactoe.ki.RandomPlayer;

import java.util.Random;

public final class ConsoleTicTacToe {

    private static final int SIZE = 3;

    private ConsoleTicTacToe() {
    }

    public static void main(String[] args) {
        Player cross = new RandomPlayer();
        Player nought = new RandomPlayer();

        if (new Random().nextInt() % 2 == 0) {
            cross = new ConsolePlayer(new ConsoleBoard(SIZE), Seed.CROSS);
        } else {
            nought = new ConsolePlayer(new ConsoleBoard(SIZE), Seed.NOUGHT);
        }

        new Game(new Board(SIZE), cross, nought).start();
    }

}
