package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserLoginDTO {
    private String loginId;
    private String password;
}
