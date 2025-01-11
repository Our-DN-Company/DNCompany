package com.example.dncompany.controller.zip.zipLike;

import com.example.dncompany.service.zip.zipLike.ZipLikeService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZipLikeApi {
    private final ZipLikeService zipLikeService;

    @Getter @ToString @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LikeResponse {
        private boolean liked;
        private int likeCount;
        private String message;
        private boolean success;

        public static LikeResponse error(boolean liked, int likeCount, String message) {
            return new LikeResponse(liked, likeCount, message, false);
        }

        public static LikeResponse success(boolean liked, int likeCount, String message) {
            return new LikeResponse(liked, likeCount, message, true);
        }
    }

    @GetMapping("/v1/zips/{zipId}/likes/check")
    public LikeResponse checkLikeStatus(@PathVariable Long zipId,
                                        @SessionAttribute(value = "usersId", required = false) Long usersId
    ){
        ZipLikeService.ZipLikeResult result = zipLikeService.isLikeByUser(zipId, usersId);

        return LikeResponse.success(result.isLiked(), result.getLikeCount(), "확인 완료");
    }

    @PutMapping("/v1/zips/{zipId}/likes")
    public LikeResponse putLike(@PathVariable("zipId") Long zipId,
                                @SessionAttribute(value = "usersId", required = true) Long usersId
    ) {
        if (usersId == null) {
            ZipLikeService.ZipLikeResult result = zipLikeService.isLikeByUser(zipId, usersId);
            return  LikeResponse.error(result.isLiked(), result.getLikeCount(), "로그인이 필요합니다.");
        }

        ZipLikeService.ZipLikeResult result = zipLikeService.toggleLike(zipId, usersId);

        log.debug("result: {}", result);

        return LikeResponse.success(result.isLiked(), result.getLikeCount(), "추천 되었습니다.");
    }
}
