package com.example.dncompany.service.help;

import com.example.dncompany.dto.help.HelpListResponseDTO;
import com.example.dncompany.dto.help.HelpRequestDTO;
import com.example.dncompany.dto.help.HelpResponseDTO;
import com.example.dncompany.exception.help.HelpNotFoundException;
import com.example.dncompany.mapper.help.HelpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelpService {

    private final HelpMapper helpMapper;


    // 게시글 등록
    public void registerHelp(HelpRequestDTO helpRequestDTO) {
        helpMapper.insertHelp(helpRequestDTO);
    }

    // 게시글 목록 조회
    public List<HelpListResponseDTO> getHelpList() {
        return helpMapper.selectHelpList();
    }


    // 게시글 상세 조회
    public HelpResponseDTO getHelpDetail(Long helpId) {
        return Optional.ofNullable(helpMapper.selectHelpDetail(helpId))
                .orElseThrow(() -> new HelpNotFoundException("게시글을 찾을 수 없습니다."));
    }

}
