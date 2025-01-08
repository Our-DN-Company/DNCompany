package com.example.dncompany.mapper.help;
import java.util.List;
import com.example.dncompany.dto.help.HelpListResponseDTO;
import com.example.dncompany.dto.help.HelpRequestDTO;
import com.example.dncompany.dto.help.HelpResponseDTO;
import org.apache.ibatis.annotations.Select;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HelpMapperTest {
    @Autowired
    HelpMapper helpMapper;

    private HelpRequestDTO helpRequestDTO;

    @BeforeEach
    void setUp() {
        helpRequestDTO=new HelpRequestDTO();
        helpRequestDTO.setHelpTitle("test");
        helpRequestDTO.setHelpAddress("test");
        // helpRequestDTO.setHelpAddressDetail("test");
        helpRequestDTO.setHelpPoint(2000);
        helpRequestDTO.setHelpPrice(40000);
        helpRequestDTO.setHelpSpecies("test");
        helpRequestDTO.setHelpStartTime(LocalDateTime.of(2025, 1,1,16,30));
        helpRequestDTO.setHelpEndTime(LocalDateTime.of(2025, 1, 1, 20, 20));
        helpRequestDTO.setHelpCareDate(LocalDate.of(2025, 1, 1));
        helpRequestDTO.setHelpCareType("test");

    }

    @Test
    @DisplayName("게시글 등록 및 상세 조회 테스트")
    void insertHelpAndSelect() {
        // given
        // 이미 @BeforeEach에서 설정

        // when
        helpMapper.insertHelp(helpRequestDTO); // 게시글 등록
        HelpResponseDTO findHelp=helpMapper.selectHelpDetail(helpRequestDTO.getHelpId());

        // then
        assertThat(findHelp)
                .isNotNull()
                .extracting(
                        "helpTitle", "helpAddress", "helpAddressDetail",
                        "helpPoint", "helpPrice", "helpSpecies", "helpCareType"
                )
                .containsExactly(
                        helpRequestDTO.getHelpTitle(),
                        helpRequestDTO.getHelpAddress(),
                        helpRequestDTO.getHelpAddressDetail(),
                        helpRequestDTO.getHelpPoint(),
                        helpRequestDTO.getHelpPrice(),
                        helpRequestDTO.getHelpSpecies(),
                        helpRequestDTO.getHelpCareType()
                );
        // 날짜/시간 검증
        assertThat(findHelp.getHelpStartTime()).isEqualTo(helpRequestDTO.getHelpStartTime());
        assertThat(findHelp.getHelpEndTime()).isEqualTo(helpRequestDTO.getHelpEndTime());
        assertThat(findHelp.getHelpCareDate()).isEqualTo(helpRequestDTO.getHelpCareDate());
    }

//
//    @Test
//    @DisplayName("게시글 목록 조회 테스트")
//    void selectHelpList() {
//        // given
//        helpMapper.insertHelp(helpRequestDTO);
//
//        // when
//        List<HelpListResponseDTO> helpList = helpMapper.selectHelpList();
//
//        // then
//        assertThat(helpList).isNotEmpty();
//
//        assertThat(helpList)
//                .filteredOn(help->help.getHelpId().equals(helpRequestDTO.getHelpId()))
//                .hasSize(1)
//                .first()
//                .extracting(
//                        "helpTitle", "helpCareType",
//                        "helpAddress", "helpPoint", "helpCareDate"
//                )
//                .containsExactly(
//                        helpRequestDTO.getHelpTitle(),
//                        helpRequestDTO.getHelpSpecies(),
//                        helpRequestDTO.getHelpCareType(),
//                        helpRequestDTO.getHelpAddress(),
//                        helpRequestDTO.getHelpPoint(),
//                        helpRequestDTO.getHelpCareDate()
//                );
    }

