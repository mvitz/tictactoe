package de.mvitz.game.tictactoe.ki;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Board.Field;
import de.mvitz.game.tictactoe.domain.Game.Turn;
import de.mvitz.game.tictactoe.domain.Player;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RandomPlayer implements Player {

    @Override
    public Turn nextTurn(Board board) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
        }
        final List<Field> freeFields = board.getFreeFields();
        Collections.shuffle(freeFields);
        return new Turn(freeFields.get(0));
    }

}
