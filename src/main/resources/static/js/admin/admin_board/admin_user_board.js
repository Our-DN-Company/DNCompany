
// DOM이 완전히 로드된 후 실행되는 이벤트 리스너
document.addEventListener("DOMContentLoaded", function () {



    const dummyData = [
        {번호: 1, 아이디: 'user1', 이름: '홍길동', 주소: '서울특별시 중랑구 겸재로92 502호', 폰번호: '010-1234-5678', 생년월일: '1990-01-01', 성별: '남성', 가입일: '2022-01-01', 신고: 0, 포인트: 1000},
        {번호: 2, 아이디: 'user2', 이름: '김철수', 주소: '서울특별시 서초구 신반포로 270 (반포동, 반포자이아파트) 102동 706호', 폰번호: '010-2345-6789', 생년월일: '1992-02-02', 성별: '남성', 가입일: '2022-02-02', 신고: 1, 포인트: 800},
        {번호: 3, 아이디: 'user3', 이름: '이영희', 주소: '부산광역시 강서구 녹산산단382로14번가길 10~29번지(송정동) 301호', 폰번호: '010-3456-7890', 생년월일: '1993-03-03', 성별: '여성', 가입일: '2022-03-03', 신고: 2, 포인트: 1200},
        {번호: 4, 아이디: 'user4', 이름: '박영수', 주소: '인천', 폰번호: '010-4567-8901', 생년월일: '1994-04-04', 성별: '남성', 가입일: '2022-04-04', 신고: 1, 포인트: 500},
        {번호: 5, 아이디: 'user5', 이름: '최지우', 주소: '광주', 폰번호: '010-5678-9012', 생년월일: '1995-05-05', 성별: '여성', 가입일: '2022-05-05', 신고: 0, 포인트: 2000},
        {번호: 6, 아이디: 'user6', 이름: '장미희', 주소: '대전', 폰번호: '010-6789-0123', 생년월일: '1996-06-06', 성별: '여성', 가입일: '2022-06-06', 신고: 0, 포인트: 1700},
        {번호: 7, 아이디: 'user7', 이름: '홍길순', 주소: '울산', 폰번호: '010-7890-1234', 생년월일: '1997-07-07', 성별: '여성', 가입일: '2022-07-07', 신고: 3, 포인트: 600},
        {번호: 8, 아이디: 'user8', 이름: '강철민', 주소: '경기', 폰번호: '010-8901-2345', 생년월일: '1998-08-08', 성별: '남성', 가입일: '2022-08-08', 신고: 2, 포인트: 1100},
        {번호: 9, 아이디: 'user9', 이름: '심지훈', 주소: '강원', 폰번호: '010-9012-3456', 생년월일: '1999-09-09', 성별: '남성', 가입일: '2022-09-09', 신고: 0, 포인트: 1400},
        {번호: 10, 아이디: 'user10', 이름: '하진수', 주소: '충남', 폰번호: '010-0123-4567', 생년월일: '2000-10-10', 성별: '남성', 가입일: '2022-10-10', 신고: 1, 포인트: 900},
        {번호: 11, 아이디: 'user11', 이름: '엄지현', 주소: '전북', 폰번호: '010-1234-5678', 생년월일: '2001-11-11', 성별: '여성', 가입일: '2022-11-11', 신고: 0, 포인트: 1300}
    ];

    // DOM 요소 참조 변수
    const memberListBody = document.getElementById('memberListBody');
    const itemsPerPageSelect = document.getElementById('itemsPerPage');
    // 회원 목록을 화면에 렌더링하는 코드
    function renderMemberList(members, itemsPerPage = 10) {
        // 기존 테이블 내용을 초기화
        memberListBody.innerHTML = "";
        // 페이지당 표시할 회원 수만큼 데이터 자르기 #삭제 예성 데이터베이스 연결시 쿼리문으로 대체 예정임
        const paginatedMembers = members.slice(0, itemsPerPage);


        // 각 회원별 테이블 행 생성
        paginatedMembers.forEach(member => {
            // 회원 정보와 관리 버튼을 포함한 HTML 템플릿
            const row = document.createElement('tr');
            row.innerHTML = `
              <td><input type="checkbox"></td>
              <td>${member.번호}</td>
              <td>${member.아이디}</td>
              <td>${member.이름}</td>
              <td>${member.주소}</td>
              <td>${member.폰번호}</td>
              <td>${member.생년월일}</td>
              <td>${member.성별}</td>
              <td>${member.가입일}</td>
              <td>${member.신고}</td>
              <td><span class="current-points">${member.포인트}</span></td>
              <td>
                <input type="number" min="1" placeholder="포인트" class="small-input" id="point-${member.번호}">
                <button onclick="applyCustomPoints(${member.번호})" class="blue-button">적용</button>
              </td>
              <td class="activity-buttons">
                <button onclick="applyBan(${member.번호}, 1)" class="blue-button">1일</button>
                <button onclick="applyBan(${member.번호}, 3)" class="blue-button">3일</button>
                <button onclick="applyBan(${member.번호}, 7)" class="blue-button">7일</button>
                <div class="ban-input">
                  <input type="number" min="1" placeholder="일수" id="ban-${member.번호}">
                  <button onclick="applyCustomBan(${member.번호})" class="blue-button">적용</button>
                </div>
              </td>
            `;
            memberListBody.appendChild(row);
        });
    }

    // 페이지당 게시물 수 선택 변경 이벤트
    itemsPerPageSelect.addEventListener("change", function () {
        const itemsPerPage = parseInt(itemsPerPageSelect.value, 10);
        renderMemberList(dummyData, itemsPerPage);
    });

    // 페이지 로드 시 초기 목록 렌더링
    renderMemberList(dummyData);
});
