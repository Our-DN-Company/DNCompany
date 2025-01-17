{
    const $table = document.querySelector('table');

    $table.addEventListener('click', (e) => {
        const $tr = e.target.closest('tr');
        console.log(e.target)
        console.log($tr)
        if ($tr) {
            const dnId = $tr.dataset.dnId;
            location.href = `/dn/detail?dnId=${dnId}`;
        }
    })
}