package com.example.dncompany.mapper.help;

import com.example.dncompany.dto.help.HelpListDTO;
import com.example.dncompany.dto.help.HelpDetailDTO;
import com.example.dncompany.dto.help.HelpSearchDTO;
import com.example.dncompany.dto.help.HelpWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HelpMapper {
    // 게시글 등록
    void insertHelp(HelpWriteDTO helpWriteDTO);

    // 게시글 목록 조회
    List<HelpListDTO> selectHelpList();

    // 게시글 상세 조회
    Optional<HelpDetailDTO> selectHelpDetail(Long helpId);

    // 검색 기능 추가
    List<HelpListDTO> searchHelpList(HelpSearchDTO searchDTO);
}
