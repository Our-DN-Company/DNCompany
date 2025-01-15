package com.example.dncompany.service.zip.zipAnswer;

import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerModifyDTO;
import com.example.dncompany.dto.zip.zipAnswer.ZipAnswerWriteDTO;
import com.example.dncompany.mapper.zip.zipAnswer.ZipAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ZipAnswerService {
    private final ZipAnswerMapper zipAnswerMapper;

    public void addAnswer(ZipAnswerWriteDTO zipAnswerWriteDTO, Long zipId, Long usersId){
        zipAnswerWriteDTO.setUsersId(usersId);
        zipAnswerWriteDTO.setZipId(zipId);

        zipAnswerMapper.insertAnswer(zipAnswerWriteDTO);
    }

    public List<ZipAnswerDTO> getListByZipId (Long zipId) {
        return zipAnswerMapper.selectListByZipId(zipId);
    }

    public void modifyAnswer(ZipAnswerModifyDTO zipAnswerModifyDTO, Long answerId){
        zipAnswerModifyDTO.setZipAnswerId(answerId);

        zipAnswerMapper.updateAnswer(zipAnswerModifyDTO);
    }

    public void removeAnswer(Long zipAnswerId){
        zipAnswerMapper.deleteByAnswerId(zipAnswerId);
    }

}































