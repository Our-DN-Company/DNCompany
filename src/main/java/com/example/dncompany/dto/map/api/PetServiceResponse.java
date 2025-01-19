package com.example.dncompany.dto.map.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class PetServiceResponse {
    private int currentCount;
    private List<Data> data;
    private int matchCount;
    private int page;
    private int perPage;
    private int totalCount;
}
