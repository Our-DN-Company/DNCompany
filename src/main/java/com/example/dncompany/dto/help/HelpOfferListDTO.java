package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class HelpOfferListDTO {
    private Long helpOfferId;
    private String helpOfferStatus;
    private Long helpId;
    private Long usersId;
}
