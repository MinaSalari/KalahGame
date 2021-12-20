package com.minasalari.kalah.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

@Setter
@Getter
public class Game {
    public static final int BOUND = 999;
    public static final int TICKET_TIMEOUT = 60;
    @NotNull
    private Integer id;
    @NotNull
    private Player turn;
    @NotNull
    private int round;
    private Player winner;
    @NotNull
    private GameBoard gameBoard;
    @JsonIgnore
    private String gameTicket;

    public Game() {
        this.id = new Random().nextInt(BOUND);
        this.gameBoard = new GameBoard();
        this.turn = Player.PLAYER_1;
        this.round = 1;
        this.gameTicket = createTicket();
    }

    //TODO: In future development we can make ticket more complicated and hash it
    private String createTicket() {
        return LocalTime.now().plusMinutes(TICKET_TIMEOUT) + UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", turn=" + turn +
                ", round=" + round +
                ", winner=" + winner +
                ", gameBoard=" + gameBoard +
                '}';
    }
}
