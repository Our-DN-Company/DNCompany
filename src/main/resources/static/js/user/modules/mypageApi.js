

export function getMypageZipList(page, callback) {
    fetch(`/api/v1/mypage/zips?page=${page}`)
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


export function getMypageDnSellList(page, callback) {
    fetch(`/api/v1/mypage/dn/sell?page=${page}`)
        .then(resp => {
            if(resp.ok){
                return resp.json();
            }
        })
        .then(data => {
            if (callback) {
                callback(data);
            }
        })
}

export function patchMypageDnSell(dnId, callback) {
    fetch(`/api/v1/mypage/dn/sell/${dnId}`, {
        method: 'PATCH',
    })
        .then(resp => {
            if (resp.ok && callback) {
                callback();
            }
        });
}

export function getMypageHelpList(page, callback) {
    fetch(`/api/v1/mypage/main?page=${page}`)
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