document.addEventListener("DOMContentLoaded", () => {
  const petLists = document.querySelectorAll(".mypage_main_pet_list");
  const prevButton = document.querySelector(".mypage_main_pet_button_left");
  const nextButton = document.querySelector(".mypage_main_pet_button_right");
  let currentIndex = 0;

  // 초기 활성화
  petLists[currentIndex].classList.add("active");

  // 이전 버튼 클릭 이벤트
  prevButton.addEventListener("click", () => {
    petLists[currentIndex].classList.remove("active");
    currentIndex = (currentIndex - 1 + petLists.length) % petLists.length;
    petLists[currentIndex].classList.add("active");
  });

  // 다음 버튼 클릭 이벤트
  nextButton.addEventListener("click", () => {
    petLists[currentIndex].classList.remove("active");
    currentIndex = (currentIndex + 1) % petLists.length;
    petLists[currentIndex].classList.add("active");
  });
});
