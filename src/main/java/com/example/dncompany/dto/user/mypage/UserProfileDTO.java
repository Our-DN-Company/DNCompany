package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProfileDTO {
    private Long usersId;
    private String nickName;
    private String loginId;
    private String email;
    private String phoneNumber;
    private int hbCount;
    private int hoCount;

    private int point;

}
