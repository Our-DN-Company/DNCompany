package com.example.dncompany.mapper.help;

import com.example.dncompany.dto.help.HelpRequestDTO;
import com.example.dncompany.dto.help.HelpResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        helpRequestDTO.setHelpAddressDetail("test");
        helpRequestDTO.setHelpPoint(2000);
        helpRequestDTO.setHelpPrice(40000);
        helpRequestDTO.setHelpSpecies("test");
        helpRequestDTO.setHelpStartTime(LocalDateTime.of(2025, 1,1,16,30));
        helpRequestDTO.setHelpEndTime(LocalDateTime.of(2025, 1, 1, 20, 20));
        helpRequestDTO.setHelpCareDate(LocalDate.of(2025, 1, 1));
        helpRequestDTO.setHelpCareType("test");
    }

    @Test
    @DisplayName("게시글 등록 및 게시글 조회")
    void insertAndSelectHelp() {
      // given
      // 이미 @BeforeEach에서 설정

      // when
      helpMapper.insertHelp(helpRequestDTO); // 게시글 등록
        HelpResponseDTO findHelp=helpMapper.selectHelpDetail(helpRequestDTO.getHelpId());

      // then
        assertThat(findHelp)
                .isNotNull()
                .extracting(
                        "helpTitle", "helpAddress", "helpAddreddDetail",
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
        assertThat(findHelp.getHelpEndDateTime()).isEqualTo(helpRequestDTO.getHelpEndTime());
        assertThat(findHelp.getHelpCareDate()).isEqualTo(helpRequestDTO.getHelpCareDate());
    }

}