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
}
