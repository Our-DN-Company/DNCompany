function applyForCare(helpId) {
    // helpId가 유효한지 확인
    if(!helpId) {
        console.error('helpId is undefined:', helpId);
        alert('오류가 발생했습니다.');
        return;
    }

    if(confirm('돌봄을 신청하시겠습니까?')) {
        fetch(`/help/offer/${helpId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.text())
            .then(message => {
                alert(message);
                location.reload();
            })
            .catch(error => {
                console.error('신청 실패:', error);
                alert('신청 처리 중 오류가 발생했습니다.');
            });
    }
}
function deleteHelpBoard(helpId, usersId) {
    if(confirm('정말 삭제하시겠습니까?')) {
        fetch(`/api/help/${helpId}/user/${usersId}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                if(data.status === 'success') {
                    alert('삭제되었습니다.');
                    // 삭제된 행만 화면에서 제거
                    const deletedRow = document.querySelector(`tr[data-help-id="${helpId}"]`);
                    if(deletedRow) {
                        deletedRow.remove();
                    }
                } else {
                    alert(data.message || '삭제에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('삭제 중 오류가 발생했습니다.');
            });
    }
}