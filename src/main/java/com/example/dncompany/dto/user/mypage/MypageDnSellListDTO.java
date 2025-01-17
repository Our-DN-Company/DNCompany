package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MypageDnSellListDTO {
  private  int rnum;
  private  Long productId;
  private  String productName;
  private  Long productPrice;
  private  Long usersId;

  private String productCategory;
  private String productImgId;
  private String productPath;
  private String productUuid;
  private String productExtension;
  private String sellerUsersId;
  private String sellerLoginId;
  private String sellerNickname;
}
