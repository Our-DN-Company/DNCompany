import * as scheduleListApi from '../modules/scheduleListApi.js';

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
        editable: true, // false로 변경 시 draggable 작동 x

    });
    calendar.render();
}
