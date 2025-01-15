package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class QnaListDTO {
    private  int rnum;
    private Long qnaId;
    private String qnaTitle;
    private LocalDate qnaCreateAt;
    private int qnaAnswerCount;
    private Long usersId;
}