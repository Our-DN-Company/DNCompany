package com.example.dncompany.service.zip;

import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.mapper.zip.ZipMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZipServiceTest {
    @Mock
    ZipMapper zipMapper;
    @InjectMocks
    ZipService zipService;

    @Test
    void getZipBoardById() {
        // given
        when(zipMapper.selectById(any())).thenReturn(Optional.of(new ZipBoardDetailDTO()));
        // when
        ZipBoardDetailDTO foundzip = zipService.getZipBoardById(1L);
        // then
        verify(zipMapper).selectById(any());
        verify(zipMapper).updateViewCount(any());
        assertThat(foundzip).isNotNull();
    }

}