package com.example.dncompany.service.zip.zipAnswer;

import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerWriteDTO;
import com.example.dncompany.mapper.zip.zipAnswer.ZipAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ZipAnswerService {
    private final ZipAnswerMapper zipAnswerMapper;

//    public addAnswer(ZipAnswerWriteDTO zipAnswerWriteDTO,
//                     Long zipId,
//                     Long userId) {
//        zipAnswerWriteDTO.setZipId(zipId);
//        zipAnswerWriteDTO.setUserId(userId);
//
//        zipAnswerMapper.insertAnswer();
//    }

}
