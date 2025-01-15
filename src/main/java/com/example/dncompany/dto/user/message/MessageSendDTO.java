package com.example.dncompany.dto.user.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MessageSendDTO {
    private Long usersId;
    private Long userFrom;
    private Long userTo;
    private String loginIdTo;
    private String msContent;
    private Long messageId;
}
