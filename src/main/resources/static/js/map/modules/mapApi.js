const apiKey = "bK/zV894lvdg4qwlgIFNtBcMtEezvGSAxBpys6P+jDDxruTsgVerEArOTQx9chRqOXdyeNITsDiHSP0F1Dn4Wg==";

// TODO : 반려동물 동반가능 시설 api 호출 필요
export function petServiceMapApi() {
    const url = `http://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc?page=1&perPage=10`;
    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': `Infuser ${apiKey}`,
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    })
        .then(resp => {
            if (resp.ok) {
                return resp.json()
            } else {
                console.log(resp)
                console.error('응답을 받지 못했습니다.', resp.statusText, resp.status);
            }
        })
        .then(data => {
            console.log(data)
            return data;
            })
        .catch(error => {
            console.error(error)
        })
}




