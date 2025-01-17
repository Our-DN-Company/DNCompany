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
            }
        })
        .then(data => callback(data));
}
