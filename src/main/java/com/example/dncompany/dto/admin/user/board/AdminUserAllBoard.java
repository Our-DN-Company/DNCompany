package com.example.dncompany.dto.admin.user.board;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Integer reportCount;
    private Integer points;

    // 검색 조건으로 사용될 필드
    private Integer age;        // 나이 검색용
    private String searchLoginId;   // 아이디 검색용
    private String searchName;      // 이름 검색용
    private String searchGender;   // 성별 검색용
    private String searchPhoneNumber; // 폰번호 검색용
    private String searchSignUpDate;// 가입일 검색용

    private String searchSignStartDate;  // 시작 날짜
    private String searchSignEndDate;    // 종료 날짜

    private String reportStatus;      // 신고여부 검색용



}
