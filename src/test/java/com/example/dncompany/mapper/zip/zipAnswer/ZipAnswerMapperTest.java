package com.example.dncompany.mapper.zip.zipAnswer;

import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerModifyDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerWriteDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ZipAnswerMapperTest {
    @Autowired
    ZipAnswerMapper zipAnswerMapper;

    ZipAnswerWriteDTO zipAnswerWriteDTO;
    ZipAnswerDTO zipAnswerDTO;

    @BeforeEach
    void setUp() {
        zipAnswerWriteDTO = new ZipAnswerWriteDTO();
        zipAnswerWriteDTO.setZipAnswerContent("게시글 확인 완료");
        zipAnswerWriteDTO.setUserId(21L);
        zipAnswerWriteDTO.setZipId(4L);

        zipAnswerMapper.insertAnswer(zipAnswerWriteDTO);

        List<ZipAnswerDTO> answers = zipAnswerMapper.selectListByZipId(zipAnswerWriteDTO.getZipId());
        zipAnswerDTO = answers.get(0);
    }

    @Test
    void insertAnswer() {
        ZipAnswerWriteDTO newAnswer = new ZipAnswerWriteDTO();
        newAnswer.setZipAnswerContent("새 댓글");
        newAnswer.setUserId(22L);
        newAnswer.setZipId(4L);

        zipAnswerMapper.insertAnswer(newAnswer);

        List<ZipAnswerDTO> answers = zipAnswerMapper.selectListByZipId(newAnswer.getZipId());
        assertThat(answers)
                .isNotEmpty()
                .extracting("zipAnswerContent")
                .contains("새 댓글");
    }

    @Test
    void selectListByZipId() {
        List<ZipAnswerDTO> answers = zipAnswerMapper.selectListByZipId(zipAnswerWriteDTO.getZipId());
        assertThat(answers)
                .isNotEmpty()
                .extracting("zipAnswerContent")
                .contains("게시글 확인 완료");
    }

    @Test
    void selectTotalByZipId() {
        int totalAnswers = zipAnswerMapper.selectTotalByZipId(zipAnswerWriteDTO.getZipId());
        assertThat(totalAnswers).isEqualTo(1);
    }

    @Test
    void updateAnswer() {
        ZipAnswerModifyDTO answerModifyDTO = new ZipAnswerModifyDTO();
        answerModifyDTO.setZipAnswerId(zipAnswerDTO.getZipAnswerId());
        answerModifyDTO.setZipAnswerContent("수정된 댓글 내용");
//        answerModifyDTO.setUserId(21L);

        zipAnswerMapper.updateAnswer(answerModifyDTO);

        List<ZipAnswerDTO> updatedAnswers = zipAnswerMapper.selectListByZipId(zipAnswerWriteDTO.getZipId());
        assertThat(updatedAnswers)
                .isNotEmpty()
                .extracting("zipAnswerContent")
                .contains("수정된 댓글 내용");
    }

    @Test
    void deleteAnswer() {
        zipAnswerMapper.deleteAnswer(zipAnswerDTO.getZipAnswerId());

        List<ZipAnswerDTO> remainingAnswers = zipAnswerMapper.selectListByZipId(zipAnswerWriteDTO.getZipId());
        assertThat(remainingAnswers).isEmpty();
    }
}