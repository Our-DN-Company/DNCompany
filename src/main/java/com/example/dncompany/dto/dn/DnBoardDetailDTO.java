package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class DnBoardDetailDTO {
    private Long dnId;
    private LocalDateTime dbCreatedAt;
    private LocalDateTime dbUpdatedAt;
    private String dnPetCategory;
    private String dnTitle;
    private String dnContent;
    private String productCategory;
    private int productPrice;
    private Long productId;
    private Long usersId;
    private String loginId;
    private int likeCount;

    private Long productImgId;
    private String productOriginalFilename;
    private String productUuid;
    private String productPath;
    private String productExtension;
}
