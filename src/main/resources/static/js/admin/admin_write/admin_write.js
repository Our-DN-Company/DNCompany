document.getElementById('contentArea').addEventListener('paste', function (event) {
    event.preventDefault();

    const items = (event.clipboardData || event.originalEvent.clipboardData).items;
    for (const item of items) {
        if (item.type.startsWith('image/')) {
            const file = item.getAsFile();
            const reader = new FileReader();
            reader.onload = function (e) {
                const contentArea = document.getElementById('contentArea');

                // 이미지 래퍼 생성
                const imageWrapper = document.createElement('div');
                imageWrapper.classList.add('image-wrapper');

                // 이미지 컨테이너 생성
                const resizableDiv = document.createElement('div');
                resizableDiv.classList.add('resizable');

                // 이미지 추가
                const img = document.createElement('img');
                img.src = e.target.result;

                // 드래그 핸들 추가
                const handle = document.createElement('div');
                handle.classList.add('resize-handle');

                // DOM 구성
                resizableDiv.appendChild(img);
                resizableDiv.appendChild(handle);
                imageWrapper.appendChild(resizableDiv);

                // 이미지 위와 아래에 새 줄 추가
                const brBefore = document.createElement('br');
                const brAfter = document.createElement('br');
                contentArea.appendChild(brBefore);
                contentArea.appendChild(imageWrapper);
                contentArea.appendChild(brAfter);

                // 이미지 리사이즈 이벤트 적용
                applyResizeEvent(resizableDiv);

                // 이미지 아래로 커서 이동
                const range = document.createRange();
                const selection = window.getSelection();
                range.setStartAfter(brAfter);
                range.collapse(true);
                selection.removeAllRanges();
                selection.addRange(range);
            };
            reader.readAsDataURL(file);
        } else if (item.kind === 'string') {
            item.getAsString(function (text) {
                document.execCommand('insertText', false, text);
            });
        }
    }
});

function applyResizeEvent(container) {
    const img = container.querySelector('img');
    const handle = container.querySelector('.resize-handle');

    const controlsArea = document.createElement('div');
    controlsArea.classList.add('controls-area');
    container.appendChild(controlsArea);

    const alignmentControls = document.createElement('div');
    alignmentControls.classList.add('alignment-controls');
    alignmentControls.contentEditable = false;

    const fineLeftBtn = document.createElement('button');
    fineLeftBtn.innerHTML = '←';
    fineLeftBtn.title = '10px 왼쪽으로';
    fineLeftBtn.contentEditable = false;
    fineLeftBtn.onclick = (e) => {
        e.preventDefault();
        e.stopPropagation();
        const currentMargin = parseInt(container.parentElement.style.marginLeft || '0');
        container.parentElement.style.marginLeft = `${currentMargin - 10}px`;
    };

    const leftBtn = document.createElement('button');
    leftBtn.innerHTML = '◀';
    leftBtn.title = '왼쪽 정렬';
    leftBtn.contentEditable = false;
    leftBtn.onclick = (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.parentElement.style.justifyContent = 'flex-start';
        container.parentElement.style.marginLeft = '0';
    };

    const centerBtn = document.createElement('button');
    centerBtn.innerHTML = '•';
    centerBtn.title = '가운데 정렬';
    centerBtn.contentEditable = false;
    centerBtn.onclick = (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.parentElement.style.justifyContent = 'center';
        container.parentElement.style.marginLeft = '0';
    };

    const rightBtn = document.createElement('button');
    rightBtn.innerHTML = '▶';
    rightBtn.title = '오른쪽 정렬';
    rightBtn.contentEditable = false;
    rightBtn.onclick = (e) => {
        e.preventDefault();
        e.stopPropagation();
        container.parentElement.style.justifyContent = 'flex-end';
        container.parentElement.style.marginLeft = '0';
    };

    const fineRightBtn = document.createElement('button');
    fineRightBtn.innerHTML = '→';
    fineRightBtn.title = '10px 오른쪽으로';
    fineRightBtn.contentEditable = false;
    fineRightBtn.onclick = (e) => {
        e.preventDefault();
        e.stopPropagation();
        const currentMargin = parseInt(container.parentElement.style.marginLeft || '0');
        container.parentElement.style.marginLeft = `${currentMargin + 10}px`;
    };

    alignmentControls.appendChild(fineLeftBtn);
    alignmentControls.appendChild(leftBtn);
    alignmentControls.appendChild(centerBtn);
    alignmentControls.appendChild(rightBtn);
    alignmentControls.appendChild(fineRightBtn);
    controlsArea.appendChild(alignmentControls);

    let isOverControls = false;
    let isOverImage = false;
    let hideTimeout;

    const showControls = () => {
        clearTimeout(hideTimeout);
        alignmentControls.style.display = 'flex';
    };

    const hideControls = () => {
        if (!isOverControls && !isOverImage) {
            hideTimeout = setTimeout(() => {
                alignmentControls.style.display = 'none';
            }, 200);
        }
    };

    img.addEventListener('mouseenter', () => {
        isOverImage = true;
        showControls();
    });

    img.addEventListener('mouseleave', () => {
        isOverImage = false;
        hideControls();
    });

    alignmentControls.addEventListener('mouseenter', () => {
        isOverControls = true;
        showControls();
    });

    alignmentControls.addEventListener('mouseleave', () => {
        isOverControls = false;
        hideControls();
    });

    controlsArea.addEventListener('mouseenter', () => {
        isOverImage = true;
        showControls();
    });

    controlsArea.addEventListener('mouseleave', () => {
        isOverImage = false;
        hideControls();
    });

    let originalWidth, originalHeight, originalX, originalY;

    handle.addEventListener('mousedown', function (event) {
        event.preventDefault();
        originalWidth = img.offsetWidth;
        originalHeight = img.offsetHeight;
        originalX = event.pageX;
        originalY = event.pageY;

        function resize(event) {
            const deltaX = event.pageX - originalX;
            const newWidth = originalWidth + deltaX;
            const newHeight = (originalHeight / originalWidth) * newWidth;
            img.style.width = `${newWidth}px`;
            img.style.height = `${newHeight}px`;
        }

        function stopResize() {
            window.removeEventListener('mousemove', resize);
            window.removeEventListener('mouseup', stopResize);
        }

        window.addEventListener('mousemove', resize);
        window.addEventListener('mouseup', stopResize);
    });
}

document.getElementById('contentArea').addEventListener('keydown', function(e) {
    if (e.key === 'Backspace' || e.key === 'Delete') {
        const selection = window.getSelection();
        const alignmentControls = document.querySelectorAll('.alignment-controls');
        alignmentControls.forEach(control => {
            if (control === e.target || control.contains(e.target)) {
                e.preventDefault();
                return;
            }
        });
    }
});
