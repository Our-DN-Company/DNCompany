package com.example.dncompany.resp.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoAccount {
    private String email;
    private String name;
    private KakaoProfile profile;

}
