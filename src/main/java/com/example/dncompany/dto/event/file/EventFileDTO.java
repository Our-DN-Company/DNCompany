package com.example.dncompany.dto.event.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EventFileDTO {
    private Long eventImgId;
    private String eventOriginalFilename;
    private String eventUuid;
    private String eventPath;
    private String eventExtension;
    private String EventRegDate;
    private Long EventId;
}
