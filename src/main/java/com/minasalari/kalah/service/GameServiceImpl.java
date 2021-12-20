package com.minasalari.kalah.service;

import com.minasalari.kalah.dto.BusinessMessage;
import com.minasalari.kalah.dto.Message;
import com.minasalari.kalah.dto.GameResponse;
import com.minasalari.kalah.model.*;
import com.minasalari.kalah.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Map;

@Service
@SessionScope
@Slf4j
public class GameServiceImpl implements GameService {

    private final RuleService ruleService;
    private final GameRepository gameRepository;
    private Message message;

    public GameServiceImpl(RuleService ruleService, GameRepository gameRepository) {
        this.ruleService = ruleService;
        this.gameRepository = gameRepository;
    }

    @Override
    public GameResponse startNewGame() {
        Game game = gameRepository.save(new Game());
        message = new Message(BusinessMessage.GAME_STARTED, game.getTurn().name());
        log.info("Game is created {}", game);
        return new GameResponse(game, message);
    }

    @Override
    public GameResponse play(Integer gameId,Integer inputPitId) {
        Game game = findGameById(gameId);
        ruleService.validateMove(game.getGameBoard().getPitMap().get(inputPitId), game.getTurn(), inputPitId);
        move(game, inputPitId);
        checkEndGame(game);
        return new GameResponse(game, message);
    }

    private void checkEndGame(Game game) {
        Player emptySide = ruleService.getEmptySide(game.getGameBoard().getPitMap());
        if( emptySide!= null) {
            captureAllRemainedStone(game, Player.PLAYER_1);
            captureAllRemainedStone(game, Player.PLAYER_2);

            detectWinner(game);
            message = new Message(BusinessMessage.WINNER, game.getTurn().name());
        }
    }

    private GameResponse decideNextRoundPlayer(Game game, Integer endPitId) {
        if(ruleService.isFreeMove(getPitById(game, endPitId), game.getTurn())){
            log.info("It is free move.");
            message = new Message(BusinessMessage.FREE_MOVE, game.getTurn().name());
        }
        else{
            switchTurn(game);
            message = new Message(BusinessMessage.TURN, game.getTurn().name());
        }
        return new GameResponse(game, message);
    }

    private void switchTurn(Game game) {
        if (Player.PLAYER_1.equals(game.getTurn())) {
            game.setTurn(Player.PLAYER_2);
        } else if(Player.PLAYER_2.equals(game.getTurn())) {
            game.setTurn(Player.PLAYER_1);
        }
        log.info("It is {} turn.", game.getTurn());
    }

    private void move(Game game,Integer inputPitId){
        GameBoard gameBoard = game.getGameBoard();
        Map<Integer, Pit> pitMap = gameBoard.getPitMap();
        int stoneNumber = gameBoard.getPitMap().get(inputPitId).getStoneNumber();
        int moveCount = stoneNumber;
        int nextPitId = 0;
        int lastPitId;
        gameBoard.emptyPit(inputPitId);
        log.info("inputPitId {} have {} stones", inputPitId, stoneNumber);
        while(moveCount>0) {
            nextPitId = gameBoard.getPitIdOnGameBoard(++inputPitId);
            Pit pit = pitMap.get(nextPitId);
            if(!PitType.BIG.equals(pit.getPitType()) || pit.getOwner().equals(game.getTurn())) {
                pit.setStoneNumber(pit.getStoneNumber()+1);
                moveCount--;
                log.info("add one stone to pit id: {}", nextPitId);
            }
        }
        gameBoard.setPitMap(pitMap);
        lastPitId = nextPitId;
        game.setRound(game.getRound()+1);
        captureOppositeStones(game, lastPitId, game.getTurn());
        decideNextRoundPlayer(game, lastPitId);
    }

    private void detectWinner(Game game) {
        int player1Stones = game.getGameBoard().getPitMap().get(Player.PLAYER_1.getBigPitIndex()).getStoneNumber();
        int player2Stones = game.getGameBoard().getPitMap().get(Player.PLAYER_2.getBigPitIndex()).getStoneNumber();
        log.info("player1 stone number is: {}, player2 stonNumber is: {}", player1Stones, player2Stones);
        if (player1Stones > player2Stones) {
            game.setWinner(Player.PLAYER_1);
        } else if(player1Stones < player2Stones) {
            game.setWinner(Player.PLAYER_2);
        }
    }

    private void captureOppositeStones(Game game,Integer endPitId, Player player){
        GameBoard gameBoard = game.getGameBoard();
        if(ruleService.isCaptureOppositeStones(getPitById(game, endPitId), game.getTurn())) {
            int competentOppositePitId = Player.PLAYER_2.getBigPitIndex() - endPitId;
            log.info("it is capture move. end pit id is: {}, opposite pit id is: {}", endPitId, competentOppositePitId);
            Pit competentOppositePit = gameBoard.getPitMap().get(competentOppositePitId);
            int competentStone = competentOppositePit.getStoneNumber();
            if (competentStone > 0) {
                gameBoard.emptyPit(competentOppositePitId);
                int endPitStone = gameBoard.getPitMap().get(endPitId).getStoneNumber();
                gameBoard.emptyPit(endPitId);
                addToBigPit(game, player, competentStone + endPitStone);
            }
        }
    }
    private void captureAllRemainedStone(Game game, Player player){
        GameBoard gameBoard = game.getGameBoard();
        int allRemainedStone = 0;
        for (Integer key : gameBoard.getPitMap().keySet()) {
            if(player.equals(gameBoard.getPitMap().get(key).getOwner()) &&
                    gameBoard.getPitMap().get(key).getStoneNumber() !=0 &&
                    PitType.LITTLE.equals(gameBoard.getPitMap().get(key).getPitType())) {
                allRemainedStone = allRemainedStone + gameBoard.getPitMap().get(key).getStoneNumber();
                gameBoard.emptyPit(key);
            }
        }
         addToBigPit(game, player, allRemainedStone);
    }

    private void addToBigPit(Game game, Player player, int stones){
        Map<Integer, Pit> pitMap = game.getGameBoard().getPitMap();
        int bigPitStones;
        int totalStones;
        if(player.equals(Player.PLAYER_1)) {
            int bigPitIdPlayer1 = Player.PLAYER_1.getBigPitIndex();
            bigPitStones = pitMap.get(bigPitIdPlayer1).getStoneNumber();
            totalStones = stones + bigPitStones;
            pitMap.get(bigPitIdPlayer1).setStoneNumber(totalStones);
            log.info("{} stones was added to player1 big pit", stones);
        }
        if(player.equals(Player.PLAYER_2)) {
            int bigPitIdPlayer2 = Player.PLAYER_2.getBigPitIndex();
            bigPitStones = pitMap.get(bigPitIdPlayer2).getStoneNumber();
            totalStones = stones + bigPitStones;
            pitMap.get(bigPitIdPlayer2).setStoneNumber(totalStones);
            log.info("{} stones was added to player1 big pit", stones);
        }
    }

    private Pit getPitById(Game game, Integer id){
        return game.getGameBoard().getPitMap().get(id);
    }

    public Game findGameById(Integer gameId){
        return gameRepository.findById(gameId);
    }
}
