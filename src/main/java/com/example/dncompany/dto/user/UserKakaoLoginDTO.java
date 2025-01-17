package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserKakaoLoginDTO {
    private Long usersId;
    private String LoginId;
    private String Password;
}
