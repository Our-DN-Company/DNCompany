package com.example.dncompany.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MainEventDTO {
    private String eventTitle;
    private Long eventId;

    //    이미지 파일 불러오기
    private Long eventImgId;
    private String eventUuid;
    private String eventFilePath;
    private String eventExtension;

}
