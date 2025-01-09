package com.example.dncompany.dto.zip.zipAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ZipAnswerWriteDTO {
    private Long zipAnswerId;
    private String zipAnswerContent;
    private Long zipId;
    private Long userId;
}
