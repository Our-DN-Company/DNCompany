// 등록사진 미리보기

document.addEventListener("DOMContentLoaded", function () {
  const fileInput = document.getElementById("mypage_add_pet_img");
  const previewContainer = document.getElementById(
    "mypage_add_pet_img_preview"
  );

  fileInput.addEventListener("change", function (event) {
    // 선택된 파일 가져오기
    const file = event.target.files[0];

    // 파일이 없는 경우 종료
    if (!file) {
      previewContainer.innerHTML = "";
      return;
    }

    // 파일 타입 체크 (이미지 파일만 허용)
    if (!file.type.startsWith("image/")) {
      alert("이미지 파일만 업로드 가능합니다.");
      fileInput.value = ""; // 입력값 초기화
      previewContainer.innerHTML = ""; // 미리보기 초기화
      return;
    }

    // FileReader로 이미지 읽기
    const reader = new FileReader();
    reader.onload = function (e) {
      // 미리보기 이미지 생성 및 삽입
      previewContainer.innerHTML = `<img src="${e.target.result}" alt="미리보기" style="max-width: 100%; max-height: 200px; display: block; margin: 10px 0;" />`;
    };

    // 파일 읽기 시작
    reader.readAsDataURL(file);
  });
});

//동물 종류 선택(직접입력칸 설정)

const speciesList = document.getElementById("addPetSpecieslist");
const speciesInput = document.querySelector(".addPetSpecies");

// select 값 변경 이벤트
speciesList.addEventListener("change", (event) => {
  const selectedValue = event.target.value; // 현재 선택된 값

  if (selectedValue === "other") {
    speciesInput.value = ""; // 입력값 초기화
    speciesInput.removeAttribute("disabled"); // 입력 활성화
    speciesInput.focus(); // 포커스 이동
  } else {
    speciesInput.value = event.target.options[event.target.selectedIndex].text; // 옵션 텍스트로 설정
    speciesInput.setAttribute("disabled", "true"); // 입력 비활성화
  }
});

//생일 등록후 자동으로 나이 설정

const birthDayInput = document.getElementById("addBrithDay");
const ageInput = document.getElementById("addAge");

// 생일 입력 시 나이 계산 함수
birthDayInput.addEventListener("change", () => {
  const birthDate = new Date(birthDayInput.value); // 입력된 생일
  const today = new Date(); // 현재 날짜

  if (!isNaN(birthDate)) {
    // 생일 값이 유효한 경우
    const yearsDifference = today.getFullYear() - birthDate.getFullYear(); // 연도 차이 계산
    const monthsDifference = today.getMonth() - birthDate.getMonth(); // 월 차이 계산
    const daysDifference = today.getDate() - birthDate.getDate(); // 일 차이 계산

    // 생일이 아직 안 지난 경우
    const isBeforeBirthdayThisYear =
      monthsDifference < 0 || (monthsDifference === 0 && daysDifference < 0);

    // 나이 계산
    let age = yearsDifference;
    if (isBeforeBirthdayThisYear) {
      age--; // 생일이 안 지났으면 나이를 1 감소
    }

    // 개월수 계산
    let months = monthsDifference;
    if (monthsDifference < 0) {
      months += 12; // 월 차이가 음수일 경우 1년(12개월)을 추가
    }
    if (daysDifference < 0) {
      months--; // 일 차이가 음수라면 개월수 감소
    }

    // 출력
    if (age < 1) {
      ageInput.value = `${months}개월`; // 1살 미만이면 개월수 출력
    } else {
      ageInput.value = `${age}살`; // 1살 이상이면 나이 출력
    }
  } else {
    ageInput.value = ""; // 생일 값이 유효하지 않을 경우 초기화
  }
});

{//TODO:이름, 반려동물 분류 문자열만 받기


}


