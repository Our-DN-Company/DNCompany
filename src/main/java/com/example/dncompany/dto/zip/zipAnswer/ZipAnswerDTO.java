package com.example.dncompany.dto.zip.zipAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class ZipAnswerDTO {
    private Long zipAnswerId;
    private String zipAnswerContent;
    private LocalDate zipAnswerCreatedAt;
    private LocalDate zipAnswerUpdatedAt;
    private Long zipId;
    private Long usersId;
    private String nickname;
}
