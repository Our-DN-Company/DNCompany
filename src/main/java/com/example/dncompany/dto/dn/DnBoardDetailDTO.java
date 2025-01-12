package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class DnBoardDetailDTO {
    Long dnId;
    LocalDateTime dbCreatedAt;
    LocalDateTime dbUpdatedAt;
    String dnPetCategory;
    String dnTitle;
    String dnContent;
    String productCategory;
    int productPrice;
    Long productId;
    Long usersId;
}
