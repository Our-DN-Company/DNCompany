package com.example.dncompany.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class UserUpdateKakaoDTO {
    private Long usersId;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String gender;
    private LocalDate birthDate;
    private String role;

    public String getBirthDate() {
        return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
