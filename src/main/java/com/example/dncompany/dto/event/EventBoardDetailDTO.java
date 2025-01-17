package com.example.dncompany.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class EventBoardDetailDTO {
    Long eventId;
    String eventTitle;
    String eventContent;
    LocalDateTime eventCreatedAt;
    Long usersId;

    public String getEventCreatedAt() {
        return eventCreatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
