package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DnBoardModifyDTO {
    Long dnId;
    String dnPetCategory;
    String dnTitle;
    String dnContent;
}
