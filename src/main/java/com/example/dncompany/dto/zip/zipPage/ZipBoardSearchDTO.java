package com.example.dncompany.dto.zip.zipPage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ZipBoardSearchDTO {
    private String searchType;
    private String keyword;
    private String order;
    private String category;
}
