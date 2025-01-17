package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DnBoardWriteDTO {
    private Long dnId;
    private Long usersId;
    private String productName;
    private String dnPetCategory;
    private String dnTitle;
    private String dnContent;
    private Long productId;
}
