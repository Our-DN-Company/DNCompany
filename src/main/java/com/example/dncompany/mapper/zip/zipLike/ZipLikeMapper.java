package com.example.dncompany.mapper.zip.zipLike;

import com.example.dncompany.dto.zip.zipLike.ZipLikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ZipLikeMapper {
    // 추천 삽입
    void insertZipLike(ZipLikeDTO zipLikeDTO);

    // 누가 추천을 눌럿는지?
    int countByZipId(Long zipId);

    // 게시글 총 추천 수
    int countByZipIdAndUserId(@Param("zipId") Long zipId,
                              @Param("usersId") Long usersId);

    //추천 취소
    void deleteByZipIdAndUserId(@Param("zipId") Long zipId,
                                @Param("usersId") Long usersId);

}
