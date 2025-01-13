package com.example.dncompany.controller.qna.qnaAnswer;

import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerModifyDTO;
import com.example.dncompany.dto.qna.qnaAnswer.QnaAnswerWriteDTO;
import com.example.dncompany.service.qna.qnaAnswer.QnaAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QnaAnswerApi {
    private final QnaAnswerService qnaAnswerService;

    @PostMapping("/v1/qnas/{qnaId}/answers")
    public void postAnswer(@RequestBody QnaAnswerWriteDTO qnaAnswerWriteDTO,
                           @PathVariable("qnaId") Long qnaId,
                           @SessionAttribute(value = "usersId", required = false) Long usersId) {
//        usersId = 6L;
        qnaAnswerService.addAnswer(qnaAnswerWriteDTO, qnaId, usersId);
    }

    @GetMapping("/v1/qnas/{qnaId}/answers")
    public List<QnaAnswerDTO> getAnswer(@PathVariable("qnaId") Long qnaId) {
        return qnaAnswerService.getListByQnaId(qnaId);
    }

    @PatchMapping("/v1/qna/answers/{answerId}")
    public void patchAnswer(@PathVariable("answerId") Long answerId,
                            @RequestBody QnaAnswerModifyDTO qnaAnswerModifyDTO) {
        qnaAnswerService.modifyAnswer(qnaAnswerModifyDTO, answerId);
    }

    @DeleteMapping("/v1/qna/answers/{answerId}")
    public void deleteAnswer(@PathVariable("answerId") Long answerId){
        qnaAnswerService.removeAnswer(answerId);
    }
}
