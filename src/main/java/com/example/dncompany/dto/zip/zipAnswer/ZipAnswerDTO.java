package com.example.dncompany.dto.zip.zipAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class ZipAnswerDTO {
    Long zipAnswerId;
    String zipAnswerContent;
    LocalDate zipAnswerCreatedAt;
    LocalDate zipAnswerUpdatedAt;
    Long userId;
    Long zipId;
}
