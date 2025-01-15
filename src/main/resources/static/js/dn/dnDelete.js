const $deleteBtn = document.querySelector('.delete_Btn_item');

$deleteBtn?.addEventListener('click', function () {
    if (confirm('정말 삭제하시겠습니까?')) {
        console.dir(this)
        const dnId = this.dataset.dnId;
        const productId = this.dataset.productId;
        location.href = `/dn/delete?dnId=${dnId}&productId=${productId}`;
    }
});