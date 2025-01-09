// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 지도를 표시하는 div 크기를 변경하는 함수입니다
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

// 지도 레벨은 지도의 확대 수준을 의미합니다
// 지도 레벨은 1부터 14레벨이 있으며 숫자가 작을수록 지도 확대 수준이 높습니다
function zoomIn() {
  // 현재 지도의 레벨을 얻어옵니다
  var level = map.getLevel();

  // 지도를 1레벨 내립니다 (지도가 확대됩니다)
  map.setLevel(level - 2);

  // 지도 레벨을 표시합니다
  displayLevel();
}

function zoomOut() {
  // 현재 지도의 레벨을 얻어옵니다
  var level = map.getLevel();

  // 지도를 1레벨 올립니다 (지도가 축소됩니다)
  map.setLevel(level + 1);

  // 지도 레벨을 표시합니다
  displayLevel();
}

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

// 키워드로 장소를 검색합니다 -> html의 병원 검색에서 장소를 입력하면 해당하는 장소를 검색하는 함수
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
  var input = document.getElementById("keyword");

  // 입력값이 없으면 placeholder 값 사용
  var keyword = input.value.trim() || input.placeholder;

  // // 아래 코드는 만약 사용자가 빈문자열을 보내게되면 경고창으로 키워드를 입력해주세요! 라고 띄운다.
  // if (!keyword.replace(/^\s+|\s+$/g, "")) {
  //   keyword.value = "노원구 동물병원";
  //   alert("다시 입력해주세요!");
  //   return false;
  // }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
// 이 콜백 함수를 사용하려면 변수로 data, status, pagination을 받아야 한다.
function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);
    zoomIn();
    // 페이지 번호를 표출합니다
    displayPagination(pagination);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert("검색 결과가 존재하지 않습니다.");
    return;
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
  // html 파일에서 id가 placesList를 찾아 변수 listEl에 저장함.
  var listEl = document.getElementById("placesList"),
    menuEl = document.getElementById("menu_wrap"),
    // fragment를 선언
    // fragment는 DOM 트리에 반영되기 전까지는 메모리 상에서만 존재하는  DOM노드이다.
    // DOM에 반영되기 전까지 DOM 트리가 아닌 메모리상에서만 존재하기에 구조에 변경이 일어나도 브라우저가
    // 다시 화면을 렌더링 하지 않는다. 성능 최적화에 사용됨
    // 하지만 fragment를 사용하지 않고 Element를 사용하여도 된다.
    // 둘의 차이가 변수가 적을때는 크게 영향은 없지만 변수가 많아지게되면
    // fragment를 사용하여 불필요한 데이터를 생성하지 않게 만들 수 있다.
    fragment = document.createDocumentFragment(),
    // WGS84 좌표계에서 사각영역 정보를 표현하는 객체를 생성합니다.
    // 인자를 주지 않으면 빈영역을 생성하고 sw, ne 인자를 받을 수 있습니다.
    //sw : 사각 영역에서 남서쪽 좌표
    //ne : 사각 영역에서 북동쪽 좌표
    bounds = new kakao.maps.LatLngBounds(),
    listStr = "";

  // 검색 결과 목록에 추가된 항목들을 제거합니다
  removeAllChildNods(listEl);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeMarker();

  for (var i = 0; i < places.length; i++) {
    // 마커를 생성하고 지도에 표시합니다
    // LatLng : 좌표정보를 가지고 있는 객체 생성
    // 해당 함수는 places에 i번째 요소의 x, y를 가져온다.
    var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
      // addMarker 함수는 장소와 인덱스 번호를 받아서 이를 추가해준다.
      marker = addMarker(placePosition, i),
      // getListItem는 index와 해당 장소를 받아서 Element를를반환해준다.
      itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function (marker, title) {
      kakao.maps.event.addListener(marker, "mouseover", function () {
        displayInfowindow(marker, title);
      });

      kakao.maps.event.addListener(marker, "mouseout", function () {
        infowindow.close();
      });

      itemEl.onmouseover = function () {
        displayInfowindow(marker, title);
      };

      itemEl.onmouseout = function () {
        infowindow.close();
      };
    })(marker, places[i].place_name);

    fragment.appendChild(itemEl);
  }

  // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
  listEl.appendChild(fragment);
  menuEl.scrollTop = 0;

  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
  map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
  // li 태그를 el 변수에 생성한 뒤
  var el = document.createElement("li"),
    // itemlStr에 span태그의 클래스 이름은 받아온 index에 +1을 한 숫자를 대입해주고
    // 0번째 요소라면 markerbg marker_1 이런식으로 클래스 명이 붙게 된다.
    // 해당 span 태그를 생성 후 div의 info라는 클래스명으로 형제 선택자를 생성하고
    // 이 div에 h5 요소를 넣고 h5에는 해당 장소의 name을 저장한다.
    // 이때 </div>는 아래 if문에서 해당하는 내용들을 받은 뒤
    // 각각의 요소를 삽입한 후 마지막에 /div로 처리를 한다.
    itemStr =
      '<span class="markerbg marker_' +
      (index + 1) +
      '"></span>' +
      '<div class="info">' +
      "   <h5>" +
      places.place_name +
      "</h5>";

  // 만약 places에 road_address_name이 있다면 아래 코드를 실행하라는 의미미
  if (places.road_address_name) {
    itemStr +=
      "    <span>" +
      places.road_address_name +
      "</span>" +
      '   <span class="jibun gray">' +
      places.address_name +
      "</span>";
    // 그렇지 않고 road_address_name이 없다면 road_address_name을 제외한
    // 그냥 일반 주소만 생성성
  } else {
    itemStr += "    <span>" + places.address_name + "</span>";
  }

  // 마지막으로 전화번호를 span태그로 넣고 div를 마무리
  itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

  // 위에서 완성된 itemStr을 위에서 생성한 el에 innerHTML로 DOM에 삽입
  // el의 클래스 명을 item으로 지정한다.
  // 이는 li태그를 생성한 것의 클래스명을 item으로 명명하는 것
  el.innerHTML = itemStr;
  el.className = "item";

  // getListItem 함수를 호출하면 el요소를 반환함
  return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
