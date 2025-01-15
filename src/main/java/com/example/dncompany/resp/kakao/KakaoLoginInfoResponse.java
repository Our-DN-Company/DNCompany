package com.example.dncompany.resp.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoLoginInfoResponse {
    private Long id;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
}
