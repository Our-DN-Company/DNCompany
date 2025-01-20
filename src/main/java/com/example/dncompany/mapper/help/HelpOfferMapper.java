package com.example.dncompany.mapper.help;

import com.example.dncompany.dto.help.HelpOfferListDTO;
import com.example.dncompany.dto.help.HelpOfferResponseDTO;
import com.example.dncompany.dto.help.HelpOfferSmsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Mapper
public interface HelpOfferMapper {
    // 도움 신청 완료
    void insertHelpOffer(HelpOfferListDTO helpOfferListDTO);

    boolean isMePostTest(@Param("helpId") Long helpId, @Param("usersId") Long usersId);

    // 헬퍼에게 문자 보내기위한 정보
    Optional<HelpOfferSmsDTO> selectByIdForHelperSms(Long helpOfferId);

}
