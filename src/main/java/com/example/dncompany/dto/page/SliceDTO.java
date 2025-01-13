package com.example.dncompany.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class SliceDTO<T> {
    private int page;
    private int size;
    private boolean hasNext;
    private List<T> list;

    public SliceDTO(int page, int size, List<T> list) {
        this.page = page;
        this.size = size;
        this.list = list;
        this.hasNext = list.size() > this.size;

        if(this.hasNext) {
            list.removeLast();
        }
    }
}















