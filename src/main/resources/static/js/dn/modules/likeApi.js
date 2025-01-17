export function checkLike(dnId, callback) {
    fetch(`/api/v1/dn-markets/${dnId}/likes/check`)
        .then(res => {
            if (res.ok) {
                return res.json();
            }
        })
        .then(data => callback(data));
}

export function toggleLike(dnId, callback) {
    fetch(`/api/v1/dn-markets/${dnId}/likes`, {
        method:'PUT'
    })
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                throw new Error('Authorization required'); // 실패 시 에러를 던짐
            }
        })
        .then(data => {
            callback(data)
        })
    .catch(err => {
        alert("로그인이 필요한 서비스입니다."); // 알림 표시
        window.location.href = "/user/login"; // 로그인 페이지로 이동
    });
}
