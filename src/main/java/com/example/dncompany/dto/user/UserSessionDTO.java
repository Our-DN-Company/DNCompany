package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class UserSessionDTO {
    private Long usersId;
    private String loginId;
    private String role;

    private String userStatus;     // 추가
    private Date banStartDate;     // 추가
    private Date banEndDate;       // 추가
}
