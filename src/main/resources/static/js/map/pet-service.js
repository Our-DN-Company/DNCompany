import {petServiceMapApi} from "./modules/mapApi.js";

// TODO : 강사님과 함께 공공데이터 api 하는것 해야함
petServiceMapApi(function (mapApi) {
    console.log(mapApi);
    console.log(mapApi.data.경도)
});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커 생성 함수
function addMarker(position, map) {
    var marker = new kakao.maps.Marker({
        position: position
    });
    marker.setMap(map);
}

function searchPlaces(map) {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
    var ps = new kakao.maps.services.Places();
        ps.keywordSearch(keyword, function (data, status) {
            console.log(data)
            if (status === kakao.maps.services.Status.OK) {
                var bounds = new kakao.maps.LatLngBounds();
                for (var i = 0; i < data.length; i++) {

                    var placePosition = new kakao.maps.LatLng(data[i].위도, data[i].경도);
                    addMarker(placePosition, map);
                    bounds.extend(placePosition);
                }
                map.setBounds(bounds);
            } else {
                alert('검색 결과가 존재하지 않습니다.');
            }
        });
}

