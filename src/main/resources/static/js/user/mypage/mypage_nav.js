let gnb = document.querySelectorAll("#gnb__myP > li");
let gnbElement = document.querySelector("#gnb__myP");

// 'mouseover' 이벤트: 메뉴 항목에 마우스를 올리면 하위 메뉴 표시
for (let i = 0; i < gnb.length; i++) {
  gnb[i].addEventListener("mouseover", () => {
    gnb[i].classList.add("on");
  });

  // 'mouseout' 이벤트: 메뉴 항목에서 마우스를 떼면 하위 메뉴 숨기기
  gnb[i].addEventListener("mouseout", () => {
    gnb[i].classList.remove("on");
  });
}

// 전체 메뉴에서 마우스를 떼면 하위 메뉴가 사라지도록 처리
let myPageElement = document.querySelector("#myPage");

myPageElement.addEventListener("mouseout", (e) => {
  // 전체 메뉴에서 마우스를 떼면 하위 메뉴가 사라지도록 처리
  if (!gnbElement.contains(e.relatedTarget)) {
    gnb.forEach((item) => {
      item.classList.remove("on");
    });
  }
});
