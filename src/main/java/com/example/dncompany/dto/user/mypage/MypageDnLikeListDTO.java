package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MypageDnLikeListDTO {
    private Integer rnum;
    private Long dnId;
    private Long usersId;
    private String dnTitle;
    private String productName;
    private int productPrice;
    private Long productImgId;
    private String productPath;
    private String productUuid;
    private String productExtension;
    private String sellerLoginId;
    private String sellerNickname;
}
