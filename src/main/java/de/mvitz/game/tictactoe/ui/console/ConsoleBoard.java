package de.mvitz.game.tictactoe.ui.console;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Board.Field;
import de.mvitz.game.tictactoe.domain.Seed;

final class ConsoleBoard {

    private final String firstLine;
    private final String middleLine;
    private final String lastLine;

    public ConsoleBoard(int size) {
        final StringBuilder firstLineBuilder = new StringBuilder(",");
        final StringBuilder middleLineBuilder = new StringBuilder("|");
        final StringBuilder lastLineBuilder = new StringBuilder("'");
        for (int i = 0; i < size; i++) {
            firstLineBuilder.append("-,");
            middleLineBuilder.append("-+");
            lastLineBuilder.append("-'");
        }
        firstLine = firstLineBuilder.append("\n").toString();
        middleLine = middleLineBuilder.append("\n").toString();
        lastLine = lastLineBuilder.append("\n").toString();
    }

    public void draw(Board board) {
        final StringBuilder boardView = new StringBuilder(firstLine);
        for (int row = 0; row < board.getSize(); row++) {
            boardView.append(row(board, row));
            if (row + 1 < board.getSize()) {
                boardView.append(middleLine);
            }
        }
        boardView.append(lastLine);
        System.out.println(boardView.toString());
    }

    private static String row(Board board, int row) {
        final StringBuilder rowView = new StringBuilder("|");
        for (int col = 0; col < board.getSize(); col++) {
            final Field field = new Field(row, col);
            rowView.append(field(board, field)).append("|");
        }
        return rowView.append("\n").toString();
    }

    private static String field(Board board, Field field) {
        if (board.isFree(field)) {
            return " ";
        } else {
            return seed(board.getSeed(field));
        }
    }

    private static String seed(final Seed seed) {
        switch (seed) {
            case CROSS:
                return "X";
            case NOUGHT:
                return "O";
            default:
                throw new IllegalStateException("Can not draw seed: " + seed);
        }
    }

}
