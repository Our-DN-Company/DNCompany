document.addEventListener("DOMContentLoaded", function () {
    // 변수 선언
    const itemsPerPageSelect = document.getElementById('itemsPerPage');
    const searchForm = document.getElementById('searchForm');
    const selectAllCheckbox = document.getElementById('selectAll');
    const memberListBody = document.getElementById('memberListBody');
    const reportModal = document.getElementById('reportModal');
    const reportTableBody = document.getElementById('reportTableBody');

    // itemsPerPage 변경 이벤트
    itemsPerPageSelect.addEventListener("change", function () {
        const formData = new FormData(searchForm);
        formData.append('page', '1');  // 페이지 1로 리셋
        formData.append('size', this.value);

        fetch("/admin/user/board/list/data", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.text();
            })
            .then(html => {
                memberListBody.innerHTML = html;
            })
            .catch(error => {
                console.error('Error:', error);
                alert('데이터 로딩 중 오류가 발생했습니다.');
            });
    });

    // 검색 폼 제출 이벤트
    searchForm.addEventListener("submit", function (e) {
        e.preventDefault();
        const formData = new FormData(this);
        formData.append('page', '1');  // 검색 시 첫 페이지로
        formData.append('size', itemsPerPageSelect.value);

        fetch("/admin/user/board/list/data", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.text();
            })
            .then(html => {
                memberListBody.innerHTML = html;
            })
            .catch(error => {
                console.error('Error:', error);
                alert('검색 중 오류가 발생했습니다.');
            });
    });

    // 전체 선택 체크박스 이벤트
    selectAllCheckbox.addEventListener("change", function () {
        const checkboxes = memberListBody.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });
});

// 페이지 변경 함수
window.changePage = function(page) {
    const formData = new FormData(document.getElementById('searchForm'));
    formData.append('page', page);
    formData.append('size', document.getElementById('itemsPerPage').value);

    fetch("/admin/user/board/list/data", {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.text();
        })
        .then(html => {
            // 임시 div를 생성하여 반환된 HTML을 파싱
            const tempDiv = document.createElement('div');
            tempDiv.innerHTML = html;

            // memberListBody 내용을 업데이트
            const memberListBody = document.getElementById('memberListBody');
            if (memberListBody) {
                memberListBody.innerHTML = tempDiv.querySelector('#memberListBody').innerHTML;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('데이터 로딩 중 오류가 발생했습니다.');
        });
};

// 신고 모달 열기
window.openReportModal = function(userId) {
    const reportModal = document.getElementById('reportModal');
    const reportTableBody = document.getElementById('reportTableBody');

    fetch(`/admin/user/board/reportDetails/${userId}`)
        .then(response => response.json())
        .then(reports => {
            reportTableBody.innerHTML = '';

            reports.forEach(report => {
                const row = document.createElement('tr');
                let banPeriod = '-';

                // 정지 상태 배지 설정
                const statusBadge = `<span class="status-badge ${
                    report.status === 'SUSPENDED' ? 'status-processed' : 'status-pending'
                }">${report.status === 'SUSPENDED' ? '정지' : '정상'}</span>`;

                // 정지 기간 설정
                if (report.status === 'SUSPENDED' && report.banStartDate && report.banEndDate) {
                    banPeriod = `${formatDateTime(report.banStartDate)} ~ ${formatDateTime(report.banEndDate)}`;
                }

                row.innerHTML = `
                    <td>${report.reportTitle || '-'}</td>
                    <td>${report.reportContent || '-'}</td>
                    <td>${formatDateTime(report.reportDate)}</td>
                    <td>${report.reporterName || '-'} (${report.reporterLoginId || '-'})</td>
                    <td>${report.reportedName || '-'} (${report.reportedLoginId || '-'})</td>
                    <td>${report.status}</td>
                    <td>${report.banStartDate ? new Date(report.banStartDate).toISOString()
                            .slice(0,19).replace('T', ' ') : '-'} ~
                    <br> 
                        (${report.banEndDate ? new Date(report.banEndDate).toISOString()
                            .slice(0,19).replace('T', ' ') : '-'})
                    </td>
                `;

                reportTableBody.appendChild(row);
            });

            reportModal.style.display = "block";
        })
        .catch(error => {
            console.error('Error:', error);
            alert('신고 내역을 불러오는데 실패했습니다.');
        });
};

// 날짜 포맷팅 함수
function formatDateTime(dateTime) {
    if (!dateTime) return '-';
    const date = new Date(dateTime);
    return new Intl.DateTimeFormat('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false
    }).format(date);
}

// 신고 모달 닫기
window.closeReportModal = function() {
    const reportModal = document.getElementById('reportModal');
    reportModal.style.display = "none";
};


// 포인트 수정
window.applyCustomPoints = function(userId) {
    const pointInput = document.getElementById(`point-${userId}`);
    const points = parseInt(pointInput.value, 10);

    if (isNaN(points)) {
        alert('포인트 값을 입력해주세요.');
        return;
    }

    fetch(`/admin/user/board/updatePoints/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ points: points })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert(`포인트가 성공적으로 수정되었습니다. 현재 포인트: ${data.newPoints}`);
                const pointsDisplay = document.querySelector(`#point-${userId}`).parentNode.previousElementSibling.querySelector('.current-points');
                pointsDisplay.textContent = data.newPoints;
                pointInput.value = '';
            } else {
                alert('포인트 수정에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('포인트 수정 중 오류가 발생했습니다.');
        });
};

// 활동 정지
window.applyBan = function(userId, days) {
    fetch(`/admin/user/board/banUser/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ banDays: days })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert(`${days}일 동안 사용자의 활동이 정지되었습니다.`);
            } else {
                alert('활동 정지 적용에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('활동 정지 처리 중 오류가 발생했습니다.');
        });
};

// 사용자 정의 정지 일수 적용
window.applyCustomBan = function(userId) {
    const banInput = document.getElementById(`ban-${userId}`);
    const days = banInput.value;

    if (!days) {
        alert('정지 일수를 입력해주세요.');
        return;
    }

    applyBan(userId, days);
    banInput.value = '';
};

// 모달 외부 클릭 시 닫기
window.onclick = function(event) {
    const reportModal = document.getElementById('reportModal');
    if (event.target === reportModal) {
        closeReportModal();
    }
};