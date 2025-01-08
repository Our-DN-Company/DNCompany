package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter@Setter@ToString
public class HelpOfferResponseDTO {
    private Long helpOfferId;
    private Long helpId;
    private String helpOfferStatus;
    private LocalDateTime helpEnrollDate;
    private Long usersId;
}
