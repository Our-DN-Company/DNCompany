package com.example.dncompany.dto.qna;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class QnADTO {
    private Long qnaId;
    private String qnaTitle;
    private String qnaContent;
    private LocalDateTime qnaCreatedAt;
    private LocalDateTime qnaUpdatedAt;
    private String qnaCheckStatus;
    private Long userId;
}
