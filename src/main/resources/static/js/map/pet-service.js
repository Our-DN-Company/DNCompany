import {petServiceMapApi} from "./modules/mapApi.js";

// 1. 지도 생성
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

// 2. 지도 로드
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

// 3. 페이지 처리 함수 불러오기
// pagination 데이터 변수 정의
var pagination = {
    current: 1, // 현재 페이지 번호
    perPage: 10, // 한 페이지당 가져올 데이터 개수
    last: 1, // 마지막 페이지 (초기화는 1로 설정)
    gotoPage: function(page) {
        this.current = page;
        fetchPlaces(page, this.perPage);
    }
};

// 페이지 번호를 표출합니다
displayPagination(pagination);

// 페이지 번호와 perPage 값을 기반으로 데이터를 업데이트하는 fetchPlaces 함수
function fetchPlaces(page, perPage) {
    // page와 perPage 값을 사용하여 데이터를 슬라이싱
    petServiceMapApi(function (mapApi) {
        var start = (page - 1) * perPage;
        var end = start + perPage;

        var paginatedData = mapApi.data.slice(start, end);

        pagination.last = Math.ceil(mapApi.data.length / perPage);

        displayPlaces(paginatedData); // 페이지에 해당하는 장소 표시
        displayPagination(pagination); // 페이지 번호 표시
    });
}

// 검색결과 목록 하단에 페이지번호를 표시하는 함수
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment();

    // 기존 페이지 번호 초기화
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
    }

    for (var i = 1; i <= pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function (i) {
                return function () {
                    pagination.gotoPage(i); // 페이지 이동
                };
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색시 기존 마커는 제거하는 로직
function clearMarkers() {
    markers.forEach(function(marker) {
        marker.setMap(null);
    });
    markers = [];
}

function displayPlaces(places) {
    // 검색 전 기존 마커 지우는 로직
    clearMarkers();

    // 필터링된 장소 확인 (디버깅용)
    console.log("Displaying Places:", places);

    // JSON 데이터를 기반으로 마커를 생성하고 지도에 표시
    const markerMap = new Map();
    places.forEach(function (location) {
        const markerPosition = new kakao.maps.LatLng(location.lat, location.lng);

        const marker = new kakao.maps.Marker({
            position: markerPosition,
            map: map,
            title: location.store_name
        });

        // Map 객체에 장소와 마커를 매핑
        markerMap.set(location.store_name, marker);

        // 마커에 클릭 이벤트를 등록하여 인포윈도우를 표시
        kakao.maps.event.addListener(marker, 'click', function () {
            // 기존 인포윈도우가 열려 있으면 닫기
            if (currentInfowindow) currentInfowindow.close();

            const content = `
                        <div style="padding:5px;">
                            <strong>${location.store_name}</strong><br>
                            주소: ${location.address_road_name}<br>
                            전화번호: ${location.phone_number || "전화번호 없음"}
                        </div>
                    `;

            const infowindow = new kakao.maps.InfoWindow({
                content: content,
                removable: true
            });
            infowindow.open(map, marker);
            currentInfowindow = infowindow;  // 열린 인포윈도우 추적
            // 마커 클릭 시 해당 마커의 위치로 지도 중심 이동
            map.setCenter(markerPosition);
        });

        // 생성된 마커를 배열에 추가
        markers.push(marker);
    });
    // 장소 리스트 업데이트 함수 호출
    updatePlacesList(places, markerMap);
}

function updatePlacesList(places, markerMap) {
    const placesList = document.getElementById('placesList');
    placesList.innerHTML = ''; // 기존 리스트 초기화

    // , index
    places.forEach(function(place) {
        const listItem = document.createElement('li');
        listItem.textContent = place.store_name;
        listItem.className = 'item';
        listItem.innerHTML = `
                    <div class="info">
                        <h5>${place.store_name}</h5>
                        <span>도로명 주소 : ${place.address_road_name}</span>
                        <span>지번 : ${place.address_jibun_name}</span>
                        <span>${place.phone_number || '전화번호 없음'}</span>
                    </div>
                `;
        // 리스트 항목 클릭 이벤트 추가
        listItem.addEventListener("click", function () {
            const marker = markerMap.get(place.store_name);
            if (marker) {
                kakao.maps.event.trigger(marker, "click"); // 마커의 클릭 이벤트를 트리거
            }
        });
        placesList.appendChild(listItem);
    });
}

// 4. callback으로 받은 Json 데이터를 mapApi에 저장
petServiceMapApi(function (mapApi) {
    // 초기 로드 시 모든 장소의 마커를 표시
    displayPlaces(mapApi.data);
    pagination.current = 1; // 현재 페이지 초기화
    fetchPlaces(pagination.current, pagination.perPage);

    function
    searchPlaces(keyword) {
        if (!keyword || keyword.trim() === "") {
            alert("검색어를 입력해주세요.");
            return;
        }
        keyword = keyword.trim();

        if (!mapApi || !Array.isArray(mapApi.data)) {
            console.error("Invalid mapApi data");
            return;
        }

        // mapApi.data에서 키워드로 필터링
        var filteredData = mapApi.data.filter(function (place) {
            return place && // place가 null 또는 undefined가 아닌지 확인
                (place.store_name.includes(keyword)|| // 키워드가 store_name에 포함된 항목만 반환
                place.address_road_name.includes(keyword));  // 키워드가 address_road_name에 포함된 항목만 반환
        });

        // 필터링된 데이터 확인 (디버깅용)
        console.log("Filtered Data:", filteredData);

        if (filteredData.length === 0) {
            alert("검색 결과가 없습니다.");
        } else {
            // 검색된 장소를 지도에 표시
            displayPlaces(filteredData);
        }
    }
    // searchPlaces를 전역으로 노출
    window.searchPlaces = searchPlaces;
});


