package com.example.dncompany.dto.admin.board.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class AdminFIleDTO {

   private long eventImgId ;
   private long eventId;
   private String eventOriginalFilename;
   private String eventUuid;
   private String eventPath;
   private String eventExtension;
   private LocalDateTime eventRegDate;
   private long eventBoardId;




}
