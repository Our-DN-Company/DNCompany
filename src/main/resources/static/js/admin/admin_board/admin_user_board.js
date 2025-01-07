document.addEventListener("DOMContentLoaded", function () {
    // 기존 변수 선언
    const itemsPerPageSelect = document.getElementById('itemsPerPage');
    const searchForm = document.getElementById('searchForm');
    const selectAllCheckbox = document.getElementById('selectAll');
    const memberListBody = document.getElementById('memberListBody');
    const reportModal = document.getElementById('reportModal');
    const reportTableBody = document.getElementById('reportTableBody');

    // 기존 이벤트 리스너들 유지
    itemsPerPageSelect.addEventListener("change", function () {
        searchForm.submit();
    });

    searchForm.addEventListener("submit", function (e) {
        e.preventDefault();
        const formData = new FormData(searchForm);

        // FormData를 서버에 전송
        fetch("/admin/user/board/list/data", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
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

    selectAllCheckbox.addEventListener("change", function () {
        const checkboxes = memberListBody.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });
});

// 전역 함수들을 window 객체에 명시적으로 할당
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
                    <td>${statusBadge}</td>
                    <td>${banPeriod}</td>
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


window.closeReportModal = function() {
    const reportModal = document.getElementById('reportModal');
    reportModal.style.display = "none";
};

window.processReport = function(reportId, banDays) {
    fetch(`/admin/user/board/processReport/${reportId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ banDays: banDays })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert(`신고가 처리되었습니다. ${banDays}일 정지가 적용되었습니다.`);
                openReportModal(data.userId);
            } else {
                alert('신고 처리에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('신고 처리 중 오류가 발생했습니다.');
        });
};

window.applyCustomPoints = function(userId) {
    const pointInput = document.getElementById(`point-${userId}`);
    const points = pointInput.value;

    if (!points) {
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
                alert(`포인트가 성공적으로 수정되었습니다.`);
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