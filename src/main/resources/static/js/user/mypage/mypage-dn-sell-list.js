import * as mypageApi from '../modules/mypageApi.js'

{
    mypageApi.getMypageDnSellList(1, function (data) {
        console.log(data);
        displayDnSellList(data.list);
        makeDnSellPageGroup(data);   });

    const $pageGroupContainer = document.querySelector('.dn-sell-list-pagination');

    $pageGroupContainer.addEventListener('click', function (e) {
        if (e.target.tagName === 'SPAN'){
            const page = e.target.dataset.page;

            mypageApi.getMypageDnSellList(page, function (data) {
                displayDnSelldList(data.list);
                makeDnSellPageGroup(data);
            });
        }

    });

}


function displayDnSellList(dnSellList) {
    console.log('displayDnSellList', dnSellList);

    let html = '';

    dnBoardList.forEach(zip => {
        html += `
             <tr>
                    <td>${dnSellList.rnum}</td>
                    <td>${dnSellList.productName}</td>
                    <td>${dnSellList.productPrice}</td>

                </tr>
        `;
    });

    document.querySelector('.dn-sell-list-tbody').innerHTML = html;
}

function makeDnBoardPageGroup(pageDTO) {

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


    document.querySelector('.dn-sell-list-pagination').innerHTML = html;
}





