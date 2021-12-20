package com.minasalari.kalah.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Setter
@Getter
@ToString
public class Message {
    private Integer messageCode;
    private MessageType messageType;
    private String messageBody;

    public Message(BusinessMessage businessMessage, String... params) {
        this.messageCode = businessMessage.getCode();
        this.messageType = businessMessage.getType();
        this.messageBody = String.format(businessMessage.getMessageBody(), params);
    }

}
