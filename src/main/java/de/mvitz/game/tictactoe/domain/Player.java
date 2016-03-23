package de.mvitz.game.tictactoe.domain;

import de.mvitz.game.tictactoe.domain.Game.Turn;

public interface Player {

    Turn nextTurn(Board board);

}
