import * as likeApi from "./modules/likeApi.js";

{
    const $favoriteBtn = document.querySelector('.favorite_button');
    const $deleteBtn = document.querySelector('.delete_Btn_item');

    $deleteBtn?.addEventListener('click', function () {
        if (confirm('정말 삭제하시겠습니까?')) {
            console.dir(this)
            const dnId = this.dataset.dnId;
            const productId = this.dataset.productId;
            location.href = `/dn/delete?dnId=${dnId}&productId=${productId}`;
        }
    });

    {// 진입과 동시에 좋아요 체크
        likeApi.checkLike(dnId, function (data) {
            console.log(data); // 진입과 동시에 어떤 데이터가 나오는지 확인하는 로그
            // 전달받은 데이터를 활용하여 좋아요 버튼의 상태와 좋아요 수 반영하기
            const $favoriteCount = document.querySelector('.favorite_count');
            $favoriteCount.textContent = data.likeCount; // 전달받은 data에 있는 likecount를 넘겨줌

            if(data.liked) {// trusy : 데이터가 있으면 true 없으면 false
                $favoriteBtn.classList.add('active');
            } else {
                $favoriteBtn.classList.remove('active');
            }

        })
    }

    // 좋아요 버튼 클릭
    $favoriteBtn.addEventListener('click', function () {
        // this.classList.toggle('active'); // classList는 클래스를 추가하는 것. 조건에 따라 active를 토글하도록 변경
        const $favoriteCount = this.querySelector('.favorite_count');
        // const currentCount = parseInt($likeCount.textContent); // 토글 처리할 때 사용한 코드

        // 서버에 좋아요 토글 처리, 반영된 결과 다시 조회해서 화면 처리
        likeApi.toggleLike(dnId, function (data) {
            console.log(data);
            if (!data.success) {
                console.log(data.message);
                alert(data.message);
                return;
            }

            if (data.liked) {
                $favoriteBtn.classList.add('active');
            } else {
                $favoriteBtn.classList.remove('active');
            }

            $favoriteCount.textContent = data.likeCount;

        })
    });
}