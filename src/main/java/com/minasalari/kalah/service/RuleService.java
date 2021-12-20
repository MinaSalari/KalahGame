package com.minasalari.kalah.service;

import com.minasalari.kalah.dto.BusinessMessage;
import com.minasalari.kalah.exception.IllegalMoveException;
import com.minasalari.kalah.model.Pit;
import com.minasalari.kalah.model.PitType;
import com.minasalari.kalah.model.Player;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class RuleService {

    Player getEmptySide(Map<Integer,Pit> pitMap){

        if(isSideEmpty(getLittlePitByOwner(pitMap, Player.PLAYER_2)))
            return  Player.PLAYER_2;
        if(isSideEmpty(getLittlePitByOwner(pitMap, Player.PLAYER_1)))
            return Player.PLAYER_1;
        else
            return null;
    }

    Set<Pit> getLittlePitByOwner(Map<Integer,Pit> pitMap, Player player) {
        return pitMap.values().stream().filter(p -> p.getOwner().equals(player) && p.getPitType().equals(PitType.LITTLE) ).collect(Collectors.toSet());
    }

    boolean isSideEmpty(Set<Pit> pitSet) {
        boolean sideEmptyFlag = true;
        for (Pit pit : pitSet) {
            if(pit.getStoneNumber()!=0){
                sideEmptyFlag=false;
                break;
            }
        }
        return sideEmptyFlag;
    }

    boolean isFreeMove(Pit endPit, Player currentPlayer){
        return PitType.BIG.equals(endPit.getPitType()) && currentPlayer.equals(endPit.getOwner());
    }

    boolean isCaptureOppositeStones(Pit endPit, Player currentPlayer){
        return endPit.getStoneNumber()==1 && currentPlayer.equals(endPit.getOwner()) && endPit.getPitType().equals(PitType.LITTLE); //TODO
    }

    void validateMove(Pit pit, Player turn, Integer inputPitId){
        validatePitIdRange(inputPitId);
        validatePitType(pit.getPitType());
        validatePitOwner(inputPitId, turn);
        validateEmptyPit(pit.getStoneNumber());
    }

    void validatePitIdRange(Integer inputPitId) {
        if(inputPitId>Player.PLAYER_2.getBigPitIndex())
            throw new IllegalMoveException(BusinessMessage.PITID_IS_OUT_OF_RANGE);
    }

    void validatePitType(PitType pitType) {
        if (PitType.BIG.equals(pitType))
            throw new IllegalMoveException(BusinessMessage.BIG_PIT_COULD_NOT_BE_SELECTED_AS_A_START_POINT);
    }

    void validatePitOwner(int pitId, Player player) {
        if((Player.PLAYER_1.equals(player) && pitId >=Player.PLAYER_1.getBigPitIndex()) || (Player.PLAYER_2.equals(player) && pitId <=Player.PLAYER_1.getBigPitIndex()))
            throw new IllegalMoveException(BusinessMessage.PLAYER_DOES_NOT_OWN_THIS_PIT_ID);
    }

    void validateEmptyPit(int stoneNumber) {
        if(stoneNumber == 0)
            throw new IllegalMoveException(BusinessMessage.PIT_IS_EMPTY);
    }

}
