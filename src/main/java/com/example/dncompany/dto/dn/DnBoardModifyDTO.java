package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DnBoardModifyDTO {
    private Long usersId;
    private String productName;
    private Long dnId;
    private String dnPetCategory;
    private String dnTitle;
    private String dnContent;
}
