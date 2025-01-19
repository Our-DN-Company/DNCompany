import {petServiceMapApi} from "./modules/mapApi.js";

var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
    center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도의 중심좌표 (서울 시청 기준)
    level: 5 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// // 장소 검색 객체를 생성합니다
// var ps = new kakao.maps.services.Places();

// 마커를 담을 배열입니다
var markers = [];
var currentInfowindow = null;  // 열린 인포윈도우를 추적하는 변수

function resizeMap() {
    var mapContainer = document.getElementById("map");
    mapContainer.style.width = "100%";
    mapContainer.style.height = "800px";
}

resizeMap();

function relayout() {
    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다
    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
    map.relayout();
}

relayout();


petServiceMapApi(function (mapApi) {
    // 초기 로드 시 모든 장소의 마커를 표시
    displayPlaces(mapApi.data);

    function searchPlaces(keyword) {
        if (!keyword || keyword.trim() === "") {
            alert("검색어를 입력해주세요.");
            return;
        }

        // mapApi.data에서 키워드로 필터링
        var filteredData = mapApi.data.filter(function (place) {
            return place.store_name.includes(keyword); // 키워드가 store_name에 포함된 항목만 반환
        });

        console.log(filteredData);

        // 검색된 장소를 지도에 표시
        displayPlaces(filteredData);
    }
    console.log(mapApi);

    // 검색시 기존 마커는 제거하는 로직
    function clearMarkers() {
        console.log("Removing markers:", markers.length); // 현재 마커 개수 출력
        markers.forEach(function(marker) {
            marker.setMap(null);
        });
        markers = [];
    }


    function displayPlaces(places) {
        // 검색 전 기존 마커 지우는 로직
        clearMarkers();
        // JSON 데이터를 기반으로 마커를 생성하고 지도에 표시
        places.forEach(function(location) {
            console.log(location);
            var markerPosition = new kakao.maps.LatLng(location.lat, location.lng);

            var marker = new kakao.maps.Marker({
                position: markerPosition,
                map: map,
                title: location.store_name
            });
            
            markers.push(marker);

            // 마커에 클릭 이벤트를 등록하여 인포윈도우를 표시
            kakao.maps.event.addListener(marker, 'click', function() {
                // 기존 인포윈도우가 열려 있으면 닫기
                if (currentInfowindow) {
                    currentInfowindow.close();
                }

                var content = '<div style="padding:5px;">' +
                    '<strong>' + location.store_name + '</strong><br>' +
                    '주소: ' + location.address_road_name + '<br>' +
                    '전화번호: ' + location.phone_number +
                    '</div>';

                var infowindow = new kakao.maps.InfoWindow({
                    content: content,
                    removable: true
                });

                infowindow.open(map, marker);
                currentInfowindow = infowindow;  // 열린 인포윈도우 추적

                // 마커 클릭 시 해당 마커의 위치로 지도 중심 이동
                map.setCenter(markerPosition);
            });
        });

        function updatePlacesList(places) {
            var listEl = document.getElementById('placesList');
            listEl.innerHTML = ''; // 기존 리스트 초기화

            places.forEach(function(place, index) {
                var listItem = document.createElement('li');
                listItem.className = 'item';
                listItem.innerHTML = `
                <div class="info">
                    <h5>${place.store_name}</h5>
                    <span>도로명 주소 : ${place.address_road_name}</span>
                    <span>지번 : ${place.address_jibun_name}</span>
                    <span>${place.phone_number || '전화번호 없음'}</span>
                </div>
                `;
                listEl.appendChild(listItem);

                // 리스트 클릭 시 해당 마커로 이동
                listItem.onclick = function() {
                    var marker = markers[index];
                    kakao.maps.event.trigger(marker, 'click');
                };
            });
        }
        updatePlacesList(places);
        // searchPlaces를 전역으로 노출
        window.searchPlaces = searchPlaces;
    };
});


