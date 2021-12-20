package com.minasalari.kalah.model;

public enum Player {
    PLAYER_1(GameBoard.PIT_END_INDEX/2),//down
    PLAYER_2(GameBoard.PIT_END_INDEX);//up

    private final int bigPitIndex;

    Player(int bigPitIndex) {
        this.bigPitIndex = bigPitIndex;
    }

    public int getBigPitIndex() {
        return bigPitIndex;
    }
}
