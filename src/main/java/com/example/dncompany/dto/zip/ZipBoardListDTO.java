package com.example.dncompany.dto.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class ZipBoardListDTO {
    private Long zipId;
    private String zipPetCategory;
    private String zipCategory; // zip 카테고리 X, 댕냥바다에 추가 해야할거 같음
    private String zipTitle;
    private String zipContent;
    private LocalDate zipCreatedAt;
    private LocalDate zipUpdatedAt;
    private int zipViewCount;
    private int zipLikeCount;
    private int answerCount;
    private Long userId;
    private String nickname;
}
