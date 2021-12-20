package com.minasalari.kalah.repository;

import com.minasalari.kalah.model.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameRepositoryImplTest {

    @Autowired
    private GameRepository gameRepository;


    @Test
    void testSave() {
        final Game game = gameRepository.save(new Game());
        assertNotNull(game);
    }

    @Test
    void testFindById() {
        final Game given = gameRepository.save(new Game());
        final Game when = gameRepository.findById(given.getId());

        assertNotNull(given);
        assertEquals(given, when);
    }
}