import * as smsApi from './modules/smsApi.js';
import * as userApi from './modules/userApi.js';


// 입력 필드와 메시지 DOM 요소 가져오기
// 아이디
const elInputId = document.querySelector("#userId");
const elFailureMessageOneId = document.querySelector(".id__notmessage1");
const elFailureMessageTwoId = document.querySelector(".id__notmessage2");
const elSuccessMessageId = document.querySelector(".id__okmessage");

// 이미 존재하는 아이디 목록 (예시)
const existingIds = ["test123", "user456", "admin789"];



// 유효성 검사 함수
function validateUsername(userid) {
  const regexid = /^[A-Za-z][A-Za-z0-9]{5,15}$/;
  // 영문 시작, 6~16자, 숫자 포함 가능
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
  // 최소 10자 이상
  const regexpw = /^[a-zA-Z0-9!@#$%^&*()?_~]{10,}$/;
  return regexpw.test(password);
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


// 변수 정의
const domainEl = document.querySelector(".info__box__email__btn__select");
const domainListEl = document.querySelector(".info__dropdown__email");
const emailTxt = document.getElementById("emailTxt");
const emailId = document.getElementById("emailId");
const emailField = document.getElementById("email");
const massageemailNot1 = document.querySelector(".email__notmessage1");
const massageemailNot2 = document.querySelector(".email__notmessage2");
const messageOk = document.querySelector(".email__okmessage");

let isActiveDomainList = false;

// 드롭다운 토글
domainEl.addEventListener("click", function () {
  isActiveDomainList = !isActiveDomainList;
  domainListEl.classList.toggle("active1", isActiveDomainList);
});

// 도메인 선택 이벤트 (이벤트 위임 사용)
domainListEl.addEventListener("click", function (e) {
  if (e.target.classList.contains("dropdown__item")) {
    const domain = e.target.textContent.trim();

    if (domain === "직접입력") {
      enableEditing();
    } else {
      btnClick(domain);
    }
  }
});

// 도메인 버튼 클릭 시
function btnClick(domain) {
  emailTxt.value = domain;
  emailTxt.disabled = true;
  domainListEl.classList.remove("active1");
  validateEmail();
}

// 직접 입력 모드 활성화
function enableEditing() {
  emailTxt.value = "";
  emailTxt.placeholder = "입력하기";
  emailTxt.disabled = false;
  domainListEl.classList.remove("active1");
}

// 유효성 검사 함수
function isValidEmail(email) {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailRegex.test(email.trim());
}

// 이메일 입력 및 유효성 검사
function validateEmail() {
  const domain = emailTxt.value.trim();
  const localPart = emailId.value.trim();
  const emailValue = localPart + (domain ? "@" + domain : "");

  emailField.value = emailValue;

  // 이메일 ID가 비어있는 경우
  if (!localPart) {
    massageemailNot1.style.display = "block";
    massageemailNot2.style.display = "none";
    messageOk.style.display = "none";
    return;
  }

  // 도메인이 비어있는 경우
  if (!domain) {
    massageemailNot1.style.display = "none";
    massageemailNot2.style.display = "block";
    messageOk.style.display = "none";
    return;
  }

  // 이메일 형식이 유효하지 않은 경우
  if (!isValidEmail(emailValue)) {
    massageemailNot1.style.display = "none";
    massageemailNot2.style.display = "block";
    messageOk.style.display = "none";
    return;
  }

  // 유효한 이메일인 경우
  massageemailNot1.style.display = "none";
  massageemailNot2.style.display = "none";
  messageOk.style.display = "block";
}

// 입력 이벤트 리스너 추가
emailId.addEventListener("input", validateEmail);
emailTxt.addEventListener("input", validateEmail);






const elInputphoneNumber = document.querySelector("#phoneNumber");
const elFailureMessageOneMobileNumber = document.querySelector(
    ".mobileNumber__message"
);
const elPhoneNumberBtn = document.querySelector("#phoneNumber-btn");
const $authNumberGroup = document.getElementById("authNumberGroup");
const $authNumber = document.getElementById("authNumber");
const $verifyAuthBtn = document.getElementById("verifyAuthBtn");
const $authTimer = document.getElementById("authTimer");

// 정규 표현식: 숫자만 허용
const numberOnlyRegex = /^[0-9]+$/;

// 인증 관련 상태 변수
let isPhoneVerified = false;
let authTimeoutId = null;
let isAuthenticating = false;

// 휴대폰 번호 입력 이벤트
elInputphoneNumber.addEventListener("input", function (e) {
  const currentValue = e.target.value;

  // 숫자 외 문자 제거
  e.target.value = currentValue.replace(/[^0-9]/g, "");

  // 입력값이 비어있으면 메시지 표시
  if (e.target.value.trim() === "") {
    elFailureMessageOneMobileNumber.classList.add("active");
    elPhoneNumberBtn.disabled = true;
    elPhoneNumberBtn.style.backgroundColor = "rgb(255, 255, 255)";
    elPhoneNumberBtn.style.color = "rgb(95, 0, 128)";
  } else {
    elFailureMessageOneMobileNumber.classList.remove("active");
    elPhoneNumberBtn.disabled = false;
    elPhoneNumberBtn.style.backgroundColor = "rgb(95, 0, 128)";
    elPhoneNumberBtn.style.color = "#fff";
  }
});

// 인증번호 전송
elPhoneNumberBtn.addEventListener("click", function () {
  if (!elInputphoneNumber.value.match(numberOnlyRegex)) {
    alert("올바른 휴대폰 번호를 입력해주세요.");
    return;
  }

  isAuthenticating = true;
  resetAuthState();

  // 예시: SMS 전송 API 호출
  smsApi.sendVerificationCode(elInputphoneNumber.value, function (data) {
    if (data.success) {
      $authNumberGroup.style.display = "block";
      $authTimer.style.display = "block";
      startAuthTimer();
      alert("인증번호가 전송되었습니다.");
    } else {
      isAuthenticating = false;
      alert("인증번호 전송에 실패했습니다. 다시 시도해주세요.");
    }
  });
});

// 인증번호 확인
$verifyAuthBtn.addEventListener("click", function () {
  if (!isAuthenticating) {
    alert("인증이 만료되었습니다. 다시 시도해주세요.");
    resetAuthState();
    return;
  }

  const authNumber = $authNumber.value.trim();
  if (authNumber.length !== 6) {
    alert("인증번호 6자리를 입력해주세요.");
    return;
  }

  // 예시: 인증번호 확인 API 호출
  smsApi.verifyCode(authNumber, function (data) {
    if (data.success) {
      completeAuth();
      alert("인증이 완료되었습니다.");
    } else {
      alert("인증번호가 일치하지 않습니다.");
      $authNumber.value = "";
      $authNumber.focus();
    }
  });
});

// 인증 타이머 시작
function startAuthTimer() {
  let timeLeft = 180;
  clearTimeout(authTimeoutId);

  function updateTimer() {
    const minutes = Math.floor(timeLeft / 60);
    const seconds = timeLeft % 60;
    $authTimer.textContent = `${minutes}:${seconds.toString().padStart(2, "0")}`;

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

  $authTimer.style.display = "none";
  $authNumberGroup.style.display = "none";
  elInputphoneNumber.readOnly = true;
  elPhoneNumberBtn.disabled = true;
}

// 인증 만료 처리
function expireAuth() {
  isAuthenticating = false;
  clearTimeout(authTimeoutId);

  // $authTimer.textContent = "인증시간이 만료되었습니다.";
  alert("인증 시간이 만료되었습니다.");
  $authNumber.value = "";
  elPhoneNumberBtn.disabled = false;
}

// 인증 상태 초기화
function resetAuthState() {
  clearTimeout(authTimeoutId);
  $authNumber.value = "";
  $authTimer.textContent = "";
}

$authNumber.addEventListener("input", function (e) {
  const currentValue = e.target.value;

  // 숫자 외 문자 제거
  e.target.value = currentValue.replace(/[^0-9]/g, "");

  // 입력값이 비어있으면 메시지 표시
  if (e.target.value.trim() === "") {
    $verifyAuthBtn.disabled = true;
    $verifyAuthBtn.style.backgroundColor = "rgb(255, 255, 255)";
    $verifyAuthBtn.style.color = "rgb(95, 0, 128)";
  } else {
    $verifyAuthBtn.disabled = false;
    $verifyAuthBtn.style.backgroundColor = "rgb(95, 0, 128)";
    $verifyAuthBtn.style.color = "#fff";
  }
});



// 주소 api
// 주소 검색 버튼 클릭 시 실행되는 함수

function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ''; // 주소 변수
      var extraAddr = ''; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else { // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if(data.userSelectedType === 'R'){
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraAddr !== ''){
          extraAddr = ' (' + extraAddr + ')';
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("addressExtra").value = extraAddr;

      } else {
        document.getElementById("addressExtra").value = '';
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('zipCode').value = data.zonecode;
      document.getElementById("address").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("addressDetail").focus();
    }
  }).open();
}



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

// 숫자만 허용하는 keydown 이벤트
function allowNumbersOnly(event) {
  const key = event.key;

  // 숫자 키(0-9)와 백스페이스만 허용
  if (!/^[0-9]$/.test(key) && key !== "Backspace") {
    event.preventDefault(); // 다른 키 입력을 막음
  }
}

// 각 입력 필드에 keydown 이벤트 리스너 추가
[elInputYear, elInputMonth, elInputDay].forEach((inputField) => {
  inputField.addEventListener("keydown", allowNumbersOnly);

  inputField.addEventListener("input", function (e) {
    const currentValue = e.target.value;

    // 숫자 외 문자 제거
    e.target.value = currentValue.replace(/[^0-9]/g, "");

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

  // 포커스 해제 시 0으로 채우기 (예: 1 -> 01)
  inputField.addEventListener("blur", function (e) {
    if (e.target.value.trim() !== "") {
      const minLength = e.target === elInputYear ? 4 : 2;
      e.target.value = e.target.value.padStart(minLength, "0");
    }
  });
});
