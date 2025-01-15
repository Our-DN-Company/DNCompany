package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class HelpYouListDTO {
    private int rnum;
    private Long helpOfferId;
    private String helpOfferStatus;
    private String helpTitle;
    private String loginId;
    private Long helpId;
    private Long usersId;}