// 마커 인터페이스를 바꾸려면 여기서 바꿔주면 됨.
function addMarker(position, idx, title) {
  var imageSrc =
      // 마커의 이미를 담당하는 변수, 가져오고 싶은 이미지의 링크를 수정하고 해당 이미지의
      // 크기를 설정한다.
      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
    imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
    imgOptions = {
      spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
      spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
      offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    },
    markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
    marker = new kakao.maps.Marker({
      position: position, // 마커의 위치
      image: markerImage,
    });

  marker.setMap(map); // 지도 위에 마커를 표출합니다
  markers.push(marker); // 배열에 생성된 마커를 추가합니다

  return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
  var paginationEl = document.getElementById("pagination"),
    fragment = document.createDocumentFragment(),
    i;

  // 기존에 추가된 페이지번호를 삭제합니다
  while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild(paginationEl.lastChild);
  }

  for (i = 1; i <= pagination.last; i++) {
    var el = document.createElement("a");
    el.href = "#";
    el.innerHTML = i;

    if (i === pagination.current) {
      el.className = "on";
    } else {
      el.onclick = (function (i) {
        return function () {
          pagination.gotoPage(i);
        };
      })(i);
    }

    fragment.appendChild(el);
  }
  paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
  var content = '<div style="padding:10px;z-index:1;">' + title + "</div>";

  infowindow.setContent(content);
  infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
  while (el.hasChildNodes()) {
    el.removeChild(el.lastChild);
  }
}

// const $searchBefore = document.querySelector(".search_before");

// $searchBefore.addEventListener("click", (e) => {
//   e.style.display = "hidden";
// });


import * as mapApi from './modules/mapApi.js';

mapApi.petServiceMapApi();