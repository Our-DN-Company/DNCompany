package com.example.dncompany.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class PageDTO<T> {
    private int page;
    private int size;
    private int total;
    private int totalPages;
    private int startPage;
    private int endPage;
    private int pageGroup;
    private List<T> list;


    public PageDTO(int page, int size, int total, List<T> list) {
        this(page, size, total, 5, list);
    }

    public PageDTO(int page, int size, int total, int pageGroup, List<T> list) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.pageGroup = pageGroup;
        this.list = list;

        this.totalPages = total == 0 ? 1 : (int)Math.ceil( (double)total / size ) ;

        if (this.page <= 0) {
            this.page = 1; // 기본 페이지 설정
        }

        if (this.totalPages < 1) {
            this.totalPages = 1; // 최소 1 페이지 보장
        }

        this.startPage = ((this.page - 1) / pageGroup) * pageGroup + 1;
        this.endPage = Math.min(startPage + (pageGroup - 1), totalPages);

        if (this.startPage > this.endPage) {
            this.startPage = 1; // 기본 시작 페이지
        }
    }

    public boolean hasPrevious() {
        return startPage > 1;
    }

    public boolean hasNext() {
        return page < totalPages;
    }

    public boolean hasPreviousGroup() {
        return startPage > 1;
    }

    public boolean hasNextGroup() {
        return endPage < totalPages;
    }
}
