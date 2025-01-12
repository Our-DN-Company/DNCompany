package com.example.dncompany.service.dn;

import com.example.dncompany.dto.dn.DnBoardDetailDTO;
import com.example.dncompany.mapper.dn.DnBoardMapper;
import com.example.dncompany.mapper.dn.DnProductMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DnBoardServiceTest {

    @Mock
    DnProductMapper dnProductMapper;

    @Mock
    DnBoardMapper dnBoardMapper;

    @InjectMocks
    DnBoardService dnBoardService;

    @Test
    @DisplayName("댕냥바다 게시물 조회 테스트")
    void getDnBoardById() {
        //given
        when(dnBoardMapper.selectDnBoardById(any())).thenReturn(Optional.of(new DnBoardDetailDTO()));
        //when
        DnBoardDetailDTO foundDnBoard = dnBoardService.getDnBoardById(4L);
        //then
        verify(dnBoardMapper).insertDnBoard(any());
        assertThat(foundDnBoard).isNotNull();
    }
}