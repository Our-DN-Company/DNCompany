package com.example.dncompany.dto.map.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Data {
    /*
            "경도": "126.886336",
            "위도": "37.64454276",
            "우편번호": 10598,
            "도로명주소": "경기도 고양시 덕양구 동세로 19",
            "법정읍면동명칭": "동산동",
            "시군구 명칭": "고양시 덕양구",
            "시도 명칭": "경기도",
            "지번주소": "경기도 고양시 덕양구 동산동 352-1",
            "카테고리1": "반려동물업",
            "카테고리2": "반려의료",
            "카테고리3": "동물약국",
            "홈페이지": "정보없음",
            "휴무일": "매주 토, 일, 법정공휴일"

            "시설명": "1004 약국",
            "운영시간": "월~금 09:00~18:00",
            "기본 정보_장소설명": "동물약국",
            "전화번호": "02-381-5052",

            "반려동물 동반 가능정보": "Y",
            "반려동물 전용 정보": "해당없음",
            "반려동물 제한사항": "제한사항 없음",
            "애견 동반 추가 요금": "없음",
            "입장 가능 동물 크기": "모두 가능",
            "입장(이용료)가격 정보": "변동",
            "장소(실내) 여부": "Y",
            "장소(실외)여부": "N",
     */
    @JsonProperty("lng") // JSON 출력 시 사용될 키 이름
    @JsonAlias("경도")    // JSON 입력 시 매핑할 한글 키 이름
    private double lng;

    @JsonProperty("lat")
    @JsonAlias("위도")
    private double lat;

    @JsonProperty("address_number")
    @JsonAlias("우편번호")
    private int addressNumber;

    @JsonProperty("address_road_name")
    @JsonAlias("도로명주소")
    private String addressRoadName;

    @JsonProperty("dong_name")
    @JsonAlias("법정읍면동명칭")
    private String dongName;

    @JsonProperty("si_gu_name")
    @JsonAlias("시군구 명칭")
    private String siGuName;

    @JsonProperty("city_name")
    @JsonAlias("시도 명칭")
    private String cityName;

    @JsonProperty("address_jibun_name")
    @JsonAlias("지번주소")
    private String addressJiBunName;

    @JsonProperty("category_business")
    @JsonAlias("카테고리1")
    private String categoryBusiness;

    @JsonProperty("category_business_name")
    @JsonAlias("카테고리2")
    private String categoryBusinessName;

    @JsonProperty("category_business_store_name")
    @JsonAlias("카테고리3")
    private String categoryBusinessStoreName;

    @JsonProperty("homepage_url")
    @JsonAlias("홈페이지")
    private String homePageUrl;

    @JsonProperty("holiday")
    @JsonAlias("휴무일")
    private String holiday;

    @JsonProperty("store_name")
    @JsonAlias("시설명")
    private String storeName;

    @JsonProperty("business_hours")
    @JsonAlias("운영시간")
    private String businessHours;

    @JsonProperty("place_description")
    @JsonAlias("기본 정보_장소설명")
    private String placeDescription;

    @JsonProperty("phone_number")
    @JsonAlias("전화번호")
    private String phoneNumber;

    @JsonProperty("pets_allowed_info")
    @JsonAlias("반려동물 동반 가능정보")
    private String petsAllowedInfo;

    @JsonProperty("only_pets_info")
    @JsonAlias("반려동물 전용 정보")
    private String onlyPetsInfo;

    @JsonProperty("pet_restrictions_info")
    @JsonAlias("반려동물 제한사항")
    private String petRestrictionsInfo;

    @JsonProperty("pets_allowed_price_info")
    @JsonAlias("애견 동반 추가 요금")
    private String petsAllowedPriceInfo;

    @JsonProperty("pets_allowed_size_info")
    @JsonAlias("입장 가능 동물 크기")
    private String petsAllowedSizeInfo;

    @JsonProperty("pets_allowed_price_change_info")
    @JsonAlias("입장(이용료)가격 정보")
    private String petsAllowedPriceChangeInfo;

    @JsonProperty("pets_indoors_info")
    @JsonAlias("장소(실내) 여부")
    private String petsIndoorsInfo;

    @JsonProperty("pets_outdoors_info")
    @JsonAlias("장소(실외)여부")
    private String petsOutdoorsInfo;
}
