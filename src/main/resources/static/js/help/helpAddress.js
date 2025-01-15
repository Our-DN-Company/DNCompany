const addressData = {
    '서울': ['강남구', '강동구', '강북구', '강서구', '관악구', '광진구', '구로구', '금천구', '노원구', '도봉구',
        '동대문구', '동작구', '마포구', '서대문구', '서초구', '성동구', '성북구', '송파구', '양천구',
        '영등포구', '용산구', '은평구', '종로구', '중구', '중랑구'],
    '경기': ['수원시', '성남시', '용인시', '부천시', '안산시', '안양시', '의정부시', '파주시', '구리시', '남양주시'],
    '인천': ['중구', '동구', '미추홀구', '연수구', '남동구', '부평구', '계양구', '서구']
};

document.addEventListener('DOMContentLoaded', function() {
    const sidoSelect = document.getElementById('sido');
    const gugunSelect = document.getElementById('gugun');

    sidoSelect.addEventListener('change', function() {
        updateGugunList(this.value);
    });

    function updateGugunList(sido) {
        const gugunList = addressData[sido] || [];
        gugunSelect.innerHTML = '<option value="">구/군 선택</option>' +
            gugunList.map(gugun => `<option value="${gugun}">${gugun}</option>`).join('');
    }
});