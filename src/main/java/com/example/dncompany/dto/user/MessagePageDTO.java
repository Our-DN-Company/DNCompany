package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class MessagePageDTO {
    private Long userId;
    private Long messageId;
    private Long userFrom;
    private Long userTo;
    private String loginId;
    private String msContent;
    private LocalDate msDate;
}
