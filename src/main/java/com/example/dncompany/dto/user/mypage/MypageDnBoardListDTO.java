package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MypageDnBoardListDTO {
   private int rnum;
   private Long dnId;
   private String dnProductCategory;
   private String dnTitle;
   private Long usersId;
}
