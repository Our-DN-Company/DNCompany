package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UpdateUserProfileDTO {
    private Long usersId;
    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String addressExtra;

}
