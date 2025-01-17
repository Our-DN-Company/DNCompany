package com.example.dncompany.dto.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class ZipBoardDetailDTO {
    private Long zipId;
    private String zipPetCategory;
    private String zipTitle;
    private String zipContent;
    //    private LocalDateTime zipCreatedAt;
    private LocalDateTime zipUpdatedAt;
    private int zipViewCount;
    private int answerCount;
    private int likeCount;
    private Long usersId;
    private String nickname;

//    public String getCreatedAt () {
//        return zipCreatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }

//    public String getZipUpdatedAt () {
//        return zipUpdatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
}
