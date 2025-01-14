package com.example.dncompany.service.admin;


import com.example.dncompany.dto.admin.board.file.AdminFIleDTO;
import com.example.dncompany.mapper.admin.AdminFIleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminFileService {
      private final AdminFIleMapper adminFIleMapper;

      @Value("C:/upload/event")
      private String eventUploadPath;

      public void removeOldEventFiles() throws IOException {
          List<AdminFIleDTO> oldEventFiles = adminFIleMapper.selectOldEventFileDTO();

          List<Path> oldEventPathList = new ArrayList<>();
          for (AdminFIleDTO adminFIleDTO : oldEventFiles) {
              Path eventPath = Path.of(eventUploadPath,"/", adminFIleDTO.getEventPath(),"/",
                      adminFIleDTO.getEventUuid(),adminFIleDTO.getEventExtension());
              log.info(eventPath.toString());
              Path thEventPath = Path.of(eventUploadPath,"/", adminFIleDTO.getEventPath(),"/",
                      adminFIleDTO.getEventUuid(),adminFIleDTO.getEventExtension());
              log.info(thEventPath.toString());

              oldEventPathList.add(eventPath);
              oldEventPathList.add(thEventPath);
          }
            // 하루 전 날짜 구하기

             LocalDate eventToday = LocalDate.now();
                log.info(eventToday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
             LocalDate eventYesterday = eventToday.minusDays(1);
                log.info(eventYesterday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
             String eventDatePath = eventYesterday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                log.info(eventDatePath);
//                String eventDatePath2 = eventToday.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            //  datePath 경로상에 있는 디렉토리를 불러오기
             Path eventDirectory = Path.of(eventUploadPath, "/", eventDatePath);
//             Path eventDirectory = Path.of(eventUploadPath, "/", eventDatePath2);

             List<Path> unnecessayEventPathList = Files.list(eventDirectory).
                     filter(eventPath -> !oldEventFiles.contains(eventPath)).toList();
             log.info("경로 찾기용 {} ",unnecessayEventPathList);

             for (Path path : unnecessayEventPathList) {
                 if(Files.exists(path)) {// 존재 여부 확인 처리
                     log.info("Deleting old event file {}", path);
                     log.info("Deleting event file {}", Files.exists(path));
                     Files.delete(path);
                 }
             }
      }






}
