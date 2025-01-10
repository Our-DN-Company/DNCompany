package com.example.dncompany.service.zip.zipLike;

import com.example.dncompany.dto.zip.zipLike.ZipLikeDTO;
import com.example.dncompany.mapper.zip.zipLike.ZipLikeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ZipLikeService {
    private final ZipLikeMapper zipLikeMapper;

    @Getter @ToString @AllArgsConstructor
    public static class ZipLikeResult {
        private boolean liked;
        private int likeCount;
    }

    // 로그인한 회원의 추천 유무(현재 추천 수 포함)
    public ZipLikeResult isLikeByUser(Long zipId, Long usersId) {
        int countExists = zipLikeMapper.countByZipIdAndUserId(zipId, usersId);
        int countLike = zipLikeMapper.countByZipId(zipId);

        return new ZipLikeResult(countExists > 0, countLike);
    }

    // 추천 유무에 따른 추천 삽입/삭제
    public ZipLikeResult toggleLike(Long zipId, Long usersId) {
        // 상태 확인
        int countExists = zipLikeMapper.countByZipIdAndUserId(zipId, usersId);
        boolean isLiked = countExists > 0;

        // 상태 변경
        if (isLiked) {
            // 취소
            zipLikeMapper.deleteByZipIdAndUserId(zipId, usersId);
        } else {
            // 삽입
            ZipLikeDTO zipLikeDTO = new ZipLikeDTO();
            zipLikeDTO.setZipId(zipId);
            zipLikeDTO.setUsersId(usersId);
            zipLikeMapper.insertZipLike(zipLikeDTO);
        }
        // 변경후 전체 추천수 조회
        int updateCount = zipLikeMapper.countByZipId(zipId);

        return new ZipLikeResult(!isLiked, updateCount);

    }

}
