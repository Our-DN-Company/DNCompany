export function scheduleList(usersId, callback) {

// 아래 url로 요청을 보낸다. 그러면 우리 서버가 응답을 하고 응답 받은걸 첫번째 then에 있는 resp에 받게됨
// 응답의 body에 담긴 데이터를 json으로 변환 후 다음 then으로 넘겨준다.
// 이유는? 값이 단일 값이면 다음메서드로 넘겨주는 체이닝 방식이기 때문!!
// 2번째 then에는 1번째 then에서 넘어온 resp 값이 전달되고 이를 매개변수로 받아주면
// 해당 매개변수에는 json 데이터가 들어온다.
// 그 후 활용하면 된다.
    fetch(`/api/v1/users/schedules/${usersId}`)
        .then(resp => {
            if(resp.ok){
                return resp.json();
            } else {
                console.error('응답 받지 못함', resp.status, resp.statusText);
            }
        })
        .then(data => {
            if(callback){
                callback(data);
            } else {
                console.error('callback 함수 전달 실패', data);
            }
        })
        .catch(error => {
            console.error('Error during fetch:', error);
        })
}

