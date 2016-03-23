package de.mvitz.game.tictactoe.domain;

public enum Seed {
    CROSS, NOUGHT;

    public Seed change() {
        switch (this) {
            case CROSS:
                return NOUGHT;
            case NOUGHT:
                return CROSS;
            default:
                throw new IllegalStateException("Can't change from seed: " +
                    this);
        }
    }

}
