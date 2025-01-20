const mainHelpText = "도와드려요 & 도와드립니다";
const maintext = document.querySelector(".main__help__text");
let i = 0;

function typing() {
  if (i < mainHelpText.length) {
    const txt = mainHelpText[i++];
    maintext.innerHTML += txt === "\n" ? "<br/>" : txt;
  } else {
    // 타이핑이 끝난 후 멈춤
    setTimeout(() => {
      maintext.textContent = ""; // 초기화
      i = 0; // 인덱스 초기화
    }, 2000); // 2초 대기 후 초기화
    clearInterval(typingInterval); // 반복 멈춤
    setTimeout(startTyping, 2000); // 2초 후 다시 시작
  }
}

function startTyping() {
  typingInterval = setInterval(typing, 200); // 타이핑 시작
}

let typingInterval = setInterval(typing, 200); // 초기 타이핑 시작

// ===========================================

{ // 슬라이딩 배너
  let currentIndex = 0; // 현재 슬라이드 인덱스
  const slideBox = document.querySelector('.banner-img-box'); // 슬라이드 박스
  const slides = document.querySelectorAll('.banner-img-box img'); // 모든 슬라이드
  const totalSlides = slides.length; // 슬라이드 개수

// 슬라이드를 이동하는 함수
  function showSlide(index) {
    const slideWidth = document.querySelector('.banner-img-wrap').clientWidth; // 슬라이드 너비
    const translateX = -index * slideWidth; // 슬라이드 이동 거리 계산 (px 단위)
    slideBox.style.transform = `translateX(${translateX}px)`;
  }


// 이전 버튼 클릭 이벤트
  document.querySelector('.prev').addEventListener('click', () => {
    currentIndex = (currentIndex - 1 + totalSlides) % totalSlides; // 순환하여 이전 슬라이드로 이동
    showSlide(currentIndex);
  });

// 다음 버튼 클릭 이벤트
  document.querySelector('.next').addEventListener('click', () => {
    currentIndex = (currentIndex + 1) % totalSlides; // 순환하여 다음 슬라이드로 이동
    showSlide(currentIndex);
  });

// 자동 슬라이드
  setInterval(() => {
    currentIndex = (currentIndex + 1) % totalSlides; // 다음 슬라이드로
    showSlide(currentIndex);
  }, 5000);
}