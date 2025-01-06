package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserSessionDTO {
    private Long usersId;
    private String loginId;
    private String role;
}
