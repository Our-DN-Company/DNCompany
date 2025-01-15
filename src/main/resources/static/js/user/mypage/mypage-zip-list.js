import * as mypageApi from '../modules/mypageApi.js'

{
    mypageApi.getMypageZipList(1, function (data) {
        console.log(data);
        displayZipList(data.list);
        makeZipPageGroup(data);
    });

    const $pageGroupContainer = document.querySelector('.zip-list-pagination');

    $pageGroupContainer.addEventListener('click', function (e) {
        if (e.target.tagName === 'SPAN'){
            const page = e.target.dataset.page;

            mypageApi.getMypageZipList(page, function (data) {
                displayZipList(data.list);
                makeZipPageGroup(data);
            });
        }

    });
}

function displayZipList(zipList) {
    console.log('displayZipList', zipList);

    let html = '';

    zipList.forEach(zip => {
        html += `
             <tr onclick="location.href='/zip/detail?zipId=${zip.zipId}'">
                <td>${zip.rnum}</td>
                <td>${zip.zipTitle}</td>
                <td>${zip.zipCreatedAt}</td>
            </tr>
        `;
    });

    document.querySelector('.zip-list-tbody').innerHTML = html;
}

function makeZipPageGroup(pageDTO) {

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


    document.querySelector('.zip-list-pagination').innerHTML = html;
}

{

}












