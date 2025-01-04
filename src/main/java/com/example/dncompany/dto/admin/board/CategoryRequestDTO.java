package com.example.dncompany.dto.admin.board;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    private String category;
    private String searchType;
    private String searchInput;
    private String startDate;
    private String endDate;
}
