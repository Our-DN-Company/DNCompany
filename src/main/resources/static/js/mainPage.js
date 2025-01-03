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
