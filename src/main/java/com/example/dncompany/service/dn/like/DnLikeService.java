package com.example.dncompany.service.dn.like;

import com.example.dncompany.dto.dn.like.DnLikeDTO;
import com.example.dncompany.mapper.dn.like.DnLikeMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DnLikeService {
    private final DnLikeMapper dnLikeMapper;

    @Getter
    @ToString
    @AllArgsConstructor
    public static class DnLikeResult {
        // 좋아요를 눌렀는지 확인하는 liked 변수
        private boolean liked;
        // 좋아요 갯수
        private int likeCount;
    }

    // 1. 현재 로그인한 회원이 현재 글을 좋아요 눌렀었는지 유무를 반환 한다. ( 현재 좋아요 수 포함!)
    public DnLikeResult isLikedByUsers(Long usersId, Long dnId) {
        int countExists = dnLikeMapper.countByUsersIdAndDnId(usersId, dnId);
        int countLike = dnLikeMapper.countByDnId(dnId);

        return new DnLikeResult(countExists > 0 , countLike);
    }

    // 2. 현재 회원이 좋아요 버튼을 누르면 유/무에 따라 좋아요 삽입/삭제 처리
    public DnLikeResult toggleLike(Long usersId, Long dnId) {
        // 1. 좋아요 상태 확인
        int countExists = dnLikeMapper.countByUsersIdAndDnId(usersId, dnId);
        boolean isLiked = countExists > 0;

        // 2. 좋아요 상태 변경
        if (isLiked) {
            // 이미 좋아요 상태이므로 취소 처리 진행
            dnLikeMapper.deleteByUsersIdAndDnId(usersId, dnId);
        } else {
            // 좋아요 상태가 아니면 삽입 진행
            DnLikeDTO dnLikeDTO = new DnLikeDTO();
            dnLikeDTO.setUsersId(usersId);
            dnLikeDTO.setDnId(dnId);
            dnLikeMapper.insertDnLike(dnLikeDTO);
        }

        // 3. 변경된 후의 전체 좋아요 수 다시 조회
        int updatedCount = dnLikeMapper.countByDnId(dnId);

        return new DnLikeResult(!isLiked, updatedCount);
    }
}
