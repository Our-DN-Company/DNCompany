package com.example.dncompany.mapper.help;

import com.example.dncompany.dto.help.HelpOfferListDTO;
import com.example.dncompany.dto.help.HelpOfferResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelpOfferMapper {
    // 도움 신청하기
    void insertHelpOffer(HelpOfferListDTO helpOfferListDTO);

    // 특정 게시글의 도움 신청 목록 조회
    List<HelpOfferResponseDTO> selectHelpOfferList(Long helpId);
}
