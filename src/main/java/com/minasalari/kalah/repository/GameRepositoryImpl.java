package com.minasalari.kalah.repository;

import com.minasalari.kalah.dto.BusinessMessage;
import com.minasalari.kalah.exception.GameNotFoundException;
import com.minasalari.kalah.model.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private final Map<Integer, Game> repository = new HashMap<>();

    @Override
    public Game save(Game game) {
        this.repository.put(game.getId(), game);
        return findById(game.getId());
    }

    @Override
    public Game findById(Integer gameId) {
        Game game =  this.repository.get(gameId);
        Optional.ofNullable(game).orElseThrow(() -> new GameNotFoundException(BusinessMessage.GAME_IS_NOT_FOUND));
        return game;
    }
}
