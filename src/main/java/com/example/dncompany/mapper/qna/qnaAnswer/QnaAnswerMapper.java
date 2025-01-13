package com.example.dncompany.mapper.qna.qnaAnswer;

import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerModifyDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaAnswerMapper {
    // 삽입
    void insertAnswer(QnaAnswerWriteDTO qnaAnswerWriteDTO);

    // 댓글 조회
    List<QnaAnswerDTO> selectListByQnaId (Long qnaId);

    // 댓글 수정
    void updateAnswer(QnaAnswerModifyDTO qnaAnswerModifyDTO);

    // 댓글 삭제
    void deleteByAnswerId(Long qnaAnswerId);
}
