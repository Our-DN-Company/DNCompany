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
