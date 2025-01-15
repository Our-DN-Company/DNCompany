import * as mypageApi from '../modules/mypageApi.js'

{
    mypageApi.getMypageDnBoardList(1, function (data) {
        console.log(data);
        displayDnBoardList(data.list);
        makeDnBoardPageGroup(data);   });

    const $pageGroupContainer = document.querySelector('.dn-board-list-pagination');

    $pageGroupContainer.addEventListener('click', function (e) {
        if (e.target.tagName === 'SPAN'){
            const page = e.target.dataset.page;

            mypageApi.getMypageDnBoardList(page, function (data) {
                displayDnBoardList(data.list);
                makeDnBoardPageGroup(data);
            });
        }

    });

}


function displayDnBoardList(dnBoardList) {
    console.log('displayDnBoardList', dnBoardList);

    let html = '';

    dnBoardList.forEach(zip => {
        html += `
              <tr>
                    <td>${dnBoardList.rnum}</td>
                    <td>${dnBoardList.dnProductCategory}</td>
                    <td >${dnBoardList.dnTitle}</td>
                </tr>
        `;
    });

    document.querySelector('.dn-board-list-tbody').innerHTML = html;
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


    document.querySelector('.dn-board-list-pagination').innerHTML = html;
}





