// 폼 유효성 검사
const form = document.querySelector('form');
form.addEventListener('submit', function (e) {
    const title = document.querySelector('input[name="eventTitle"]');
    const content = document.querySelector('textarea[name="eventContent"]');

    if (title.value.trim().length === 0) {
        e.preventDefault();
        alert('제목을 입력해주세요.');
        title.focus();
        return;
    }

    if (content.value.trim().length === 0) {
        e.preventDefault();
        alert('내용을 입력해주세요.');
        content.focus();
        return;
    }
});

// 이미지 업로드 및 미리보기
const uploadFile = document.getElementById('uploadFile');
const imagePreview = document.getElementById('imagePreview');
const imgContainer = document.querySelector('.admin_event_imgContainer');

uploadFile.addEventListener('change', function () {
    const file = this.files[0];
    if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            img.style.maxWidth = '200px';
            img.style.maxHeight = '200px';
            imagePreview.innerHTML = '';
            imagePreview.appendChild(img);
        };
        reader.readAsDataURL(file);
    }
});

// 드래그 앤 드롭
imgContainer.addEventListener('dragover', function (e) {
    e.preventDefault();
    this.style.borderColor = '#007bff';
    this.style.backgroundColor = '#f8f9fa';
});

imgContainer.addEventListener('dragleave', function (e) {
    e.preventDefault();
    this.style.borderColor = '#868688';
    this.style.backgroundColor = '#fff';
});

imgContainer.addEventListener('drop', function (e) {
    e.preventDefault();
    this.style.borderColor = '#868688';
    this.style.backgroundColor = '#fff';

    const file = e.dataTransfer.files[0];
    if (file && file.type.startsWith('image/')) {
        uploadFile.files = e.dataTransfer.files;
        const event = new Event('change');
        uploadFile.dispatchEvent(event);
    }
});





// 서머노트 설정
$('#contentArea').summernote({
    placeholder: '5자 이상의 글 내용을 입력해주세요.',
    height: 300,
    tabsize: 2,
    toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'underline', 'clear']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture']],
        ['view', ['fullscreen', 'codeview', 'help']]
    ],
    callbacks: {
        onImageUpload: function(files) {
            for(let file of files) {
                uploadSummernoteImage(file, this);
            }
        },
        onPaste: function (e) {
            var clipboardData = e.originalEvent.clipboardData;
            if (clipboardData && clipboardData.items && clipboardData.items.length) {
                var item = clipboardData.items[0];
                if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
                    e.preventDefault();
                }
            }
        }
    },
    disableDragAndDrop: true,
    codeviewFilter: true,
    codeviewIframeFilter: true
});

function uploadSummernoteImage(file, editor) {
    console.log('Uploading image:', file.name);  // 파일 정보 로그
    const formData = new FormData();
    formData.append("file", file);

    fetch('/admin/api/upload/summernote', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            console.log('Upload response status:', response.status);  // 응답 상태 로그
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Upload success:', data);  // 성공 데이터 로그
            const imageUrl = data.url;
            $(editor).summernote('insertImage', imageUrl, function ($image) {
                $image.css('max-width', '100%');
            });
        })
        .catch(error => {
            console.error('Upload error:', error);  // 에러 로그
            console.error('이미지 업로드 실패:', error);
            alert('이미지 업로드에 실패했습니다.');
        });
}