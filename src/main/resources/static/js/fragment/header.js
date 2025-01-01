let gnb = document.querySelectorAll("#gnb > li");
let gnbElement = document.querySelector("#gnb");

for (let i = 0; i < gnb.length; i++) {
  gnb[i].addEventListener("mouseenter", () => {
    gnbElement.classList.add("on");
  });
}

let headerElement = document.querySelector("#header");

headerElement.addEventListener("mouseleave", () => {
  gnbElement.classList.remove("on");
});