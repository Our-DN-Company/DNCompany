package com.example.dncompany.dto.user.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MessageSendDTO {
    private String loginId;
    private Long usersId;
    private String msContent;
    private Long messageId;
}
