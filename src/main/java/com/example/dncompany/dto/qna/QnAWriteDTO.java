package com.example.dncompany.dto.qna;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class QnAWriteDTO {
    private Long qnaId;
    private String qnaTitle;
    private String qnaContent;
    private Long usersId;
}
