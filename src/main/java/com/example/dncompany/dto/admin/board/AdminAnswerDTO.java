package com.example.dncompany.dto.admin.board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class AdminAnswerDTO {
    private Long qnaAnswerId;
    private String qnaAnswerContent;
    private Date qnaAnswerCreatedAt;
    private Date qnaAnswerUpdatedAt;
    private Long qnaId;
    private String category;
}
