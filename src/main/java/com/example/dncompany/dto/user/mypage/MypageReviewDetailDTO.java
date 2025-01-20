package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class MypageReviewDetailDTO {
    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private String reviewCheckStatus;
    private LocalDate reviewCreatedAt;
    private String reviewAiAssessment;
    private Long helpId;
    private Long helpOfferId;
    private Long usersId;
    private String loginId;
    private String nickname;
}
