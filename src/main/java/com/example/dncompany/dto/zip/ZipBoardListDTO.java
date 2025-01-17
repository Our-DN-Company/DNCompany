package com.example.dncompany.dto.zip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter @Setter @ToString
public class ZipBoardListDTO {
    private Long zipId;
    private String zipPetCategory;
    private String zipCategory; // zip 카테고리 X, 댕냥바다에 추가 해야할거 같음
    private String zipTitle;
    private String zipContent;
    private LocalDateTime zipCreatedAt;
//    private LocalDateTime zipUpdatedAt;
    private int zipViewCount;
    private int zipLikeCount;
    private int answerCount;
    private Long usersId;
    private String nickname;

//    public String getZipCreatedAt () {
//        return zipCreatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }

    public String getZipUpdatedAt () {
        return zipUpdatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getZipContent() {
        String regEx = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
        return this.zipContent.replaceAll(regEx, "");
    }
}
