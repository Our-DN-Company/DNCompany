package com.example.dncompany.service.qna.qnaAnswer;

import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerModifyDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerWriteDTO;
import com.example.dncompany.mapper.qna.qnaAnswer.QnaAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaAnswerService {
    private final QnaAnswerMapper qnaAnswerMapper;

    public void addAnswer(QnaAnswerWriteDTO qnaAnswerWriteDTO,
                          Long qnaId, Long usersId) {
        qnaAnswerWriteDTO.setQnaId(qnaId);

        qnaAnswerMapper.insertAnswer(qnaAnswerWriteDTO);
    }

    public List<QnaAnswerDTO> getListByQnaId(Long qnaId) {
        return qnaAnswerMapper.selectListByQnaId(qnaId);
    }

    public void modifyAnswer(QnaAnswerModifyDTO qnaAnswerModifyDTO,
                             Long answerId) {
        qnaAnswerModifyDTO.setQnaAnswerId(answerId);

        qnaAnswerMapper.updateAnswer(qnaAnswerModifyDTO);
    }

    public void removeAnswer(Long qnaAnswerId) {
        qnaAnswerMapper.deleteByAnswerId(qnaAnswerId);
    }

}































