package com.minasalari.kalah.service;

import com.minasalari.kalah.model.Game;
import com.minasalari.kalah.model.GameBoard;
import com.minasalari.kalah.model.Pit;
import com.minasalari.kalah.model.Player;
import com.minasalari.kalah.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private RuleService ruleService;

    private Game mockGame;
    private Game mockGameWinnerPlayer1;
    private Game mockGameWinnerPlayer2;
    private Game mockGameNextTurnPlayer1;
    private Game mockGameNextTurnPlayer2;

    @BeforeEach
    void init() {
        mockGame = new Game();
        mockGameWinnerPlayer1 = new Game();
        mockGameWinnerPlayer1.getGameBoard().getPitMap().get(Player.PLAYER_1.getBigPitIndex()).setStoneNumber(1);
        mockGameWinnerPlayer2 = new Game();
        mockGameWinnerPlayer2.getGameBoard().getPitMap().get(Player.PLAYER_2.getBigPitIndex()).setStoneNumber(1);
        mockGameNextTurnPlayer1 = new Game();
        mockGameNextTurnPlayer1.setTurn(Player.PLAYER_2);
        mockGameNextTurnPlayer2 = new Game();
        mockGameNextTurnPlayer2.setTurn(Player.PLAYER_1);
    }


    @Test
    void testStartNewGame() {
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);
        GameBoard gameBoard = gameService.startNewGame().getGame().getGameBoard();
        assertNotNull(gameBoard);
        assertEquals(mockGame.getGameBoard(),gameBoard);
        verify(gameRepository,times(1)).save(any(Game.class));
    }

    @Test
    void testFindGameById() {
        when(gameRepository.findById(anyInt())).thenReturn(mockGame);
        Game game = gameService.findGameById(mockGame.getId());
        assertEquals(mockGame,game);
        verify(gameRepository, times(1)).findById(anyInt());
    }

    @Test
    public void testDetectWinner() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method detectWinner = openMethodForTest(gameService.getClass().getDeclaredMethod("detectWinner", Game.class));
        detectWinner.invoke(gameService, mockGame);
        detectWinner.invoke(gameService, mockGameWinnerPlayer1);
        detectWinner.invoke(gameService, mockGameWinnerPlayer2);

        assertNull(mockGame.getWinner());
        assertEquals(Player.PLAYER_1, mockGameWinnerPlayer1.getWinner());
        assertEquals(Player.PLAYER_2, mockGameWinnerPlayer2.getWinner());
    }

    @Test
    public void testSwitchTurn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method switchTurn = openMethodForTest(gameService.getClass().getDeclaredMethod("switchTurn", Game.class));
        switchTurn.invoke(gameService, mockGame);
        switchTurn.invoke(gameService, mockGameNextTurnPlayer1);
        switchTurn.invoke(gameService, mockGameNextTurnPlayer2);

        assertNotNull(mockGame.getTurn());
        assertEquals(Player.PLAYER_1, mockGameNextTurnPlayer1.getTurn());
        assertEquals(Player.PLAYER_2, mockGameNextTurnPlayer2.getTurn());
    }

    @Test
    public void testAddToBigPit() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method addToBigPit = openMethodForTest(gameService.getClass().getDeclaredMethod("addToBigPit", Game.class, Player.class, int.class));
        addToBigPit.invoke(gameService, mockGame, Player.PLAYER_1, 20);

        assertEquals(20, mockGame.getGameBoard().getPitMap().get(Player.PLAYER_1.getBigPitIndex()).getStoneNumber());
    }

    @Test
    public void testCaptureAllRemainedStone() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method captureAllRemainedStone = openMethodForTest(gameService.getClass().getDeclaredMethod("captureAllRemainedStone", Game.class, Player.class));
        captureAllRemainedStone.invoke(gameService, mockGame, Player.PLAYER_1);
        assertEquals(36, mockGame.getGameBoard().getPitMap().get(Player.PLAYER_1.getBigPitIndex()).getStoneNumber());
    }

    @Test
    public void testMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method move = openMethodForTest(gameService.getClass().getDeclaredMethod("move", Game.class, Integer.class));
        move.invoke(gameService, mockGame, 1);
        assertEquals(0, mockGame.getGameBoard().getPitMap().get(1).getStoneNumber());
        assertEquals(1, mockGame.getGameBoard().getPitMap().get(Player.PLAYER_1.getBigPitIndex()).getStoneNumber());
    }

    static Method openMethodForTest(final Method method) {
        ReflectionUtils.makeAccessible(method);
        return method;
    }
}