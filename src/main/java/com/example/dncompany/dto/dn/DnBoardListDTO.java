package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class DnBoardListDTO {
    private Long dnId;
    private LocalDateTime dnCreatedAt;
    private LocalDateTime dnUpdatedAt;
    private String dnPetCategory;
    private String dnTitle;
    private String productName;
    private String productCategory;
    private int productPrice;
    private String productId;
    private String usersId;
    private String loginId;
    private int likeCount;

    // 파일 정보 추가
    private Long productImgId;
    private String productOriginalFilename;
    private String productUuid;
    private String productPath;
    private String productExtension;
}
