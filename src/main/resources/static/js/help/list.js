const $postList = document.querySelectorAll('.post-card');

$postList.forEach(post => {
    post.addEventListener('click', function () {
       const helpId = this.dataset.helpId;
        location.href=`/help/detail?helpId=${helpId}`;
    });
})
