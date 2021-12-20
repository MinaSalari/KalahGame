package com.minasalari.kalah.dto;

public enum BusinessMessage {

    GAME_STARTED(1000, MessageType.INFO, " Game started. It is %s turn."),
    WINNER(1001, MessageType.INFO, "Congrats! %s is winner"),
    FREE_MOVE(1002, MessageType.INFO, "%s have free move"),
    TURN(1003, MessageType.INFO, " is %s turn"),
    GAME_IS_NOT_FOUND(2000,MessageType.ERROR,"Game is not Found"),
    PIT_IS_EMPTY(2001,MessageType.ERROR, "This pit is empty"),
    PLAYER_DOES_NOT_OWN_THIS_PIT_ID(2002, MessageType.ERROR, "Current player does not own this pitId"),
    BIG_PIT_COULD_NOT_BE_SELECTED_AS_A_START_POINT(2003,MessageType.ERROR, "Start point is not correct. Big Pit could not be selected as a start point"),
    PITID_IS_OUT_OF_RANGE(2004,MessageType.ERROR,"Start point is not correct. PitId is out of range"),
    GAME_ACCESS_IS_DENIED(2005,MessageType.ERROR,"Game access is denied");


    private final int code;
    private final MessageType type;
    private final String messageBody;

    BusinessMessage(int code, MessageType type, String messageBody) {
        this.code = code;
        this.type = type;
        this.messageBody = messageBody;
    }

    public int getCode() {
        return code;
    }

    public MessageType getType() {
        return type;
    }

    public String getMessageBody() {
        return messageBody;
    }


}
