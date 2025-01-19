package com.example.dncompany.mapper.event;

import com.example.dncompany.dto.event.EventBoardDTO;
import com.example.dncompany.dto.event.EventBoardWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EventBoardMapper {
    // 게시물 삽입
    void insertEventBoard(EventBoardWriteDTO eventWriteDTO);

    // 게시물 전체 목록 조회
    List<EventBoardDTO> selectAllEventBoards();

    // 게시글 상세 페이지 조회
    Optional<EventBoardDTO> selectById();

    // 게시글 수정
    void updateEventBoard(EventBoardWriteDTO eventWriteDTO);

    // 게시글 삭제
    void deleteEventBoard(EventBoardWriteDTO eventWriteDTO);

}
