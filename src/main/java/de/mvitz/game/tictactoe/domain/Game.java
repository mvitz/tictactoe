package de.mvitz.game.tictactoe.domain;

import de.mvitz.game.tictactoe.domain.Board.Field;

public final class Game {

    private static final Rules RULES = new Rules();

    private Board board;
    private final Player cross;
    private final Player nought;

    public Game(Board board, Player cross, Player nought) {
        this.board = board;
        this.cross = cross;
        this.nought = nought;
    }

    public void start() {
        Seed onTurn = Seed.CROSS;
        do {
            Turn turn;
            do {
                switch (onTurn) {
                    case CROSS:
                        turn = cross.nextTurn(board);
                        break;
                    case NOUGHT:
                        turn = nought.nextTurn(board);
                        break;
                    default:
                        throw new IllegalStateException(
                            "Can't get next turn for seed: " + onTurn);
                }
            } while (!take(turn, onTurn));
            onTurn = onTurn.change();
        } while (!isFinished());

        /*
        TODO: inform players about result
        System.out.println("Game finished!");
        if (game.isDraw()) {
            System.out.println("Result: DRAW");
        } else {
            System.out.println("Result: WON by " + game.getWinner());
        }*/

    }

    public boolean isFinished() {
        return RULES.isFinished(board);
    }

    private boolean take(Turn turn, Seed onTurn) {
        if (isValid(turn)) {
            board = board.take(turn.field, onTurn);
            return true;
        }
        return false;
    }

    private boolean isValid(Turn turn) {
        return board.inBounds(turn.field) && board.isFree(turn.field);
    }

    public static final class Turn {

        private final Field field;

        public Turn(int row, int column) {
            this(new Field(row, column));
        }

        public Turn(Field field) {
            this.field = field;
        }

    }

}
