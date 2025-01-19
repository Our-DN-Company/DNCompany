
export function petServiceMapApi(callback) {
    fetch('/api/v1/pet-service')
        .then(resp => {
            if (resp.ok) {
                return resp.json()
            } else {
                console.error('응답을 받지 못했습니다.', resp.statusText, resp.status);
            }
        })
        .then(data => {
            if (callback) {
                console.log(data)
                callback(data);
            } else {
                console.error('콜백 함수를 호출하지 못했습니다.', data);
            }
        })
        .catch(error => {
            console.error(error)
        })
}




