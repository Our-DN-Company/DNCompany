package com.example.dncompany.dto.qna;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class QnADTO {
    private Long qnaId;
    private String qnaTitle;
    private String qnaContent;
//    private LocalDateTime qnaCreatedAt;
    private LocalDateTime qnaUpdateAt;
    private String qnaCheckStatus;
    private Long usersId;
    private String nickname;

//    public String getQnaCreateAt() {
//        return qnaCreatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }

    public String getQnaUpdateAt() {
        return qnaUpdateAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getQnaContent() {
        String regEx = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
        return this.qnaContent.replaceAll(regEx, "");
    }

}
