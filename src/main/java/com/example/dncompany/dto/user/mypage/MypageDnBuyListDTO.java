package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MypageDnBuyListDTO {
   private int rnum;
   private Long dnId;
   private String productCategory;
   private String dnTitle;
   private Long usersId;
   private int productPrice;
   private String productName;
   private String productPath;
   private String productUuid;
   private String productExtension;
}
