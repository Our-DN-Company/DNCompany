package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReviewDTO {
  private  Long reviewId;
  private  int reviewStarRating;
  private  String reviewContent;
  private  Long helpId;
  private  Long usersId;
}
