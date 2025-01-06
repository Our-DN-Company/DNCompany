import * as smsApi from './modules/smsApi.js';
import * as membersApi from './modules/userApi.js';


// 입력 필드와 메시지 DOM 요소 가져오기
// 아이디
const elInputId = document.querySelector("#memberId");
const elFailureMessageOneId = document.querySelector(".id__notmessage1");
const elFailureMessageTwoId = document.querySelector(".id__notmessage2");
const elSuccessMessageId = document.querySelector(".id__okmessage");

// 이미 존재하는 아이디 목록 (예시)
const existingIds = ["test123", "user456", "admin789"];

// 유효성 검사 함수
function validateUsername(userid) {
  const regexid = /^[A-Za-z][A-Za-z0-9]{5,15}$/; // 영문 시작, 6~16자, 숫자 포함 가능
  return regexid.test(userid);
}

// 입력 이벤트 핸들러
elInputId.addEventListener("keyup", function () {
  const value = elInputId.value;

  // 모든 메시지 숨김 초기화
  elFailureMessageOneId.classList.remove("active");
  elFailureMessageTwoId.classList.remove("active");
  elSuccessMessageId.classList.remove("active");

  // 입력값이 없는 경우
  if (!value) {
    return;
  }

  // 입력값이 이미 존재하는 아이디인 경우
  if (existingIds.includes(value)) {
    elFailureMessageTwoId.classList.add("active");
  }
  // 아무 단어라도 입력했을 때
  else if (!validateUsername(value)) {
    elFailureMessageOneId.classList.add("active");
  }
  // 유효성 검사를 통과하고, 중복되지 않은 경우
  else {
    elSuccessMessageId.classList.add("active");
  }
});

// 비밀번호
// 비밀번호 입력 필드와 메시지 DOM 요소 가져오기
const elInputPassword = document.querySelector("#password");
const elFailureMessageOnePw = document.querySelector(".pw__notmessage1");
const elFailureMessageTwoPw = document.querySelector(".pw__notmessage2");

