package com.example.dncompany.dto.admin.login;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminSessionDTO {
    private Long usersId;
    private String loginId;
    private String role;
}
