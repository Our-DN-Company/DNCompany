package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class DnBoardDTO {
    Long dnId;
    Long usersId;
    String dnPetCategory;
    String dnTitle;
    String dnContent;
}
