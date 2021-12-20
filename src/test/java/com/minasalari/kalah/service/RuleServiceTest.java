package com.minasalari.kalah.service;

import com.minasalari.kalah.exception.IllegalMoveException;
import com.minasalari.kalah.model.Game;
import com.minasalari.kalah.model.Pit;
import com.minasalari.kalah.model.PitType;
import com.minasalari.kalah.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RuleServiceTest {

    @Autowired
    private RuleService ruleService;

    private Game game;

    @BeforeEach
    void init(){
        game = new Game();
    }

    @Test
    void testIsFreeMove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method isFreeMove = GameServiceImplTest.openMethodForTest(ruleService.getClass().getDeclaredMethod("isFreeMove", Pit.class, Player.class));
        Boolean result1 = (Boolean) isFreeMove.invoke(ruleService, game.getGameBoard().getPitMap().get(Player.PLAYER_1.getBigPitIndex()),Player.PLAYER_1);
        Boolean result2 = (Boolean) isFreeMove.invoke(ruleService, game.getGameBoard().getPitMap().get(2),Player.PLAYER_1);

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void testValidateEmptyPit() {
        assertThrows(IllegalMoveException.class, () -> {
            ruleService.validateEmptyPit( 0);
        });
    }

    @Test
    void testValidatePitOwner(){
        assertThrows(IllegalMoveException.class, () -> {
            ruleService.validatePitOwner( 2, Player.PLAYER_2);
        });
    }

    @Test
    void testValidatePitType(){
        assertThrows(IllegalMoveException.class, () -> {
            ruleService.validatePitType( PitType.BIG);
        });
    }

    @Test
    void testvalidatePitIdRange(){
        assertThrows(IllegalMoveException.class, () -> {
            ruleService.validatePitIdRange( 17);
        });
    }

}