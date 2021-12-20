package com.minasalari.kalah.model;

public enum PitType {
    LITTLE(GameBoard.INITIAL_LITTLE_PIT_STONE_NUMBER),
    BIG(GameBoard.INITIAL_BIG_PIT_STONE_NUMBER);

    private final int initialStoneNumber;

    PitType(int initialStoneNumber) {
        this.initialStoneNumber = initialStoneNumber;
    }

    public int getInitialStoneNumber() {
        return initialStoneNumber;
    }
}
