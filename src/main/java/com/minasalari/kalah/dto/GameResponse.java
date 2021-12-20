package com.minasalari.kalah.dto;

import com.minasalari.kalah.model.Game;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class GameResponse {
    private Game game;
    private Message message;


}
