package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.board.AdminEventWriteDTO;
import com.example.dncompany.mapper.admin.AdminBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminEventTestService {

    private final AdminBoardMapper adminBoardMapper;

    @Transactional(readOnly = true)
    public List<AdminEventWriteDTO> getAllEvents() {
        log.debug("====== Get All Events Service Start ======");
        List<AdminEventWriteDTO> eventList = adminBoardMapper.selectAllEvents();
        log.debug("Found {} events", eventList.size());
        log.debug("Event List: {}", eventList);
        log.debug("====== Get All Events Service End ======");
        return eventList;
    }
}