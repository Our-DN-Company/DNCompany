package com.example.dncompany.mapper.zip.zipLike;

import com.example.dncompany.dto.zip.zipLike.ZipLikeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ZipLikeMapperTest {
    @Autowired
    private ZipLikeMapper zipLikeMapper;

    ZipLikeDTO zipLikeDTO;

    @Test
    void insertZipLike() {
        zipLikeDTO = new ZipLikeDTO();
        zipLikeDTO.setZipId(4L);
        zipLikeDTO.setUsersId(5L);

        zipLikeMapper.insertZipLike(zipLikeDTO);
    }

    @Test
    void countByZipId() {
        zipLikeDTO = new ZipLikeDTO();
        zipLikeDTO.setZipId(4L);
        zipLikeDTO.setUsersId(5L);
        zipLikeDTO.setUsersId(42L);
        zipLikeDTO.setUsersId(5L);
        zipLikeDTO.setUsersId(1L);
        zipLikeDTO.setUsersId(52L);

        zipLikeMapper.insertZipLike(zipLikeDTO);
    }

    @Test
    void countByZipIdAndUserId() {
    }

    @Test
    void deleteByZipIdAndUserId() {
    }
}