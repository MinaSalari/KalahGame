package com.minasalari.kalah.repository;

import com.minasalari.kalah.model.Game;

public interface GameRepository {

    Game save(Game game);
    Game findById(Integer gameId);

}
