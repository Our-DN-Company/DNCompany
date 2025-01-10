export function postAnswer(zipId, answerObj, callback) {
    fetch(`/api/v1/zips/${zipId}/answers`, {
        method: "POST",
        headers : {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(answerObj)
    })
        .then(resp => {
            if (resp.ok && callback) {
                callback();
            }
        })
}

export function getAnswerList(zipId, callback) {
    fetch(`/api/v1/zips/${zipId}/answers`)
        .then(resp => {
            if (resp.ok){
                return resp.json();
            }
        })
        .then(data => {
            if (callback) {
                callback(data);
            }
        })
}

export function patchAnswer(answerId, answerObj, callback) {
    fetch(`/api/v1/answers/${answerId}`, {
        method: "PATCH",
        headers : {'Content-Type': 'application/json'},
        body: JSON.stringify(answerObj)
    })
        .then(resp => {
            if (resp.ok && callback) {
                callback();
            }
        })
}

export function deleteAnswer(answerId, callback) {
    fetch(`/api/v1/answers/${answerId}`, {
        method: "DELETE",
    })
        .then(resp => {
            if (resp.ok && callback) {
                callback();
            }
        })
}