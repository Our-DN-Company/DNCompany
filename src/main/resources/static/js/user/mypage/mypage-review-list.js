import * as mypageApi from '../modules/mypageApi.js';

{   // 모달창 처리
    const $table = document.querySelector('table');
    const $reviewModalBox = document.querySelector('.review-modal-box');

    $table.addEventListener('click', (e) => {
        const $tr = e.target.closest('tr');
        if ($tr) {
            const reviewId = $tr.dataset.reviewId;
            mypageApi.getMypageReviews(reviewId, function (data) {
                $reviewModalBox.style.display = 'block';
                console.log(data);

                const $title = document.querySelector('.review-modal__title');
                const $writer = document.querySelector('.review-modal__writer');
                const $content = document.querySelector('.review-modal__content');
                const $reviewModalEmoji = document.querySelector('.review-modal-emoji');

                let emoji = {
                    BAD : '🙄',
                    NORMAL : '🙂',
                    GOOD : '😍'
                }

                $title.textContent = data.reviewTitle;
                $writer.textContent = `${data.nickname}(${data.loginId})`;
                $content.textContent = data.reviewContent;
                $reviewModalEmoji.textContent = emoji[data.reviewAiAssessment];

            });
        }
    });

    const $closeBtn = document.querySelector('.review-modal-close-btn');

    // 모달 창 닫기 버튼 처리
    $closeBtn.addEventListener('click', (e) => {
        $reviewModalBox.style.display = 'none';
    });

    // 모달 창 영역이 아닌 곳을 클릭하면 닫기 처리
    document.addEventListener('click', (e) => {
        const $modalBox = e.target.closest('.review-modal-box');

        if ($modalBox) {
            return;
        }

        $reviewModalBox.style.display = 'none';
    });
}









