package com.example.dncompany.dto.zip.zipAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class ZipAnswerDTO {
    private Long zipAnswerId;
    private String zipAnswerContent;
//    private LocalDate zipAnswerCreatedAt;
    private LocalDate zipAnswerUpdatedAt;
    private Long zipId;
    private Long usersId;
    private String nickname;

//    public String getZipAnswerUpdatedAt () {
//        return zipAnswerUpdatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
}
