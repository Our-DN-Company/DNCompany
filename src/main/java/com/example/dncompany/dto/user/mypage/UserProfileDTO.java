package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserProfileDTO {
   private Long usersId;
   private String nickname;
   private String email;
   private int point;

}