// 비밀번호 유효성 검사 함수
function validateUserpassword(password) {
  const regex = /^[a-zA-Z0-9!@#$%^&*()?_~]{10,}$/; // 최소 10자 이상
  return regex.test(password);
}

// 비밀번호 복합성 검사 함수
function checkPasswordComplexity(password) {
  let count = 0;
  if (/[0-9]/.test(password)) count++; // 숫자 포함 여부
  if (/[a-zA-Z]/.test(password)) count++; // 영문 포함 여부
  if (/[!@#$%^&*()?_~]/.test(password)) count++; // 특수문자 포함 여부
  return count >= 2; // 2가지 이상 조합
}

// 입력 이벤트 핸들러
elInputPassword.addEventListener("keyup", function () {
  const value = elInputPassword.value;

  // 모든 메시지 숨김 초기화
  elFailureMessageOnePw.classList.remove("active");
  elFailureMessageTwoPw.classList.remove("active");

  // 입력값이 없는 경우
  if (!value) {
    return;
  }

  // 최소 10자 이상이 아닌 경우
  if (!validateUserpassword(value)) {
    elFailureMessageOnePw.classList.add("active");
  }
  // 최소 10자는 만족했으나 복합성을 만족하지 않는 경우
  else if (!checkPasswordComplexity(value)) {
    elFailureMessageTwoPw.classList.add("active");
  }
});

// 비밀번호 확인
const elInputPwConfirm = document.querySelector("#passwordConfirm");
const elFailureMessageOnePwCon = document.querySelector(".pw__confirmmessage1");
const elFailureMessageTwoPwCon = document.querySelector(".pw__confirmmessage2");

// 비밀번호 확인 입력 이벤트 핸들러
elInputPwConfirm.addEventListener("keyup", function () {
  const password = elInputPassword.value;
  const confirmPassword = elInputPwConfirm.value;

  // 비밀번호 확인 필드와 메시지 숨김 초기화
  elFailureMessageOnePwCon.classList.remove("active");
  elFailureMessageTwoPwCon.classList.remove("active");

  // 비밀번호가 입력되지 않았을 때는 확인 비밀번호 메시지를 표시하지 않음
  if (!password && confirmPassword) {
    elFailureMessageTwoPwCon.classList.add("active"); // 비밀번호가 없으면 "동일한 비밀번호를 입력" 메시지를 표시
    return;
  }

  // 비밀번호 확인이 비어있는 경우
  if (!confirmPassword) {
    elFailureMessageOnePwCon.classList.add("active"); // 비밀번호 확인 필드가 비어있으면 "비밀번호를 한번 더 입력해 주세요" 메시지를 표시
  }
  // 비밀번호 확인이 비밀번호와 다를 경우
  else if (password !== confirmPassword) {
    elFailureMessageTwoPwCon.classList.add("active"); // 두 비밀번호가 다르면 "동일한 비밀번호를 입력" 메시지를 표시
  }
});

// 이름
// 이름 입력 필드와 메시지 DOM 요소 가져오기
const elInputName = document.querySelector("#name");
const elFailureMessageOneName = document.querySelector(".name__message");

// 이름 입력 이벤트 핸들러
elInputName.addEventListener("input", function (e) {
  // 입력이 비어있지 않으면 메시지 숨기기
  if (e.target.value.trim() !== "") {
    elFailureMessageOneName.classList.remove("active");
  } else {
    // 입력이 비어있으면 메시지 표시
    elFailureMessageOneName.classList.add("active");
  }
});


const domainEl = document.querySelector(".info__box__email__btn__select");
const domainListEl = document.querySelector(".info__dropdown__email");
const emailTxt = document.getElementById("emailTxt");
const emailId = document.getElementById("emailId");
const emailField = document.getElementById("email");
const massageemailNot1 = document.querySelector(".email__notmessage1");
const massageemailNot2 = document.querySelector(".email__notmessage2");
const messageOk = document.querySelector(".email__okmessage");

function isValidEmail(email) {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailRegex.test(email.trim()); // 공백 제거 후 검사
}

let isActiveDomainList = false;

// 드롭다운 토글
domainEl.addEventListener("click", function () {
  isActiveDomainList = !isActiveDomainList; // toggle active state
  if (isActiveDomainList) {
    domainListEl.classList.add("active1"); // show the dropdown list
  } else {
    domainListEl.classList.remove("active1"); // hide the dropdown list
  }
});

// 도메인 버튼 클릭 시
function btnClick(domain) {
  if (!domain) {
    console.error("No domain passed to btnClick");
    return;
  }
  console.log("Selected domain:", domain); // 전달된 도메인 확인
  emailTxt.value = domain.trim(); // 도메인 설정
  emailTxt.disabled = true; // 수정 불가 상태로 변경
  domainListEl.classList.remove("active1"); // 드롭다운 숨기기
  console.log("emailTxt.value after setting:", emailTxt.value); // emailTxt 값 확인
  validateEmail(); // 이메일 유효성 검사 실행
}

// 입력 가능 상태로 전환 (직접 입력 모드)
function enableEditing() {
  console.log("Switching to manual input mode");
  emailTxt.value = ""; // 현재 값 지우기
  emailTxt.placeholder = "입력하기"; // placeholder 설정
  emailTxt.disabled = false; // 수정 가능 상태로 변경
  domainListEl.classList.remove("active1"); // 드롭다운 숨기기
}

// 이메일 ID와 도메인 입력 시 유효성 검사
emailId.addEventListener("input", validateEmail);
emailTxt.addEventListener("input", validateEmail); // 도메인 변경 시도 유효성 검사

function validateEmail() {
  const domain = emailTxt.value.trim();
  const localPart = emailId.value.trim();
  const emailValue = localPart + (domain ? "@" + domain : "");

  console.log("emailId.value:", localPart); // emailId 값 확인
  console.log("emailTxt.value:", domain); // emailTxt 값 확인
  console.log("Generated Email Value:", emailValue); // 생성된 전체 이메일 확인

  emailField.value = emailValue;

  // 이메일 ID가 비어있는 경우
  if (!localPart) {
    massageemailNot1.style.display = "block"; // 이메일을 입력해 주세요.
    massageemailNot2.style.display = "none"; // 이메일 형식으로 입력해 주세요.
    messageOk.style.display = "none"; // 사용 가능한 이메일입니다.
    return;
  }

  // 도메인이 비어있는 경우
  if (!domain) {
    massageemailNot1.style.display = "none";
    massageemailNot2.style.display = "block"; // 이메일 형식으로 입력해 주세요.
    messageOk.style.display = "none";
    return;
  }

  // 이메일 형식이 유효하지 않은 경우
  if (!isValidEmail(emailValue)) {
    massageemailNot1.style.display = "none";
    massageemailNot2.style.display = "block"; // 이메일 형식으로 입력해 주세요.
    messageOk.style.display = "none";
    return;
  }

  // 유효한 이메일인 경우
  massageemailNot1.style.display = "none";
  massageemailNot2.style.display = "none";
  messageOk.style.display = "block";
}




// 휴대폰 번호 입력 필드와 메시지 DOM 요소 가져오기
const elInputphoneNumber = document.querySelector("#phoneNumber");
const elFailureMessageOneMobileNumber = document.querySelector(
    ".mobileNumber__message"
);
const elMobileNumber = document.querySelector(".signup__info__number");

// 숫자만 허용하는 정규 표현식
const numberOnlyRegex = /[^0-9]/g;

// 입력 이벤트 핸들러
elInputphoneNumber.addEventListener("input", function (e) {
  const currentValue = e.target.value;

  // 특수문자나 알파벳이 포함되면 제거
  if (numberOnlyRegex.test(currentValue)) {
    // 특수문자나 문자를 제거하고 값을 갱신
    e.target.value = currentValue.replace(numberOnlyRegex, "");
  }

  // 입력값이 있으면 메시지 숨기기
  if (e.target.value.trim() !== "") {
    elFailureMessageOneMobileNumber.classList.remove("active");
    elMobileNumber.style.backgroundColor = "rgb(95, 0, 128)";
    elMobileNumber.style.color = "#fff";

  } else {
    // 입력이 비어있으면 메시지 표시
    elFailureMessageOneMobileNumber.classList.add("active");
    elMobileNumber.style.backgroundColor = "rgb(255, 255, 255)";
    elMobileNumber.style.color = "rgb(95, 0, 128)";
  }
});

const $sendAuthBtn = document.getElementById('phoneNumber-btn');
const $authNumberGroup = document.getElementById('authNumberGroup');
const $authNumber = document.getElementById('authNumber');
const $verifyAuthBtn = document.getElementById('verifyAuthBtn');
const $authTimer = document.getElementById('authTimer');

// 인증 관련 변수
let isPhoneVerified = false; // 휴대폰 인증 완료 여부
let authTimeoutId = null; // 인증 타이머의 ID (타이머 정리용)
let isAuthenticating = false; // 인증 진행 중 여부 (인증번호 전송 후 ~ 완료/만료 전)

// 휴대폰 인증 관련
{
  // 인증번호 전송
  $sendAuthBtn.addEventListener('click', function () {
    if (!numberOnlyRegex.test(elInputphoneNumber.value)) {
      alert('올바른 휴대폰 번호를 입력해주세요.');
      elInputphoneNumber.focus();
      return;
    }

    // 인증 진행 중 상태로 변경
    isAuthenticating = true;
    resetAuthState();

    smsApi.sendVerificationCode(elInputphoneNumber.value, function (data) {
      console.log(data);

      if (data.success) {
        $authNumberGroup.style.display = 'block';
        $authTimer.style.display = 'block';
        startAuthTimer();
        alert('인증번호가 전송되었습니다.');
      } else {
        isAuthenticating = false;
        alert('인증번호 전송에 실패했습니다. 다시 시도해주세요.');
      }

    });

  });

  // 인증번호 확인
  $verifyAuthBtn.addEventListener('click', function () {
    if (!isAuthenticating) {
      alert('인증이 만료되었습니다. 다시 시도해주세요.');
      resetAuthState();
      return;
    }

    const authNumber = $authNumber.value;
    if (authNumber.length !== 6) {
      alert('인증번호 6자리를 입력해주세요.');
      return;
    }

    smsApi.verifyCode(authNumber, function (data) {
      if (data.success) {
        completeAuth();
        alert('인증이 완료되었습니다.');
      } else {
        alert('인증번호가 일치하지 않습니다.');
        $authNumber.value = '';
        $authNumber.focus();
      }
    });

  });

  // 인증 타이머 (3분)
  function startAuthTimer() {
    let timeLeft = 180;
    clearTimeout(authTimeoutId);

    function updateTimer() {
      const minutes = Math.floor(timeLeft / 60);
      const seconds = timeLeft % 60;
      $authTimer.textContent = `${minutes}:${seconds.toString().padStart(2, '0')}`;

      if (timeLeft === 0) {
        expireAuth();
        return;
      }

      timeLeft--;
      authTimeoutId = setTimeout(updateTimer, 1000);
    }

    updateTimer();
  }

  // 인증 완료 처리
  function completeAuth() {
    isPhoneVerified = true;
    isAuthenticating = false;
    clearTimeout(authTimeoutId);

    $authTimer.style.display = 'none';
    $authNumberGroup.style.display = 'none';
    elInputphoneNumber.readOnly = true;
    $sendAuthBtn.disabled = true;
  }

  // 인증 만료 처리
  function expireAuth() {
    isAuthenticating = false;
    clearTimeout(authTimeoutId);

    $authTimer.textContent = '인증시간이 만료되었습니다.';
    $authNumber.value = '';
    $sendAuthBtn.disabled = false;
  }

  // 인증 상태 초기화
  function resetAuthState() {
    clearTimeout(authTimeoutId);
    $authNumber.value = '';
    $authTimer.textContent = '';
  }
}

// UI 관련 유틸리티 함수
function toggleValidationUI(element, isValid) {
  if (!isValid) {
    element.classList.add('invalid');
  } else {
    element.classList.remove('invalid');
  }
}


















// 주소 api
// 주소 검색 버튼 클릭 시 실행되는 함수
function address_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        addr = data.roadAddress; // 도로명 주소
      } else {
        addr = data.jibunAddress; // 지번 주소
      }

      // 도로명 주소일 경우 참고항목을 추가
      if (data.userSelectedType === "R") {
        if (data.bname !== "") {
          extraAddr += data.bname;
        }
        if (data.buildingName !== "") {
          extraAddr +=
              extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        document.getElementById("address_extraAddress").value = extraAddr;
      } else {
        document.getElementById("address_extraAddress").value = "";
      }

      // 우편번호와 주소 입력 필드에 값 넣기
      document.getElementById("address_postcode").value = data.zonecode;
      document.getElementById("address_address").value = addr;

      // 상세주소 입력 필드로 커서 이동
      document.getElementById("address_detailAddress").focus();

      // 상세주소 필드만 활성화
      document.getElementById("address_detailAddress").disabled = false;
      document
          .getElementById("address_detailAddress")
          .classList.remove("disabled"); // 비활성화 클래스 제거

      // 주소 입력 필드는 계속 비활성화
      document.getElementById("address_address").disabled = true;
    },
  }).open();
}

