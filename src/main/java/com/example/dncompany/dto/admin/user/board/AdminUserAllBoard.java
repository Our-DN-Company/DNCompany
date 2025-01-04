package com.example.dncompany.dto.admin.user.board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminUserAllBoard {

    private Long userId;
    private String loginId;
    private String name;
    private String address;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private String signUpDate;
    private String reportCount;
    private int points;


}
