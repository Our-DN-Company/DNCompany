package com.example.dncompany.dto.admin.main;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminBoardCountDTO {

    private String postDate;
    private int qnaCount;
    private int zipCount;
    private int eventCount;
    private int reportCount;
    private int helpCount;
}
