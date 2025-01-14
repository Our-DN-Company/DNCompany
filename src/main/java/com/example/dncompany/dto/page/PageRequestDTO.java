package com.example.dncompany.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PageRequestDTO {
    private int page;
    private int size;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    // offset 계산 메서드
    public int getOffset() {
        return (page - 1) * size;
    }

}








