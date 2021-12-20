package com.minasalari.kalah.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Slf4j
@ToString
public class GameBoard {
    private Map<Integer, Pit> pitMap = new HashMap<>();
    public static final int INITIAL_LITTLE_PIT_STONE_NUMBER = 6;
    public static final int INITIAL_BIG_PIT_STONE_NUMBER = 0;
    protected static final int PIT_END_INDEX = 14;

    public GameBoard() {
        this.pitMap.put(1, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(2, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(3, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(4, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(5, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(6, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(7, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_BIG_PIT_STONE_NUMBER).pitType(PitType.BIG).build());
        this.pitMap.put(8, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(9, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(10, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(11, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(12, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(13, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        this.pitMap.put(14, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_BIG_PIT_STONE_NUMBER).pitType(PitType.BIG).build());
    }

    public int getPitIdOnGameBoard(int moveNumber) {
        int pitId = moveNumber % PIT_END_INDEX;
        if (pitId == 0) pitId = PIT_END_INDEX;
        return pitId;
    }

    public void emptyPit(Integer pitId) {
        this.pitMap.get(pitId).setStoneNumber(0);
        log.info("pit {} was emptied", pitId);
    }

}
