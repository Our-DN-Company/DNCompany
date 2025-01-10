package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter @Setter @ToString
public class MypageZipBoardListDTO {
    private Long rnum;
    private Long zipId;
    private String zipTitle;
    private LocalDate zipCreatedAt;
    private Long usersId;
}
