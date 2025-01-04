package com.example.dncompany.dto.admin.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDTO {
    private String category;      // 게시판 카테고리(QnA, 신고, 도와주세요, 이벤트)
    private String searchType;    // 검색 유형(제목, 내용, 작성자, 아이디)
    private String searchKeyword; // 검색어
    private String startDate;     // 시작 날짜
    private String endDate;       // 종료 날짜
}