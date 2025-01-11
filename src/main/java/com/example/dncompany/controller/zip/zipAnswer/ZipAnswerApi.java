package com.example.dncompany.controller.zip.zipAnswer;

import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerModifyDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerWriteDTO;
import com.example.dncompany.service.zip.zipAnswer.ZipAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZipAnswerApi {
    private final ZipAnswerService zipAnswerService;

    @PostMapping("/v1/zips/{zipId}/answers")
    public void postAnswer(@RequestBody ZipAnswerWriteDTO zipAnswerWriteDTO,
                           @PathVariable("zipId") Long zipId,
                           @SessionAttribute(value = "usersId", required = false) Long usersId) {
        usersId = 6L;
        zipAnswerService.addAnswer(zipAnswerWriteDTO, zipId, usersId);
    }

    @GetMapping("/v1/zips/{zipId}/answers")
    public List<ZipAnswerDTO> getAnswer(@PathVariable("zipId") Long zipId) {
        return zipAnswerService.getListByZipId(zipId);
    }

    @PatchMapping("/v1/answers/{answerId}")
    public void patchAnswer(@PathVariable("answerId") Long answerId,
                            @RequestBody ZipAnswerModifyDTO zipAnswerModifyDTO) {
        zipAnswerService.modifyAnswer(zipAnswerModifyDTO, answerId);
    }

    @DeleteMapping("/v1/answers/{answerId}")
    public void deleteAnswer(@PathVariable("answerId") Long answerId){
        zipAnswerService.removeAnswer(answerId);
    }
}
