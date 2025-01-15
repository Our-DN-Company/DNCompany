package com.example.dncompany.provider;

import com.example.dncompany.resp.kakao.KakaoLoginInfoResponse;
import com.example.dncompany.resp.kakao.KakaoTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
public class AuthProvider {
    @Value("${auth.kakao.client-id}")
    private String kakaoClientId;
    @Value("${auth.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    public String getLocation() {
        RestClient restClient = RestClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .build();

        ResponseEntity<Void> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/authorize")
                        .queryParam("client_id", this.kakaoClientId)
                        .queryParam("redirect_uri", this.kakaoRedirectUri)
                        .queryParam("response_type", "code")
                        .build())
                .retrieve()
                .toEntity(Void.class);
        // toEntity는 응답 결과를 응답 객체로 변환하여 받는 것
        // (응답 바디에 데이터가 없으므로 바디에 내용이 필요 없다.)
        System.out.println("response = " + response);

        // 리디렉션은 재요청 보낼 URL을 응답 헤더에 Location이라는 이름으로 저장하여 전송한다.
        // 우리는 응답 헤더의 Location을 꺼내서 카카오 로그인 페이지 진입 URL을 직접 사용할 것이다.
        String location = response.getHeaders().getLocation().toString();

        return location;
    }
    public KakaoTokenResponse getKakaoToken(String accessCode) {
        RestClient restClient = RestClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .build();

        // 바디에 담을 데이터 준비
        // 일반 DTO 같은 객체 또는 Map 등은 JSON으로 자동 변환이 되지만
        // 폼 데이터로는 자동 변환되지 않음!!
        // 스프링에서 제공하는 MultiValueMap은 폼 데이터로 변환됨
        // JSON 통신 : Map, DTO 같은 커스텀 객체
        // 폼 데이터 통신 : MultiValueMap

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", this.kakaoClientId);
        formData.add("redirect_uri", this.kakaoRedirectUri);
        formData.add("code", accessCode);

        KakaoTokenResponse kakaoTokenResponse = restClient.post()
                .uri("/oauth/token")
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .body(formData)
                .retrieve()
                .body(KakaoTokenResponse.class);
        System.out.println("kakaoTokenResponse = " + kakaoTokenResponse);

        return kakaoTokenResponse;
    }

    public KakaoLoginInfoResponse getKakaoLoginInfo(String accessToken) {
        RestClient restClient = RestClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .build();

        KakaoLoginInfoResponse loginInfoResponse = restClient.get()
                .uri("/v2/user/me")
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    httpHeaders.setBearerAuth(accessToken);
                })
                .retrieve()
                .body(KakaoLoginInfoResponse.class);

        System.out.println("loginInfoResponse = " + loginInfoResponse);

        return loginInfoResponse;
    }
}
