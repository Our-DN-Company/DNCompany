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
    // Service
    public void registerHelpOffer(Long helpId, Long usersId) {
        log.info("========== 도움 신청 서비스 시작 ==========");
        log.info("helpId: {}, usersId: {}", helpId, usersId);

        HelpOfferListDTO helpOfferListDTO = new HelpOfferListDTO();
        helpOfferListDTO.setHelpId(helpId);
        helpOfferListDTO.setUsersId(usersId);
        helpOfferListDTO.setHelpOfferStatus("ACCEPT");

        helpOfferMapper.insertHelpOffer(helpOfferListDTO);
        log.info("생성된 helpOfferId: {}", helpOfferListDTO.getHelpOfferId());
        log.info("========== 도움 신청 서비스 종료 ==========");
    }



}
