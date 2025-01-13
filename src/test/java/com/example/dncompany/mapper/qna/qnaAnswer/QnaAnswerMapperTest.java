package com.example.dncompany.mapper.qna.qnaAnswer;

import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerModifyDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerWriteDTO;
import com.example.dncompany.mapper.zip.zipAnswer.ZipAnswerMapper;
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
class QnaAnswerMapperTest {
    @Autowired
    QnaAnswerMapper qnaAnswerMapper;

    QnaAnswerWriteDTO qnaAnswerWriteDTO;
    QnaAnswerDTO qnaAnswerDTO;
    @Autowired
    private ZipAnswerMapper zipAnswerMapper;

    @BeforeEach
    void setUp() {
        qnaAnswerWriteDTO = new QnaAnswerWriteDTO();
        qnaAnswerWriteDTO.setQnaAnswerContent("게시글 확인 완료");
        qnaAnswerWriteDTO.setQnaId(4L);

        qnaAnswerMapper.insertAnswer(qnaAnswerWriteDTO);

        List<QnaAnswerDTO> QnaAnswer = qnaAnswerMapper.selectListByQnaId(qnaAnswerWriteDTO.getQnaId());
        qnaAnswerDTO = QnaAnswer.get(0);
    }

    @Test
    void insertAnswer() {
        QnaAnswerWriteDTO newAnswer = new QnaAnswerWriteDTO();
        newAnswer.setQnaAnswerContent("새 댓글");
        newAnswer.setQnaId(4L);


        qnaAnswerMapper.insertAnswer(newAnswer);

        List<QnaAnswerDTO> answers = qnaAnswerMapper.selectListByQnaId(qnaAnswerWriteDTO.getQnaId());
        assertThat(answers)
                .isNotEmpty()
                .extracting("qnaAnswerContent")
                .contains("게시글 확인 완료");
    }

    @Test
    void selectListByQnaId() {
        List<QnaAnswerDTO> answers = qnaAnswerMapper.selectListByQnaId(qnaAnswerWriteDTO.getQnaId());
        assertThat(answers)
                .isNotEmpty()
                .extracting("qnaAnswerContent")
                .contains("게시글 확인 완료");
    }

    @Test
    void updateAnswer() {
        QnaAnswerModifyDTO answerModifyDTO = new QnaAnswerModifyDTO();
        answerModifyDTO.setQnaAnswerId(qnaAnswerDTO.getQnaAnswerId());
        answerModifyDTO.setQnaAnswerContent("수정된 QNA 댓글");

        qnaAnswerMapper.updateAnswer(answerModifyDTO);

        List<QnaAnswerDTO> updatedAnswers = qnaAnswerMapper.selectListByQnaId(qnaAnswerWriteDTO.getQnaId());
        assertThat(updatedAnswers)
                .isNotEmpty()
                .extracting("qnaAnswerContent")
                .contains("수정된 QNA 댓글");
    }

    @Test
    void deleteAnswer() {
        qnaAnswerMapper.deleteByAnswerId(qnaAnswerDTO.getQnaAnswerId());

        List<QnaAnswerDTO> remainingAnswers = qnaAnswerMapper.selectListByQnaId(qnaAnswerWriteDTO.getQnaId());
        assertThat(remainingAnswers).isEmpty();
    }
}




















