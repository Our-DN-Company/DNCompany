// header.html 파일을 로드하여 #header__container에 삽입
document.addEventListener("DOMContentLoaded", function () {
  fetch("/common_header/html/header.html")
    .then((response) => response.text())
    .then((data) => {
      document.getElementById("header__container").innerHTML = data;
      // HTML이 로드된 후에 이벤트 리스너 추가
      let gnb = document.querySelectorAll("#gnb > li");
      let gnbElement = document.querySelector("#gnb");

      for (let i = 0; i < gnb.length; i++) {
        gnb[i].addEventListener("mouseover", () => {
          gnbElement.classList.add("on");
        });
      }

      let headerElement = document.querySelector("#header");

      header.addEventListener("mouseout", (e) => {
        if (e.target.id == "gnb") {
          gnbElement.classList.remove("on");
        }
      });
    });
});
