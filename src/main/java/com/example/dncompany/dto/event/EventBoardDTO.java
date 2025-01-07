package com.example.dncompany.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class EventBoardDTO {
    Long eventId;
    String eventTitle;
    String eventContent;
    LocalDateTime eventCreatedAt;
    LocalDateTime eventUpdatedAt;
    Long userId;
}
