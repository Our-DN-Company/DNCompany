package com.example.dncompany.dto.qna;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class QnADetailDTO {
    private Long qnaId;
    private String qnaTitle;
    private String qnaContent;
//    private LocalDateTime qnaCreateAt;
    private LocalDateTime qnaUpdateAt;
    private Long usersId;
    private String loginId;

    public String getQnaUpdateAt() {
        return qnaUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
