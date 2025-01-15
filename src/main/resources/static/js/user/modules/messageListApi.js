export function getMessageListTo(page, callback) {
    fetch(`/api/v1/message/listTos?page=${page}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            }
        })
            .then(data => {
                if (callback) {
                    callback(data);
                }
            });
}

export function getMessageListFrom(page, callback) {
    fetch(`/api/v1/message/listFroms?page=${page}`)
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            }
        })
        .then(data => {
            if (callback) {
                callback(data);
            }
        });
}


export function deleteMessage(messageId, callback) {
    fetch(`/api/v1/message/${messageId}`, {
        method: 'DELETE',
    })
        .then(resp => {
            if (resp.ok && callback) {
                callback();
            }
        })
}
