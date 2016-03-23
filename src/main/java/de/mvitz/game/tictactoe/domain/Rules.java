package de.mvitz.game.tictactoe.domain;

import de.mvitz.game.tictactoe.domain.Board.Field;

final class Rules {

    public boolean isFinished(Board board) {
        return !board.hasFreeFields() || hasWinner(board);
    }

    public boolean hasWinner(Board board) {
        return hasRowWinner(board)
            || hasColumnWinner(board)
            || hasDiagonalWinner(board);
    }

    private static boolean hasRowWinner(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            final Field firstField = new Field(row, 0);
            if (CheckDirection.ROW.check(board, firstField)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasColumnWinner(final Board board) {
        for (int column = 0; column < board.getSize(); column++) {
            final Field firstField = new Field(0, column);
            if (CheckDirection.COLUMN.check(board, firstField)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDiagonalWinner(final Board board) {
        return CheckDirection.LR.check(board, new Field(0, 0))
            || CheckDirection.RL.check(board, new Field(0, board.getSize() - 1));
    }

//    public boolean isDraw(final Board board) {
//        return board.getFreeFields().isEmpty() && !hasWinner(board);
//    }

    private static enum CheckDirection {
        ROW(0, +1), COLUMN(+1, 0), LR(+1, +1), RL(+1, -1);
        private final int rowDelta;
        private final int colDelta;

        private CheckDirection(int rowDelta, int colDelta) {
            this.rowDelta = rowDelta;
            this.colDelta = colDelta;
        }

        public boolean check(Board board, Field start) {
            if (board.isFree(start)) {
                return false;
            }

            final Seed seed = board.getSeed(start);

            Field field = Field.relativeTo(start, rowDelta, colDelta);
            while (board.inBounds(field)) {
                if (seed != board.getSeed(field)) {
                    return false;
                }
                field = Field.relativeTo(field, rowDelta, colDelta);
            }
            return true;
        }

        private static boolean inBounds(final Board board, final int rowOrColumn) {
            return rowOrColumn >= 0 && rowOrColumn < board.getSize();
        }

    }

}
