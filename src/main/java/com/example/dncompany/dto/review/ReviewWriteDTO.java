package com.example.dncompany.dto.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class ReviewWriteDTO {
    private Long reviewId;
    private Integer reviewStarRating;
    private String reviewContent;
    private Long helpOfferId;
    private String reviewTitle;
    private Date reviewCreatedAt;
    private Integer helpId;


}