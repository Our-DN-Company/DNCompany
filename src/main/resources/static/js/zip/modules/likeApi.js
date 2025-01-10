export function checkLike(zipId, callback) {
    fetch(`/api/v1/zips/${zipId}/likes/check`)
        .then(resp => {
            if (resp.ok){
                return resp.json();
            }
        })
        .then(data => callback(data));
}

export function toggleLike(zipId, callback) {
    fetch(`/api/v1/zips/${zipId}/likes`, {
        method: 'PUT'
    })
        .then(resp => {
            if (resp.ok){
                return resp.json();
            }
        })
        .then(data => callback(data));
}