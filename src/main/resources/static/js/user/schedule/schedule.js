import * as scheduleListApi from '../modules/scheduleListApi.js';
import { fetchEventsForDate } from '../modules/scheduleListApi.js';

// 두 함수, 메서드 모두 log 찍어보았으나 이상없이 잘 출력됨
// console.log(scheduleListApi);
// console.log(scheduleListApi.scheduleList);

// scheduleListApi.scheduleList(sessionStorage.getItem("usersId"))
let usersId = 6;

scheduleListApi.scheduleList(usersId, function (data) {
    if (data) {
        console.log('데이터 로드 성공!', data);
        insertSchedule(data);
    } else {
        console.error('데이터 로드 실패..')
    }
});

function makeScheduleObject(title, helpCareDate) {
    return {
        title: title,
        start: helpCareDate,
        end: helpCareDate,
    };
}


function insertSchedule(data) {
    let scheList = [];

    for (let item of data) {
        let scheObj = makeScheduleObject(item.helpTitle, item.helpCareDate);
        scheList.push(scheObj);
    }

    console.log(scheList);

    var calendarEl = document.getElementById("calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
        aspectRatio: 1.3, // 캘린더 높이 설정
        timeZone: "UTC",
        locale: "ko", // 한국어 설정
        initialView: "dayGridMonth", // 홈페이지에서 다른 형태의 view를 확인할  수 있다.
        dayMaxEventRows: true,
        // 일정 이벤트
        // info는 캘린더 내부의 전체 정보에 관한 프로퍼티
        // info.event.title : info내부에 있는 event 객체에서  title을 가져옴
        eventClick: function (info) {
            // 이벤트에 있는 title을 출력
            alert("내 일정 " + info.event.title);
            // 해당 이벤트의 x,y 좌표를 출력해줌
            alert("Coordinates" + info.jsEvent.pageX + "," + info.jsEvent.pageY);

            alert("Views : " + info.view.type);

            info.el.style.borderColor = "black";
        },

        // 캘린더 사이즈 조절

        // 날짜에서 일 없애기
        dayCellContent: function (info) {
            var number = document.createElement("a");
            number.classList.add("fc-daygrid-day-number");
            number.innerHTML = info.dayNumberText.replace("일", "");
            if (info.view.type === "dayGridMonth") {
                return {
                    html: number.outerHTML,
                };
            }
            return {
                domNodes: [],
            };
        },
        events:scheList,
        editable: false, // false로 변경 시 draggable 작동 x

        // 날짜 클릭 시 호출될 함수 정의
        dateClick: function(info) {
            // 클릭한 날짜의 이벤트를 가져오는 함수 호출
            fetchEventsForDate(info.dateStr)
                .then(events => {
                    // 가져온 이벤트 목록을 화면에 표시하는 함수 호출
                    displayEventList(events);
                })
                .catch(error => {
                    // 오류 발생 시 처리 로직 (예: 사용자에게 알림)
                    console.error('Error fetching events:', error);
                });
        }

    });
    calendar.render();
}

// 이벤트 목록을 표시하는 함수 정의
function displayEventList(events) {
    // 스케줄 목록을 표시할 요소 선택
    var scheduleContainer = document.querySelector('.my-schedule');

    // 기존 스케줄 목록 초기화
    scheduleContainer.innerHTML = '';

    if (events.length > 0) {
        // 이벤트가 있는 경우
        events.forEach(event => {
            // 새로운 스케줄 아이템 생성
            var scheduleItem = document.createElement('div');
            scheduleItem.classList.add('my-schedule-list');

            // 스케줄 내용 설정
            scheduleItem.innerHTML = `
            <a href="#">
              <span class="my-schedule-list-top">${event.title}</span>
              <div>
                <span>돌봄 날짜: ${event.date}</span><br>
                <span>돌봄 종류: ${event.type}</span><br>
                <span>돌봄 시간: ${event.startTime}</span><br>
                <span>종료 시간: ${event.endTime}</span><br>
              </div>
            </a>
          `;

            // 스케줄 컨테이너에 아이템 추가
            scheduleContainer.appendChild(scheduleItem);
        });
    } else {
        // 이벤트가 없는 경우
        var noScheduleMessage = document.createElement('span');
        noScheduleMessage.textContent = '오늘은 스케줄이 없습니다.';
        scheduleContainer.appendChild(noScheduleMessage);
    }
}