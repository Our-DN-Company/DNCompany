package com.example.dncompany.dto.qna.qnaAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class QnaAnswerDTO {
    private Long qnaAnswerId;
    private String qnaAnswerContent;
    private LocalDate qnaAnswerCreatedAt;
    private LocalDate qnaAnswerUpdatedAt;
    private Long qnaId;
    private Long usersId;
    private String nickname;
}
