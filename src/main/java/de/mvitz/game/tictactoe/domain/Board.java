package de.mvitz.game.tictactoe.domain;

import java.util.ArrayList;
import java.util.List;

public final class Board {

    private final Seed[][] seeds;

    public Board(final int size) {
        seeds = new Seed[size][size];
    }

    private Board(Board board) {
        seeds = new Seed[board.seeds.length][];
        for (int i = 0; i < board.seeds.length; i++) {
            seeds[i] = new Seed[board.seeds[i].length];
            System.arraycopy(board.seeds[i], 0, seeds[i], 0, board.seeds[i].length);
        }
    }

    public int getSize() {
        return seeds.length;
    }

    public Seed getSeed(Field field) {
        return seeds[field.row][field.column];
    }

    public Board take(Field field, Seed seed) {
        final Board board = new Board(this);
        board.seeds[field.row][field.column] = seed;
        return board;
    }

    public boolean isFree(Field field) {
        return seeds[field.row][field.column] == null;
    }

    public boolean inBounds(Field field) {
        return inBounds(field.row) && inBounds(field.column);
    }

    public boolean hasFreeFields() {
        return !getFreeFields().isEmpty();
    }

    public List<Field> getFreeFields() {
        final List<Field> freeFields = new ArrayList<>();
        for (int row = 0; row < seeds.length; row++) {
            for (int col = 0; col < seeds[row].length; col++) {
                final Field field = new Field(row, col);
                if (isFree(field)) {
                    freeFields.add(field);
                }
            }
        }
        return freeFields;
    }

    private boolean inBounds(int rowOrColumn) {
        return rowOrColumn >= 0 && rowOrColumn < getSize();
    }

    public static class Field {

        private final int row;
        private final int column;

        public Field(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public static Field relativeTo(Field field, int rowDelta,
                                       int columnDelta) {
            return new Field(field.row + rowDelta, field.column + columnDelta);
        }

    }
}
