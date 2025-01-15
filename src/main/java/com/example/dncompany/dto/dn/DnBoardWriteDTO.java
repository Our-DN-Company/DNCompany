package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DnBoardWriteDTO {
    Long dnId;
    Long usersId;
    String dnPetCategory;
    String dnTitle;
    String dnContent;
}
