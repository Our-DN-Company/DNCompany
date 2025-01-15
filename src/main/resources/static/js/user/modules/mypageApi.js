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