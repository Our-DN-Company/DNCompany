package com.example.dncompany.mapper.zip.zipAnswer;

import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerModifyDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZipAnswerMapper {
    // 삽입
    void insertAnswer(ZipAnswerWriteDTO zipAnswerWriteDTO);

    // 댓글 조회
    List<ZipAnswerDTO> selectListByZipId (Long zipId);

    // 댓글수
    int selectTotalByZipId(Long zipId);

    // 댓글 수정
    void updateAnswer(ZipAnswerModifyDTO zipAnswerModifyDTO);

    // 댓글 삭제
    void deleteAnswer(Long zipAnswerId);


}
