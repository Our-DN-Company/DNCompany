package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class HelpOfferSmsDTO {
    private String helpTitle;
    private LocalDate helpCareDate;
    private LocalTime helpStartTime;
    private String helpAddress;
    private String helpAddressDetail;
    private Long usersId;
    private String phoneNumber;

    public String getHelpCareDate() {
        return helpCareDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }

    public String getHelpStartTime() {
        return helpStartTime.format(DateTimeFormatter.ofPattern("HH시 mm분"));
    }
}
