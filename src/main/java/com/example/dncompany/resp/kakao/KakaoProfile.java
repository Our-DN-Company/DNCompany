package com.example.dncompany.resp.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoProfile {
    private String nickname;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    @JsonProperty("thumbnail_image_url")
    private String thumbnailImageUrl;
}