// 상세주소 입력 이벤트 리스너
document
    .getElementById("address_detailAddress")
    .addEventListener("input", function () {
      // 상세주소 입력을 시작하면 메시지 숨기기
      if (this.value.trim() !== "") {
        document.querySelector(".address__message").style.display = "none";
      } else {
        // 모든 입력이 지워졌을 때 메시지 보이기
        if (this.value.trim() === "") {
          document.querySelector(".address__message").style.display = "block";
        }
      }
    });

// 상세주소가 비어있을 경우 메시지 보여주기
document
    .getElementById("address_detailAddress")
    .addEventListener("blur", function () {
      if (this.value.trim() === "") {
        document.querySelector(".address__message").style.display = "block";
      }
    });

// 성별
const $radios = document.querySelectorAll(".info__gender");
$radios.forEach(($radio) => {
  $radio.addEventListener("click", function () {
    removeActive();

    this.classList.add("active");
    const $inner = this.querySelector(".gender__btn__in");

    $inner.classList.add("active");
  });
});

function removeActive() {
  $radios.forEach(($r) => {
    $r.classList.remove("active");
    $r.querySelector(".gender__btn__in").classList.remove("active");
  });
}

// 생년월일
// 숫자만 허용하는 정규식

// 각 입력 필드 가져오기
const elInputYear = document.querySelector("#birthYear");
const elInputMonth = document.querySelector("#birthMonth");
const elInputDay = document.querySelector("#birthDay");

