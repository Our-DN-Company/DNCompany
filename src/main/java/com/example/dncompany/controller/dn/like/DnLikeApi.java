package com.example.dncompany.controller.dn.like;

import com.example.dncompany.service.dn.like.DnLikeService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DnLikeApi {
    private final DnLikeService dnLikeService;

    // 내부 클래스 사용
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LikeResponse {
        private boolean liked;
        private int likeCount;
        private String message;
        private boolean success;


        // 좋아요가 실패하면 실행되는 메서드
        public static LikeResponse error(boolean liked, int likeCount, String message) {
            return new LikeResponse(liked, likeCount, message, false);
        }

        // 좋아요가 성공하면 실행되는 메서드
        public static LikeResponse success(boolean liked, int likeCount, String message) {
            return new LikeResponse(liked, likeCount, message, true);
        }
    }

    @GetMapping("/v1/dn-markets/{dnId}/likes/check")
    public LikeResponse checkLikeStatus(@PathVariable("dnId") Long dnId,
                                        @SessionAttribute(value = "usersId", required = false) Long usersId) {

        DnLikeService.DnLikeResult result = dnLikeService.isLikedByUsers(usersId,dnId);

        return LikeResponse.success(result.isLiked(), result.getLikeCount(), "확인 완료");
    }

    @PutMapping("/v1/dn-markets/{dnId}/likes")
    public LikeResponse putLike(@PathVariable("dnId") Long dnId,
                                @SessionAttribute(value = "usersId", required = false) Long usersId){
        if (usersId == null) {
            DnLikeService.DnLikeResult result = dnLikeService.isLikedByUsers(usersId, dnId);
            return LikeResponse.error(result.isLiked(), result.getLikeCount(), "로그인이 필요한 서비스입니다.");
        }
        DnLikeService.DnLikeResult result = dnLikeService.toggleLike(usersId,dnId);
        return LikeResponse.success(result.isLiked(), result.getLikeCount(), "좋아요 처리되었습니다.");
    }

}
