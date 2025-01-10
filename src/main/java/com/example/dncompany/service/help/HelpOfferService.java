package com.example.dncompany.service.help;

import com.example.dncompany.dto.help.HelpOfferListDTO;
import com.example.dncompany.dto.help.HelpOfferResponseDTO;
import com.example.dncompany.mapper.help.HelpOfferMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelpOfferService {

    private final HelpOfferMapper helpOfferMapper;

    // 도움 신청하기
    public void registerHelpOffer(Long helpId, Long usersId) {
        log.info("도움 신청 - helpId: {}, usersId: {}", helpId, usersId);

        HelpOfferListDTO helpOfferDTO = new HelpOfferListDTO();
        helpOfferDTO.setHelpId(helpId);
        helpOfferDTO.setUsersId(usersId);
        helpOfferDTO.setHelpOfferStatus("POSSIBLE");

        helpOfferMapper.insertHelpOffer(helpOfferDTO);
    }

    // 특정 게시글의 도움 신청 목록 조회
    public List<HelpOfferResponseDTO> getHelpOfferList(Long helpId) {
        log.info("도움 신청 목록 조회 - helpId: {}", helpId);
        return helpOfferMapper.selectHelpOfferList(helpId);
    }
}
