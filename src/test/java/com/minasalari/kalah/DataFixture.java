package com.minasalari.kalah;

import com.minasalari.kalah.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.minasalari.kalah.model.GameBoard.INITIAL_BIG_PIT_STONE_NUMBER;
import static com.minasalari.kalah.model.GameBoard.INITIAL_LITTLE_PIT_STONE_NUMBER;

public class DataFixture {

    public static Game anyInitialGame(){
        Game game=new Game();
        game.setRound(1);
        game.setId(1);
        game.setTurn(Player.PLAYER_1);
        game.setWinner(Player.PLAYER_1);
        game.setGameTicket("123");
        game.setGameBoard(aDefaultGameBoard());
        return game;
    }

    public static GameBoard aDefaultGameBoard(){
        Map<Integer, Pit> pitMap = new HashMap<>();

        pitMap.put(1, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(2, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(3, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(4, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(5, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(6, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(7, Pit.builder().owner(Player.PLAYER_1).stoneNumber(INITIAL_BIG_PIT_STONE_NUMBER).pitType(PitType.BIG).build());
        pitMap.put(8, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(9, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(10, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(11, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(12, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(13, Pit.builder().owner(Player.PLAYER_2).stoneNumber(INITIAL_LITTLE_PIT_STONE_NUMBER).pitType(PitType.LITTLE).build());
        pitMap.put(14, Pit.builder().owner(Player.PLAYER_2).stoneNumber(0).pitType(PitType.BIG).build());

        GameBoard gameBoard = new GameBoard();
        gameBoard.setPitMap(pitMap);
        return gameBoard;
    }

    public static GameBoard anEndGameBoard(){
        Random random = new Random();
        int upperbound = INITIAL_LITTLE_PIT_STONE_NUMBER;
        Map<Integer, Pit> pitMap = new HashMap<>();
        pitMap.put(1, Pit.builder().owner(Player.PLAYER_1).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(2, Pit.builder().owner(Player.PLAYER_1).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(3, Pit.builder().owner(Player.PLAYER_1).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(4, Pit.builder().owner(Player.PLAYER_1).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(5, Pit.builder().owner(Player.PLAYER_1).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(6, Pit.builder().owner(Player.PLAYER_1).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(7, Pit.builder().owner(Player.PLAYER_1).stoneNumber(20).pitType(PitType.BIG).build());
        pitMap.put(8, Pit.builder().owner(Player.PLAYER_2).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(9, Pit.builder().owner(Player.PLAYER_2).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(10, Pit.builder().owner(Player.PLAYER_2).stoneNumber(0).pitType(PitType.LITTLE).build());
        pitMap.put(11, Pit.builder().owner(Player.PLAYER_2).stoneNumber(2).pitType(PitType.LITTLE).build());
        pitMap.put(12, Pit.builder().owner(Player.PLAYER_2).stoneNumber(2).pitType(PitType.LITTLE).build());
        pitMap.put(13, Pit.builder().owner(Player.PLAYER_2).stoneNumber(2).pitType(PitType.LITTLE).build());
        pitMap.put(14, Pit.builder().owner(Player.PLAYER_2).stoneNumber(10).pitType(PitType.BIG).build());

        GameBoard gameBoard = new GameBoard();
        gameBoard.setPitMap(pitMap);
        return gameBoard;
    }

    public static GameBoard anyGameBoard(){
        Random random = new Random();
        int upperbound = INITIAL_LITTLE_PIT_STONE_NUMBER;
        Map<Integer, Pit> pitMap = new HashMap<>();
        pitMap.put(1, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(2, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(3, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(4, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(5, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(6, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(7, Pit.builder().owner(Player.PLAYER_1).stoneNumber(random.nextInt(upperbound)).pitType(PitType.BIG).build());
        pitMap.put(8, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(9, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(10, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(11, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(12, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(13, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.LITTLE).build());
        pitMap.put(14, Pit.builder().owner(Player.PLAYER_2).stoneNumber(random.nextInt(upperbound)).pitType(PitType.BIG).build());

        GameBoard gameBoard = new GameBoard();
        gameBoard.setPitMap(pitMap);
        return gameBoard;
    }

}
