package com.example.dncompany.service.event;

import com.example.dncompany.dto.event.EventBoardDTO;
import com.example.dncompany.dto.event.EventBoardWriteDTO;
import com.example.dncompany.mapper.event.EventBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventBoardMapper eventMapper;

    // 게시물 전체 정보
    public List<EventBoardDTO> getAllEvents() {
        return eventMapper.selectAllEventBoards();
    }


    // 게시물 조회
//    public List<EventBoardDTO> getEventBoardList(){
//        return eventMapper.selectById()
//                .orElseThrow(() -> EventNotFoundException("이벤트 게시판 글을 찾을 수 없음"))
//
//    }

    // 게시글 수정
    public void modifyEventBoard(EventBoardWriteDTO eventBoardModifyDTO) {
        eventMapper.updateEventBoard(eventBoardModifyDTO);
    }

    // 게시글 삭제
    public void removeEventBoard(EventBoardWriteDTO eventBoardWriteDTO) {
        eventMapper.deleteEventBoard(eventBoardWriteDTO);
    }

}
