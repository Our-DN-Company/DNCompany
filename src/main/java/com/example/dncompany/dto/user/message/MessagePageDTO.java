package com.example.dncompany.dto.user.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class MessagePageDTO {
    private long userFrom;
    private long userFromLoginId;
    private long userTo;
    private String msContent;
    private long messageId;
    private LocalDate msDate;
    private long usersId;
    private String loginId;
}
