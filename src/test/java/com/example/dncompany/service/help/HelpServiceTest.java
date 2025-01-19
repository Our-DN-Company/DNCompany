package com.example.dncompany.service.help;

import com.example.dncompany.dto.help.HelpListDTO;
import com.example.dncompany.dto.help.HelpDetailDTO;
import com.example.dncompany.dto.help.HelpWriteDTO;
import com.example.dncompany.exception.help.HelpNotFoundException;
import com.example.dncompany.mapper.help.HelpMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelpServiceTest {
    @Mock
    private HelpMapper helpMapper;

    @InjectMocks
    private HelpService helpService;

    @Test
    @DisplayName("게시글 등록 테스트")
    void registerHelp() {
        // given
        HelpWriteDTO helpWriteDTO = new HelpWriteDTO();
        helpWriteDTO.setHelpTitle("test");

        // when
//        helpService.registerHelp(helpWriteDTO);

        // then
//        verify(helpMapper, times(1)).insertHelp(helpWriteDTO);
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void getHelpList() {
        // given
        List<HelpListDTO> mockList = List.of(new HelpListDTO());
        when(helpMapper.selectHelpList()).thenReturn(mockList);

        // when
        List<HelpListDTO> result = helpService.getHelpList();

        // then
        assertThat(result).isEqualTo(mockList);
    }

    @Test
    @DisplayName("게시글 상세 조회 테스트")
    void getHelpDetail() {
        // given
        Long helpId = 1L;
        HelpDetailDTO expectedResponse = new HelpDetailDTO();
        when(helpMapper.selectHelpDetail(helpId)).thenReturn(Optional.of(expectedResponse));

        // when
        HelpDetailDTO result = helpService.getHelpDetail(helpId);

        // then
        assertThat(result).isNotNull();

    }


    // 예외 테스트
    @Test
    @DisplayName("존재하지 않는 게시글 조회시 예외 발생")
    void getHelpDetailNotFoundTest() {
        // given
        Long helpId = 999L;
        when(helpMapper.selectHelpDetail(helpId)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> helpService.getHelpDetail(helpId))
                .isInstanceOf(HelpNotFoundException.class)
                .hasMessage("게시글을 찾을 수 없습니다.");
    }
    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteMyHelpBoard() {
        // given
        Long helpId = 124L;
        Long userId = 1L;
//        when(helpMapper.deleteHelpBoard(helpId)).thenReturn(1);  // 삭제 성공을 의미하는 1을 반환하도록 설정

        // when
        helpService.deleteHelpBoard(helpId);

        // then
        verify(helpMapper, times(1)).deleteHelpBoard(helpId);
    }

}