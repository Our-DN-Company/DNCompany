package com.example.dncompany.dto.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class ZipBoardDetailDTO {
    private Long zipId;
    private String zipPetCategory;
    private String zipTitle;
    private String zipContent;
    private LocalDate zipCreatedAt;
    private LocalDate zipUpdatedAt;
    private int zipViewCount;
    private int zipLikeCount;
    private Long userId;
}
