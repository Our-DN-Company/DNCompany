package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MypageZipAnswerListDTO {
   private Long rnum;
    private Long zipAnswerId;
    private String zipTitle;
    private String zipAnswerContent;
    private LocalDate zipAnswerCreatedAt;
    private Long usersId;
    private Long zipId;
}