package com.example.dncompany.dto.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EventBoardModifyDTO {
    Long eventId;
    String eventTitle;
    String eventContent;
    Long usersId;
}