// 입력값 검사 함수 (범위 제한)
function limitValue(inputField, min, max) {
  let value = parseInt(inputField.value, 10);
  if (value < min) {
    inputField.value = min
        .toString()
        .padStart(inputField.placeholder.length, "0"); // 최소값으로 설정
  } else if (value > max) {
    inputField.value = max
        .toString()
        .padStart(inputField.placeholder.length, "0"); // 최대값으로 설정
  }
}

// 각 입력 필드에 이벤트 리스너 추가
[elInputYear, elInputMonth, elInputDay].forEach((inputField) => {
  inputField.addEventListener("input", function (e) {
    const currentValue = e.target.value;

    // 숫자만 남기고 특수문자 제거
    e.target.value = currentValue.replace(numberOnlyRegex, "");

    // 길이 제한
    if (e.target === elInputYear && e.target.value.length > 4) {
      e.target.value = e.target.value.slice(0, 4); // 4자리로 제한
    }
    if (
        (e.target === elInputMonth || e.target === elInputDay) &&
        e.target.value.length > 2
    ) {
      e.target.value = e.target.value.slice(0, 2); // 2자리로 제한
    }

    // 입력값 범위 제한
    if (e.target === elInputMonth) {
      limitValue(e.target, 1, 12); // 01부터 12까지
    }
    if (e.target === elInputDay) {
      limitValue(e.target, 1, 31); // 01부터 31까지
    }
  });
});
