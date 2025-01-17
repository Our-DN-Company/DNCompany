import * as mypageApi from '../modules/mypageApi.js'

{   // 판매 리스트 / 페이징 처리
    loadSellList(1);

    const $pageGroupContainer = document.querySelector('.dn-sell-list-pagination');

    $pageGroupContainer.addEventListener('click', function (e) {
        if (e.target.tagName === 'SPAN'){
            const page = e.target.dataset.page;

            loadSellList(page);
        }

    });
}

/**
 * 판매 내역 / 페이징 로드 함수
 * 
 * @param page
 */
function loadSellList(page) {
    mypageApi.getMypageDnSellList(page, function (data) {
        // console.log(data);
        const $sellListContainer = document.querySelector('.dn-sell-list-tbody');
        const $sellPageGroupContainer = document.querySelector('.dn-sell-list-pagination');
        displayDnList(data.list, $sellListContainer);
        makeDnPageGroup(data, $sellPageGroupContainer);
    });
}

// /**
//  * 구매 내역 / 페이징 로드 함수
//  *
//  * @param page
//  */
// function loadBuyList(page) {
//     mypageApi.getMypageDnSellList(page, function (data) {
//         // console.log(data);
//         const $buyListContainer = document.querySelector('.dn-buy-list-tbody');
//         const $buyPageGroupContainer = document.querySelector('.dn-buy-list-pagination');
//         displayDnList(data.list, $buyListContainer);
//         makeDnPageGroup(data, $buyPageGroupContainer);
//     });
// }

/**
 * 내역 리스트 화면 출력 함수
 * 
 * @param dnList
 * @param $listContainer - 내역 리스트를 삽입할 DOM 요소
 */
function displayDnList(dnList, $listContainer) {
    let html = '';

    dnList.forEach(dn => {
        html += `
             <tr class="dn-history-list">
                 <td>${dn.rnum}</td>
                 <td>${dn.productCategory}</td>
                 <td>
                    <div class="dn-product-info-container">
                        <div class="dn-product-img-wrap">
                        `;

        if (dn.productImgId) {
            html += `
                        <img src="/upload/dn/${dn.productPath}/${dn.productUuid}${dn.productExtension}" alt="">
                `;
        } else {
            html += `
                        <img src="/images/fragment/logo_header4.png" alt="">
                `;
        }

        html += `                      
                        </div>
                        <div class="dn-product-content-wrap">
                            <p><span class="dn-product-info-title">상품명 : </span><span
                                    class="dn-product-info-content">${dn.productName}</span></p>
                            <p><span class="dn-product-info-title">가격 : </span><span
                                    class="dn-product-info-content"> <span>${dn.productPrice}</span> 원</span></p>
                        </div>
    
                    </div>
                </td>
                <td>
                ${dn.dnStatus == 'PENDING' ? '대기' : '판매 완료'}
                </td>
            </tr>
        `;
    });

    $listContainer.innerHTML = html;
}

/**
 * 페이지네이션 그룹 생성 함수
 * 
 * @param pageDTO
 * @param $pageGroupContainer - 페이지 그룹을 삽입할 DOM객체
 */
function makeDnPageGroup(pageDTO, $pageGroupContainer) {
    let html = ``;

    if (pageDTO.startPage > 1) {
        html += `
        <span class="page-btn prev" aria-label="Go to previous group" data-page="${pageDTO.startPage - 1}">‹</span>
        `;
    }

    for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
        html += `
        <span class="page-btn ${pageDTO.page == i ? 'active' : ''}" data-page="${i}">${i}</span>
        `;
    }

    if (pageDTO.endPage < pageDTO.totalPages){
        html += `
            <span class="page-btn next" aria-label="Go to next group" data-page="${pageDTO.endPage+1}">›</span>
        `;
    }

    $pageGroupContainer.innerHTML = html;
}







