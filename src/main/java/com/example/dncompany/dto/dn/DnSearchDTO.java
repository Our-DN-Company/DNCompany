package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DnSearchDTO {
    private String searchType;
    private String keyword;
    private String order;
    private String dnPetCategory;
    private String productCategory;
}
