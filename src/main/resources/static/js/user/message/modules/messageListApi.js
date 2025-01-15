export function patchMessageListTo(usersId, messageListObj, callback) {
    fetch(`/api/v1/messagelist-To/${usersId}"`, {
        method: 'PATCH',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(messageListObj)
    })
        .then(resp => {
            if (resp.ok && callback) {
                callback();
            }
        });
}

export async function patchMessageListFrom(usersId, messageListObj, callback) {
    try {
        const response = await fetch(`/api/v1/messagelist-From/${usersId}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(messageListObj),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        // 서버 응답 데이터 처리 (필요한 경우)
        const responseData = await response.json();

        // 콜백 함수 호출 (응답 데이터를 전달할 수도 있음)
        if (callback) {
            callback(responseData);
        }
    } catch (error) {
        console.error('Failed to patch message list:', error);
        // 추가적인 에러 처리 (알림 표시 등)
    }
}


