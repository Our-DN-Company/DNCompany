package com.example.dncompany.service.user;

import com.example.dncompany.provider.AuthProvider;
import com.example.dncompany.resp.kakao.KakaoLoginInfoResponse;
import com.example.dncompany.resp.kakao.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthProvider authProvider;

    public String getKakaoLoginURI() {
        return authProvider.getLocation();
    }
    public Long getKakaoLoginInfo(String accessCode) {
        KakaoTokenResponse kakaoToken = authProvider.getKakaoToken(accessCode);
        KakaoLoginInfoResponse kakaoLoginInfo = authProvider.getKakaoLoginInfo(kakaoToken.getAccessToken());
        System.out.println("kakaoLoginInfo = " + kakaoLoginInfo);

        Long kakaoId = kakaoLoginInfo.getId();
        // 1. 멤버 테이블에 카카오 식별자를 저장할 수 있는 칼럼 또는 별도 카카오 인증 테이블 생성
        // 2. 우리 DB에 저장된 회원 중 카카오 식별자가 위에서 받아온 정보와 일치하는 회원이 존재하는지 조회, 조회 결과가 있으면 해당 회원의 MemberId를 반환

        return kakaoId;
        // 없으면 지금 가입 처리를 한 후 가입한 회원의 MemberId를 반환
        // 가입 처리를 진행할 때 추가 정보를 받아야하므로 회원가입 페이지로 리디렉션 처리 필요
        // 즉, 조회 결과가 없으면 가입 처리 진행 -> 예외 발생시키고 컨트롤러에서 예외를 catch 후
        // 추가 정보 받기 페이지로 리디렉션.
    }
}
