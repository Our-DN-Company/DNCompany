//비밀번호 조건 확인
function validatePassword() {
  const password1 = document.getElementById("password1").value;
  const passwordMessage = document.getElementById("passwordMessage");

  // 비밀번호 조건: 8자 이상, 대문자 포함, 특수문자 포함
  const minLength = password1.length >= 8;
  const hasUpperCase = /[A-Z]/.test(password1); // 대문자 포함 여부
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password1); // 특수문자 포함 여부

  if (password1) {
    if (minLength && hasUpperCase && hasSpecialChar) {
      passwordMessage.style.display = "inline";
      passwordMessage.textContent = "비밀번호 조건이 충족되었습니다.";
      passwordMessage.className = "success";
    } else {
      passwordMessage.style.display = "inline";
      passwordMessage.textContent =
        "비밀번호는 8자 이상, 대문자, 특수문자를 포함해야 합니다.";
      passwordMessage.className = "error";
    }
  } else {
    passwordMessage.style.display = "none"; // 비밀번호가 비어 있을 때 메시지 숨김
  }
}

//비밀번호 일치확인
function checkPasswords() {
  const password1 = document.getElementById("password1").value;
  const password2 = document.getElementById("password2").value;
  const message = document.getElementById("message");

  if (password1 && password2) {
    if (password1 === password2) {
      message.style.display = "inline";
      message.textContent = "비밀번호가 일치합니다!";
      message.className = "success";
    } else {
      message.style.display = "inline";
      message.textContent = "비밀번호가 일치하지 않습니다!";
      message.className = "error";
    }
  } else {
    message.style.display = "none"; // 입력 필드가 비어 있으면 메시지 숨김
  }
}
//이메일 확인
function validateEmail() {
  const emailInput = document.getElementById("email").value;
  const emailMessage = document.getElementById("emailMessage");

  // 간단한 이메일 유효성 검사 정규식
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (emailInput) {
    if (emailRegex.test(emailInput)) {
      emailMessage.style.display = "inline";
      emailMessage.textContent = "유효한 이메일 형식입니다.";
      emailMessage.className = "success";
    } else {
      emailMessage.style.display = "inline";
      emailMessage.textContent = "유효하지 않은 이메일 형식입니다.";
      emailMessage.className = "error";
    }
  } else {
    emailMessage.style.display = "none"; // 입력 필드가 비어 있으면 메시지 숨김
  }
}
