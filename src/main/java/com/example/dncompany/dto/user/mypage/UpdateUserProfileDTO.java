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

    private String emailText;
    private String emailId;

    public String getEmailText() {
        String[] emailtextlist = this.email.split("@");

        emailText=emailtextlist[0];


        return emailText;
    }

    public String getEmailId() {
        String[] emailtextlist = this.email.split("@");
        emailId=emailtextlist[1];
        return emailId;
    }



}
