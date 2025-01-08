package com.example.dncompany.mapper.help;

import com.example.dncompany.dto.help.HelpListResponseDTO;
import com.example.dncompany.dto.help.HelpRequestDTO;
import com.example.dncompany.dto.help.HelpResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HelpMapper {
    // 게시글 등록
    void insertHelp(HelpRequestDTO helpRequestDTO);

    // 게시글 목록 조회
    List<HelpListResponseDTO> selectHelpList();

    // 게시글 상세 조회
    HelpResponseDTO selectHelpDetail(Long helpId);
}
