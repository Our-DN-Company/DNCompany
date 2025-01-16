package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserKakaoLogin {
    Long usersId;
    String LoginId;
    String Password;
}
