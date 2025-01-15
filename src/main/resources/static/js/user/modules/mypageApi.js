

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

export function getMypageDnBoardList(page, callback) {
    fetch(`/api/v1/mypage/dn/board?page=${page}`)
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