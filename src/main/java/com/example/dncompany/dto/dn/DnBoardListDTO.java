package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class DnBoardListDTO {
    Long dnId;
    LocalDateTime dnCreatedAt;
    LocalDateTime dnUpdatedAt;
    String dnPetCategory;
    String dnTitle;
    String productCategory;
    int productPrice;
    String productId;
    String usersId;
    String loginId;
}
