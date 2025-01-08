package com.example.dncompany.mapper.qna;

import com.example.dncompany.dto.qna.QnADTO;
import com.example.dncompany.dto.qna.QnAWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    // 게시글 삽입
    void insertQnABoard(QnAWriteDTO qnaWriteDTO);

    // 게시글 전체 정보
    List<QnADTO> selectAllQnABoards();
}
