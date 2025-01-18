package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class MypageReviewListDTO {
    private int rnum;
    private Long reviewId;
    private String reviewContent;
    private LocalDate reviewCreatedAt;
    private Long usersId;

}
