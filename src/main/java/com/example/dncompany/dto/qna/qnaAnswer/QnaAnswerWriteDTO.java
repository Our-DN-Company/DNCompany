package com.example.dncompany.dto.qna.qnaAnswer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class QnaAnswerWriteDTO {
    private Long qnaAnswerId;
    private String qnaAnswerContent;
    private Long qnaId;
    private Long usersId;
}
