package com.example.dncompany.mapper.qna;

import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.qna.QnADTO;
import com.example.dncompany.dto.qna.QnADetailDTO;
import com.example.dncompany.dto.qna.QnAModifyDTO;
import com.example.dncompany.dto.qna.QnAWriteDTO;
import com.example.dncompany.dto.qna.qnaPage.QnaBoardSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QnaMapper {
    // QnA 게시글 삽입
    void insertQnABoard(QnAWriteDTO qnaWriteDTO);

    // QnA 게시글 전체 정보
    List<QnADTO> selectAllQnABoards();

    // QnA 게시글 상페 페이지 정보
    Optional<QnADetailDTO> QnASelectById(Long QnAId);

    // QnA 게시글 수정
    void updateQnABoard (QnAModifyDTO qnaModifyDTO);

    // QnA 게시글 삭제
    void deleteQnABoard(Long QnAId);

    // 페이징 처리
    List<QnADTO> selectBySearchCondWithPage (
            @Param("cond") QnaBoardSearchDTO qnaBoardSearchDTO,
            @Param("page")PageRequestDTO pageRequestDTO);

    int countBySearchCondition (@Param("cond") QnaBoardSearchDTO qnaBoardSearchDTO);

}
