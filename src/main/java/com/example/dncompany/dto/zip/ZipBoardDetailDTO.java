package com.example.dncompany.dto.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class ZipBoardDetailDTO {
    private Long zipId;
    private String zipPetCategory;
    private String zipTitle;
    private String zipContent;
    private LocalDateTime zipCreatedAt;
    private LocalDateTime zipUpdatedAt;
    private int zipViewCount;
    private int answerCount;
    private Long userId;
    private String nickname;
}
