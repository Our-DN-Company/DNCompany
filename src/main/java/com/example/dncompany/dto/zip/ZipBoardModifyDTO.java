package com.example.dncompany.dto.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ZipBoardModifyDTO {
    private Long zipId;
    private String zipTitle;
    private String zipCategory;
    private String zipPetCategory;
    private String zipContent;
    private Long usersId;
}
