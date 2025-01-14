package com.example.dncompany.scheduler;


import com.example.dncompany.service.admin.AdminFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventFileScheduler {

    private final AdminFileService adminFileService;

    @Scheduled(cron = "0 0 23 * * *")
//    @Scheduled(cron = "0/10 * * * * *")
    public void deleteEventOldFiles() {
        log.info("Deleting old files");
        try {
            adminFileService.removeOldEventFiles();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info("파일 삭제 완료");
    }

}
