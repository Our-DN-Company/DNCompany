package com.example.dncompany.dto.admin.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter @Setter @ToString
public class AdminBoardDeleteDTO {

    private List<Long> zipIds;
    private List<Long> qnaIds;
    private List<Long> helpIds;
    private List<Long> dnIds;
    private List<Long> eventIds;
    private List<Long> reportIds;
}
